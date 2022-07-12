package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerMapper {

    @Autowired
    private PetRepository petRepository;

    public CustomerDTO customerToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        if (customer.getPets() != null && !customer.getPets().isEmpty()) {
            ArrayList<Long> petIds = new ArrayList<>();
            customer.getPets().forEach(pet -> petIds.add(pet.getId()));
            customerDTO.setPetIds(petIds);
        }

        return customerDTO;
    }

    public Customer DTOtoCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        if (customerDTO.getPetIds() != null && !customerDTO.getPetIds().isEmpty()) {
            ArrayList<Pet> petList = new ArrayList<>();
            customerDTO.getPetIds().forEach(petId -> {
                Pet pet = petRepository.findById(petId).orElse(null);
                petList.add(pet);
            });
            customer.setPets(petList);
        }

        return customer;
    }
}
