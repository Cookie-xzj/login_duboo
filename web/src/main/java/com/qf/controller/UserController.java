package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xzj
 * @date 2019/6/29 13:02
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private IUserService userService;

    @Reference
    private IEmailService emailService;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String login(User user){
        int i  = userService.login(user);
        if (i > 0) {
            return "redirect:/user/getList";
        }
        return "login";
    }
    @RequestMapping("getList")
    public String getList(Model model){
        List<User> userList  = userService.getList();
        model.addAttribute("userList",userList);
        return "userInfo";
    }
    @RequestMapping("toRegister")
    public String toregister(){
        return "register";
    }

    @RequestMapping("captchaEmail")
    @ResponseBody
    public void captchaEmail(String to, HttpServletRequest request){
        Email email = new Email();
        email.setTo(to);
        email = (Email) emailService.captchaEmail(email);
        String text = email.getText();
        request.getSession().setAttribute("captcha",text);
    }
    @RequestMapping("verifyEmail")
    @ResponseBody
    public Object verifyEmail(String verify,HttpServletRequest request){
        String text = (String) request.getSession().getAttribute("captcha");
        if (text.equals(verify.toString())){
            return 1;
        }
        return 0;
    }

    @RequestMapping("register")
    public String register(User user){
        int i = userService.addUser(user);
        if (i > 0) {
            return "login";
        }
        return "register";
    }

    @RequestMapping("toReset")
    public String toReset(){
        return "reset";
    }

    @RequestMapping("reset")
    public String reset(User user){
        User user1 = userService.reset(user);
        emailService.resetPsw(user1);
        return "redirect:/user/toLogin";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(){
        return "update";
    }

    @RequestMapping("update")
    public String update(User user){
        System.out.println(user.getPassword());
        User user1 = userService.reset(user);
        userService.updata(user1);
        return "redirect:/user/toLogin";
    }


}
