package com.intershop.meetingsapp.controller;


import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {
    @Autowired
    MeetingsService meetingsService;

    @PostMapping
    public String createMeeting(@RequestBody Meeting meeting) {
        meetingsService.createMeeting(meeting);
        return "Meeting created successfully";
    }

    @GetMapping
    public List<Meeting> getMeetings() {
        List<Meeting> meetings = meetingsService.getMeetings();
        return meetings;
    }



}
