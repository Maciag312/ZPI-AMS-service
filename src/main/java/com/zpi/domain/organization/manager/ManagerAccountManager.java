package com.zpi.domain.organization.manager;

import java.util.NoSuchElementException;

public interface ManagerAccountManager {
    String signIn(String organizationName, String username, String password) throws IllegalArgumentException, NoSuchElementException;
    void createAccount(String organizationName, String username, String password, String role);
    void changePassword(String organizationName, String username, String password);
}
