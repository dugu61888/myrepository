package com.apcompany.learn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.learn.dao.SteppingDao;
import com.apcompany.learn.pojo.Stepping;
import com.apcompany.learn.service.SteppingService;
@Service
public class SteppingServiceImpl implements SteppingService {

	@Autowired
	private SteppingDao steppingDao;

	@Override
	public boolean valid(String input, int questiongId, int step) {
		if (input == null || "".equals(input)) {
			return false;
		}
		Stepping stepping = steppingDao.getStep(questiongId, step);
		if (stepping != null) {
			return stepping.getProceduranswer().equals(input);
		}
		return false;	
	}

	@Override
	public Stepping getNextStep(int questiongId, int step) {
		Stepping stepping = steppingDao.getStep(questiongId, step);
		stepping.setProceduranswer("");
		return stepping;
	}

	@Override
	public List<Stepping> getAllStep(int questiongId) {
		return steppingDao.getAllStep(questiongId);
	}

}
