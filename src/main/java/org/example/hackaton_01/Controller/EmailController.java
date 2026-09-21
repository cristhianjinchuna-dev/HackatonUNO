package org.example.hackaton_01.Controller;

import org.example.hackaton_01.Model.Mail;
import org.example.hackaton_01.Service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/simple")
    public void SendSimpleEmail(@RequestBody Mail mail) {
        emailService.SendSimpleEmail(mail);
    }
}
