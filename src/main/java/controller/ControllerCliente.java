
package controller;

import dao.RepoCliente;
import model.Cliente;

public class ControllerCliente {

    public ControllerCliente() {
    }

    public boolean cadastrarCliente(String nome, String email, String cpf) {
        if (nome != null && !nome.isEmpty() &&
                email != null && !email.isEmpty() &&
                cpf != null && !cpf.isEmpty()) {
             Cliente c = new Cliente(null, nome, email, cpf, 50);
            new RepoCliente().save(c);
            return true;

        }
        return false;

    }
}
