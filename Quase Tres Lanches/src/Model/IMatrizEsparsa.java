package Model;

/**
 *
 * @author Dercio Custodio
 */

public interface IMatrizEsparsa<T> {
   Node search(int x, int y);
   Node search_previous(int x, int y);
   void add(T key, int x, int y);
   T remove(int x, int y);
   boolean is_full();
   boolean is_empty();
   void clear();           // Limpa todos os elementos
   int getLines();         // Retorna o número de linhas
   int getColumns();       // Retorna o número de colunas
   int size();
}