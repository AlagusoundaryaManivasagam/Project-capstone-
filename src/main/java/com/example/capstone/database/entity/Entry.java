package com.example.capstone.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private float amount;

    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "date", columnDefinition = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "flag")
    private String flag;
}
