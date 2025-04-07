package br.com.ero.ecommerce_backend.order.application;

import br.com.ero.ecommerce_backend.order.domain.user.aggregate.User;
import br.com.ero.ecommerce_backend.order.domain.user.repository.UserRepository;
import br.com.ero.ecommerce_backend.order.domain.user.service.UserReader;
import br.com.ero.ecommerce_backend.order.domain.user.service.UserSynchronizer;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserEmail;
import br.com.ero.ecommerce_backend.order.infraestructure.secondary.service.kinde.KindeService;
import br.com.ero.ecommerce_backend.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService {

  private final UserSynchronizer userSynchronizer;

  private final UserReader userReader;

  public UserApplicationService(UserRepository userRepository, KindeService kindeService) {
    this.userSynchronizer = new UserSynchronizer(userRepository, kindeService);
    this.userReader = new UserReader(userRepository);
  }

  @Transactional
  public User getAuthenticationUserWithSync(Jwt jwtToken, boolean forceSync) {
    userSynchronizer.syncWithIdp(jwtToken, forceSync);

    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get())).orElseThrow();
  }
}
