package com.grom.music.person.model;

import com.grom.music.band.model.Band;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "band_id", referencedColumnName = "id")
    private Band band;

    private String name;

    private String surname;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "financial_condition")
    private Float financialCondition;

    private String biography;

    public void getCreateDto(CreatePersonDto dto) {
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.birthday = dto.getBirthday();
        this.financialCondition = dto.getFinancialCondition();
        this.biography = dto.getBiography();
    }

    public void getUpdateDto(UpdatePersonDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.birthday = dto.getBirthday();
        this.financialCondition = dto.getFinancialCondition();
        this.biography = dto.getBiography();
    }
}
