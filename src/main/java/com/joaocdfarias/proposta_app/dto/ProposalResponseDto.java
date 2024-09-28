package com.joaocdfarias.proposta_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProposalResponseDto {

  private Long id;

  private String name;

  private String lastName;

  private String phone;

  private String cpf;

  private Double income;

  private String requestedAmount;

  private int paymentDeadline;

  private Boolean approved;

  private String observation;
}
