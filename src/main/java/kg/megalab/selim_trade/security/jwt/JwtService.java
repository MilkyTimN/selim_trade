package kg.megalab.selim_trade.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwt_access_token_expiration_in_minutes}")
    private long access_token_expiration_minutes;

    @Value("${jwt_refresh_token_expiration_in_hours}")
    private long refresh_token_expiration_hours;

    @Value("${jwt_refresh_cookie_name}")
    private String jwt_refresh_cookie_name;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> claims,
            UserDetails userDetails)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                + 1000 * 60 * access_token_expiration_minutes))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(jwt);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public ResponseCookie generateCookie(String name, String value, String path) {
        return ResponseCookie.from(name, value).path(path).maxAge(24 * 60 * 60).httpOnly(true).build();
    }

    public ResponseCookie generateRefreshJwtCookie(String refreshToken) {
        return generateCookie(jwt_refresh_cookie_name, refreshToken, "/api/auth/refreshtoken");
    }

    private String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if(cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    public String getRefreshTokenFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, jwt_refresh_cookie_name);
    }

    public ResponseCookie getCleanRefreshJwtCookie() {
        return ResponseCookie.from(jwt_refresh_cookie_name, "").path("/api/auth/refreshtoken").build();
    }
}
