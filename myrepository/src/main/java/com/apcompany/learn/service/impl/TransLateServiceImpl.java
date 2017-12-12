package com.apcompany.learn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.learn.dao.TKeyWordsDao;
import com.apcompany.learn.dao.TTranslationsDao;
import com.apcompany.learn.pojo.TKeyWords;
import com.apcompany.learn.pojo.TTranslations;
import com.apcompany.learn.service.TransLateService;

@Service
public class TransLateServiceImpl implements TransLateService {

	@Autowired
	private TKeyWordsDao tKeyWordsDao;
	@Autowired
	private TTranslationsDao tTranslationsDao;

	@Override
	public TKeyWords getKeyWords(int id) {
		return tKeyWordsDao.getKeyWords(id);
	}

	@Override
	public int insertKeyWords(TKeyWords tKeyWords) {
		return tKeyWordsDao.insert(tKeyWords);
	}

	@Override
	public int updateKeyWords(TKeyWords tKeyWords) {
		return tKeyWordsDao.update(tKeyWords);
	}

	@Override
	public int deleteKeyWords(int id) {
		return tKeyWordsDao.delete(id);
	}

	@Override
	public TTranslations getTranslations(int id) {
		return tTranslationsDao.getTranslations(id);
	}

	@Override
	public TTranslations getTranslationsByQuestionId(int questionId) {
		return tTranslationsDao.getTranslationsByQuestionId(questionId);
	}

	@Override
	public int insertTranslations(TTranslations tTranslations) {
		return tTranslationsDao.insert(tTranslations);
	}

	@Override
	public int updateTranslations(TTranslations tTranslations) {
		return tTranslationsDao.update(tTranslations);
	}

	@Override
	public int updateTranslationsByQuestionId(TTranslations tTranslations) {
		return tTranslationsDao.updateByQuestionId(tTranslations);
	}

	@Override
	public int deleteTranslations(int id) {
		return tTranslationsDao.delete(id);
	}

	@Override
	public List<TKeyWords> selectAllKey() {
		return tKeyWordsDao.searchAll();
	}

	@Override
	public String getKeyWordsByFname(String fName) {
		// TODO Auto-generated method stub
		return tKeyWordsDao.getKeyWordsByFName(fName);
	}

}
