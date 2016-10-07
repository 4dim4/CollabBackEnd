package com.sutta.collab.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table
public class Blog {
	@Id
	private String id;
	private String title;
	private char status;
	
	private String Description;
	@Column(name = "user_id")
	private String userId;
	@CreationTimestamp
	@Column(updatable = false)
	private Date created;
	@UpdateTimestamp
	@Column(insertable = false)
	private Date updated;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", status=" + status + ", Description=" + Description
				+ ", userId=" + userId + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
	
	

}
