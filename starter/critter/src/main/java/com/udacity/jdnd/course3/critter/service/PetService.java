package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Long save(Pet pet) {
        pet = petRepository.save(pet);

        //TODO: Refactor for more domain specific exception
        Customer customer = customerRepository.findById(pet.getOwner().getId())
                .orElseThrow(RuntimeException::new);

        if (customer.getPets() == null) {
            List<Pet> petList = new ArrayList<>();
            customer.setPets(petList);
        }

        customer.getPets().add(pet);
        customerRepository.save(customer);

        return pet.getId();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByOwnerId(Long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
