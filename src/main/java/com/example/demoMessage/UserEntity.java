package com.example.demoMessage;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by Tanmoy Mandal on 12/27/2016.
 */
@UniqueKey(columnNames = {"name"}, message = "This Username is already used")
//@UniqueKeyJPA(columnNames = {"name"},message = "This Username is already used")
//@UniqueKey(columnName = "name",className = UserEntity.class, message = "This Name is Already Used")
@Entity
@Table(name = "UserEntity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Name Cannot Be empty")
    @Column(unique = true)
//    @UniqueKey(columnName = "name",className = UserEntity.class, message = "This Name is Already Used")
    private String name;
    @Min(value = 18, message = "Age Cannot Be Less Than 18")
//    @UniqueKey(columnName = "age",className = UserEntity.class, message = "This Age is Already Used")
    private int age;

    public UserEntity() {
    }

    public UserEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
