package Model;

import java.io.Serializable;

/**
 *
 * @author Dercio Custodio
 */
public class Node<T> implements Serializable{
    public T key;    // A chave do elemento, que pode ser de qualquer tipo T
    public int x;    // Coordenada horizontal
    public int y;    // Coordenada vertical
    public Node next = null; // Próximo nó na lista

    public Node(T key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
    }
}