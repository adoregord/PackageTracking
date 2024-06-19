package com.example.tracking.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomJWTAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");// jwt token or bearer token
		final String jwt;
		final String username;
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;

		}
		// extract token from authorization token
		jwt = authHeader.substring(7);

		// extract username from jwt token
		username = jwtService.extractUsername(jwt);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// update security context
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // get the userdetails form
																							// db
			// check the token is valid or not
			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null, // credentials
						userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken); // update auth token

			}
		}
		filterChain.doFilter(request, response);
	}
}
// @Autowired
// private JwtTokenUtil jwtTokenUtil;

// @Override
// protected void doFilterInternal(HttpServletRequest request,
// HttpServletResponse response, FilterChain chain)
// throws ServletException, IOException {

// try{
// // JWT Token is in the form "Bearer token". Remove Bearer word and
// // get only the Token
// String jwtToken = extractJwtFromRequest(request);

// if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
// String username = jwtTokenUtil.getUserNameFromToken(jwtToken);

// // List<SimpleGrantedAuthority> roles =
// jwtTokenUtil.getRolesFromToken(jwtToken);

// UserDetails userDetails = new User(username);
// UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
// UsernamePasswordAuthenticationToken(
// userDetails, null, userDetails.getAuthorities());
// // After setting the Authentication in the context, we specify
// // that the current user is authenticated. So it passes the
// // Spring Security Configurations successfully.
// SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
// } else {
// logger.warn("Cannot set the Security Context");
// }
// }catch(ExpiredJwtException ex)
// {
// request.setAttribute("exception", ex);
// throw ex;
// }
// catch(BadCredentialsException ex)
// {
// request.setAttribute("exception", ex);
// throw ex;
// }
// chain.doFilter(request, response);
// }

// private String extractJwtFromRequest(HttpServletRequest request) {
// String bearerToken = request.getHeader("Authorization");
// if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
// return bearerToken.substring(7, bearerToken.length());
// } else {
// logger.warn("JWT Token does not begin with Bearer String");
// }
// return null;
// }

// }
