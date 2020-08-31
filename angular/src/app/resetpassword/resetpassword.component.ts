import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../register/services/register.service';
import { Router } from '@angular/router';
import { Visitor } from '../shared/backendModels/interfaces';
import { Observable } from 'rxjs';
import { LoginService } from '../form-login/components/login/services/login.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  reset$: Observable<boolean>;

  constructor(private registerService: RegisterService, private loginService: LoginService,
    private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
  }

  submitPassword(formValue) {
    const visitor: Visitor = new Visitor();
    visitor.username = formValue.username;
    visitor.password = formValue.password;

    this.registerService.reset(visitor);

    this.router.navigate(['Formlogin']);
  }


}
