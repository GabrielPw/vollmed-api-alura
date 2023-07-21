package com.volmed.api.alura.domain.paciente;

import com.volmed.api.alura.domain.medico.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Boolean ativo;
    private String cpf;
    private String telefone;

    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dadosPaciente) {

        this.ativo = true;

        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.cpf = dadosPaciente.cpf();
        this.telefone = dadosPaciente.telefone();
        this.endereco = new Endereco(dadosPaciente.endereco());
    }

    public void atualizarInformacoes(DadosAtualizarPaciente dados) {
        if(dados.nome() != null)
            this.nome = dados.nome();
        if(dados.telefone() != null)
            this.telefone = dados.telefone();
        if(dados.endereco() != null)
            this.endereco.atualizarInformacoes(dados.endereco());

    }

    public void inativar() {
        this.ativo = false;
    }
}
