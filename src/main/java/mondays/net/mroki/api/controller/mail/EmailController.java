package mondays.net.mroki.api.controller.mail;

import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.authDTO.EmailDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class EmailController {
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public CustomerServiceImpl customerService;

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public ResponseEntity<ResponseDTO> sendSimpleEmail(@RequestBody EmailDTO dto){
        ResponseDTO responseDTO = new ResponseDTO();
        if(customerService.findByEmail(dto.getEmail()) == false){

            responseDTO.setErrorCode(ErrorCode.SEND_EMAIL);
            return ResponseEntity.ok().body(responseDTO);
        }
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(dto.getEmail());
        message.setSubject("Change your password");
        message.setText("http://localhost:3000/auth/change-password");
        this.emailSender.send(message);
        responseDTO.setErrorCode(SuccessCode.SEND_EMAIL);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/change-password-by-email")
    public ResponseEntity<ResponseDTO> changePassword (@Valid @RequestBody EmailDTO user ){
        ResponseDTO response = new ResponseDTO();
        try {
            if(customerService.updatePassword(user)== true){
                response.setSuccessCode(SuccessCode.UPDATE_PASS);
                return ResponseEntity.ok().body(response);
            }
            else {
                response.setErrorCode(ErrorCode.UPDATE_PASS);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e){
            response.setErrorCode(ErrorCode.UPDATE_PASS);
            return ResponseEntity.ok().body(response);
        }
    }
}