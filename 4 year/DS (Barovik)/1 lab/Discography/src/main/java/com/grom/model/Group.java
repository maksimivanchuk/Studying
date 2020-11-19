package com.grom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private String name;
    private String country;
    private String genre;
    private List<Album> albums;
}
