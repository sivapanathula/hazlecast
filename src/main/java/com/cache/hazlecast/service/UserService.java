package com.cache.hazlecast.service;

import com.cache.hazlecast.dao.UserRepository;
import com.cache.hazlecast.model.UserAccount;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMap<String, UserAccount> accountMap;

    @Cacheable(value = "users",sync = true)
    public UserAccount findByAccountNumber(String accountNumber) {

        /*UserAccount userAccount = (accountMap.get(accountNumber) != null) ? accountMap.get(accountNumber)
                : userRepository.findByAccountNumber(accountNumber);*/
        return userRepository.findByAccountNumber(accountNumber);

    }

    public void save(UserAccount user) {
       // accountMap.put(user.getAccountNumber(), user);
        userRepository.save(user);
    }

    @CacheEvict(value = "users",allEntries = true)
    public UserAccount deleteByAccountNumber(String accountNumber) {
      //  accountMap.remove(accountNumber);
      return  userRepository.deleteByAccountNumber(accountNumber);
    }
}
