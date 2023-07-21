package com.volmed.api.alura.domain.medico;

import com.volmed.api.alura.domain.endereco.DadosEndereco;
import com.volmed.api.alura.enuns.EspecialidadeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
         @NotBlank @Email
         String email,
        @NotBlank
        String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}")
         String crm,
         @NotNull
         EspecialidadeEnum especialidade,

        @NotNull @Valid // valid para informar ao spring que dentro de 'Endereço' também há campos a serem validados.
        DadosEndereco endereco) {

}
