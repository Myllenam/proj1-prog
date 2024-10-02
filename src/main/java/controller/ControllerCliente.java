
package controller;

import dao.RepoCliente;
import dao.RepoProduto;
import model.Cliente;
import model.Produto;

import java.util.List;

public class ControllerCliente extends Controller {

    public ControllerCliente() {
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

    /**
     * Verifica se o cliente possui direito a uma recompensa de acordo com a quantidade de pontos
     * que ele possui em sua conta
     * @return
     */
    public boolean verificaRecompensa(int idCliente) {
        Cliente cliente = new RepoCliente().getById(idCliente);
        int pontosCliente = cliente.getPontos();

        // Com quinhentos pontos o cliente ganhar o brinde da promoção
        if(pontosCliente >= 500) {
            // Zera os pontos do cliente para começar a contagem novamente
            cliente.setPontos(0);
            new RepoCliente().update(cliente);
            return true;
        } else {
            return false;
        }
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

    public List<Cliente> getClientes() {
        return new RepoCliente().getAll();
    }

}
