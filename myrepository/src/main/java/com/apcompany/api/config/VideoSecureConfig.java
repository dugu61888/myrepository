package com.apcompany.api.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.apcompany.api.constrant.VideoAccountStatusEnum;
import com.apcompany.api.pojo.VideoAccount;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Configuration
public class VideoSecureConfig {
	
	private List<VideoAccount> configList = Lists.newArrayList();
	private Map<Integer, VideoAccount> usedMaps =Maps.newHashMap();
	private Set<VideoAccount> frees =Sets.newHashSet();

	@PostConstruct
	public void init(){
		frees = Sets.newHashSet(configList);
	}
	
	public synchronized VideoAccount getFreeOne(int inviteId){
		for(VideoAccount v:configList){
			if(v.getStatus()==VideoAccountStatusEnum.FREE){
				v.setInvitationId(inviteId);
				v.setStatus(VideoAccountStatusEnum.USED);
				return v;
			}
			continue;
		}
		VideoAccount videoAccount=new VideoAccount();
		videoAccount.setKey("270373193@qq.com");
		videoAccount.setPassword("1qaz2wsx");
		videoAccount.setStatus(VideoAccountStatusEnum.FREE);
		videoAccount.setInvitationId(inviteId);
		return videoAccount;
	}
	
	public synchronized void returnFree(int inviteId){
		VideoAccount account = usedMaps.get(inviteId);
		if(account==null){
			return;
		}
		account.setStatus(VideoAccountStatusEnum.FREE);
		frees.add(account);
		usedMaps.remove(inviteId);
	}

	public List<VideoAccount> getConfigList() {
		return configList;
	}

	public void setConfigList(List<VideoAccount> configList) {
		this.configList = configList;
	}

	
	
}
