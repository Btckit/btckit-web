package com.ionia.btckit.scheduler.service;

import java.io.IOException;

import com.ionia.btckit.service.PairServiceException;

public interface SchedulerService {

	public void checkAllPair() throws IOException;

	public void generatePair() throws PairServiceException;

}
