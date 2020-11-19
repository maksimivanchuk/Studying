package com.grom.music.band.controller;

import com.grom.music.band.model.Band;
import com.grom.music.band.model.CreateBandDto;
import com.grom.music.band.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BandController {
    @Autowired
    private BandService bandService;

    @GetMapping("bands")
    public List<Band> allBands() {
        return bandService.getAllBands();
    }

    @GetMapping("bands/{id}")
    public Band getBand(@PathVariable long id) {
        return bandService.getBand(id);
    }

    @DeleteMapping("bands/{id}")
    public void deleteBand(@PathVariable long id) {
        bandService.delete(id);
    }

    @PostMapping("bands")
    public Band createBand(@RequestBody CreateBandDto dto) {
        return bandService.create(dto);
    }

    @PutMapping("bands")
    public void updateBand(@RequestBody Band band) {
        bandService.update(band);
    }
}
