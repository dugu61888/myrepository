package com.apcompany.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.apcompany.api.service.ITaskService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.DateUtil;

@Service
public class TaskServiceImp implements ITaskService{

	@Autowired IUserOnlineInfoService userOnlineInfoService;
	
	//@Scheduled(cron="0 0/2 * * * *")
	@Override
	public void refreshUserOnlineStatus() {
		try {
			System.out.println("task to offline user");
			//定义最后访问时间，--当前世界-30分钟
			String lastAccessTime = DateUtil.formateDateToYMDHMS(DateUtil.addMinutes(new Date(), -30));
			//将所有最后访问时间<lastAccessDate 的状态update为offline。
			userOnlineInfoService.offlineAuto(lastAccessTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
