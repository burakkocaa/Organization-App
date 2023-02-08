package io.digitopia.organization.dataAccess;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.digitopia.organization.entities.concretes.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, UUID> {
	
	List<Invitation> getInvitationByUserId (UUID userID);
}
