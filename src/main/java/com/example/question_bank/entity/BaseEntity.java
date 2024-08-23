package com.example.question_bank.entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, unique = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "edited_at", nullable = true, unique = false, updatable = true)
    private Date editedAt;
}