package com.qf.service;

import com.qf.entity.User;

import java.util.List;

/**
 * @author xzj
 * @date 2019/6/29 13:25
 */
public interface IUserService {

    int login(User user);

    int addUser(User user);

    List<User> getList();

    User reset(User user);

    void updata(User user1);
}
