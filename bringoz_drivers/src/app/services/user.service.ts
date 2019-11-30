import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { IDriver } from '../drivers/IDriver';
import {Observable,throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators'
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8081/api/test/user';
  private adminUrl = 'http://localhost:8081/api/test/admin';
  private createDriverUrl = 'http://localhost:8081/api/driver-service/createDriver';
  private getAllDriversURL = 'http://localhost:8081/api/driver-service/getAllDrivers';
  private getAllAvailableDriversURL = 'http://localhost:8081/api/driver-service/availableDriver';
  private getDriverByIdURL = 'http://localhost:8081/api/driver-service/getDriverById';
  private removeDriverURL = 'http://localhost:8081/api/driver-service/removeDriver';


  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }
  
  createDriverPostRequest(userdata){
     return this.http.post<any>(this.createDriverUrl, userdata );
  }

  getAllDrivers(): Observable<IDriver[]>
  {
   return this.http.get<IDriver[]>(this.getAllDriversURL).pipe(
      catchError(
          (error:HttpErrorResponse)=>
           {
             console.log(error)
             return throwError(error);     
           }
         )
   )
  }

  getAllAvailableDrivers(start, end): Observable<IDriver[]>
  {
    let httpParams = new HttpParams()
    .append("start", start)
    .append("end",end);
   return this.http.get<IDriver[]>(this.getAllAvailableDriversURL, {params: httpParams}).pipe(
      catchError(
          (error:HttpErrorResponse)=>
           {
             console.log(error)
             return throwError(error);     
           }
         )
   )
  }

  getDriverById(id: number): Observable<IDriver> {
    return this.http.get<IDriver>(this.getDriverByIdURL+'/'+id).pipe(
    )
    }

  removeDriver(id) {
      console.log('in remove method');
      return this.http.delete<any>(this.removeDriverURL + '/'+id)
      .subscribe(() => console.log('Deleted successfully'));
    }

}
