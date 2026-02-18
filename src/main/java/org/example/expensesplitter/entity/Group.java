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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JoinColumn
    @ManyToOne(optional = false)
    private User owner;

    private boolean active;
    private Instant createdAt;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "group")
    private List<Member> members = new ArrayList();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group")
    private List<Expense> expenses = new ArrayList<>();

    @PrePersist
    private void defaults(){
        this.createdAt = Instant.now();
        this.active = true;
    }
}
