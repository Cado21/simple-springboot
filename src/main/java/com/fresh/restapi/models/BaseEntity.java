package com.fresh.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 123671273712L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonIgnore
    protected ZonedDateTime createdAt;
    @JsonIgnore
    protected ZonedDateTime updatedAt;


    @PrePersist
    private void prePersist() {
        if (createdAt == null) createdAt = ZonedDateTime.now();
        if (updatedAt == null) updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if (updatedAt == null) updatedAt = ZonedDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        System.out.println("DELETED");
    }
}
