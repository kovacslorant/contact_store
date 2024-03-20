import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

export class AuthInterceptor implements HttpInterceptor{

  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const jwtToken = localStorage.getItem('jwtToken');

    if (jwtToken) {
      const cloneRequest = request.clone({
        setHeaders: {Authorization: `Bearer ${jwtToken}`}
      })
      return next.handle(cloneRequest);
    }


    return next.handle(request);
  }

}
