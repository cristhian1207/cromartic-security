package com.cchacalcaje.cromartic.security.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="NVARCHAR(25)", nullable=false)
	private String username;
	
	@Column(columnDefinition="NVARCHAR(50)", nullable=false)
	private String name;
	
	@Column(columnDefinition="NVARCHAR(30)", nullable=false)
	private String pat_lastname;
	
	@Column(columnDefinition="NVARCHAR(30)", nullable=false)
	private String mat_lastname;
	
	@Column(columnDefinition="NVARCHAR(60)", nullable=false)
	private String email;
	
	@Column(columnDefinition="NVARCHAR(100)", nullable=false)
	private String password;
	
	@Column(columnDefinition="NVARCHAR(255)", nullable=false)
	private String image;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	
	@Column(nullable=false, columnDefinition="TINYINT(1)")	
	private Boolean status;	
	
	protected User() { }
	
	public User(Long id, String username, String name, String pat_lastname, String mat_lastname, String email,
			String password, String image, Date created_date, Date updated_date, Boolean status) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.pat_lastname = pat_lastname;
		this.mat_lastname = mat_lastname;
		this.email = email;
		this.password = password;
		this.image = image;
		this.created_date = created_date;
		this.updated_date = updated_date;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPat_lastname() {
		return pat_lastname;
	}

	public void setPat_lastname(String pat_lastname) {
		this.pat_lastname = pat_lastname;
	}

	public String getMat_lastname() {
		return mat_lastname;
	}

	public void setMat_lastname(String mat_lastname) {
		this.mat_lastname = mat_lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", pat_lastname=" + pat_lastname
				+ ", mat_lastname=" + mat_lastname + ", email=" + email + ", password=" + password + ", image=" + image
				+ ", created_date=" + created_date + ", updated_date=" + updated_date + ", status=" + status + "]";
	}
			
}
