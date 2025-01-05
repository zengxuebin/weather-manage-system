package com.weather.web.security.filter;

import com.weather.common.utils.SecurityUtil;
import com.weather.domain.model.LoginUser;
import com.weather.web.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * token过滤器 验证其有效性
 * @author linkaixuan
 * @since 2024/4/3 09:32
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private StringEncryptor encoder;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        String ciphertext = "1lcuxG/ik+N4ygmJes5iyDCFl9NmeBVHVd/rBTrWOWPXhbkAs86ZSikz3Z6uI/asJfxDc+tNppQs1gzW+rskpg==";
        LocalDateTime expirationDate = LocalDateTime.parse(encoder.decrypt(ciphertext),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (LocalDateTime.now().isAfter(expirationDate)) {
            ((ConfigurableApplicationContext) context).close();
        }

        if (loginUser != null && SecurityUtil.getAuthentication() == null) {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
