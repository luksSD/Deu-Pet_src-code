package br.dp.api.email;

import br.dp.api.DpApiApplication;
import br.dp.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String SENDER = "Suporte Deu Pet <deupet@outlook.com>";

    public String buildMessage(final String username, final String newPassword) {
        final String message = "<html lang=\"pt-br\">" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">" +
            "    <title>Suporte Deu Pet </title>" +
            "</head>" +
            "<body>" +
            "<p>Olá Sr(a). " + username + ".</p>\n" +
            "\n" +
            "<p>Recebemos sua solicitação para troca de senha.</p>\n" +
            "\n" +
            "<p>Geramos uma nova senha de acesso para que você possa acessar o sistema Deu Pet!</p>" +
            "\n" +
            "<p>A nova senha é:</p> " +
            "<p style=\"font-size: 20px\"><b>" + newPassword + "</b></p>" +
            "\n" +
            "<p>É importante que após realizar o acesso, vá até os detalhes do seu perfil e efetue a troca de sua senha.</p>" +
            "\n" +
            "\n" +
            "<p>Caso não tenha realizado essa solicitação, entre em contato com a equipe Deu Pet através deste e-mail ou desconsidere essa mensagem.</p>" +
            "</body>" +
            "</html>";

        return message;
    }

    public boolean send(final String receivers, final String subject, final String bodyEmail) {
        boolean isMessageSent = false;
        final AnnotationConfigApplicationContext aplication =
            new AnnotationConfigApplicationContext(DpApiApplication.class.getPackage().getName());
        mailSender = aplication.getBean(JavaMailSender.class);

        try {
            final MimeMessage message = mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(SENDER);
            helper.setTo(receivers);
            helper.setSubject(subject);
            helper.setText(bodyEmail, true);

            mailSender.send(message);
            isMessageSent = true;
        } catch (final MailException e) {
            e.printStackTrace();
        } catch (final MessagingException e) {
            e.printStackTrace();
        }

        return isMessageSent;
    }
}
