package com.apcompany.learn.service;

import java.util.List;

import com.apcompany.learn.pojo.Stepping;

public interface SteppingService {
	
	public boolean valid(String input,int questiongId,int step);
	
	public Stepping getNextStep(int questiongId,int step);
	
	public List<Stepping> getAllStep(int questiongId);
	 
}
