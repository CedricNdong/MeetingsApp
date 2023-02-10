package com.intershop.meetingsapp.repository;

import com.intershop.meetingsapp.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsRepository extends JpaRepository<Meeting, Integer> {

}

