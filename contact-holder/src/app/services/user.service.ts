import {HttpClient} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {UserLoginModel} from "../models/user-login.model";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {UserCreateCommandModel} from "../models/user-create-command.model";

const BASE_URL = "http://localhost:8080"

@Injectable({
  providedIn: 'root'
})

export class UserService {
  constructor(private http: HttpClient,
             ) {

  }

  login(data: UserLoginModel): Observable<any>{
    return this.http.post(BASE_URL + '/auth/login', data, {responseType:"text"});
  }

  saveUser(data: UserCreateCommandModel) {
    return this.http.post(BASE_URL + '/users/register', data)
  }
}
