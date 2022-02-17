package mondays.net.mroki.api.controller.mail;

import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public CustomerServiceImpl customerService;

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(@RequestBody String email){
        if(customerService.findByEmail(email) == false) return "email note Found";
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Change your password");
        message.setText("http://localhost:3000/auth/change-password");
        this.emailSender.send(message);
        return "Check Your Email";
    }
}
