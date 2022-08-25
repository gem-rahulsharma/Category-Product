package com.rahul.demo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int categoryId;
    String categoryName;

    String description;
    @CreationTimestamp
    LocalDate createDate;
    @UpdateTimestamp
    LocalDate upDate;
    Boolean isActive;
    Boolean isDeleted;

}
