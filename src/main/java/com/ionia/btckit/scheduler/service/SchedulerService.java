package com.ionia.btckit.scheduler.service;

import com.ionia.btckit.service.PairServiceException;

public interface SchedulerService {

	public void generatePair() throws PairServiceException;

}
