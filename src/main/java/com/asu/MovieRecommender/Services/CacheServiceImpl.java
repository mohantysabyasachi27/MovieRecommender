package com.asu.MovieRecommender.Services;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.nimbusds.oauth2.sdk.util.StringUtils;

public class CacheServiceImpl implements CacheService {

	@Autowired
	private LettuceConnectionFactory connectionFactory;
	
	
	@Override
	public void put(String key, String col, String value) {
		RedisConnection connection=connectionFactory.getConnection();
		if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(col) && StringUtils.isNotBlank(value)) {
			connection.hSet(key.getBytes(),col.getBytes(),value.getBytes());	
		}
	}

	@Override
	public String get(String key, String col) {
		RedisConnection connection = connectionFactory.getConnection();
		byte[] val = connection.hGet(key.getBytes(), col.getBytes()) == null ? new byte[0] : connection.hGet(key.getBytes(), col.getBytes());
		return new String(val, StandardCharsets.UTF_8);
	}

	@Override
	public void evict(String key, List<String> col) {
		RedisConnection connection=connectionFactory.getConnection();
	}

}
