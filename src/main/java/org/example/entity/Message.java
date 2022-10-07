package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "sender", nullable = false)
    private Long sender;

    @Basic
    @Column(name = "receiver", nullable = false)
    private Long receiver;

    @Basic
    @Column(name = "message", length = -1)
    private String message;

    @Basic
    @Column(name = "creationDate")
    private Timestamp creationDate;
}
