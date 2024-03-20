import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import {UserAuthService} from "../../../services/user-auth.service";
import {Router} from "@angular/router";
import {UserLoginModel} from "../../../models/user-login.model";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

  logInForm!: FormGroup;
  errorMessage: string = '';

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private userAuthService: UserAuthService,
              private router: Router
              ) {
    this.logInForm = this.formBuilder.group({
      email:['', Validators.required],
      password:['', Validators.required]
    })
  }


  logIn() {
    const loginData: UserLoginModel = {
      email: this.logInForm.get('email')?.value,
      password: this.logInForm.get('password')?.value
    }
    this.userService.login(loginData).subscribe({
      next: (token) => {
        console.log(token);
        const parsedToken = JSON.parse(token)
        this.userAuthService.setRole(parsedToken.userDetailsItem.role)
        this.userAuthService.setToken(parsedToken.jwtToken)
        this.userAuthService.setEmail(parsedToken.userDetailsItem.email)
      },
      error: (err: { status: number; }) => {
        if (err.status === 400) {
          this.errorMessage = 'Invalid username or password';
        }
      },
      // complete: () => this.router.navigate(["/**"]),
    } )
  }


}
