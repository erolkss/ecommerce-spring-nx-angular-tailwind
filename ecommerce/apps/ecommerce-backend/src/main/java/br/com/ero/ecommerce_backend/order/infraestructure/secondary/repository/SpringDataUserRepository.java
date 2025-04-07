package br.com.ero.ecommerce_backend.order.infraestructure.secondary.repository;

import br.com.ero.ecommerce_backend.order.domain.user.aggregate.User;
import br.com.ero.ecommerce_backend.order.domain.user.repository.UserRepository;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserAddressToUpdate;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserEmail;
import br.com.ero.ecommerce_backend.order.domain.user.vo.UserPublicId;
import br.com.ero.ecommerce_backend.order.infraestructure.secondary.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpringDataUserRepository implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  public SpringDataUserRepository(JpaUserRepository jpaUserRepository) {
    this.jpaUserRepository = jpaUserRepository;
  }


  @Override
  public void save(User user) {
    if (user.getDbId() != null) {
      Optional<UserEntity> userToUpdateOpt = jpaUserRepository.findById(user.getDbId());
      if (userToUpdateOpt.isPresent()) {
        UserEntity userToUpdate = userToUpdateOpt.get();
        userToUpdate.updateFromUser(user);
        jpaUserRepository.saveAndFlush(userToUpdate);
      }
    } else {
      jpaUserRepository.save(UserEntity.from(user));
    }
  }

  @Override
  public Optional<User> get(UserPublicId userPublicId) {
    return jpaUserRepository.findOneByPublicId(userPublicId.value())
      .map(UserEntity::toDomain);
  }

  @Override
  public Optional<User> getOneByEmail(UserEmail userEmail) {
    return jpaUserRepository.findByEmail(userEmail.value())
      .map(UserEntity::toDomain);
  }

  @Override
  public void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddress) {
    jpaUserRepository.updateAddress(userPublicId.value(),
      userAddress.userAddress().street(),
      userAddress.userAddress().city(),
      userAddress.userAddress().country(),
      userAddress.userAddress().zipCode());
  }
}
