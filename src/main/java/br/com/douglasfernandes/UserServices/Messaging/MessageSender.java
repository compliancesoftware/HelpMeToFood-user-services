package br.com.douglasfernandes.UserServices.Messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class MessageSender {

    @Autowired
    @Qualifier("rabbitTemplate")
    private RabbitTemplate template;

    public void send(String exchangeName, String queueName, String object) {
        template.convertAndSend(exchangeName, queueName, object);
        log.info("M=send, I=Enviado mensagem para a Fila. QUEUE=" + queueName);
    }
}
