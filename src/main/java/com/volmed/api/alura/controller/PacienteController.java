package com.volmed.api.alura.controller;

import com.volmed.api.alura.paciente.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void cadastrarPaciente(@RequestBody DadosCadastroPaciente dadosPaciente){

        pacienteRepository.save(new Paciente(dadosPaciente));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizarPaciente dadosPaciente){

        Paciente paciente = pacienteRepository.getReferenceById(dadosPaciente.id());
        paciente.atualizarInformacoes(dadosPaciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable Long id){

        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}
