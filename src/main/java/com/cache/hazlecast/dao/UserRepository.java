package com.cache.hazlecast.dao;

import com.cache.hazlecast.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount,Long> {


    UserAccount findByAccountNumber(String accountNumber);

    UserAccount deleteByAccountNumber(String accountNumber);
}
