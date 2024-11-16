package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Dercio Custodio
 */

public class Venda implements Serializable {
    // Usando Matrizesparsa para armazenar vendas de pizzas e salgadinhos
    private Matrizesparsa<Integer> vendasPizzas;
    private Matrizesparsa<Integer> vendasSalgadinhos;
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private ArrayList<Salgado> salgadinhos = new ArrayList<>();
    private LocalDate data;
    private int quantidade;
    private int valorTotal;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda(){
    }
    
    public Venda(int numPizzas, int numSalgadinhos) {
      // Inicializa a matriz esparsa para pizzas e salgadinhos
      vendasPizzas = new Matrizesparsa<>(numPizzas, 1);  // Apenas uma coluna, cada linha representa uma pizza
      vendasSalgadinhos = new Matrizesparsa<>(numSalgadinhos, 1);  // Apenas uma coluna, cada linha representa um salgadinho
    }

    // Registrar uma venda de pizza
    public void registrarPizza(Pizza pizza) {
        if (!pizzas.contains(pizza)) {
            pizzas.add(pizza);
        }
        // Incrementa a quantidade de vendas daquela pizza (representada pela linha da matriz esparsa)
        int index = pizzas.indexOf(pizza);
        Integer currentSales = 0;
        Node node = vendasPizzas.search(index, 0);
        if (node != null) {
            currentSales = (Integer) node.key;  // Obtém a quantidade de vendas se o nó existir
        }
        vendasPizzas.add(currentSales + 1, index, 0);  // Atualiza ou adiciona a venda
    }

    // Registrar uma venda de salgadinho
    public void registrarSalgadinho(Salgado salgado) {
        if (!salgadinhos.contains(salgado)) {
            salgadinhos.add(salgado);
        }

        // Inicializa a matriz esparsa se for null
        if (vendasSalgadinhos == null) {
            vendasSalgadinhos = new Matrizesparsa<Integer>(10, 1);  // Inicializar com 10 linhas e 1 coluna
        }

        // Incrementa a quantidade de vendas daquele salgadinho (representada pela linha da matriz esparsa)
        int index = salgadinhos.indexOf(salgado);
        Node node = vendasSalgadinhos.search(index, 0);
        Integer currentSales = (node != null) ? (Integer) node.key : 0;
        vendasSalgadinhos.add(currentSales + 1, index, 0);
    }
    
    // Listar todas as pizzas vendidas com a quantidade
    public void listarPizzas() {
        System.out.println("Pizzas Vendidas:");
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            Integer quantidade = 0;
            Node node = vendasPizzas.search(i, 0);
            if (node != null) {
                quantidade = (Integer) node.key;  // Obtém a quantidade de vendas se o nó existir
            }
            System.out.println(pizza + " - Quantidade Vendida: " + quantidade);
        }
    }

    // Listar todos os salgadinhos vendidos com a quantidade
    public void listarSalgadinhos() {
        System.out.println("Salgadinhos Vendidos:");
        for (int i = 0; i < salgadinhos.size(); i++) {
            Salgado salgado = salgadinhos.get(i);
            Integer quantidade = 0;
            Node node = vendasSalgadinhos.search(i, 0);
            if (node != null) {
                quantidade = (Integer) node.key;  // Obtém a quantidade de vendas se o nó existir
            }
            System.out.println(salgado + " - Quantidade Vendida: " + quantidade);
        }
    }

    public Matrizesparsa<Integer> getVendasPizzas() {
        return vendasPizzas;
    }

    public void setVendasPizzas(Matrizesparsa<Integer> vendasPizzas) {
        this.vendasPizzas = vendasPizzas;
    }

    public Matrizesparsa<Integer> getVendasSalgadinhos() {
        return vendasSalgadinhos;
    }

    public void setVendasSalgadinhos(Matrizesparsa<Integer> vendasSalgadinhos) {
        this.vendasSalgadinhos = vendasSalgadinhos;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public ArrayList<Salgado> getSalgadinhos() {
        return salgadinhos;
    }

    public void setSalgadinhos(ArrayList<Salgado> salgadinhos) {
        this.salgadinhos = salgadinhos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public void registrarVenda(String tipoProduto, Object produto, int quantidade, double preco) {
        if (tipoProduto.equalsIgnoreCase("pizza") && produto instanceof Pizza) {
            Pizza pizza = (Pizza) produto;
            if (!pizzas.contains(pizza)) {
                pizzas.add(pizza);
            }
            int index = pizzas.indexOf(pizza);
            atualizarQuantidade(vendasPizzas, index, quantidade);
        } else if (tipoProduto.equalsIgnoreCase("salgado") && produto instanceof Salgado) {
            Salgado salgado = (Salgado) produto;
            if (!salgadinhos.contains(salgado)) {
                salgadinhos.add(salgado);
            }
            int index = salgadinhos.indexOf(salgado);
            atualizarQuantidade(vendasSalgadinhos, index, quantidade);
        }

        // Atualiza a data da venda
        this.data = LocalDate.now();

        // Atualiza a quantidade e o valor total
        this.quantidade = quantidade;
       // this.valorTotal = (double) quantidade * precoUnitario;
    }

    private void atualizarQuantidade(Matrizesparsa<Integer> matriz, int index, int quantidade) {
        if (matriz == null) {
            matriz = new Matrizesparsa<>(10, 1);
        }
        Node node = matriz.search(index, 0);
        Integer currentSales = (node != null) ? (Integer) node.key : 0;
        matriz.add(currentSales + quantidade, index, 0);
    }

}