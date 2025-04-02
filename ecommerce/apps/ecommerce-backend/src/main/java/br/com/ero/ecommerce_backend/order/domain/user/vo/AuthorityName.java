package br.com.ero.ecommerce_backend.order.domain.user.vo;

import br.com.ero.ecommerce_backend.shared.error.domain.Assert;

public record AuthorityName(String name) {
  public AuthorityName {
    Assert.field("name", name).notNull();
  }
}
