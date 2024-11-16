package Model;


/**
 *
 * @author Dercio Custodio
 */
import java.io.Serializable;

public class Pizza implements Serializable {
    private String recheio;
    private String borda;
    private String molho;
    private double preco;

    public Pizza(){}
 
    public Pizza(String recheio, String borda, String molho, double preco) {
        this.recheio = recheio;
        this.borda = borda;
        this.molho = molho;
        this.preco = preco;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    public String getBorda() {
        return borda;
    }

    public void setBorda(String borda) {
        this.borda = borda;
    }

    public String getMolho() {
        return molho;
    }

    public void setMolho(String molho) {
        this.molho = molho;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}