package com.joaocdfarias.proposta_app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;
import com.joaocdfarias.proposta_app.entity.Proposal;
import com.joaocdfarias.proposta_app.mapper.ProposalMapper;
import com.joaocdfarias.proposta_app.repository.ProposalRepository;
import com.joaocdfarias.proposta_app.service.WebSocketService;

@Component
public class FinishedProposalListener {

  @Autowired
  private ProposalRepository proposalRepository;

  @Autowired
  private WebSocketService webSocketService;

  @RabbitListener(queues = "${rabbitmq.queue.finished.proposal}")
  public void finishedProposal(Proposal proposal) {
    proposalRepository.save(proposal);
    ProposalResponseDto responseDto = ProposalMapper.INSTANCE.convertEntityToDto(proposal);
    webSocketService.notify(responseDto);
  }
}
