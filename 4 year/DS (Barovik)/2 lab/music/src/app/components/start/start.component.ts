import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToBands() {
    this.router.navigateByUrl('/bands');
  }

  goToPersons() {
    this.router.navigateByUrl('/persons')
  }
}
