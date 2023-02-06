package io.digitopia.organization.entities.concretes;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="organization")
public class Organization {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( updatable = false, nullable = false)
    private UUID id;
	
		@Column(nullable = true, name = "created_at")
	    @CreatedDate
	    private Date createdAt;

	    @Column(nullable = true, name = "updated_at")
	    @LastModifiedDate
	    private Date updatedAt;
	    
	    @NotNull
	    @Column(nullable = false)
	    private String organizationName;
	    
	    @NotNull
	    @Pattern(regexp ="^[A-Za-z0-9]+$")
	    @Column(nullable = false, unique = true)
	    private String registryNumber;
	    
	    @NotNull
	    @Column(nullable = false)
	    private String normalizedOrganizationName;
	    
	    @NotNull
	    @Column(nullable = false)
	    private String contactEmail;
	    
	    @NotNull
	    @Column(nullable = false)
	    private int yearFounded;
	    
	    @NotNull
	    @Column(nullable = false)
	    private String phone;
	    
	    @NotNull
	    @Column(nullable = false)
	    private int companySize;
	    
	    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "organization")
	    @JsonIgnore
	    private List<User> user;
	    
	 

}
