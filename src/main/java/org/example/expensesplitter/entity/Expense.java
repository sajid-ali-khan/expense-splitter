package org.example.expensesplitter.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @ManyToOne(optional = false)
    private Group group;
    private double value;
    private String reason;

    @JoinColumn
    @ManyToOne(optional = false)
    private Member payer;

    private Instant payedAt;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "expense")
    private List<Debt> debts = new ArrayList<>();
}
