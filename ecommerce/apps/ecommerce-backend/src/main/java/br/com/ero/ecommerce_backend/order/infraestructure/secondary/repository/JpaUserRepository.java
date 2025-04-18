package br.com.ero.ecommerce_backend.order.infraestructure.secondary.repository;

import br.com.ero.ecommerce_backend.order.infraestructure.secondary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  List<UserEntity> findByPublicIdIn(List<UUID> publicIds);

  Optional<UserEntity> findOneByPublicId(UUID publicId);

  @Modifying
  @Query("UPDATE UserEntity  user " +
    "SET user.addressStreet = :street, user.addressCity = :city, " +
    " user.addressCountry = :country, user.addressZipCode = :zipCode " +
    "WHERE user.publicId = :userPublicId")
  void updateAddress(UUID userPublicId, String address, String city, String country, String zipCode);
}
