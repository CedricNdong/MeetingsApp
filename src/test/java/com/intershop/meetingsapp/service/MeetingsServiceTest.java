package com.intershop.meetingsapp.service;

import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.repository.MeetingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MeetingsService.class})
@ExtendWith(MockitoExtension.class)
class MeetingsServiceTest {
    @Mock
    private MeetingsRepository meetingsRepository;

    @InjectMocks
    private MeetingsService meetingsService;

    @Test
    void testCreateMeeting() {
        ArgumentCaptor<Meeting> meetingArgumentCaptor = ArgumentCaptor.forClass(Meeting.class);


        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());

        when(meetingsRepository.save(any(Meeting.class))).thenReturn(meeting);

        Meeting meeting1 = new Meeting();
        meeting1.setDate("2020-03-01");
        meeting1.setId(1);
        meeting1.setRoom("Room");
        meeting1.setStudents(new ArrayList<>());
        assertSame(meeting, meetingsService.createMeeting(meeting1));
        verify(meetingsRepository).save(meetingArgumentCaptor.capture());
    }

    @Test
    void testGetMeetings() {
        List<Meeting> meetingList = new ArrayList<>();

        when(meetingsRepository.findAll()).thenReturn(meetingList);

        List<Meeting> actualMeetings = meetingsService.getMeetings();
        assertSame(meetingList, actualMeetings);
        assertTrue(actualMeetings.isEmpty());
        verify(meetingsRepository).findAll();
    }

    @Test
    void testGetMeeting() {
        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());

        Meeting meeting2 = new Meeting();
        meeting2.setDate("2020-03-02");
        meeting2.setId(2);
        meeting2.setRoom("Room2");
        meeting2.setStudents(new ArrayList<>());

        List<Meeting> meetingList = new ArrayList<>();
        meetingList.add(meeting);
        meetingList.add(meeting2);
        when(meetingsRepository.findAll()).thenReturn(meetingList);
        assertEquals(2, meetingsService.getMeetings().size());
        verify(meetingsRepository).findAll();
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
        when(meetingsRepository.save((Meeting) any())).thenReturn(meeting1);
        when(meetingsRepository.findById((Integer) any())).thenReturn(ofResult);

        Meeting meeting2 = new Meeting();
        meeting2.setDate("2020-03-01");
        meeting2.setId(1);
        meeting2.setRoom("Room");
        meeting2.setStudents(new ArrayList<>());
        assertSame(meeting1, meetingsService.updateMeeting(1, meeting2));
        verify(meetingsRepository).save((Meeting) any());
        verify(meetingsRepository).findById((Integer) any());
    }

    @Test
    void testDeleteMeeting() {
        doNothing().when(meetingsRepository).deleteById((Integer) any());
        meetingsService.deleteMeeting(1);
        verify(meetingsRepository).deleteById((Integer) any());
    }

    // should throw exception if meeting not found
    @Test
    void testUpdateMeetingException() {
        Meeting meeting = new Meeting();
        meeting.setDate("2020-03-01");
        meeting.setId(1);
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());
        Optional<Meeting> ofResult = Optional.of(meeting);

        when(meetingsRepository.findById(1)).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> meetingsService.getMeetingById(2));



    }



}

