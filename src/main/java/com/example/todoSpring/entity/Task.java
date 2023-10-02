package com.example.todoSpring.entity;

import com.example.todoSpring.model.TaskSortType;
import com.example.todoSpring.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ag_task")
@JsonIgnoreProperties({"user"})
public class Task implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "task_sequence",
            sequenceName = "task_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "task_sequence")
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_sort_type")
    private Integer taskSortType;

    @Column(name = "task_status")
    private Integer taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "task_create_date")
    @CreatedDate
    private LocalDate taskCreateDate;

    @Column(name = "task_deadline_date")
    private LocalDate taskDeadlineDate;

    public TaskSortType getTaskSortType() {
        return Arrays.stream(TaskSortType.values())
                .filter(sortType -> Objects.equals(sortType.getId(), this.taskSortType))
                .findFirst()
                .orElse(TaskSortType.UNSUPPORTED);
    }

    public void setTaskSortType(TaskSortType taskSortType) {
        if (taskSortType != null) {
            this.taskSortType = taskSortType.getId();
        }
    }

    public TaskStatus getTaskStatus() {
        return Arrays.stream(TaskStatus.values())
                .filter(status -> Objects.equals(status.getId(), this.taskStatus))
                .findFirst()
                .orElse(TaskStatus.UNSUPPORTED);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        if (taskStatus != null) {
            this.taskStatus = taskStatus.getId();
        }
    }

}
