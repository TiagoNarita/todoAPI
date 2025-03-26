package com.example.demo.infrastructure.entitys;

import com.example.demo.infrastructure.enums.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="tasks")
@Entity(name = "tasks")
@Getter()
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column
    private User user; // Reference to the user

    @PrePersist
    private void prePersist(){
        status = TaskStatusEnum.ACTIVE;
    }
}
