import {Component, OnInit, DoCheck} from '@angular/core';
import { IDriver } from '../IDriver';
import {UserService} from '../../services/user.service'
import { HttpErrorResponse } from '@angular/common/http';
@Component({

selector:'driver-list',
templateUrl:"./driver-list.component.html",
styleUrls:['./driver-list.component.css'],


})
export class DriverListComponent implements OnInit,DoCheck
{
    pageTitle:string ='Driver List';
    listFilter:string;
    drivers:IDriver[];
    constructor(private userService:UserService){
    }
    
  
   ngOnInit():void{
    console.log("In OnInit()")

     this.userService.getAllDrivers().subscribe(
       data=>this.drivers=data ,(err:HttpErrorResponse)=>console.log(err.status)
     )
  }
  ngDoCheck():void{
    console.log("In DoCheck()")

  }

}