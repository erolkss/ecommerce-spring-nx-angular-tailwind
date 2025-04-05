package br.com.ero.ecommerce_backend.order.domain.user.repository;

import br.com.ero.ecommerce_backend.order.domain.user.aggregate.User;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserAddressToUpdate;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserEmail;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserPublicId;

import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> get(UserPublicId userPublicId);

  Optional<User> getOneByEmail(UserEmail userEmail);

  void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddress);
}
