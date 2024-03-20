import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginFormComponent} from "./components/user/login-form/login-form.component";
import {UserFormComponent} from "./components/user/user-form/user-form.component";

const routes: Routes = [
  {path:'login-form', component: LoginFormComponent},
  {path:'user-form', component: UserFormComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
