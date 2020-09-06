package com.smoothstack.lms.librarianservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table
public class LibraryBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column
    @Size(min = 2, max = 100)
    private String name;

    @Column
    @Size(min = 1, max = 100)
    private String address;

    public LibraryBranch() {
    }

    public LibraryBranch(@Min(1) Long id, @Size(min = 2, max = 100) String name,
            @Size(min = 1, max = 100) String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LibraryBranch [id=" + this.id + ", name=" + this.name + ", address=" + this.address + "]";
    }

}
