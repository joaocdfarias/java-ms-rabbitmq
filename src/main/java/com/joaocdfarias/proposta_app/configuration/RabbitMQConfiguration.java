package com.joaocdfarias.proposta_app.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

  @Bean
  public Queue createQueuePendingProposalMsCreditAnalysis() {
    return QueueBuilder.durable("proposal-pending.ms-credit-analysis").build();
  }

  @Bean
  public Queue createQueuePendingProposalMsNotification() {
    return QueueBuilder.durable("proposal-pending.ms-notification").build();
  }

  @Bean
  public Queue createQueueFinishedProposalMsProposal() {
    return QueueBuilder.durable("proposal-finished.ms-proposal").build();
  }

  @Bean
  public Queue createQueueFinishedProposalMsNotification() {
    return QueueBuilder.durable("proposal-finished.ms-notification").build();
  }

  @Bean
  public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
    return event -> rabbitAdmin.initialize();
  }

  @Bean
  public FanoutExchange createFanoutExchangePendingProposal() {
    return ExchangeBuilder.fanoutExchange("proposal-pending.ex").build();
  }

  @Bean
  public Binding createBindingPendingProposalMsCreditAnalysis() {
    return BindingBuilder.bind(createQueuePendingProposalMsCreditAnalysis())
        .to(createFanoutExchangePendingProposal());
  }

  @Bean
  public Binding createBindingPendingProposalMsNotification() {
    return BindingBuilder.bind(createQueuePendingProposalMsNotification())
        .to(createFanoutExchangePendingProposal());
  }
}
