package fpt.edu.vn.sfafinal.services.impl;

import fpt.edu.vn.sfafinal.models.Token;
import fpt.edu.vn.sfafinal.services.TokenAuthenticationService;
import fpt.edu.vn.sfafinal.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private static final Long EXPIRATION_TIME = 86_400_000L; // 1 days
    private static final String SECRET = "SFA_SECRET";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final UserService userService;

    @Autowired
    public TokenAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Token getToken(String username) {
        String tokenStr = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        Token token = new Token();
        token.setToken(tokenStr);
        return token;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String username;
            try {
                username = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
            } catch (Exception e) {
                return null;
            }

            if (username != null) {
                UserDetails user = userService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities());
            }
        }
        return null;
    }
}
