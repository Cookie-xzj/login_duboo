package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xzj
 * @date 2019/6/29 13:33
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int login(User user) {
        List<User> userList = userMapper.selectList(null);
        User u = getUser(user, userList);
        if (u != null) {
            return 1;
        }
        return 0;
    }

    private User getUser(User user, List<User> userList) {
        for (User u : userList) {
            if (u.getUsername().equals(user.getUsername())){
                return u;
            }
        }
        return null;
    }

    @Override
    public int addUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public List<User> getList() {
        List<User> userList = userMapper.selectList(null);
        return userList;
    }

    @Override
    public User reset(User user) {
        List<User> userList = userMapper.selectList(null);
        return getUser(user, userList);
    }

    @Override
    public void updata(User user1) {
    }
}
