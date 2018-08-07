package com.dds.journal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid")
	private Long id;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="userid"), inverseJoinColumns = @JoinColumn(name="roleid"))
	private Set<Role> roles;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user", orphanRemoval=true)
	private Set<Journal> journals = new HashSet<>();
	
	@NotBlank(message="Please provide a username...")
	@Column(unique=true)
	@Size(min=3, max=20)
	private String username;
	
	private boolean enabled;
	
	@Transient
	@NotBlank
	private String password;
	
	@Column(name="confirmationtoken")
	private String confirmationToken;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@NotBlank
	@Column(unique=true)
	@Email(message="Please provide a valid email...")
	private String email;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	private String image;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated;
	
	/*@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updated =  new Date();
	}*/
	
	public User(){}

	public User(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.created = new Date();
		this.updated = new Date();
	}
	
	public User(Set<Role> roles, Set<Journal> journals, String username, boolean enabled, String password,
			String firstName, String lastName, String email, String city, String state) {
		super();
		this.roles = roles;
		this.journals = journals;
		this.username = username;
		this.enabled = enabled;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.state = state;
		this.created = new Date();
		this.updated = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Journal> getJournals() {
		return journals;
	}

	public void setJournals(Set<Journal> journals) {
		this.journals = journals;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", roles=" + roles.toArray() + ", journals=" + journals.toArray() + ", username=" + username
				+ ", enabled=" + enabled + ", password=" + password + ", confirmationToken=" + confirmationToken
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", city=" + city
				+ ", state=" + state + ", image=" + image + ", created=" + created + ", updated=" + updated + "]";
	}
}
