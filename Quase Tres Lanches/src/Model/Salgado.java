package Model;

/**
 *
 * @author Dercio Custodio
 */
import java.io.Serializable;

public class Salgado implements Serializable {
    private String tipo;  // Frito ou Assado
    private String massa;
    private String recheio;
    private double preco;

    public Salgado(){}
    
    public Salgado(String tipo, String massa, String recheio, double preco) {
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMassa() {
        return massa;
    }

    public void setMassa(String massa) {
        this.massa = massa;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}