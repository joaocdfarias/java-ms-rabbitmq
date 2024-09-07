package com.joaocdfarias.proposta_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaocdfarias.proposta_app.dto.ProposalRequestDto;
import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

  @PostMapping
  public ResponseEntity<ProposalResponseDto> create(@RequestBody ProposalRequestDto requestDto) {
    return null;
  }
}
