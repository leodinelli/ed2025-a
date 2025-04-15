public class JogoDaVelha {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int tabuleiro[][];
    protected int jogador;

    public JogoDaVelha(int tamanho) {
        tabuleiro = new int[tamanho][tamanho];
        limpaTabuleiro();
    }

    public void limpaTabuleiro() {
        int tamanho = tabuleiro.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }
        jogador = X;
    }

    public void poePeca(int i, int j) {
        int tamanho = tabuleiro.length;
        if (i < 0 || i >= tamanho || j < 0 || j >= tamanho) {
            throw new IllegalArgumentException("Posição Inválida");
        }
        if (tabuleiro[i][j] != VAZIO) {
            throw new IllegalArgumentException("Posição Ocupada");
        }
        tabuleiro[i][j] = jogador;
        jogador = -jogador;
    }

    public boolean eVencedor(int marca) {
        int tamanho = tabuleiro.length;

        // verifica as linhas do tabuleiro
        for (int i = 0; i < tamanho; i++) {
            int soma = 0;
            for (int j = 0; j < tamanho; j++) {
                soma += tabuleiro[i][j];
            }
            if (soma == marca * tamanho) {
                return true;
            }
        }

        // verifica as colunas do tabuleiro
        for (int j = 0; j < tamanho; j++) {
            int soma = 0;
            for (int i = 0; i < tamanho; i++) {
                soma += tabuleiro[i][j];
            }
            if (soma == marca * tamanho) {
                return true;
            }
        }

        // verifica a diagonal 1 do tabuleiro
        int somaDiagonal_1 = 0;
        for (int i = 0; i < tamanho; i++) {
            somaDiagonal_1 += tabuleiro[i][i];
        }
        if (somaDiagonal_1 == marca * tamanho) {
            return true;
        }

        // verifica a diagonal 2 do tabuleiro
        int somaDiagonal_2 = 0;
        for (int i = 0; i < tamanho; i++) {
            somaDiagonal_2 += tabuleiro[i][tamanho - 1 - i];
        }
        if (somaDiagonal_2 == marca * tamanho) {
            return true;
        }

        return false;
    }

    public int vencedor() {
        // X venceu
        if (eVencedor(X)) {
            return X;
        }
        // O venceu
        if (eVencedor(O)) {
            return O;
        }

        // verifica se ainda tem jogadas
        int tamanho = tabuleiro.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == VAZIO) {
                    return 0; // Jogo continua
                }
            }
        }
        // Empate, deu velha
        return 2;
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        int tamanho = tabuleiro.length;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == X) {
                    retorno.append("X");
                } else if (tabuleiro[i][j] == O) {
                    retorno.append("O");
                } else {
                    retorno.append(" ");
                }

                if (j < tamanho - 1) {
                    retorno.append("|");
                }
            }

            if (i < tamanho - 1) {
                retorno.append("\n");
                for (int j = 0; j < tamanho; j++) {
                    retorno.append("-");
                    if (j < tamanho - 1) {
                        retorno.append("+");
                    }
                }
                retorno.append("\n");
            }
        }

        return retorno.toString();
    }
}