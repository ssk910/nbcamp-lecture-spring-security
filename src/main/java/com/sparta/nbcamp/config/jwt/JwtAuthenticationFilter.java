package com.sparta.nbcamp.config.jwt;

import com.sparta.nbcamp.config.auth.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT 인증 필터. request마다 처리.
 */
@Component
@RequiredArgsConstructor
@Slf4j(topic = "Security::JwtAuthenticationFilter")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    this.validateToken(request);
    filterChain.doFilter(request, response);
  }

  /**
   * 토큰을 검증한다.
   *
   * @param request {@link HttpServletRequest}
   */
  private void validateToken(HttpServletRequest request) {
    // 토큰 검증.
    String token = getTokenFromRequest(request);
    if (!jwtTokenProvider.isValidToken(token)) {
      return;
    }
    log.debug("토큰 검증.");

    // 토큰으로부텨 username을 추출.
    String username = jwtTokenProvider.getUsername(token);

    // 토큰에 해당되는 사용자를 찾는다
    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

    // SecurityContext에 인증 객체 저장.
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    log.debug("SecurityContext에 Authentication 저장.");
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }

  /**
   * request의 헤더에서 토큰 값을 추출.
   *
   * @param request {@link HttpServletRequest}
   * @return 토큰 값 (찾지 못한 경우 {@code null})
   */
  private String getTokenFromRequest(HttpServletRequest request) {
    final String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String TOKEN_BEARER_PREFIX = "Bearer ";

    boolean tokenFound =
        StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX);
    if (tokenFound) {
      return bearerToken.substring(TOKEN_BEARER_PREFIX.length());
    }

    return null;
  }
}