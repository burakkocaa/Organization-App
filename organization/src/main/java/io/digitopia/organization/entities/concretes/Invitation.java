package io.digitopia.organization.entities.concretes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name = "invitations")
	public class Invitation {

		@Id
	    @GeneratedValue(generator = "UUID")
	    @GenericGenerator(
	            name = "UUID",
	            strategy = "org.hibernate.id.UUIDGenerator"
	    )
	    @Column( updatable = false, nullable = false)
	    private UUID id;


	
	  @ManyToOne()
	  @OnDelete(action = OnDeleteAction.NO_ACTION)
	  @JoinColumn(name = "user_id", nullable = false)
	  private User user;
	  
	  @ManyToOne()
	  @OnDelete(action = OnDeleteAction.NO_ACTION)
	  @JoinColumn(name = "organization_id", nullable = false)
	  private Organization organization;

	  @Column(name = "message")
	  private String message;

	  @Column(name = "status")
	  @Enumerated(EnumType.STRING)
	  private InvitationStatus status;

	  @Column(nullable = false, name = "created_at")
	  @CreatedDate
	  private Date createdAt;
	
	  @Column(nullable = true, name = "updated_at")
	  @LastModifiedDate
	  private Date updatedAt;

	

	}

