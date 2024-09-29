
package controller;

import dao.RepoCliente;
import model.Cliente;

public class ControllerCliente extends Controller {

    public ControllerCliente() {
    }

    public boolean cadastrarCliente(String nome, String email, String cpf) {
        System.out.println("Nome:" + nome);
        System.out.println("Email:" + email);
        System.out.println("CPF:" + cpf);
        if (nome != null && !nome.isEmpty() &&
                email != null && !email.isEmpty() &&
                cpf != null && !cpf.isEmpty()) {
             Cliente c = new Cliente(null, nome, email, cpf, 100);
            new RepoCliente().save(c);
            return true;

        }
        return false;

    }
}
