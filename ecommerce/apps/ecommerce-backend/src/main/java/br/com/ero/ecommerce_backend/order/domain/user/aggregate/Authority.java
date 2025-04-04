package br.com.ero.ecommerce_backend.order.domain.user.aggregate;

import br.com.ero.ecommerce_backend.order.domain.user.vo.AuthorityName;
import br.com.ero.ecommerce_backend.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Authority {

  private AuthorityName authorityName;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("name", authorityName);
    this.authorityName = authorityName;
  }

  public AuthorityName getAuthorityName() {
    return authorityName;
  }
}
