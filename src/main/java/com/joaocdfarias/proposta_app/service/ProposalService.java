package com.joaocdfarias.proposta_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joaocdfarias.proposta_app.dto.ProposalRequestDto;
import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;
import com.joaocdfarias.proposta_app.entity.Proposal;
import com.joaocdfarias.proposta_app.mapper.ProposalMapper;
import com.joaocdfarias.proposta_app.repository.ProposalRepository;

@Service
public class ProposalService {

  private ProposalRepository proposalRepository;

  private NotificationRabbitService notificationService;

  private String exchange;

  public ProposalService(ProposalRepository proposalRepository,
      NotificationRabbitService notificationService,
      @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
    this.proposalRepository = proposalRepository;
    this.notificationService = notificationService;
    this.exchange = exchange;
  }

  public ProposalResponseDto create(ProposalRequestDto requestDto) {
    Proposal proposal = ProposalMapper.INSTANCE.convertDtoToProposal(requestDto);
    proposalRepository.save(proposal);

    notifyRabbitMQ(proposal);

    return ProposalMapper.INSTANCE.convertEntityToDto(proposal);
  }

  private void notifyRabbitMQ(Proposal proposal) {
    try {
      notificationService.notify(proposal, exchange);
    } catch (Exception e) {
      proposal.setIntegrated(false);
      proposalRepository.save(proposal);
    }
  }

  public List<ProposalResponseDto> getProposals() {
    return ProposalMapper.INSTANCE.convertListEntityToListDto(proposalRepository.findAll());
  }
}
