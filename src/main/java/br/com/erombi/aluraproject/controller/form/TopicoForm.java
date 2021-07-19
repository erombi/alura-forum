package br.com.erombi.aluraproject.controller.form;

import br.com.erombi.aluraproject.model.Curso;
import br.com.erombi.aluraproject.model.Topico;
import br.com.erombi.aluraproject.repository.CursoRepository;
import br.com.erombi.aluraproject.repository.TopicoRepository;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoForm {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String nomeCurso;

    @Min(value = 10)
    @NotNull
    private Integer teste;

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

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Integer getTeste() {
        return teste;
    }

    public void setTeste(Integer teste) {
        this.teste = teste;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
