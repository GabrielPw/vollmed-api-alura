package com.volmed.api.alura.infra.security;

import com.volmed.api.alura.domain.usuario.Usuario;
import com.volmed.api.alura.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Intercepta requisição para executar filter. (Uma única vez por requisição)
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        if (token != null) {
            var subject = tokenService.getSubject(token);
            var usuario = usuarioRepository.findByLogin(subject);

            // DTO do Spring para autenticação.
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); // fará a autenticação do usuário.

            System.out.println("Subject: " + subject);
            System.out.println("Token: " + token);
        }

        // continuar execução de filtros seguintes.
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            // remover prefixo adicionado automaticamente ao Token.
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
