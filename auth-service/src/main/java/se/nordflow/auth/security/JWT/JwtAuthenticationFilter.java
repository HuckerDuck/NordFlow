package se.nordflow.auth.security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private static final String BEARER_PREFIX = "Bearer ";
   private final JwtTokenProvider tokenProvider;
   private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Extract the token from the authorization header
        // This is one of three parts of the JWT
        String token = extractTokenFromHeader(request);

        if (token != null && tokenProvider.validateToken(token)) {
            String username = tokenProvider.getUsernameFromToken(token);

            //? Load the user details then from the database it self
            UserDetails userDetails = userDetailsService
                    .loadUserByUsername(username);

            //? Create then a authentication token with the user authorities aka roles and so on
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            //? Set the authenctation in the security context it self
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        }


        filterChain.doFilter(request, response);


    }

    //? Helping method - private
    //? Extract the bearer token from the authorzation header
    private String extractTokenFromHeader (HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith(BEARER_PREFIX)){
            return header.substring(BEARER_PREFIX.length());
        }

        return null;
    }
}
