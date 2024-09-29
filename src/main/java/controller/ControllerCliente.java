
package controller;

import dao.RepoCliente;
import model.Cliente;

public class ControllerCliente extends Controller {

    public ControllerCliente() {
    }

    public Cliente encontrarCliente( String cpf) {
        return new RepoCliente().getCpf(cpf);
    }
    public Integer encontrarIdCliente( String cpf) {
        return new RepoCliente().getid(cpf);
    }

    private boolean validaCampos(String nome, String email, String cpf) {
        String cpfLimpo = cpf.replaceAll("\\.", "").replaceAll("-", "");
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        boolean nomeValido = nome != null && !nome.trim().isEmpty() && nome.length() > 3;
        boolean emailValido = email != null && !email.trim().isEmpty() && email.matches(emailRegex);
        boolean cpfValido = cpfLimpo.length() == 11;

        if (nomeValido && emailValido && cpfValido) {

            return true;

        }
        return false;
    }


    public boolean cadastrarCliente(String nome, String email, String cpf) {
        System.out.println("Nome:" + nome);
        System.out.println("Email:" + email);
        System.out.println("CPF:" + cpf);
        if (validaCampos(nome, email, cpf)) {
            Cliente c = new Cliente(null, nome, email, cpf, 100);
            new RepoCliente().save(c);
            return true;
        }
        return false;
    }

}
