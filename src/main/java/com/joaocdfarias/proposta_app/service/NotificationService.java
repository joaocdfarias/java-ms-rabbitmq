package com.joaocdfarias.proposta_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificationService {

  private RabbitTemplate rabbitTemplate;

  public void notificate(ProposalResponseDto proposal, String exchange) {
    rabbitTemplate.convertAndSend(exchange, "", proposal);
  }
}
