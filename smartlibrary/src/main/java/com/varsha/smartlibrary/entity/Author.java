package com.varsha.smartlibrary.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Author{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false,length=200)
    private String name;
}