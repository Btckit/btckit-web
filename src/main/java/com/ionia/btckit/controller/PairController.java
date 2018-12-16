package com.ionia.btckit.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ionia.btckit.model.Pair;
import com.ionia.btckit.service.PairService;
import com.ionia.btckit.service.PairServiceException;

@Controller
@RequestMapping("/api")
public class PairController {

	private static final Logger log = LoggerFactory.getLogger(PairController.class);

	private PairService pairService;

	@RequestMapping(value = "/createPair", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody Pair createPair() {
		try {
			return pairService.createPair();
		} catch (PairServiceException ex) {
			log.error(ex.getMessage(), ex);
		}
		return null;
	}

	@RequestMapping(value = "/createPairList", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody List<Pair> createPairList(@RequestParam("size") int size) {
		List<Pair> list = new ArrayList<>();
		try {
			for (int i = 0; i < size; i++) {
				list.add(pairService.createPair());
			}
		} catch (PairServiceException ex) {
			log.error(ex.getMessage(), ex);
		}
		return list;
	}

	@RequestMapping(value = "/getBalance", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody Pair getBalance(@RequestParam("publicAddress") String publicAddress) {
		try {
			return pairService.getPairWithBalance(publicAddress);
		} catch (PairServiceException ex) {
			log.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Autowired
	@Qualifier("pairService")
	public void setPairService(PairService pairService) {
		this.pairService = pairService;
	}

}
