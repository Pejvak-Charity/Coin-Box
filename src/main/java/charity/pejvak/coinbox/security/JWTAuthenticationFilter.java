package charity.pejvak.coinbox.security;

import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.service.JWTService;
import charity.pejvak.coinbox.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String username;
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        jwt = authHeader.substring(7);
//        username = jwtService.extractUsername(jwt);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            //fixme handle exceptions for uses that not exists
//            User user = userService.getUserByUsername(username);
//
//            if (jwtService.isValidToken(user, jwt)){
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource()
//                                .buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
        filterChain.doFilter(request,response);
    }
}
