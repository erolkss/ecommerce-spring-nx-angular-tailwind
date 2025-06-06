package br.com.ero.ecommerce_backend.order.domain.user.vo;

import br.com.ero.ecommerce_backend.shared.error.domain.Assert;

public record UserFirstName(String value) {
  public UserFirstName {
    Assert.field("value", value).maxLength(255);

  }
}
