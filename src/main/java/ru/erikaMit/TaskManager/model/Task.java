package ru.erikaMit.TaskManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Task name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500)
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Task status is required")
    @Enumerated(EnumType.STRING)
    @Column
    private TaskStatus status;

<<<<<<< HEAD

=======
    @NotNull(message = "Task priority is required")
>>>>>>> origin/main
    @Enumerated(EnumType.STRING)
    @Column
    private TaskPriority priority;


}
