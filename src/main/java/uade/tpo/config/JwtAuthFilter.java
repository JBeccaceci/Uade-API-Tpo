package uade.tpo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    public JwtAuthFilter(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = extractJwtFromRequest(request);

            if (token != null && validateToken(token)) {
                JWTAuthInfo principal = this.getPrincipal(token);
                List<GrantedAuthority> authorities = extractRoleFromToken(token);

                if (principal != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            principal, null, authorities);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            System.out.println("doFilterInternal error 401: " + e.getMessage());
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            if (isTokenSignatureValid(claimsJws) && isTokenNotExpired(claimsJws.getBody().getExpiration())) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("validateToken error: " + e.getMessage());
        }

        return false;
    }

    private boolean isTokenSignatureValid(Jws<Claims> claimsJws) {
        try {
            claimsJws.getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenNotExpired(Date expirationDate) {
        return expirationDate != null && !expirationDate.before(new Date());
    }

    private List<GrantedAuthority> extractRoleFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

            String  rol = claims.get("rol", String.class);

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(rol));
            return authorities;
        } catch (Exception e) {
            return null;
        }
    }

    private JWTAuthInfo getPrincipal(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            String userId = claims.getId();
            String role = claims.get("rol", String.class);
            String type = claims.get("type", String.class);
            return new JWTAuthInfo(username, userId, role, type);
        } catch (Exception e) {
            return null;
        }
    }

}
