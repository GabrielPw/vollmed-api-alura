package com.volmed.api.alura.controller;

import com.volmed.api.alura.domain.usuario.DadosAutenticacao;
import com.volmed.api.alura.domain.usuario.Usuario;
import com.volmed.api.alura.infra.DadosTokenJWT;
import com.volmed.api.alura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){

        // o 'UsernamePasswordAuthenticationToken' seia uma espécie de DTO do próprio Spring.
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authentication = manager.authenticate(token); // faz chamada ao AutenticacaoService

        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        System.out.println("Gerou o token: " + tokenJWT);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
