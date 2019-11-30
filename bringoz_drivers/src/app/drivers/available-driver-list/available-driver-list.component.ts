import {Component, OnInit, DoCheck} from '@angular/core';
import { IDriver } from '../IDriver';
import {UserService} from '../../services/user.service'
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ITimeWindow } from './ITimeWindow';
@Component({

selector:'available-driver-list',
templateUrl:"./available-driver-list.component.html",
styleUrls:['./available-driver-list.component.css'],


})
export class AvailableDriverListComponent implements OnInit,DoCheck
{
    timeWindow:ITimeWindow;
    pageTitle:string ='Available Driver List';
    listFilter:string;
    drivers:IDriver[];
    start:string;
    end:string;



    constructor(private formBuilder: FormBuilder, private userService:UserService){}
      availableDriversListForm = this.formBuilder.group({
        start:[''],
        end:[''],
      })

      onSubmit(){
    
        this.start=this.timeWindow.start;
        this.end=this.timeWindow.end;
        console.log('in onSubmit method!')
         this.userService.getAllAvailableDrivers(this.start,this.end)
         .subscribe(
             response =>{
              console.log('Success!', response)
             error =>console.error('Error!', error)}
         );
             }




  
   ngOnInit():void{
    console.log("In OnInit()")

     this.userService.getAllAvailableDrivers(this.start,this.end).subscribe(
       data=>this.drivers=data ,(err:HttpErrorResponse)=>console.log(err.status)
     )
  }
  ngDoCheck():void{
    console.log("In DoCheck()")

  }

 

}