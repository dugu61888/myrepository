package com.apcompany.user.service;

import java.util.List;
import java.util.Map;

import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TLabels;
import com.apcompany.user.pojo.TLabelsQuestionRel;
import com.apcompany.user.pojo.TLabelsRel;
import com.apcompany.user.pojo.TQuestions;

public interface DataService {

	public int addData(TQuestions tQuestions, List<TChoises> tChoises,
			TAnswers tAnswers, List<TLabelsQuestionRel> tLabelsQuestionRel);

	public boolean delData(int id);

	public boolean updateData(TQuestions tQuestions, List<TChoises> tChoises,
			TAnswers tAnswers, List<TLabelsQuestionRel> tLabelsQuestionRel);

	public int getDataCount();

	public TQuestions getData(int questionid,Integer keypointId);

	public List<TQuestions> getDataList(int questionid, Integer totalcount,
			Integer curPage, Integer pageSize,Integer keypointId);

	public int updateQuestion(TQuestions tQuestions);

	public int insertOrUpdateChoise(TChoises tChoises);

	public int updateAnswers(TAnswers tAnswers);

	public boolean addLabel(TLabels tLabels);

	public int updateLabel(TLabels tLabels);

	public int delLabel(int id);

	public int updateQuestionLabel(TLabelsQuestionRel tLabelsQuestionRel);

	public int delQuestionLabel(int id);

	public List<TLabels> selectByName(Integer totalcount, Integer pageStart,
			Integer pageSize, String names);

	public int countSelectByName(String names);

	public List<TLabels> selectAllLabels(Integer totalcount, Integer pageStart,
			Integer pageSize);

	public int countLabels();

	public int insertOrUpateTLabelsRel(TLabelsRel tLabelsRel);

	public int delTLabelsRel(int id);

	public TLabelsRel selectById(int id);

	public List<TLabelsRel> selectByParentId(int parentId);

	public int countLabelsRel();

	public List<TLabelsRel> selectAllLabelsRel();

	public int updateLabelRel(TLabelsRel tLabelsRel);

	public int delChoise(int id);
	
	public TQuestions getExample(int keypointId,int type);
	
	public Map<String, Object> getExampleNew(int keypointId,int type);
	
	public boolean checkAnswer(int questionId,String input);

}
