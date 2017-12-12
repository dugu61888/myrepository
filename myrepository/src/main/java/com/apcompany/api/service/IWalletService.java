package com.apcompany.api.service;

public interface IWalletService {
	
	int getStudentMoney(int studentId);
	
	void transMoney(int studentId,int teacherId, int money );

}
