package com.example.capstone.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "entries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Entry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "entry", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BudgetEntry> budgetEntries;


    @Column(name = "amount")
    private float amount;

    @Column(name = "date", columnDefinition = "Date")
    private Date date;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "flag")
    private String flag;
}