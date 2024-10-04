import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    CadastroJogadores cj = new CadastroJogadores();
    CadastroItens ci = new CadastroItens();
    Jogador jogadorLogado;

    public void executar(){
        Boolean cond = true;

        while (cond) {
            System.out.println("1. Cadastrar");
            System.out.println("2. Entrar");
            System.out.println("Any. Exit");
            int opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("Digite o email do jogador: ");
                    String email = scanner.next();
                    System.out.println("Digite o nome do jogador: ");
                    String nome = scanner.next();
                    System.out.println("Digite o pin do jogador: ");
                    String pin = scanner.next();

                    jogadorLogado = new Jogador(email, nome, pin);
                    cj.addJogador(jogadorLogado);
                    break;

                case 2:
                    System.out.println("Digite o email do jogador: ");
                    email = scanner.next();
                    System.out.println("Digite o pin do jogador: ");
                    pin = scanner.next();

                    jogadorLogado = cj.getJogador(email, pin);

                    cond = false;
                    break;

                default:
                    cond = false;
                    break;
            }
        }

        if (jogadorLogado != null) {
            cond = true;

            while (cond) {
                System.out.println("1. Cadastrar item");
                System.out.println("2. Excluir item");
                System.out.println("3. Listar itens do jogador");
                System.out.println("4. Listar itens dos outros jogadores");
                System.out.println("5. Buscar item");
                System.out.println("6. Listar propostas");
                System.out.println("7. Exibir estatísticas gerais");
                System.out.println("8. Exibir carta com mais PC e dono");
                System.out.println("9. Exibir cartas de um tipo");
                System.out.println("10. Obter Lootbox (Item aleatório)");
                System.out.println("11. Abrir Lootbox (Item aleatório)");
                System.out.println("Any. Exit");
                int opc = scanner.nextInt();

                switch (opc) {
                    case 1:
                        System.out.println("Digite o nome do item: ");
                        String nome = scanner.next();
                        System.out.println("Digite a descrição do item: ");
                        String descricao = scanner.next();
                        System.out.println("Digite o valor do item: ");
                        double valor = scanner.nextDouble();
                        String tipo = tipoItem();
                        System.out.println("Digite o PC (Pontos de Combate) do item: ");
                        int pc = scanner.nextInt();

                        Item i = new Item(nome, descricao, tipo, valor, jogadorLogado, pc);
                        jogadorLogado.addItem(i);
                        ci.addItem(i);
                        break;

                    case 2:
                        System.out.println("Escolha um item para remover:\n");
                        jogadorLogado.printItens();
                        int posi = (scanner.nextInt()) - 1;

                        i = jogadorLogado.getItem(posi);
                        jogadorLogado.removeItem(i);
                        ci.removeItem(i);
                        break;

                    case 3:
                        System.out.println("Itens do Jogador:\n");
                        jogadorLogado.printItens();
                        break;

                    case 4:
                        System.out.println("Itens dos outros jogadores:\n");
                        ci.printItens(jogadorLogado);
                        break;

                    case 5:
                        //Buscar item
                        //Faz proposta
                        break;

                    case 6:
                        //Listar propostas
                        //Aceita ou declina propostas
                        break;

                    case 7:
                        //Exibir estatísticas gerais
                        break;

                    case 8:
                        System.out.println(ci.cartaMaisPC().toString());
                        System.out.println(ci.cartaMaisPC().getDono().toString());
                        break;

                    case 9:
                        tipo = tipoItem();
                        ci.printItens(tipo);
                        break;
                    
                    case 10: //pega lootbox
                        Item item = new Item("Lootbox", "Concebe um item aleatório ao abrir", "Lootbox", 100, jogadorLogado, 0);
                        jogadorLogado.addItem(item);
                        break;

                    case 11: //abre lootbox
                        ArrayList<Item> inventory = jogadorLogado.getItens();
                        for (Item itemInventario : inventory) {
                            if (! itemInventario.getTipo().equals("Lootbox")) {
                                continue;
                            }

                            Lootbox lootbox = new Lootbox();
                            lootbox.openLootbox();
                            jogadorLogado.addItem(lootbox.getItem());

                            jogadorLogado.removeItem(itemInventario);

                            break;
                        }
                        break;
                    
                    default:
                        cond = false;
                        break;
                }
            }
        }
    }

    private String tipoItem() {
        System.out.println("Diga da carta do item: ");
        System.out.println("1. Carta de fogo");
        System.out.println("2. Carta de água");
        System.out.println("3. Carta de planta");
        System.out.println("4. Carta de dragão");
        int opcaux = scanner.nextInt();

        String tipo = "";

        switch (opcaux) {
            case 1:
                tipo = "Carta de fogo";
                break;
            case 2:
                tipo = "Carta de água";
                break;
            case 3:
                tipo = "Carta de planta";
                break;
            case 4:
                tipo = "Carta de dragão";
                break;
        }

        return tipo;
    }
}