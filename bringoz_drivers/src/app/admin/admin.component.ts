import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';



@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  board: string;
  errorMessage: string;

  constructor(private userService: UserService,private router: Router, private login: LoginComponent) { }

  // createCompany(, purchaseCouponURL){
  //   this._adminService.loginPostRequest(testCoupon, purchaseCouponURL);
  DriverCreateForm:boolean = false;
  // }
  

  toggleCreateDriverForm():void
  {
    
    this.DriverCreateForm=!this.DriverCreateForm;
  }

  ngOnInit() {
    this.userService.getAdminBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }
}
