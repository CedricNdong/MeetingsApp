package com.intershop.meetingsapp.controller;


import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:35169")
@RestController
@RequestMapping("/meetings")
public class MeetingsController {
    @Autowired
    private MeetingsService meetingsService;

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

    @GetMapping("/{id}")
    public Meeting getMeetingById(@PathVariable Integer id) {
        return meetingsService.getMeetingById(id);
    }

    @PutMapping("/{id}")
    public Meeting updateMeeting(@PathVariable Integer id, @RequestBody Meeting meeting) {
        return meetingsService.updateMeeting(id, meeting);
    }

    @DeleteMapping("/{id}")
    public void deleteMeeting(@PathVariable Integer id) {
        meetingsService.deleteMeeting(id);
    }



}
