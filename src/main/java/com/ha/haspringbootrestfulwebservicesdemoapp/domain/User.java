package com.ha.haspringbootrestfulwebservicesdemoapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// Static filtering in class level
// @JsonIgnoreProperties({"id", "birth_date"})
@Entity(name = "user_details")
public class User {

    // @JsonIgnore // Static filtering in property level
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 letters.") // Validations
    @JsonProperty("user_name") // Customize property names
    private String name;
    @Past(message = "Birth date should be a past date.") // Validations
    @JsonProperty("birth_date") // Customize property names
    // @JsonIgnore // Static filtering in property level
    private LocalDate birthDate;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + '}';
    }
}
