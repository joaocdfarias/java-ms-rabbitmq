package com.joaocdfarias.proposta_app.service;

import org.springframework.stereotype.Service;

import com.joaocdfarias.proposta_app.dto.ProposalRequestDto;
import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;
import com.joaocdfarias.proposta_app.entity.Proposal;
import com.joaocdfarias.proposta_app.repository.ProposalRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProposalService {

  private ProposalRepository proposalRepository;

  public ProposalResponseDto create(ProposalRequestDto requestDto) {
    proposalRepository.save(new Proposal());
    return null;
  }
}
