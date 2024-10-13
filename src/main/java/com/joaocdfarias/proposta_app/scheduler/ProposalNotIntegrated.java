package com.joaocdfarias.proposta_app.scheduler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.joaocdfarias.proposta_app.entity.Proposal;
import com.joaocdfarias.proposta_app.repository.ProposalRepository;
import com.joaocdfarias.proposta_app.service.NotificationRabbitService;

@Component
public class ProposalNotIntegrated {

  private ProposalRepository proposalRepository;

  private NotificationRabbitService notificationRabbitService;

  private String exchange;

  private final Logger logger = LoggerFactory.getLogger(ProposalNotIntegrated.class);

  public ProposalNotIntegrated(ProposalRepository proposalRepository,
      NotificationRabbitService notificationRabbitService,
      @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
    this.proposalRepository = proposalRepository;
    this.notificationRabbitService = notificationRabbitService;
    this.exchange = exchange;
  }

  @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
  public void findProposalsNotIntegrated() {
    proposalRepository.findAllByIntegratedIsFalse()
        .forEach(proposal -> {
          try {
            notificationRabbitService.notify(proposal, exchange);
            updateProposal(proposal);
          } catch (Exception exception) {
            logger.error(exception.getMessage());
          }
        });
  }

  private void updateProposal(Proposal proposal) {
    proposal.setIntegrated(true);
    proposalRepository.save(proposal);
  }

}
