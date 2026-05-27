import java.util.Scanner;

public class Main {

    // ── Catálogo fixo da loja ──────────────────────────────
    static final Produto[] CATALOGO = {
        new Televisao(),
        new Radio(),
        new Videogame(),
        new Tablet(),
        new Celular()
    };

    // ── Carrinho: máximo 50 itens ──────────────────────────
    static Produto[] carrinho    = new Produto[50];
    static int[]     quantidades = new int[50];
    static int       totalItens  = 0;

    static Scanner scanner = new Scanner(System.in);

    // ══════════════════════════════════════════════════════
    public static void main(String[] args) {

        boasVindas();

        int opcao;
        do {
            exibirCatalogo();
            System.out.println("  [6] Finalizar compra");
            System.out.print("\n  Escolha uma opção: ");
            opcao = lerInt();

            if (opcao >= 1 && opcao <= 5) {
                adicionarAoCarrinho(opcao - 1);
            } else if (opcao == 6) {
                finalizarCompra();
            } else {
                System.out.println("\n⚠  Opção inválida! Digite um número de 1 a 6.");
            }

        } while (opcao != 6);

        scanner.close();
    }

    // ── Boas vindas ────────────────────────────────────────
    static void boasVindas() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("       🛒  Bem-vindo à TechShop!       ");
        System.out.println("   Aqui você encontra os melhores      ");
        System.out.println("   produtos de tecnologia!             ");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ── Exibe catálogo ─────────────────────────────────────
    static void exibirCatalogo() {
        System.out.println("\n── Produtos Disponíveis ──────────────");
        for (int i = 0; i < CATALOGO.length; i++) {
            System.out.printf("  [%d] %s%n", (i + 1), CATALOGO[i]);
            System.out.println("      " + CATALOGO[i].descricao());
        }
        System.out.println("──────────────────────────────────────");
    }

    // ── Adiciona produto ao carrinho ───────────────────────
    static void adicionarAoCarrinho(int indice) {

        System.out.print("  Quantidade desejada: ");
        int qtd = lerInt();

        if (qtd <= 0) {
            System.out.println("⚠  Quantidade inválida! Digite um número maior que zero.");
            return;
        }

        Produto produto = CATALOGO[indice];

        // Verifica se produto já está no carrinho
        for (int i = 0; i < totalItens; i++) {
            if (carrinho[i].getNome().equals(produto.getNome())) {
                quantidades[i] += qtd;
                System.out.println("\n✔  " + qtd + "x " + produto.getNome() +
                                   " adicionado(s)! Total: " + quantidades[i] + " unidade(s).");
                return;
            }
        }

        // Novo item no carrinho
        carrinho[totalItens]    = produto;
        quantidades[totalItens] = qtd;
        totalItens++;

        System.out.println("\n✔  " + qtd + "x " + produto.getNome() + " adicionado(s) ao carrinho!");
    }

    // ── Finaliza a compra ──────────────────────────────────
    static void finalizarCompra() {

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("           🧾  Seu Pedido               ");
        System.out.println("╚══════════════════════════════════════╝");

        if (totalItens == 0) {
            System.out.println("\n  Seu carrinho está vazio!");
            return;
        }

        double total = 0;

        for (int i = 0; i < totalItens; i++) {
            double subtotal = carrinho[i].getPreco() * quantidades[i];
            total += subtotal;

            System.out.printf("  %-12s x%d  →  R$ %.2f%n",
                carrinho[i].getNome(), quantidades[i], subtotal);
        }

        System.out.println("──────────────────────────────────────");
        System.out.printf("  Total da compra:      R$ %.2f%n", total);
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\n  Obrigado pela preferência! 😊");
    }

    // ── Leitura segura de inteiro ──────────────────────────
    static int lerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("⚠  Digite um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
