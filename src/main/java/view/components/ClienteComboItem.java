package view.components;

public class ClienteComboItem {
    private final int id;
    private final String nome;

    public ClienteComboItem(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nome; // Isso define o que será exibido no JComboBox
    }
}