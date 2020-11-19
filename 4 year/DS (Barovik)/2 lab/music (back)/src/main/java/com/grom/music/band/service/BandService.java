package com.grom.music.band.service;

import com.grom.music.band.model.Band;
import com.grom.music.band.model.CreateBandDto;
import com.grom.music.band.repository.BandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {
    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Band> getAllBands() {
        return bandRepository.findAll();
    }

    public Band getBand(long id) {
        return bandRepository.findById(id).get();
    }

    public void delete(long id) {
        bandRepository.deleteById(id);
    }

    public Band create(CreateBandDto dto) {
        return bandRepository.save(modelMapper.map(dto, Band.class));
    }

    public void update(Band band) {
        bandRepository.save(band);
    }
}
