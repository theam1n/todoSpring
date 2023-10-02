package com.example.todoSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;



import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ag_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;


    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime registrationDate;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "reset_password_token", length = 64)
    private String resetPasswordToken;

    @Column(name = "reset_enabled")
    private Boolean resetEnabled;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    private List<Task> tasks;

    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.enabled);
    }


    public Long getId() {
        return id;
    }
}