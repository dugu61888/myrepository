package com.apcompany.learn.service;

import java.util.List;

import com.apcompany.learn.pojo.TKeyWords;
import com.apcompany.learn.pojo.TTranslations;

public interface TransLateService {

	public TKeyWords getKeyWords(int id);

	public int insertKeyWords(TKeyWords tKeyWords);

	public int updateKeyWords(TKeyWords tKeyWords);

	public int deleteKeyWords(int id);

	public TTranslations getTranslations(int id);

	public TTranslations getTranslationsByQuestionId(int questionId);

	public int insertTranslations(TTranslations tTranslations);

	public int updateTranslations(TTranslations tTranslations);

	public int updateTranslationsByQuestionId(TTranslations tTranslations);

	public int deleteTranslations(int id);

	public List<TKeyWords> selectAllKey();

	public String getKeyWordsByFname(String fName);
}
