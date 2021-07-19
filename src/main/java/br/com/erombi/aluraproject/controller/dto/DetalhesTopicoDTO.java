package br.com.erombi.aluraproject.controller.dto;

import br.com.erombi.aluraproject.model.Resposta;
import br.com.erombi.aluraproject.model.StatusTopico;
import br.com.erombi.aluraproject.model.Topico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesTopicoDTO extends TopicoDTO {

    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDto> respostas;

    public DetalhesTopicoDTO(Topico topico) {
        super(topico);
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
