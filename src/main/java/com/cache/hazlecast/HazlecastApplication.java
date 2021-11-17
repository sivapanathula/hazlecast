package com.cache.hazlecast;

import com.cache.hazlecast.model.UserAccount;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
public class HazlecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazlecastApplication.class, args);
	}

	@Bean
	public Config hazelCastConfig() {

		return new Config().setManagementCenterConfig(

		new ManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8080/hazelcast-mancenter"));

	}

	@Bean
	public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
		return Hazelcast.newHazelcastInstance(hazelCastConfig);
	}

	@Bean
	public IMap<String, UserAccount> accountMap(HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getMap("accountMap");
	}
}
