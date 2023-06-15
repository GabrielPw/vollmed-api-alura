package com.volmed.api.alura.paciente;

import com.volmed.api.alura.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizarPaciente(
    Long id,
    String nome,
    String telefone,
    @Valid DadosEndereco endereco) {


}
