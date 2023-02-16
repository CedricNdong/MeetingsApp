package com.intershop.meetingsapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intershop.meetingsapp.model.Meeting;
import com.intershop.meetingsapp.repository.MeetingsRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class MeetingIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MeetingsRepository meetingRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateMeeting() throws Exception {
        //
        Meeting meeting = new Meeting();
        meeting.setDate("2011-11-11");
        meeting.setRoom("Test Integration Room");
        meeting.setStudents(Arrays.asList("Test Student 1", "Test Student 2"));

        // when and then
        ResultActions response = this.mockMvc.perform(post("/meetings")
                .content(objectMapper.writeValueAsString(meeting))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(meeting.getDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.room", CoreMatchers.is(meeting.getRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", CoreMatchers.is(meeting.getStudents())))
                .andDo(MockMvcResultHandlers.print());// Arrange

    }

    @Test
    public void testGetMeetingById() throws Exception {
        // given
        Meeting meeting = new Meeting();
        meeting.setDate("2023-02-16");
        meeting.setRoom("Test Room");
        meeting.setStudents(Arrays.asList("John Inter", "Mama Nana"));

        Meeting savedMeeting = meetingRepository.save(meeting);

        // when and then
        ResultActions response = this.mockMvc.perform(get("/meetings/{id}", savedMeeting.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(savedMeeting.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(meeting.getDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.room", CoreMatchers.is(meeting.getRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", CoreMatchers.is(meeting.getStudents())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetAllMeetings() throws Exception {
        // given
        Meeting meeting1 = new Meeting();
        meeting1.setDate("2023-02-15");
        meeting1.setRoom("Test Room 1");
        meeting1.setStudents(List.of("Jacke Priso "));
        meetingRepository.save(meeting1);

        Meeting meeting2 = new Meeting();
        meeting2.setDate("2023-02-16");
        meeting2.setRoom("Test Room 2");
        meeting2.setStudents(Arrays.asList("Bob Smith", "Alice Smith"));
        meetingRepository.save(meeting2);

        // when and then
        ResultActions response = this.mockMvc.perform(get("/meetings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date", CoreMatchers.is(meeting1.getDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].room", CoreMatchers.is(meeting1.getRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].students", CoreMatchers.is(meeting1.getStudents())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date", CoreMatchers.is(meeting2.getDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].room", CoreMatchers.is(meeting2.getRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].students", CoreMatchers.is(meeting2.getStudents())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testUpdateMeeting() throws Exception {
        // given
        Meeting meeting = new Meeting();
        meeting.setDate("2023-02-17");
        meeting.setRoom("Test Room");
        meeting.setStudents(Arrays.asList("John Doe", "Jane Doe"));

        Meeting savedMeeting = meetingRepository.save(meeting);

        // update meeting
        savedMeeting.setDate("2023-02-18");
        savedMeeting.setRoom("Updated Room");
        savedMeeting.setStudents(Arrays.asList("Bob Smith", "Alice Smith"));

        // when and then
        ResultActions response = this.mockMvc.perform(put("/meetings/{id}", savedMeeting.getId())
                .content(objectMapper.writeValueAsString(savedMeeting))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(savedMeeting.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(savedMeeting.getDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.room", CoreMatchers.is(savedMeeting.getRoom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", CoreMatchers.is(savedMeeting.getStudents())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteMeeting() throws Exception {
        // given
        Meeting meeting = new Meeting();
        meeting.setDate("2023-02-19");
        meeting.setRoom("Test Room");
        meeting.setStudents(Arrays.asList("John Doe", "Jane Doe"));

        Meeting savedMeeting = meetingRepository.save(meeting);

        // when and then
        ResultActions response = this.mockMvc.perform(delete("/meetings/{id}", savedMeeting.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
        .andDo(MockMvcResultHandlers.print());

        // verify
        assertEquals(Optional.empty(), meetingRepository.findById(savedMeeting.getId()));

    }






}
