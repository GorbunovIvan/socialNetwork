package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Basic
    @Column(name = "login", nullable = false, length = 25)
    private String login;

    @Basic
    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @Basic
    @Column(name = "registrationDate")
    private Timestamp registrationDate;
}
