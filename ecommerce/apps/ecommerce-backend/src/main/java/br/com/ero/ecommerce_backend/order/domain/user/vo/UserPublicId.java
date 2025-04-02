package br.com.ero.ecommerce_backend.order.domain.user.vo;

import br.com.ero.ecommerce_backend.shared.error.domain.Assert;

import java.util.UUID;

public record UserPublicId(UUID value) {
  public UserPublicId {
    Assert.notNull("value", value);

  }
}
