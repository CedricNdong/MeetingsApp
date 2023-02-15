package com.intershop.meetingsapp.repository;

import com.intershop.meetingsapp.model.Meeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
class MeetingsRepositoryTest {

    @Autowired
    private MeetingsRepository meetingsRepository;

    @Test
    void save(){
        //Arrange
        Meeting meeting = new Meeting();
        meeting.setRoom("Room save");
        meeting.setDate("2020-03-01");
        meeting.setStudents(new ArrayList<>());
        //Act
        Meeting savedMeeting = meetingsRepository.save(meeting);
        //Assert
        assertNotEquals(0, savedMeeting.getId());
    }

    @Test
    void findAll() {
        //Arrange
        Meeting meeting = new Meeting();
        meeting.setRoom("Room findall");
        meeting.setDate("2020-03-01");
        meeting.setStudents(new ArrayList<>());
        //Act
        List<Meeting> allMeeting = meetingsRepository.findAll();
        //Assert
        assertNotEquals(0, allMeeting.size());
    }

    @Test
    void findById() {
        //Arrange
        Meeting meeting = new Meeting();
        meeting.setRoom("Room findbyid");
        meeting.setDate("2020-03-01");
        meeting.setStudents(new ArrayList<>());
        //Act
        Meeting foundMeeting = meetingsRepository.findById(1).orElse(null);
        //Assert
        assertEquals(null, foundMeeting);
    }

    @Test
    void update() {
        //Arrange
        Meeting meeting = new Meeting();
        meeting.setRoom("Room toUpdate3");
        meeting.setDate("dfhfhfg");
        meeting.setStudents(new ArrayList<>());
        //Act
        meetingsRepository.save(meeting);
        Meeting existingMeeting = meetingsRepository.findById(meeting.getId()).orElse(null);
        existingMeeting.setRoom("Room update3");
        meetingsRepository.save(existingMeeting);
        //Assert
        assertEquals("Room update3", existingMeeting.getRoom());
    }

    @Test
    void delete() {
        //Arrange
        Meeting meeting = new Meeting();
        meeting.setRoom("Room toDelete");
        meeting.setDate("2020-03-01");
        meeting.setStudents(new ArrayList<>());
        //Act
        meetingsRepository.save(meeting);
        meetingsRepository.deleteById(meeting.getId());
        Meeting deletedMeeting = meetingsRepository.findById(meeting.getId()).orElse(null);
        //Assert
        assertEquals(null, deletedMeeting);
    }


}