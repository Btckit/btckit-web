package com.ionia.btckit.service;

import java.io.IOException;
import java.math.BigDecimal;

import com.ionia.btckit.model.Pair;

public interface PairService {

	public Pair createPair() throws PairServiceException;

	public Pair savePair(Pair pair);

	public Pair getPairWithBalance(String publicAddress) throws PairServiceException;

	public BigDecimal getBalance(String publicAddress) throws IOException;

}
