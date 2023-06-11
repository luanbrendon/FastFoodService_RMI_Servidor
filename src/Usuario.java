import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario;
    private String senha;
    private String endereco;

    public Usuario(String usuario, String senha, String endereco) {
        this.usuario = usuario;
        this.senha = senha;
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }
}