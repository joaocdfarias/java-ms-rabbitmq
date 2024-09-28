package com.joaocdfarias.proposta_app.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Proposal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double requestedAmount;

  private int paymentDeadline;

  private Boolean approved;

  private boolean integrated;

  private String observation;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "user_id")
  private User user;
}
