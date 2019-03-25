package com.pebstone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private int balance;    

    protected Account() {}


    public Account(int id, int balance) {
        super();
        this.id = id;
        this.balance = balance;
    }


    public Account(int balance) {
        super();
        this.balance = balance;
    }


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance= balance;
	}

	@Override
    public String toString() {
        return String.format(
                "Account[id=%d, balance='%s']",
                id, balance);
    }

}