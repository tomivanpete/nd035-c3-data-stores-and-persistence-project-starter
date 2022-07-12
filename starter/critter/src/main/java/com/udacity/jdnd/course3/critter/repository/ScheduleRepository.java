package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN FETCH s.pets p WHERE p.id = :petId")
    List<Schedule> findAllByPetId(Long petId);

    @Query("SELECT s FROM Schedule s JOIN FETCH s.employees e WHERE e.id = :employeeId")
    List<Schedule> findAllByEmployeeId(Long employeeId);

    @Query("SELECT s FROM Schedule s JOIN FETCH s.pets p WHERE p.owner.id = :customerId")
    List<Schedule> findAllByCustomerId(Long customerId);
}
