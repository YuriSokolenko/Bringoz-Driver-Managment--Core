import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';


import { httpInterceptorProviders } from './auth/auth-interceptor';
import { DriversComponent } from './drivers/drivers.component';
import { CreateDriverFormComponent } from './drivers/create-driver-form/create-driver-form.component';
import { RouterModule } from '@angular/router';
import { DriverListComponent } from './drivers/driver-list/driver-list.component';
import { DriverDetailComponent } from './drivers/driver-detail/driver-detail.component';
import { UpdateDriverFormComponent } from './drivers/update-driver-form/update-driver-form.component';
import { DriverFilterPipe } from './drivers/driver-list/driver-filter.pipe';
import { AvailableDriverListComponent } from './drivers/available-driver-list/available-driver-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    DriversComponent,
    DriverFilterPipe,
    CreateDriverFormComponent,
    DriverListComponent,
    DriverDetailComponent,
    UpdateDriverFormComponent,
    AvailableDriverListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule, 


      RouterModule.forRoot([
        
        { path: 'driver-list', component: DriverListComponent },
        { path: 'available-driver-list', component: AvailableDriverListComponent },
        { path: 'driver/:id', component: DriverDetailComponent },
        { path: 'admin',  component: AdminComponent},
        { path: 'drivers',  component: DriversComponent},
        { path: 'driver-detail/:id',  component: DriversComponent},
        { path: 'create-driver-form', component: CreateDriverFormComponent },
        { path: 'update-driver-form', component: UpdateDriverFormComponent },
        { path: 'update-driver-form/:id', component: UpdateDriverFormComponent},
        { path: 'login', component: LoginComponent},
        { path: '', redirectTo: 'login', pathMatch: 'full' },
        { path: '**', redirectTo: 'login', pathMatch: 'full'},
      ])
    ],
  providers: [httpInterceptorProviders,AdminComponent, LoginComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
