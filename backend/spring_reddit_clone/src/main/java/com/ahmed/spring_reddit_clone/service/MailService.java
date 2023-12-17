package com.ahmed.spring_reddit_clone.service;


import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;


    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("springridditclone@gmail.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            throw new SpringRedditException(
                    String.format("Exception occurred when sending an email to %s", notificationEmail.getRecipient()));
        }
    }
}
