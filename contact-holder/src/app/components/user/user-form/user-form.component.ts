import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {

  userForm!: FormGroup

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private userService: UserService) {

    this.userForm = this.formBuilder.group({
      firstName:['', Validators.required],
      lastName:['', Validators.required],
      email:['', Validators.required],
      password:['', Validators.required],
      roleType:['ROLE_USER'],
    })
  }

  saveUser(){
    this.userService.saveUser(this.userForm.value).subscribe({
      next: value => {
      },
      error: (err) => {
        console.log(err);

      },
    })
  }
  clearForm(): void {
    this.userForm.reset();
  }


}
