package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleMapper {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ScheduleDTO scheduleToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setEmployeeIds(schedule.getEmployees()
                .stream()
                .map(Employee::getId).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets()
                .stream()
                .map(Pet::getId).collect(Collectors.toList()));
        scheduleDTO.setActivities(schedule.getActivities());

        return scheduleDTO;
    }

    public Schedule DTOtoSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        List<Employee> employeeList = new ArrayList<>();
        List<Pet> petList = new ArrayList<>();

        schedule.setId(schedule.getId());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        for (Long employeeId : scheduleDTO.getEmployeeIds()) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            optionalEmployee.ifPresent(employeeList::add);
        }

        for (Long petId : scheduleDTO.getPetIds()) {
            Optional<Pet> optionalPet = petRepository.findById(petId);
            optionalPet.ifPresent(petList::add);
        }

        schedule.setEmployees(employeeList);
        schedule.setPets(petList);

        return schedule;
    }
}
