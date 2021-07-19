package br.com.erombi.aluraproject.config.validacao;

public class ErrodeFormularioDTO {

    private String campo;
    private String erro;

    public ErrodeFormularioDTO() {    }

    public ErrodeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
