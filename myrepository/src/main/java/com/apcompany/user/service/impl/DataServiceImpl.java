package com.apcompany.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.user.dao.TAnswersDao;
import com.apcompany.user.dao.TChoisesDao;
import com.apcompany.user.dao.TLabelQuestionRelDao;
import com.apcompany.user.dao.TLabelsDao;
import com.apcompany.user.dao.TLabelsRelDao;
import com.apcompany.user.dao.TQuestionsDao;
import com.apcompany.user.pojo.TAnswers;
import com.apcompany.user.pojo.TChoises;
import com.apcompany.user.pojo.TLabels;
import com.apcompany.user.pojo.TLabelsQuestionRel;
import com.apcompany.user.pojo.TLabelsRel;
import com.apcompany.user.pojo.TQuestions;
import com.apcompany.user.service.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService {

	@Autowired
	private TQuestionsDao tQuestionsDao;

	@Autowired
	private TAnswersDao tAnswerdao;

	@Autowired
	private TChoisesDao tChoisedao;
	@Autowired
	private TLabelsDao tLabelsDao;
	@Autowired
	private TLabelQuestionRelDao tLabelQuestionRelDao;
	@Autowired
	private TLabelsRelDao tLabelsRelDao;

	@Override
	public int addData(TQuestions tQuestions, List<TChoises> tChoises,
			TAnswers tAnswers, List<TLabelsQuestionRel> tLabelsQuestionRel) {
		int out = 0;
		out = tQuestionsDao.insert(tQuestions);
		if (out != 1) {
			return 0;
		}

		for (TChoises choise : tChoises) {
			choise.setQuestionid(tQuestions.getId());
			out = tChoisedao.insert(choise);
			if (out != 1) {
				return 0;
			}
		}
		tAnswers.setQuestionid(tQuestions.getId());
		out = tAnswerdao.insert(tAnswers);
		if (out != 1) {
			return 0;
		}
		for (TLabelsQuestionRel tQuestionRel : tLabelsQuestionRel) {
			tQuestionRel.setQuestionid(tQuestions.getId());
			out = tLabelQuestionRelDao.insert(tQuestionRel);
			if (out != 1) {
				return 0;
			}
		}
		return tQuestions.getId();
	}

	@Override
	public boolean delData(int id) {
		int out = 0;
		out = tQuestionsDao.delete(id);
		if (out != 1) {
			return false;
		}
		out = tChoisedao.deleteByQuestionId(id);
		if (out != 1) {
			return false;
		}
		out = tAnswerdao.deleteByQuestionId(id);
		if (out != 1) {
			return false;
		}
		return true;

	}

	@Override
	public boolean updateData(TQuestions tQuestions, List<TChoises> tChoises,
			TAnswers tAnswers, List<TLabelsQuestionRel> tLabelsQuestionRel) {
		int out = 0;
		out = tQuestionsDao.update(tQuestions);
		if (out != 1) {
			return false;
		}
		for (TChoises choise : tChoises) {

			out = tChoisedao.update(choise);
			if (out != 1) {
				return false;
			}
		}

		out = tAnswerdao.update(tAnswers);
		if (out != 1) {
			return false;
		}
		for (TLabelsQuestionRel tQuestionRel : tLabelsQuestionRel) {
			tQuestionRel.setQuestionid(tQuestions.getId());
			out = tLabelQuestionRelDao.update(tQuestionRel);
			if (out != 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getDataCount() {
		int out = tQuestionsDao.getQuestionsNum();
		return out;
	}

	@Override
	public TQuestions getData(int questionid,Integer keypointId) {
		List<TQuestions> outlist = tQuestionsDao.searchAll(questionid, 0, 20,keypointId);
		if (outlist != null && outlist.size() > 0) {
			return outlist.get(0);
		}
		return null;
	}

	@Override
	public List<TQuestions> getDataList(int questionid, Integer totalcount,
			Integer curPage, Integer pageSize,Integer keypointId) {
		curPage = curPage < 1 ? 1 : curPage;
		int pageStart = (curPage - 1) * pageSize;
		if (pageStart <= totalcount) {
			return tQuestionsDao.searchAll(questionid, pageStart, pageSize,keypointId);
		} else {
			return null;
		}

	}

	@Override
	public int updateQuestion(TQuestions tQuestions) {
		return tQuestionsDao.update(tQuestions);
	}

	@Override
	public int insertOrUpdateChoise(TChoises tChoises) {
		if (tChoises.getId() == 0) {
			return tChoisedao.insert(tChoises);
		} else {
			return tChoisedao.update(tChoises);
		}

	}

	@Override
	public int updateAnswers(TAnswers tAnswers) {
		return tAnswerdao.update(tAnswers);
	}

	@Override
	public boolean addLabel(TLabels tLabels) {
		int k = tLabelsDao.insert(tLabels);
		return k > 0;
	}

	@Override
	public int updateLabel(TLabels tLabels) {
		return tLabelsDao.update(tLabels);
	}

	@Override
	public int delLabel(int id) {
		return tLabelsDao.delete(id);
	}

	@Override
	public int updateQuestionLabel(TLabelsQuestionRel tLabelsQuestionRel) {
		return tLabelQuestionRelDao.update(tLabelsQuestionRel);
	}

	@Override
	public int delQuestionLabel(int id) {
		return tLabelQuestionRelDao.delete(id);
	}

	@Override
	public List<TLabels> selectByName(Integer totalcount, Integer curPage,
			Integer pageSize, String names) {
		curPage = curPage < 1 ? 1 : curPage;
		int pageStart = (curPage - 1) * pageSize;
		if (pageStart <= totalcount) {
			return tLabelsDao.selectByName(pageStart, pageSize, names);
		} else {
			return null;
		}

	}

	@Override
	public int countSelectByName(String names) {
		return tLabelsDao.countByName(names);
	}

	@Override
	public List<TLabels> selectAllLabels(Integer totalcount, Integer curPage,
			Integer pageSize) {
		curPage = curPage < 1 ? 1 : curPage;
		int pageStart = (curPage - 1) * pageSize;
		if (pageStart <= totalcount) {
			return tLabelsDao.selectAll(pageStart, pageSize);
		} else {
			return null;
		}
	}

	@Override
	public int countLabels() {
		return tLabelsDao.countAll();
	}

	@Override
	public int insertOrUpateTLabelsRel(TLabelsRel tLabelsRel) {
		int outcome = 0;
		if (tLabelsRel == null) {
			return outcome;
		}
		if (tLabelsRel.getId() > 0) {
			tLabelsRelDao.update(tLabelsRel);
		} else {
			tLabelsRelDao.insert(tLabelsRel);
		}
		outcome = tLabelsRel.getId();
		return outcome;
	}

	@Override
	public int delTLabelsRel(int id) {
		// TODO Auto-generated method stub
		return tLabelsRelDao.delete(id);
	}

	@Override
	public TLabelsRel selectById(int id) {
		// TODO Auto-generated method stub
		return tLabelsRelDao.select(id);
	}

	@Override
	public List<TLabelsRel> selectByParentId(int parentId) {
		// TODO Auto-generated method stub
		return tLabelsRelDao.selectByParent(parentId);
	}

	@Override
	public int countLabelsRel() {
		return tLabelsRelDao.countAll();
	}

	@Override
	public List<TLabelsRel> selectAllLabelsRel() {
		// TODO Auto-generated method stub
		// return TLabelsRelDao.selectAll(pageStart, pageSize);
		return tLabelsRelDao.selectAll();

	}

	@Override
	public int updateLabelRel(TLabelsRel tLabelsRel) {
		return tLabelsRelDao.update(tLabelsRel);

	}

	@Override
	public int delChoise(int id) {
		int out = 0;
		out = tChoisedao.delete(id);
		return out;
	}

	@Override
	public TQuestions getExample(int keypointId, int type) {
		return tQuestionsDao.getExample(keypointId, type);
	}

	@Override
	public Map<String, Object> getExampleNew(int keypointId, int type) {
		return tQuestionsDao.getExampleNew(keypointId, type);
	}

	@Override
	public boolean checkAnswer(int questionId, String input) {
		TAnswers tAnswers=tAnswerdao.getAnswerByQuestionId(questionId);
		if(tAnswers!=null){
			return tAnswers.getAnswer().equals(input);
		}
		return false;
	}

}
