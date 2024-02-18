import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isLoggedIn: boolean = false;

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.checkLoginStatus();
  }

  checkLoginStatus(): void {
    this.isLoggedIn = this.authService.isAuthenticated(); // Use getLoggedInStatus
  }

  onLoginSuccess(): void {
    this.isLoggedIn = true;
  }

logout(): void {
  console.log("logout fun called");
    sessionStorage.clear();
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }
  
  // }
}
