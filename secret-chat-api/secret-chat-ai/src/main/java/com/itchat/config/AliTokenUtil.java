package com.itchat.config;

import com.alibaba.nls.client.AccessToken;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AliTokenUtil {

	@Autowired
	private AliyunTTSConfig aliyunTTSConfig;

	private static String token;
	private long expireTime;

	// 使用@PostConstruct注解初始化Token
	@PostConstruct
	public void initToken() throws IOException {
		refreshToken();
	}

	// 懒加载Token
	private synchronized void refreshToken() throws IOException {
		if (token == null || System.currentTimeMillis() >= expireTime) {
			AccessToken accessToken = new AccessToken(aliyunTTSConfig.getAkId(), aliyunTTSConfig.getAkSecret());
			accessToken.apply();
			token = accessToken.getToken();
			expireTime = accessToken.getExpireTime() + System.currentTimeMillis();
		}
	}

	// 获取Token的方法，如果Token无效或过期则重新获取
	public String getToken() throws IOException {
		if (token == null || System.currentTimeMillis() >= expireTime) {
			refreshToken();
		}
		return token;
	}

	// 提供一个方法来检查Token是否有效
	public boolean isTokenValid() {
		return token != null && System.currentTimeMillis() < expireTime;
	}
}
