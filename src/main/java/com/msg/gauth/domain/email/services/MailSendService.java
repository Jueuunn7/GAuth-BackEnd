package com.msg.gauth.domain.email.services;

import javax.mail.Message.RecipientType;
import com.msg.gauth.domain.email.EmailAuthEntity;
import com.msg.gauth.domain.email.exception.ManyRequestEmailAuthException;
import com.msg.gauth.domain.email.presentation.dto.request.EmailSendDto;
import com.msg.gauth.domain.email.repository.EmailAuthRepository;
import com.msg.gauth.global.exception.ErrorCode;
import com.msg.gauth.global.exception.exceptions.MessageSendFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
@EnableAsync
@RequiredArgsConstructor
public class MailSendService {
    private final JavaMailSender mailSender;
    private final EmailAuthRepository emailAuthRepository;

    @Async
    @Transactional
    public void execute(EmailSendDto emailSendDto){
        String email = emailSendDto.getEmail();
        String value = UUID.randomUUID().toString();
        EmailAuthEntity authEntity = emailAuthRepository.findById(email)
                .orElse(EmailAuthEntity.builder()
                        .authentication(false)
                        .randomValue(value)
                        .email(email)
                        .attemptCount(0)
                        .build());

        if (authEntity.getAttemptCount() >= 3)
            throw new ManyRequestEmailAuthException();
        authEntity.updateRandomValue(value);
        authEntity.increaseAttemptCount();

        emailAuthRepository.save(authEntity);
        try{
            MimeMessage message = mailSender.createMimeMessage();
            String msg = "<form action=\"http://localhost:8080/email/authentication?email=" + email + "&uuid=" + value + "\" method=\"post\"><input type=\"submit\" value=\"인증하기\" style=\"padding: 10px; border: none; color: white; background-color: skyblue; border-radius: 8px; align-self: center; text-align: center;\" /></form>";
            message.addRecipients(RecipientType.TO,emailSendDto.getEmail());
            message.setSubject("[Gauth] 이메일 인즘");
            message.setText(msg, "utf-8", "html");
            mailSender.send(message);
        }catch (MessagingException ex){
            throw new MessageSendFailException(ErrorCode.MAIL_SEND_FAIL);
        }
    }
}
