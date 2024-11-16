package Model;

import java.io.Serializable;

/**
 *
 * @author Dercio Custodio
 */
public class Usuario implements Serializable{
    private String nomeUsuario;
    private String senha;

    public Usuario(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }
    
}