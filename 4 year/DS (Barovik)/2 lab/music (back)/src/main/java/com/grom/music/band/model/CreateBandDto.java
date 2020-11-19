package com.grom.music.band.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBandDto {
    private String name;
    private Date dateOfCreation;
    private Integer numberOfAlbums;
    private Float sales;
    private String description;
}
