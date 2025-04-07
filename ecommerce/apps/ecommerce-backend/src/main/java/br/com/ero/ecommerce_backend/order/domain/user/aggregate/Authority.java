package br.com.ero.ecommerce_backend.order.domain.user.aggregate;

import br.com.ero.ecommerce_backend.order.domain.user.vo.AuthorityName;
import br.com.ero.ecommerce_backend.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Authority {

  private AuthorityName name;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("name", authorityName);
    this.name = authorityName;
  }

  public AuthorityName getName() {
    return name;
  }
}
