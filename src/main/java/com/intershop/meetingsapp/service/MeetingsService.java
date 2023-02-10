package com.intershop.meetingsapp.service;

import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.repository.MeetingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
