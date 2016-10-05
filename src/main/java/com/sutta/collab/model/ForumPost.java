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
public class ForumPost {

	@Id
	private String id;
	private String content;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "thread_id")
	private String threadId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	/*
	 * @PrePersist public void onCreate() { createdOn = new Date(); }
	 * 
	 * @PreUpdate public void onUpdate() { updatedOn = new Date(); }
	 */

}
