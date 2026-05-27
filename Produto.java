// Classe base — todos os produtos herdam dela
public abstract class Produto {
    private final String nome;
    private final double preco;

    public Produto(String nome, double preco) {
        this.nome  = nome;
        this.preco = preco;
    }

    public String getNome()  { return nome; }
    public double getPreco() { return preco; }

    // Polimorfismo — cada produto descreve a si mesmo
    public abstract String descricao();

    @Override
    public String toString() {
        return String.format("%-12s → R$ %.2f", nome, preco);
    }
}
