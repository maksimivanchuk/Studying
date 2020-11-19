import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Person} from './model/Person';
import {PersonService} from './service/person.service';
import {ValidationConstants} from '../../common/validation/validation-constants';
import {BandService} from '../band/service/band.service';
import {Band} from '../band/model/Band';
import {Router} from '@angular/router';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  persons: Person[] = null;
  copy: Person[] = null;
  edit: Person = null;
  personInfo: FormGroup;
  isOnePerson: boolean = false;
  openForm: boolean = false;
  allBands: Band[] = null;

  constructor(private personService: PersonService, private bandService: BandService,
              private formBuilder: FormBuilder, private router: Router) {
    this.personInfo = formBuilder.group({
      bandId: [null, [
        Validators.required
      ]],
      name: [null, [
        Validators.required,
        Validators.maxLength(ValidationConstants.MAX_LENGTH),
        Validators.minLength(ValidationConstants.MIN_LENGTH),
        Validators.pattern(ValidationConstants.LETTERS)
      ]],
      surname: [null, [
        Validators.required,
        Validators.maxLength(ValidationConstants.MAX_LENGTH),
        Validators.minLength(ValidationConstants.MIN_LENGTH),
        Validators.pattern(ValidationConstants.LETTERS)
      ]],
      birthday: [null],
      financialCondition: [null, [
        Validators.pattern(ValidationConstants.FINANCIAL)
      ]],
      biography: [null]
    });
  }

  ngOnInit() {
    this.getAllPersons();
    this.getAllBands();
  }

  back() {
    this.router.navigateByUrl("/");
  }

  getAllPersons() {
    this.personService.getAllPersons().subscribe((persons: Person[]) => {
      this.persons = persons;
      this.copy = persons;
      this.isOnePerson = false;
    });
  }

  getAllBands() {
    this.bandService.getAllBands().subscribe((bands: Band[]) => {
      this.allBands = bands;
    });
  }

  getPersonById(event: any) {
    const id = event.target.value;
    if (id !== 'empty') {
      this.personService.getPerson(id).subscribe((person: Person) => {
        this.persons = [];
        this.persons.push(person);
        this.isOnePerson = true;
      });
    }
  }

  editPerson(person: Person) {
    document.documentElement.scrollTop = 0;
    this.edit = person;
    this.openSubmit();
    this.personInfo.controls['bandId'].setValue(person.band.id);
    this.personInfo.controls['name'].setValue(person.name);
    this.personInfo.controls['surname'].setValue(person.surname);
    this.personInfo.controls['birthday'].setValue(person.birthday);
    this.personInfo.controls['financialCondition'].setValue(person.financialCondition);
    this.personInfo.controls['biography'].setValue(person.biography);
  }

  deletePerson(person: Person) {
    const id = person.id;
    const accept = confirm("Are you sure?");
    if (accept) {
      this.personService.deletePerson(id).subscribe((data: any) => {
        alert("Deleted!");
        document.location.reload();
      });
    }
  }

  openSubmit() {
    this.openForm = true;
  }

  submit() {
    const currentDate = new Date();
    const birthday = this.personInfo.value.birthday.split("-");
    const date = new Date(birthday[0], birthday[1], birthday[2]);
    if (date >= currentDate || birthday[0] === "") {
      alert("Invalid date!");
      return;
    } else {
      let person = this.personInfo.value;
      person.bandId = parseInt(person.bandId);
      if (!this.edit) {
        this.personService.createPerson(person).subscribe((person: Person) => {
          this.persons.push(person);
          this.openForm = false;
        }, function() {
          alert("Error while creating person!");
        });
      } else {
        person.id = this.edit.id;
        this.personService.updatePerson(person).subscribe((data: any) => {
          alert("Updated!");
          document.location.reload();
        }, function() {
          alert("Error while updating person!");
        });
      }
    }
  }
}
