package Controller;

import Model.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Dercio Custodio
 */
public class PersistenciaUser {
    private static final String FILE_NAME = "usuarios.dat";

    public static ArrayList<Usuario> carregarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            usuarios = (ArrayList<Usuario>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado. Criando um novo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void salvarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean registrarUsuario(Usuario novoUsuario) {
        ArrayList<Usuario> usuarios = carregarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getNomeUsuario().equals(novoUsuario.getNomeUsuario())) {
                System.out.println("Usuário já existe.");
                return false;
            }
        }
        usuarios.add(novoUsuario);
        salvarUsuarios(usuarios);
        return true;
    }

    public static Usuario autenticarUsuario(String nomeUsuario, String senha) {
        ArrayList<Usuario> usuarios = carregarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getNomeUsuario().equals(nomeUsuario) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
