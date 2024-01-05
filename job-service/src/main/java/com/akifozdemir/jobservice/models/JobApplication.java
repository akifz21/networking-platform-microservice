package com.akifozdemir.jobservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
