package com.intershop.meetingsapp.controller;

import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.repository.MeetingsRepository;
import com.intershop.meetingsapp.service.MeetingsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MeetingsControllerTest {

    @Test
    void testCreateMeeting() {
        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());
        MeetingsRepository meetingsRepository = mock(MeetingsRepository.class);

        when(meetingsRepository.save((Meeting) any())).thenReturn(meeting);
        MeetingsController meetingsController = new MeetingsController(new MeetingsService(meetingsRepository));

        Meeting meeting1 = new Meeting();
        meeting1.setDate("2020-03-01");
        meeting1.setId(1);
        meeting1.setRoom("Room");
        meeting1.setStudents(new ArrayList<>());
        assertEquals("Meeting created successfully", meetingsController.createMeeting(meeting1));
        verify(meetingsRepository).save((Meeting) any());
    }

    @Test
    void testCreateMeeting2() {
        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());
        MeetingsService meetingsService = mock(MeetingsService.class);
        when(meetingsService.createMeeting((Meeting) any())).thenReturn(meeting);
        MeetingsController meetingsController = new MeetingsController(meetingsService);

        Meeting meeting1 = new Meeting();
        meeting1.setDate("2020-03-01");
        meeting1.setId(1);
        meeting1.setRoom("Room");
        meeting1.setStudents(new ArrayList<>());
        assertNotEquals("Meeting created unsuccessfully", meetingsController.createMeeting(meeting1));
        verify(meetingsService).createMeeting((Meeting) any());
    }

    @Test
    void testGetMeetings() {

        MeetingsRepository meetingsRepository = mock(MeetingsRepository.class);
        ArrayList<Meeting> meetingList = new ArrayList<>();
        when(meetingsRepository.findAll()).thenReturn(meetingList);
        List<Meeting> actualMeetings = (new MeetingsController(new MeetingsService(meetingsRepository))).getMeetings();
        assertSame(meetingList, actualMeetings);
        assertTrue(actualMeetings.isEmpty());
        verify(meetingsRepository).findAll();
    }

    @Test
    void testGetMeetings2() {

        MeetingsService meetingsService = mock(MeetingsService.class);
        ArrayList<Meeting> meetingList = new ArrayList<>();
        when(meetingsService.getMeetings()).thenReturn(meetingList);
        List<Meeting> actualMeetings = (new MeetingsController(meetingsService)).getMeetings();
        assertSame(meetingList, actualMeetings);
        assertTrue(actualMeetings.isEmpty());
        verify(meetingsService).getMeetings();
    }
    @Test
    void testGetMeetingById() {
        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());
        MeetingsRepository meetingsRepository = mock(MeetingsRepository.class);
        when(meetingsRepository.findById((Integer) any())).thenReturn(Optional.of(meeting));
        assertSame(meeting, (new MeetingsController(new MeetingsService(meetingsRepository))).getMeetingById(1));
        verify(meetingsRepository).findById((Integer) any());
    }


    @Test
    void testUpdateMeeting() {
        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());
        Optional<Meeting> ofResult = Optional.of(meeting);

        Meeting meeting1 = new Meeting();
        meeting1.setDate("2020-03-01");
        meeting1.setId(1);
        meeting1.setRoom("Room");
        meeting1.setStudents(new ArrayList<>());
        MeetingsRepository meetingsRepository = mock(MeetingsRepository.class);
        when(meetingsRepository.save((Meeting) any())).thenReturn(meeting1);
        when(meetingsRepository.findById((Integer) any())).thenReturn(ofResult);
        MeetingsController meetingsController = new MeetingsController(new MeetingsService(meetingsRepository));

        Meeting meeting2 = new Meeting();
        meeting2.setDate("2020-03-01");
        meeting2.setId(1);
        meeting2.setRoom("Room");
        meeting2.setStudents(new ArrayList<>());
        assertSame(meeting1, meetingsController.updateMeeting(1, meeting2));
        verify(meetingsRepository).save((Meeting) any());
        verify(meetingsRepository).findById((Integer) any());
    }

    @Test
    void testDeleteMeeting() {
        MeetingsRepository meetingsRepository = mock(MeetingsRepository.class);
        doNothing().when(meetingsRepository).deleteById((Integer) any());
        (new MeetingsController(new MeetingsService(meetingsRepository))).deleteMeeting(1);
        verify(meetingsRepository).deleteById((Integer) any());
    }

}

