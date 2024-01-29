package com.cronos.cvtool.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

	@Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	@CreatedDate
	protected LocalDateTime createdAt;

	@Column(name = "MODIFIED_AT", columnDefinition = "TIMESTAMP")
	@LastModifiedDate
	protected LocalDateTime modifiedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

}
