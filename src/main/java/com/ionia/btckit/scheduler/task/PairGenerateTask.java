package com.ionia.btckit.scheduler.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ionia.btckit.scheduler.service.SchedulerService;
import com.ionia.btckit.service.PairServiceException;

@Component
public class PairGenerateTask {

	private static final Logger log = LoggerFactory.getLogger(PairGenerateTask.class);

	private SchedulerService schedulerService;

	@Scheduled(fixedRate = 100)
	public void run() {
		try {
			schedulerService.generatePair();
		} catch (PairServiceException ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Autowired
	@Qualifier("schedulerService")
	public void setScheduler(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

}
