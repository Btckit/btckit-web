package com.ionia.btckit.service;

import java.io.IOException;
import java.math.BigDecimal;

import com.ionia.btckit.model.Pair;

public interface PairService {

	public Pair createPair() throws PairServiceException;

	public Pair savePair(Pair pair);

	public Pair getBalance(String publicAddress) throws PairServiceException;

	public BigDecimal getBalanceValue(String publicAddress) throws IOException;

}
