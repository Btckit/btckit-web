package com.ionia.btckit.scheduler.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ionia.btckit.model.Pair;
import com.ionia.btckit.repository.PairRepository;
import com.ionia.btckit.service.PairService;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	private PairService pairService;
	private PairRepository pairRepository;

	@Override
	public void checkAllPair() throws IOException {
		List<Pair> pairList = pairRepository.findAll();
		for (Pair pair : pairList) {
			pair.setBalance(pairService.getBalanceValue(pair.getPublicAddress()));
		}
		pairRepository.saveAll(pairList);
	}

	@Autowired
	@Qualifier("pairService")
	public void setPairService(PairService pairService) {
		this.pairService = pairService;
	}

	@Autowired
	@Qualifier("pairRepository")
	public void setPairRepository(PairRepository pairRepository) {
		this.pairRepository = pairRepository;
	}

}
