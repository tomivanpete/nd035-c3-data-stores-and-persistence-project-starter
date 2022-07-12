package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Long save(Employee employee) {
        return employeeRepository.save(employee).getId();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        }
    }

    public List<Employee> findAvailableEmployees(EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employeesWithMatchingDay = employeeRepository.findAllByDaysAvailable(employeeRequestDTO.getDate().getDayOfWeek());

        List<Employee> availableEmployees = employeesWithMatchingDay.stream()
                .filter(employee -> employee.getSkills().containsAll(employeeRequestDTO.getSkills()))
                .collect(Collectors.toList());

        return availableEmployees;
    }
}
