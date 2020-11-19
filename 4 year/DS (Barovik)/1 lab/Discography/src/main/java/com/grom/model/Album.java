package com.grom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private Integer id;
    private String album;
    private Date releaseDate;
    private Double length;
}
