package com.joaocdfarias.proposta_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;

@Service
public class WebSocketService {

  @Autowired
  private SimpMessagingTemplate template;

  public void notify(ProposalResponseDto proposal) {
    template.convertAndSend("/proposal", proposal);
  }
}
