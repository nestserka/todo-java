package com.javarush.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table (name = "task", schema = "public")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    @Column(name = "status", columnDefinition = "smallint")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}