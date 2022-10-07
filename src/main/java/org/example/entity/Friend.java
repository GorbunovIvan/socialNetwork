package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friends")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Friend implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "sender", nullable = false)
    private Long sender;
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic
    @Column(name = "receiver", nullable = false)
    private Long receiver;
}
