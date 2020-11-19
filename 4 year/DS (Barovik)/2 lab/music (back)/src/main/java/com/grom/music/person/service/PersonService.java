package com.grom.music.person.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grom.music.band.model.Band;
import com.grom.music.band.service.BandService;
import com.grom.music.person.model.CreatePersonDto;
import com.grom.music.person.model.Person;
import com.grom.music.person.model.UpdatePersonDto;
import com.grom.music.person.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BandService bandService;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPerson(long id) {
        return personRepository.findById(id).get();
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }

    public Person create(CreatePersonDto dto) {
        Person person = new Person();
        person.getCreateDto(dto);
        Band band = bandService.getBand(dto.getBandId());
        person.setBand(band);
        return personRepository.save(person);
    }

    public void update(UpdatePersonDto dto) {
        Person person = new Person();
        person.getUpdateDto(dto);
        Band band = bandService.getBand(dto.getBandId());
        person.setBand(band);
        personRepository.save(person);
    }
}
