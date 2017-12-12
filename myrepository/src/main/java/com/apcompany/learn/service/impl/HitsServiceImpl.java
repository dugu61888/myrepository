package com.apcompany.learn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.learn.dao.THitsDao;
import com.apcompany.learn.pojo.THits;
import com.apcompany.learn.service.HitsService;

@Service
public class HitsServiceImpl implements HitsService {

	@Autowired
	private THitsDao tHitsDao;

	@Override
	public THits getTHits(int question_id, int step) {
		// TODO Auto-generated method stub
		return tHitsDao.getTHits(question_id, step);
	}

	@Override
	public int insertTHits(THits tHits) {
		return tHitsDao.insert(tHits);
	}

	@Override
	public int updateTHits(THits tHits) {
		return tHitsDao.update(tHits);
	}

	@Override
	public int deleteTHits(int id) {
		return tHitsDao.delete(id);
	}

	public List<THits> getTHitsByQuestionId(int question_id) {
		return tHitsDao.getTHitsByQuestionid(question_id);
	}

}
