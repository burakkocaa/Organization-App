package io.digitopia.organization.entities.concretes;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( updatable = false, nullable = false)
    private UUID id;

	    @Column(nullable = true, name = "created_at")//asd
	    @CreatedDate
	    private Date createdAt;

	    @Column(nullable = true, name = "updated_at")//asd
	    @LastModifiedDate
	    private Date updatedAt;
	    
	    @Column(name = "created_by")
	    private UUID createdBy;
	    
	    
	    @Column(name = "updated_by")
	    private UUID updatedBy;
	    
	    @NotNull
	    @Length(min=5,max=64)
	    @Column(nullable = false, name="full_name")
		private String fullName;
		
	    @NotNull
	    @Length(min=16,max=64)
		@Column(nullable = false, name="email", unique = true)
	    private String email;
		
	    @NotNull
	    @Length(min=5,max=64)
	    @Pattern(regexp ="^[a-zA-Z]+$")
		@Column(nullable = false, name="normalizedName") //regex ile yapabilirim.
		private String normalizedName;
		
	    
		@Enumerated(EnumType.STRING)
		@Column(nullable = true, name="status")
		private UserStatus status;
	    
	    @ManyToMany()
	    @OnDelete(action=OnDeleteAction.NO_ACTION)
	    @JoinColumn(name="organization_id" ,nullable = true)
	    private List<Organization> organization;

		public User(Date createdAt, UUID createdBy, @NotNull @Length(min = 5, max = 64) String fullName,
				@NotNull @Length(min = 16, max = 64) String email,
				@NotNull @Length(min = 5, max = 64) @Pattern(regexp = "^[a-zA-Z]+$") String normalizedName,List<Organization> organizations) {
			super();
			this.createdAt = createdAt;
			this.createdBy = createdBy;
			this.fullName = fullName;
			this.email = email;
			this.normalizedName = normalizedName;
			this.organization = organizations;
		}
	    
	    
	

}
