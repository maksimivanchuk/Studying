package com.grom.music.person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonDto {
    private long id;
    private long bandId;
    private String name;
    private String surname;
    private Date birthday;
    private Float financialCondition;
    private String biography;
}