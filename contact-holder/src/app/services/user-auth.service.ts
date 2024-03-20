import {Injectable} from '@angular/core';
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";
@Injectable({
  providedIn: 'root'
})

export class UserAuthService {
  private timer: any;
  decodedToken: any;


  constructor(private router: Router) {
    this.checkTokenExpiration();
    this.timer = setInterval(() => {
      this.checkTokenExpiration();
    }, 300000);

  }


  checkTokenExpiration(){
    let token  = this.getToken();
    if(token) {
      this.decodedToken = jwtDecode(token)

      const expirationTime = this.decodedToken.exp;
      const currentTime = Math.floor(Date.now()/1000);

      if(expirationTime < currentTime){
        this.clear();
        this.router.navigateByUrl('/**');
      }
    }
  }


  public setRole(role: string){
    localStorage.setItem('r', JSON.stringify(role))
  }
  public getRole() {
    return JSON.parse(localStorage.getItem('r') ?? 'null');
  }

  setEmail(email: string){
    localStorage.setItem('e', JSON.stringify(email))
  }


  getEmail(): string{

    return JSON.parse(localStorage.getItem('e') ?? 'null' )
  }
  setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  getToken(): string {
    // @ts-ignore
    return localStorage.getItem("jwtToken");
  }

  clear() {
    localStorage.clear();
  }

  isLoggedIn() {
    return this.getRole() && this.getToken();
  }

}
