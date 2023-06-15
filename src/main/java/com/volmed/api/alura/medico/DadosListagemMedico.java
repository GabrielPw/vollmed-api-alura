package com.volmed.api.alura.medico;

import com.volmed.api.alura.enuns.EspecialidadeEnum;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        EspecialidadeEnum especialidade
) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
