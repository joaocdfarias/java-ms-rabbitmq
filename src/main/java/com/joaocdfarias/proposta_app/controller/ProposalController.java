package com.joaocdfarias.proposta_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaocdfarias.proposta_app.dto.ProposalRequestDto;
import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;
import com.joaocdfarias.proposta_app.service.ProposalService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/proposal")
public class ProposalController {

  private ProposalService proposalService;

  @PostMapping
  public ResponseEntity<ProposalResponseDto> create(@RequestBody ProposalRequestDto requestDto) {
    ProposalResponseDto response = proposalService.create(requestDto);
    return ResponseEntity
        .created(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri())
        .body(response);
  }

  @GetMapping
  public ResponseEntity<List<ProposalResponseDto>> getProposals() {
    return ResponseEntity.ok(proposalService.getProposals());
  }
}
