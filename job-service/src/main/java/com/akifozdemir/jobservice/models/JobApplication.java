package com.akifozdemir.jobservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class JobApplication {
    @Id
    private UUID id;
    private UUID userId;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne
        @JoinColumn(name = "job_id")
        private Job job;

}
