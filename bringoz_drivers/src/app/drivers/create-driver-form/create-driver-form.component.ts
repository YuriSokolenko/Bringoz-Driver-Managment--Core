import { Component, OnInit } from '@angular/core';
import { IDriver } from '../IDriver';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { AdminComponent } from 'src/app/admin/admin.component';





@Component({
  selector: 'app-create-driver-form',
  templateUrl: './create-driver-form.component.html',
  styleUrls: ['./create-driver-form.component.css']
})
export class CreateDriverFormComponent implements OnInit {
  ngOnInit(): void {
  }

 driver: IDriver;
 id:number;
 firstName:string;
 lastName:string;
 age:number;
 address: string;
 status: string;
 start:string;
 end:string;
 isInMapBounds: boolean;



  constructor(private formBuilder: FormBuilder, private userService : UserService, private router : Router, private adminComponent: AdminComponent) { }
      
      createDriverForm = this.formBuilder.group({
     
        id:[''],
        firstName:[''],
        lastName:[''],
        age:[''],
        address:[''],
        status:['ACTIVE'],
        start:[''],
        end:[''],
        isInMapBounds:['true'],

    })

  onSubmit(){
    console.log(this.createDriverForm.value)
    this.driver=this.createDriverForm.value;
    this.id=this.driver.id;
    this.firstName=this.driver.firstName;
    this.lastName=this.driver.lastName;
    this.age=this.driver.age;
    this.address=this.driver.address;
    this.status=this.driver.status;
    this.start=this.driver.start;
    this.end=this.driver.end;
    this.isInMapBounds=this.driver.isInMapBounds;



    console.log('in onSubmit method!')
     this.userService.createDriverPostRequest(this.createDriverForm.value)
     .subscribe(

         response =>{
           
          console.log('Success!', response)
          this.router.navigate(['/admin']);
          alert('Driver created');
          } )
          this.router.navigate(['/admin']);
         }
        

   onBack(): void {
    this.router.navigate(['/admin']);
  

}
  
}
