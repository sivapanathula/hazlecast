package com.cache.hazlecast.service;

import com.cache.hazlecast.dao.UserRepository;
import com.cache.hazlecast.model.UserAccount;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMap<String, UserAccount> accountMap;

    public UserAccount findByAccountNumber(String accountNumber) {

        UserAccount userAccount = (accountMap.get(accountNumber) != null) ? accountMap.get(accountNumber)
                : userRepository.findByAccountNumber(accountNumber);
        return userAccount;

    }

    public void save(UserAccount user) {
        accountMap.put(user.getAccountNumber(), user);
        userRepository.save(user);
    }

    public UserAccount deleteByAccountNumber(String accountNumber) {
        accountMap.remove(accountNumber);
      return  userRepository.deleteByAccountNumber(accountNumber);
    }
}
