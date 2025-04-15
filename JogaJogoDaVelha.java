import java.util.Random;
import java.util.Scanner;

public class JogaJogoDaVelha {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random aleatorio = new Random();
        boolean jogarDeNovo = true;

        System.out.println("Bem-vindo ao teste do Jogo da Velha!");
        System.out.println("Digite 1 para jogar ou 0 para sair: ");
        int opcao = scanner.nextInt();

        while (jogarDeNovo) {
            System.out.println("Digite o tamanho do tabuleiro: ");
            int tamanho;
            while (true) {
                tamanho = scanner.nextInt();
                if (tamanho > 0) {
                    break;
                }
                System.out.println("O tamanho deve ser maior que 0. Tente novamente:");
            }

            JogoDaVelha jogo = new JogoDaVelha(tamanho);
            while (jogo.vencedor() == 0) {
                int linha = aleatorio.nextInt(tamanho);
                int coluna = aleatorio.nextInt(tamanho);

                try {
                    jogo.poePeca(linha, coluna); // tenta jogar
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }

            // tabuleiro final
            System.out.println("Tabuleiro final:");
            System.out.println(jogo.toString());

            int vencedor = jogo.vencedor();
            if (vencedor == 1) {
                System.out.println("Jogador X venceu!");
            } else if (vencedor == -1) {
                System.out.println("Jogador O venceu!");
            } else {
                System.out.println("Empate!");
            }

            System.out.println("Deseja jogar novamente? (1-sim, 0-nao): ");
            int resposta = scanner.nextInt();
            jogarDeNovo = (resposta == 1);
        }

        System.out.println("Obrigado por jogar!");
        scanner.close();
    }
}