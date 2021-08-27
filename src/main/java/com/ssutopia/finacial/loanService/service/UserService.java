package com.ssutopia.finacial.loanService.service;

import com.ssutopia.finacial.loanService.entity.User;

public interface UserService {
    User findUserByUsername(String name);
}