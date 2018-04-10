package br.com.douglasfernandes.UserServices.Messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MessageSender {

    @Autowired
    @Qualifier("rabbitTemplate")
    private RabbitTemplate template;

    public void send(String exchangeName, String queueName, Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = mapper.writeValueAsString(object);

        template.convertAndSend(exchangeName, queueName, jsonObject);

        log.info("M=send, I=Enviado mensagem para a Fila. QUEUE=" + queueName);
    }
}
