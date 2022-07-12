package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Long save(Schedule schedule) {
        return scheduleRepository.save(schedule).getId();
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findAllByPetId(Long petId) {
        return scheduleRepository.findAllByPetId(petId);
    }

    public List<Schedule> findAllByEmployeeId(Long employeeId) {
        //TODO Refactor
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(RuntimeException::new);

        return scheduleRepository.findAllByEmployeeId(employeeId);
    }

    public List<Schedule> findAllByCustomerId(Long customerId) {
        return scheduleRepository.findAllByCustomerId(customerId);
    }
}
