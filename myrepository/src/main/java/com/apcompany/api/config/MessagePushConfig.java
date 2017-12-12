package com.apcompany.api.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;


@Configuration
public class MessagePushConfig {

	private Logger logger = LoggerFactory.getLogger(MessagePushConfig.class);

	//baidupush config
	@Value("${baiduApiKey}")
	private String baiduApiKey = "RcXXHGPC3FgCHKWERalDhf3d" ;
	@Value("${baiduSecretKey}")
	private String baiduSecretKey = "HwYuVNKDlWVAiTYQ9HQ0l6TMgBjfuv6w";

	//alipush config
	@Value("${aliregionId}")
	private String aliregionId = "cn-hangzhou";
	@Value("${aliaccessKey}")
	private String aliaccessKey = "LTAI689aZEJjGHNX";
	@Value("${alisecret}")
	private String alisecret = "3dBMvaoG8Vw5EXOmxnMS3AxWq5pZsD";
	
	@Bean
	public BaiduPushClient createBaiduPushClient(){
		PushKeyPair pair = new PushKeyPair(baiduApiKey, baiduSecretKey);
		BaiduPushClient pushClient= new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
		pushClient.setChannelLogHandler (new YunLogHandler () {
            @Override
            public void onHandle (YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
		return pushClient;
	}

	@Bean
	public DefaultAcsClient createAliPushClient(){
		try{
			IClientProfile profile = DefaultProfile.getProfile(aliregionId, aliaccessKey, alisecret);
			DefaultAcsClient client =  new DefaultAcsClient(profile);
			logger.info("Alibaba Client is created ************");
			return client;
		}catch (Exception e){
			logger.error("createAliPushClient",e);
			throw new RuntimeException(e.getMessage());
		}
	}


}
