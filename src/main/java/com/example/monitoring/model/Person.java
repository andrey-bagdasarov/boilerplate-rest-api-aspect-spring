package com.example.monitoring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long Id;

  private String firstName;
  private String lastName;
  private int age;


  public Person() {}

  public Person(String fName, String sName, int age) {
    firstName = fName;
    lastName = sName;
    this.age = age;
  }

  public long getId() {
    return Id;
  }

  public void setId(long id) {
    Id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
