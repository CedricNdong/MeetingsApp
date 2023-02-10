package com.intershop.meetingsapp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "meeting")
public class Meeting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "room")
    private String room;
    @Column(name = "date")
    private String date;
    @ElementCollection
    private List<String> students;


}
