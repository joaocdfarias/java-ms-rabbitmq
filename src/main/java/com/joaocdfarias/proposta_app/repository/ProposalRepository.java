package com.joaocdfarias.proposta_app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joaocdfarias.proposta_app.entity.Proposal;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

  List<Proposal> findAllByIntegratedIsFalse();
}
