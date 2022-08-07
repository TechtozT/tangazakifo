package com.tangazakifo.tangazakifo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

public class CustomTokenBasedRememberMeService extends TokenBasedRememberMeServices {
  private final String TOKEN_KEY = "token";

  /**
   * Locates the Spring Security remember me token in the request and returns its
   * value.
   *
   * @param request the submitted request which is to be authenticated
   * @return the value of the request header (which was originally provided by the
   *         cookie - API expects it in header)
   */
  public CustomTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
    super(key, userDetailsService);
  }

  @Override
  protected String extractRememberMeCookie(HttpServletRequest request) {
    String token = request.getHeader(TOKEN_KEY);
    if ((token == null) || (token.length() == 0)) {
      return "";
    }
    return token;
  }
}
