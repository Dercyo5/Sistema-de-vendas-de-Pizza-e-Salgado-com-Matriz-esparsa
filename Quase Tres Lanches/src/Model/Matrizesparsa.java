package Model;

import java.io.Serializable;

/**
 *
 * @author Dercio Custodio
 */
public class Matrizesparsa<T> implements Serializable, IMatrizEsparsa<T>{
    private int lines = 0;
    private int columns = 0;
    private int number_of_elements = 0; //Número de elementos não-nulos na matriz
    private Node begin = null; // Nó inicial da lista encadeada que representa a matriz esparsa

    public Matrizesparsa(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;
    }
    
    // Método para buscar um elemento específico na matriz, dado suas coordenadas
    @Override
    public Node search(int x, int y) {
        if (this.is_empty()) 
            return null;

        Node p = this.begin;
        while (p != null) {
            if ((p.x == x) && (p.y == y)) 
                return p;
            if (p.x > x) 
                return null;
            if ((p.x == x) && (p.y > y)) 
                return null;
            p = p.next;
        }
        return null;
    }

    // Método para buscar o nó anterior a um elemento específico na matriz
    @Override
    public Node search_previous(int x, int y) {
        if (this.is_empty()) 
            return null;

        Node p = this.begin;
        while (p.next != null) {
            if ((p.next.x == x) && (p.next.y == y))
                return p;
            if (p.next.x > x) 
                return p;
            if ((p.next.x == x) && (p.next.y > y)) 
                return p;
            p = p.next;
        }
        return p;
    }

    // Método para adicionar ou substituir um elemento na matriz esparsa
    @Override
    public void add(T key, int x, int y) {
        if ((x < 0) || (y < 0)) 
            return;
        if ((x >= this.lines) || (y >= this.columns)) 
            return;

        Node previous = this.search_previous(x, y);
        Node element = this.search(x, y);

        // Matriz esparsa vazia.
        if ((previous == null) && (element == null)) {
            this.number_of_elements++;
            Node node = new Node(key, x, y);
            this.begin = node;
        }
        // Primeiro elemento.
        else if ((previous == null) && (element != null)) {
            element.key = key;
        }
        // Elemento no meio.
        else if ((previous != null) && (element == null)) {
            if (previous.next == null) {
                this.number_of_elements++;
                Node node = new Node(key, x, y);
                previous.next = node;
            } else {
                this.number_of_elements++;
                Node node = new Node(key, x, y);
                node.next = previous.next;
                previous.next = node;
            }
        }
        // Elemento no meio (ou também no final).
        else if ((previous != null) && (element != null)) {
            element.key = key;
        }
    }

    // Método para remover um elemento da matriz, dado suas coordenadas
    @Override
    public T remove(int x, int y) {
        if (this.is_empty()) 
            return null;

        Node node = this.search(x, y);
        if (node != null) {
            T key = (T) node.key;
            Node previous = this.search_previous(x, y);
            if (previous != null) {
                previous.next = node.next;
            } else {
                this.begin = node.next;
            }
            return key;
        }

        return null;
    }

    // Método para verificar se a matriz esparsa está cheia
    @Override
    public boolean is_full() {
        return this.number_of_elements == (this.lines * this.columns);
    }

    // Método para verificar se a matriz esparsa está vazia
    @Override
    public boolean is_empty() {
        return this.number_of_elements == 0;
    }

    // Método para obter uma representação da matriz esparsa em formato de string
    @Override
    public String toString() {
        if (this.is_empty())
            return "Empty sparse matrix.";

        Node p = this.begin;
        StringBuilder description = new StringBuilder("Matriz Esparsa: \n");

        for (int i = 0; i < this.lines; i++) {
            for (int j = 0; j < this.columns; j++) {
                if ((p != null) && (p.x == i && p.y == j)) {
                    description.append(String.format("%2s", p.key));
                    p = p.next;
                } else {
                    description.append(String.format("%2d", 0));
                }
                description.append("  ");
            }
            description.append("\n");
        }

        description.append("Elementos: ").append(this.number_of_elements).append(" \n");

        return description.toString();
    }

    // Método para limpar a matriz esparsa
    @Override
    public void clear() {
        this.begin = null;
        this.number_of_elements = 0;
    }

    // Método para obter o número de linhas da matriz
    @Override
    public int getLines() {
        return this.lines;
    }

    // Método para obter o número de colunas da matriz
    @Override
    public int getColumns() {
       return this.columns;
    }

    // Método para obter o número de elementos não-nulos
    @Override
    public int size() {
        return this.number_of_elements;
    }
    
}