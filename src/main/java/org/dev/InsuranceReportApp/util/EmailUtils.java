package org.dev.InsuranceReportApp.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.dev.InsuranceReportApp.binder.MailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    public void mailSender(MailDetails mailDetails, File file) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);

            messageHelper.setSubject(mailDetails.getSubject());

            messageHelper.setText(mailDetails.getBody());
            messageHelper.setTo(mailDetails.getTo());
            messageHelper.addAttachment("plans-info", file);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("mail sent successfully");
    }

}
