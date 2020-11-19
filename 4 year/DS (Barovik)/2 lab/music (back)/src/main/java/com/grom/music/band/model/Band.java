package com.grom.music.band.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_creation")
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

    @Column(name = "number_of_albums")
    private Integer numberOfAlbums;

    private Float sales;

    private String description;
}
