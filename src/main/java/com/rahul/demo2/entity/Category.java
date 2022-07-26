package com.rahul.demo2.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int categoryId;
    String categoryName;

    String description;
    @CreationTimestamp
    Date createDate;
    @UpdateTimestamp
    Date upDate;
    Boolean isActive;
    Boolean isDeleted;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, Date createDate, Date upDate, Boolean isActive, Boolean isDeleted) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.createDate = createDate;
        this.upDate = upDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", upDate=" + upDate +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
