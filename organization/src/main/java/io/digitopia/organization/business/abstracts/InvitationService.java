package io.digitopia.organization.business.abstracts;

import java.util.List;
import java.util.UUID;

import io.digitopia.organization.entities.concretes.Invitation;
import io.digitopia.organization.entities.dtos.request.InvitationCreateDto;
import io.digitopia.organization.entities.dtos.response.InvitationResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;

public interface InvitationService {
	Result delete(UUID invitationId);
	
	DataResult<InvitationCreateDto> add(InvitationCreateDto invitationDto);
	
	DataResult<List<InvitationResponseDto>> getAll();
	
	DataResult<InvitationResponseDto> getById(UUID id);
	
	
}
