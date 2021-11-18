package com.cache.hazlecast;

import com.cache.hazlecast.model.UserAccount;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class HazlecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazlecastApplication.class, args);
	}

	@Bean
	public Config hazelCastConfig() {

		return new Config()
				.setInstanceName("hazelcastInstance")
				.addMapConfig(
						new MapConfig()
								.setName("users")
								//.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
							//	.setEvictionPolicy(EvictionPolicy.LRU)
								.setTimeToLiveSeconds(200));

				/*.setManagementCenterConfig(

		new ManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8080/hazelcast-mancenter"));
*/
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
