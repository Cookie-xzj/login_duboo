package com.qf.service;

import com.qf.entity.Email;
import com.qf.entity.User;

/**
 * @author xzj
 * @date 2019/6/30 14:26
 */
public interface IEmailService {

    Object captchaEmail(Email  email);

    void resetPsw(User user);
}
