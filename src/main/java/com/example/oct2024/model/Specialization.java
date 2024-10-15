package com.example.oct2024.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

@Entity
public class Specialization {

    @Id
    @Column(name = "sp_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "specialization_sp_id_seq", allocationSize = 1)
    private int spId;
    @Column(name = "sp_name")
    private String spName;

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }
}
