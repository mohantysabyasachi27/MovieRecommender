package com.asu.MovieRecommender.Services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
		connection.close();
	}

	@Override
	public String get(String key, String col) {
		if(StringUtils.isBlank(key)) {
			return new String();
		}
		RedisConnection connection = connectionFactory.getConnection();
		byte[] val = connection.hGet(key.getBytes(), col.getBytes()) == null ? new byte[0] : connection.hGet(key.getBytes(), col.getBytes());
		connection.close();
		return new String(val, StandardCharsets.UTF_8);
	}

	@Override
	public Map get(String key) {
		if(StringUtils.isBlank(key)) {
			return new HashMap<>();
		}
		RedisConnection connection = connectionFactory.getConnection();
		Map<byte[], byte[]> map = connection.hGetAll(key.getBytes());
		connection.close();
		if(map == null) {
			return new HashMap<>();
		}
		return map;
	}

}
