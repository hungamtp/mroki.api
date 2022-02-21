package mondays.net.mroki.api.controller.mail;

import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO> sendSimpleEmail(@RequestBody String email){
        ResponseDTO responseDTO = new ResponseDTO();
        if(customerService.findByEmail(email) == false){
            responseDTO.setErrorCode(ErrorCode.SEND_EMAIL);
            return ResponseEntity.ok().body(responseDTO);
        }
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Change your password");
        message.setText("http://localhost:3000/auth/change-password");
        this.emailSender.send(message);
        responseDTO.setErrorCode(SuccessCode.SEND_EMAIL);
        return ResponseEntity.ok().body(responseDTO);
    }
}
