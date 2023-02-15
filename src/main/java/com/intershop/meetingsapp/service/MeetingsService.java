package com.intershop.meetingsapp.service;

import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.repository.MeetingsRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class MeetingsService {


    private final MeetingsRepository meetingsRepository;

    public MeetingsService(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    public Meeting createMeeting(Meeting meeting) {
        return meetingsRepository.save(meeting);
    }

    public List<Meeting> getMeetings() {
        return meetingsRepository.findAll();
    }

    public Meeting getMeetingById(Integer id) {
        return meetingsRepository.findById(id).orElse(null);
    }


    public Meeting updateMeeting(Integer id,Meeting meeting) {
        Meeting existingMeeting = meetingsRepository.findById(id).orElse(null);
        existingMeeting.setRoom(meeting.getRoom());
        existingMeeting.setDate(meeting.getDate());
        existingMeeting.setStudents(meeting.getStudents());
        return meetingsRepository.save(existingMeeting);
    }

    public void deleteMeeting(Integer id) {
        meetingsRepository.deleteById(id);
    }




}
