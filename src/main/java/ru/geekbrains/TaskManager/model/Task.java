package ru.geekbrains.TaskManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks") // переопреление имени таблицы
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column
    @Enumerated
    private TaskStatus status;
    @Column
    @Enumerated
    private TaskPriority priority;






}
