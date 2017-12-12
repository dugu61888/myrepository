package com.apcompany.learn.service;

import java.util.List;

import com.apcompany.learn.pojo.THits;

public interface HitsService {

	public THits getTHits(int question_id, int step);

	public int insertTHits(THits tHits);

	public int updateTHits(THits tHits);

	public int deleteTHits(int id);

	public List<THits> getTHitsByQuestionId(int question_id);
}
