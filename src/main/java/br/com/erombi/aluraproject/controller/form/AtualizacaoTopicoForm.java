package br.com.erombi.aluraproject.controller.form;

import br.com.erombi.aluraproject.model.Topico;
import br.com.erombi.aluraproject.repository.TopicoRepository;

import javax.validation.constraints.NotBlank;

public class AtualizacaoTopicoForm {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository repository) {
        Topico topico = repository.getOne(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
