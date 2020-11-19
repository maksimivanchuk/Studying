package com.grom.music.band.repository;

import com.grom.music.band.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    List<Band> findAll();
}
