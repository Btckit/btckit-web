package com.ionia.btckit.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ionia.btckit.service.PairService;
import com.ionia.btckit.service.PairServiceException;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	private PairService pairService;
	
	@Override
	public void generatePair() throws PairServiceException {
		pairService.createPair();
	}

	@Autowired
	@Qualifier("pairService")
	public void setPairService(PairService pairService) {
		this.pairService = pairService;
	}

}
