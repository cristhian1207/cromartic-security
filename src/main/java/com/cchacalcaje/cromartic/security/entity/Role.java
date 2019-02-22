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
@Table(name="tb_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="NVARCHAR(25)", nullable=false)
	private String rolename;
	
	@Column(columnDefinition="NVARCHAR(250)", nullable=false)
	private String description;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	
	@Column(columnDefinition="NVARCHAR(25)")
	private String created_user;
	
	@Column(columnDefinition="NVARCHAR(25)")
	private String updated_user;

	protected Role() { }
	
	public Role(Long id, String rolename, String description, Date created_date, Date updated_date, String created_user,
			String updated_user) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.description = description;
		this.created_date = created_date;
		this.updated_date = updated_date;
		this.created_user = created_user;
		this.updated_user = updated_user;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public String getUpdated_user() {
		return updated_user;
	}

	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", description=" + description + ", created_date="
				+ created_date + ", updated_date=" + updated_date + ", created_user=" + created_user + ", updated_user="
				+ updated_user + "]";
	}	
}
