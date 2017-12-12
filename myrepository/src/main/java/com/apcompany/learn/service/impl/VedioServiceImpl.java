package com.apcompany.learn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.learn.dao.VedioDao;
import com.apcompany.learn.pojo.Vedio;
import com.apcompany.learn.service.VedioService;

@Service
public class VedioServiceImpl implements VedioService {

	@Autowired
	private VedioDao vedioDao;

	@Override
	public List<Vedio> getAll(int keypointId) {
		return vedioDao.getAll(keypointId);
	}

}
