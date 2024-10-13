package com.joaocdfarias.proposta_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.joaocdfarias.proposta_app.entity.Proposal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificationService {

  private RabbitTemplate rabbitTemplate;

  public void notify(Proposal proposal, String exchange) {
    rabbitTemplate.convertAndSend(exchange, "", proposal);
  }
}
