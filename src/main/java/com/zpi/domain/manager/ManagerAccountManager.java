package com.zpi.domain.manager;

import java.util.NoSuchElementException;

public interface ManagerAccountManager {
    String signIn(String username, String password) throws IllegalArgumentException, NoSuchElementException;
    void createAccount(String username, String password, String role);
    void changePassword(String username, String password);
}
