package com.pebstone.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pebstone.model.Account;

@Repository
public interface BankRepository extends CrudRepository<Account, Integer> {

}