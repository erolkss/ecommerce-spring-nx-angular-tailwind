import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Oauth2Service } from '../../auth/oauth2.service';

@Component({
  selector: 'ecom-navbar',
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {

  oAuth2Service = inject(Oauth2Service);

  connectedUserQuery = this.oAuth2Service.connectedUserQuery;


  login() {
    this.closeDropDownMenu();
    this.oAuth2Service.login();
  }

  logout() {
    this.closeDropDownMenu();
    this.oAuth2Service.logout();
  }

  isConnected(): boolean {
    return this.connectedUserQuery?.status() === 'success'
      && this.connectedUserQuery?.data()?.email !== this.oAuth2Service.notConnected;
  }

  closeDropDownMenu() {
    const bodyElement = document.activeElement as HTMLBodyElement;
    if (bodyElement) {
      bodyElement.blur();
    }
  }


}
