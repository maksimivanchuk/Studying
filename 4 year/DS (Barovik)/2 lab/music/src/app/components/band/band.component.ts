import { Component, OnInit } from '@angular/core';
import {BandService} from './service/band.service';
import {Band} from './model/Band';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ValidationConstants} from '../../common/validation/validation-constants';
import {Router} from '@angular/router';

@Component({
  selector: 'app-band',
  templateUrl: './band.component.html',
  styleUrls: ['./band.component.css']
})
export class BandComponent implements OnInit {
  bands: Band[] = null;
  copy: Band[] = null;
  edit: Band = null;
  bandInfo: FormGroup;
  isOneBand: boolean = false;
  openForm: boolean = false;

  constructor(private bandService: BandService, private formBuilder: FormBuilder, private router: Router) {
    this.bandInfo = formBuilder.group({
      name: [null, [
        Validators.required,
        Validators.maxLength(ValidationConstants.MAX_LENGTH),
        Validators.minLength(ValidationConstants.MIN_LENGTH),
      ]],
      dateOfCreation: [null],
      numberOfAlbums: [null, [
        Validators.pattern(ValidationConstants.DIGITS)
      ]],
      sales: [null, [
        Validators.pattern(ValidationConstants.FLOAT_NUMBERS)
      ]],
      description: [null]
    });
  }

  ngOnInit() {
    this.getAllBands();
  }

  back() {
    this.router.navigateByUrl("/");
  }

  getAllBands() {
    this.bandService.getAllBands().subscribe((bands: Band[]) => {
      this.bands = bands;
      this.copy = bands;
      this.isOneBand = false;
    });
  }

  getBandById(event: any) {
    const id = event.target.value;
    if (id !== 'empty') {
      this.bandService.getBand(id).subscribe((band: Band) => {
        this.bands = [];
        this.bands.push(band);
        this.isOneBand = true;
      });
    }
  }

  editBand(band: Band) {
    document.documentElement.scrollTop = 0;
    this.edit = band;
    this.openSubmit();
    this.bandInfo.controls['name'].setValue(band.name);
    this.bandInfo.controls['dateOfCreation'].setValue(band.dateOfCreation);
    this.bandInfo.controls['numberOfAlbums'].setValue(band.numberOfAlbums);
    this.bandInfo.controls['sales'].setValue(band.sales);
    this.bandInfo.controls['description'].setValue(band.description);
  }

  deleteBand(band: Band) {
    const id = band.id;
    const accept = confirm("Are you sure?");
    if (accept) {
      this.bandService.deleteBand(id).subscribe((data: any) => {
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
    const dateOfCreation = this.bandInfo.value.dateOfCreation.split("-");
    const date = new Date(dateOfCreation[0], dateOfCreation[1], dateOfCreation[2]);
    if (date >= currentDate || dateOfCreation[0] === "") {
      alert("Invalid date!");
      return;
    } else {
      if (!this.edit) {
        this.bandService.createBand(this.bandInfo.value).subscribe((band: Band) => {
          this.bands.push(band);
          this.openForm = false;
        }, function() {
          alert("Error while creating band!");
        });
      } else {
        let band = this.bandInfo.value;
        band.id = this.edit.id;
        this.bandService.updateBand(band).subscribe((data: any) => {
          alert("Updated!");
          document.location.reload();
        }, function() {
          alert("Error while updating band!");
        });
      }
    }
  }
}
