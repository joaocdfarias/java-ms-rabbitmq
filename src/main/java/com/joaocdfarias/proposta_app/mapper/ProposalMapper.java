package com.joaocdfarias.proposta_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.joaocdfarias.proposta_app.dto.ProposalRequestDto;
import com.joaocdfarias.proposta_app.dto.ProposalResponseDto;
import com.joaocdfarias.proposta_app.entity.Proposal;

@Mapper
public interface ProposalMapper {

  ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

  @Mapping(target = "user.name", source = "name")
  @Mapping(target = "user.lastName", source = "lastName")
  @Mapping(target = "user.cpf", source = "cpf")
  @Mapping(target = "user.phone", source = "phone")
  @Mapping(target = "user.income", source = "income")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "approved", ignore = true)
  @Mapping(target = "integrated", ignore = true)
  @Mapping(target = "observation", ignore = true)
  Proposal convertDtoToProposal(ProposalRequestDto proposalRequestDto);

  @Mapping(target = "name", source = "user.name")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "cpf", source = "user.cpf")
  @Mapping(target = "phone", source = "user.phone")
  @Mapping(target = "income", source = "user.income")
  ProposalResponseDto convertEntityToDto(Proposal proposal);

}