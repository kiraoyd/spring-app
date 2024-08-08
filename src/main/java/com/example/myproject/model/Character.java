package com.example.myproject.model;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// TODO: working on this assuming I'll have my postgres DB setup soon
@Entity //specifies this class maps to a DB table
@Table(name = "characters")
public class Characters {
    @Id //specifies primary key of entity
    @GeneratedValue(strategy = GenerationType.AUTO) //specifies generation strategies for primary keys

    //Set up data members to reflect table attributes
    private Long id;
    private String name;
    private int powerLevel;
    private String elementalType;
    private String magicType;
    private String personality;


    //Establish public methods for the class
    public Characters() {}  //default constructor

    //constructor with args
    public Characters(Long id, String name, int power, String elemental, String magic, String personality) {
        this.id = id;
        this.name = name;
        this.powerLevel = power;
        this.elementalType = elemental;
        this.magicType = magic;
        this.personality = personality;
    }

    //getters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name; //'this' keyword specifies name as a class instance variable
    }





}