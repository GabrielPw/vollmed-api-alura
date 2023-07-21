package com.volmed.api.alura.domain.paciente;

import com.volmed.api.alura.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizarPaciente(
    Long id,
    String nome,
    String telefone,
    @Valid DadosEndereco endereco) {


}
