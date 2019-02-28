package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.models.Token;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenAuthenticationService {

    Token getToken(String username);

    Authentication getAuthentication(HttpServletRequest request);
}
