package com.ionia.btckit.scheduler.task;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ionia.btckit.scheduler.service.SchedulerService;

@Component
public class PairBalanceTask {

	private static final Logger log = LoggerFactory.getLogger(PairBalanceTask.class);

	private SchedulerService schedulerService;

	@Scheduled(cron = "${com.ionia.btckit.scheduler.task.balanceTask}")
	public void run() {
		try {
			schedulerService.checkAllPair();
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Autowired
	@Qualifier("schedulerService")
	public void setScheduler(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}
}
