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
public class Forum {

	@Id
	@Column(name = "thread_id")
	private String id;
	private String title;
	private String description;
	@Column(name = "user_id")
	private String userId;
	private char status;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Forum [id=" + id + ", title=" + title + ", description=" + description + ", userId=" + userId
				+ ", status=" + status + ", created=" + created + ", updated=" + updated + "]";
	}

	

	/*
	 * @PrePersist public void onCreate() { createdOn = new Date(); }
	 * 
	 * @PreUpdate public void onUpdate() { updatedOn = new Date(); }
	 */

}
