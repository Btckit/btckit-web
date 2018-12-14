package com.ionia.btckit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.MainNetParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ionia.btckit.model.Pair;
import com.ionia.btckit.repository.PairRepository;

@Service("pairService")
public class PairServiceImpl implements PairService {

	private PairRepository pairRepository;

	@Value("${com.ionia.btckit.service.url.balance}")
	private String balanceUrl;

	@Override
	public Pair createPair() throws PairServiceException {
		ECKey key = new ECKey();
		Address address = new Address(MainNetParams.get(), key.getPubKeyHash());

		Pair pair = new Pair();
		pair.setPublicAddress(address.toBase58());
		pair.setPrivateKey(key.getPrivateKeyAsWiF(MainNetParams.get()));

		try {
			pair.setBalance(getBalanceValue(pair.getPublicAddress()));
		} catch (IOException e) {
			pair.setBalance(null);
		}

		return savePair(pair);
	}

	@Override
	public Pair savePair(Pair pair) {
		return pairRepository.save(pair);
	}

	@Override
	public Pair getBalance(String publicAddress) throws PairServiceException {
		Optional<Pair> pairOpt = pairRepository.findById(publicAddress);
		Pair pair = null;
		if (pairOpt.isPresent()) {
			pair = pairOpt.get();
		} else {
			pair = new Pair();
			pair.setPublicAddress(publicAddress);
		}

		try {
			pair.setBalance(getBalanceValue(publicAddress));
		} catch (IOException ex) {
			throw new PairServiceException(ex);
		}

		return savePair(pair);
	}

	@Override
	public BigDecimal getBalanceValue(String publicAddress) throws IOException {
		String url = getBalanceCheckUrl(publicAddress);
		String balance;
		balance = readUrl(url);
		return new BigDecimal(balance).divide(new BigDecimal("100000000"));
	}

	@Autowired
	@Qualifier("pairRepository")
	public void setPairRepository(PairRepository pairRepository) {
		this.pairRepository = pairRepository;
	}

	private String readUrl(String urlStr) throws IOException {
		String result = null;
		URL url = new URL(urlStr);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			result = inputLine;
		in.close();
		return result == null ? "0" : result;
	}

	private String getBalanceCheckUrl(String publicAddress) {
		String url = balanceUrl.concat(publicAddress);
		return url;
	}

}
