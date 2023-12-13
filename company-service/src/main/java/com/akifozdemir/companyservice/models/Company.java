package com.akifozdemir.companyservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private UUID ownerId;

    private List<UUID> workers;

    public void addWorkers(List<UUID> newWorkers) {
        if (this.workers == null) {
            this.workers = new ArrayList<>();
        }
        this.workers.addAll(newWorkers);
    }
}
