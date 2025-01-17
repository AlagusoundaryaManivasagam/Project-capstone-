package com.example.capstone.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "budget_entries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BudgetEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "budget_id", nullable = false)
    @ToString.Exclude
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expense_id", nullable = false)
    @ToString.Exclude
    private Entry entry;



    @Column (name = "budget_id", insertable = false, updatable = false)
    private Integer budgetId;

    @Column(name = "expense_id", insertable = false, updatable = false)
    private Integer expenseId;

    @Column(name = "date", columnDefinition = "Date")
    private LocalDate date;

}
