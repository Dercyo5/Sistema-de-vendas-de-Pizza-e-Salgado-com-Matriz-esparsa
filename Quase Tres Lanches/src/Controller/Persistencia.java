package Controller;

import Model.Matrizesparsa;
import Model.Venda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dercio Custodio
 */

public class Persistencia {

  public static <T> void salvarDados(String arquivo, ArrayList<T> dados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(dados);
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso no arquivo: " + arquivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no arquivo " + arquivo + ": " + e.getMessage());
        }
    }

  public static <T> ArrayList<T> carregarDados(String arquivo) {
        ArrayList<T> dados = new ArrayList<>();
        File file = new File(arquivo);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + arquivo + ". Retornando lista vazia.");
            return dados; // Retorna lista vazia se o arquivo não existir
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            dados = (ArrayList<T>) ois.readObject();
            JOptionPane.showMessageDialog(null, "Dados carregados com sucesso do arquivo: " + arquivo);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do arquivo " + arquivo + ": " + e.getMessage());
        }
        return dados;
    }


    public static void salvarVendas(Venda vendas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vendas.dat"))) {
            oos.writeObject(vendas);
            JOptionPane.showMessageDialog(null, "Vendas salvas com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar vendas: " + e.getMessage());
        }
    }

    public static Venda carregarVendas() {
        Venda vendas = new Venda();
        File arquivo = new File("vendas.dat");
        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo de vendas não encontrado. Criando um novo registro.");
            return vendas; // Retorna um novo objeto Venda se o arquivo não existir
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vendas.dat"))) {
            vendas = (Venda) ois.readObject();

            // Verificar e inicializar as matrizes esparsas se necessário
            if (vendas.getVendasPizzas() == null) {
                vendas.setVendasPizzas(new Matrizesparsa<>(10, 1));
            }
            if (vendas.getVendasSalgadinhos() == null) {
                vendas.setVendasSalgadinhos(new Matrizesparsa<>(10, 1));
            }

            JOptionPane.showMessageDialog(null, "Vendas carregadas com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar vendas: " + e.getMessage());
        }

        return vendas;
    }

    // Atualizar um item existente (pizzas, salgadinhos ou vendas) e salvar as alterações
    public static <T> void atualizar(T obj, String tipo) {
        try {
            String arquivo = null;

            // Define o arquivo baseado no tipo
            switch (tipo.toLowerCase()) {
                case "pizza":
                    arquivo = "pizzas.dat";
                    break;
                case "salgado":
                    arquivo = "salgados.dat";
                    break;
                case "venda":
                    arquivo = "vendas.dat";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo desconhecido para atualização.");
                    return;
            }

            // Carrega os dados do arquivo correspondente
            ArrayList<T> lista = carregarDados(arquivo);

            // Busca o índice do objeto na lista
            int index = lista.indexOf(obj);
            if (index != -1) {
                // Atualiza o objeto na lista
                lista.set(index, obj);

                // Salva a lista atualizada de volta no arquivo
                salvarDados(arquivo, lista);

                JOptionPane.showMessageDialog(null, tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " atualizado(a) com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " não encontrado(a) para atualização.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + tipo + ": " + e.getMessage());
        }
    }

}