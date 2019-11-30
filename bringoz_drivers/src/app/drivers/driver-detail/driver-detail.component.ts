import { Component, OnInit, OnDestroy } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router';
import { IDriver } from '../IDriver';
import { UserService } from '../../services/user.service';
import { Observable } from 'rxjs';


@Component({
    templateUrl: './driver-detail.component.html'
})
export class DriverDetailComponent implements OnInit{
    constructor(private _router:Router,private _route:ActivatedRoute,private userSrv:UserService){

    }
    pageTitle: string = 'Driver Detail';
    driver:IDriver
    id:number;

  
   ngOnInit(){
            //alert(" ngOnInit ")
             this.id= +this._route.snapshot.paramMap.get('id')
              console.log("id:"+this.id);  
             this.userSrv.getDriverById(this.id).subscribe(data=>this.driver=data)  
            
            
      
   } 
   onBack(): void {
    this._router.navigate(['/driver-list']);

}



onRemoveDriver(){
  console.log("Removing driver: " +this.driver + " with ID: " + this.driver.id);
  this.userSrv.removeDriver( this.id);
this._router.navigate(['/driver-list']);
}


}