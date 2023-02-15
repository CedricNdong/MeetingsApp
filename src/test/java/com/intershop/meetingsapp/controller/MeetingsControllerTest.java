/*
package com.intershop.meetingsapp.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.service.MeetingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@WebMvcTest
class MeetingsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MeetingsService meetingsService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createMeeting() throws Exception {
        Meeting meeting = new Meeting();
        meeting.setId(1);
        meeting.setDate("2020-03-01");
        meeting.setRoom("Room");
        meeting.setStudents(new ArrayList<>());

        when(meetingsService.createMeeting(any(Meeting.class))).thenReturn(meeting);

        this.mockMvc.perform(post("/meetings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(meeting)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.date", is("2020-03-01")))
                .andExpect(jsonPath("$.room", is("Room")))
                .andExpect(jsonPath("$.students", is(new ArrayList<>())));


    }


    @Test
    void getMeetings() {
    }

    @Test
    void getMeetingById() {
    }

    @Test
    void updateMeeting() {
    }

    @Test
    void deleteMeeting() {
    }
}
 */
