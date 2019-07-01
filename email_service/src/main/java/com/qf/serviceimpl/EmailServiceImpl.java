package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;


/**
 * @author xzj
 * @date 2019/6/30 14:41
 */
@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Object captchaEmail(Email email) {
        //创建一封邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //创建一个Spring提供的邮件帮助对象
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage);
            //设置发送方
            messageHelper.setFrom("cookie_xzj@sina.com");

            //设置接收方
            //to - 普通接收方
            //cc - 抄送方
            //bcc - 密送方
            messageHelper.addTo(email.getTo());

            //设置标题
            messageHelper.setSubject("注册验证码！");

            /*//设置内容
            messageHelper.setText("请点击<a href='http://www.baidu.com'>这里</a>找回密码~", true);*/

            //设置时间
            messageHelper.setSentDate(new Date());

            /*//发送附件
            messageHelper.addAttachment("a.jpg", new File("C:\\Users\\ken\\Pictures\\Saved Pictures\\奥格瑞玛.jpg"));
*/
            int i = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
            messageHelper.setText(i+"");

            email.setText(i+"");
            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return email;
    }

    @Override
    public void resetPsw(User user) {
        //创建一封邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //创建一个Spring提供的邮件帮助对象
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage);
            //设置发送方
            messageHelper.setFrom("cookie_xzj@sina.com");

            //设置接收方
            //to - 普通接收方
            //cc - 抄送方
            //bcc - 密送方
            messageHelper.addTo(user.getEmail());

            //设置标题
            messageHelper.setSubject("找回密码");

            //设置内容
            messageHelper.setText("请点击<a href='http://localhost:8080/user/toUpdate'>这里</a>找回密码~", true);

            //设置时间
            messageHelper.setSentDate(new Date());

            /*//发送附件
            messageHelper.addAttachment("a.jpg", new File("C:\\Users\\ken\\Pictures\\Saved Pictures\\奥格瑞玛.jpg"));
*/


            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
