import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { appRoutes } from './app.routes';
import {
  provideClientHydration,
  withEventReplay,
} from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AbstractSecurityStorage, authInterceptor, LogLevel, provideAuth } from 'angular-auth-oidc-client';
import { enviroment } from '../environments/environment';
import { provideQueryClient, QueryClient } from '@tanstack/angular-query-experimental';
import { SsrStorageService } from './auth/ssr-storage.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideClientHydration(withEventReplay()),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(appRoutes, withComponentInputBinding()),
    provideHttpClient(withFetch(), withInterceptors([authInterceptor()])),
    provideAuth({
      config: {
        authority: enviroment.kinde.authority,
        redirectUrl: enviroment.kinde.redirectUrl,
        postLogoutRedirectUri: enviroment.kinde.postLogoutRedirectUri,
        clientId: enviroment.kinde.clientId,
        scope: 'openid profile email offline',
        responseType: 'code',
        silentRenew: true,
        useRefreshToken: true,
        logLevel: LogLevel.Warn,
        secureRoutes: [enviroment.apiUrl],
        customParamsAuthRequest: {
          audience: enviroment.kinde.audience
        },
      },
    }),
    { provide: AbstractSecurityStorage, useClass: SsrStorageService },
    provideQueryClient(new QueryClient)
  ],
};
