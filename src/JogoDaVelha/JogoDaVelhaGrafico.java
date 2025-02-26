package JogoDaVelha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelhaGrafico extends JFrame {
    private Campo[][] velha = new Campo[3][3];  // Mapeia o tabuleiro
    private char simboloAtual = 'X';
    private JButton[][] botoes = new JButton[3][3];  // Botões para a interface gráfica

    public JogoDaVelhaGrafico() {
        super("Jogo da Velha");
        setLayout(new GridLayout(3, 3));  // Grid de 3x3 para os botões
        inicializarJogo();

        // Adiciona os botões ao painel
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton(" ");
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                botoes[i][j].setFocusPainted(false);
                botoes[i][j].setPreferredSize(new Dimension(100, 100));
                botoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton fonte = (JButton) e.getSource();
                        jogada(fonte);
                    }
                });
                add(botoes[i][j]);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void jogada(JButton botao) {
        int linha = -1;
        int coluna = -1;

        // Localiza o botão pressionado e obtém a linha e a coluna correspondentes
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j] == botao) {
                    linha = i;
                    coluna = j;
                    break outer;
                }
            }
        }

        if (velha[linha][coluna].getSimbolo() == ' ' && simboloAtual != ' ') {
            velha[linha][coluna].setSimbolo(simboloAtual);
            botao.setText(String.valueOf(simboloAtual));

            String vencedor = verificaVitoria();
            if (!vencedor.equals("")) {
                JOptionPane.showMessageDialog(this, "Jogador " + vencedor + " venceu!");
                reiniciarJogo();
            } else {
                simboloAtual = (simboloAtual == 'X') ? 'O' : 'X';  // Troca de jogador
            }
        }
    }

    public void reiniciarJogo() {
        inicializarJogo();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText(" ");
            }
        }
    }

    public void inicializarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                velha[i][j] = new Campo();  // Cria um campo vazio
            }
        }
        simboloAtual = 'X';  // Começa com o jogador 'X'
    }

    public String verificaVitoria() {
        // Verifica as linhas
        for (int i = 0; i < 3; i++) {
            if (velha[i][0].getSimbolo() != ' ' && velha[i][0].getSimbolo() == velha[i][1].getSimbolo() && velha[i][0].getSimbolo() == velha[i][2].getSimbolo()) {
                return String.valueOf(velha[i][0].getSimbolo());
            }
        }

        // Verifica as colunas
        for (int i = 0; i < 3; i++) {
            if (velha[0][i].getSimbolo() != ' ' && velha[0][i].getSimbolo() == velha[1][i].getSimbolo() && velha[0][i].getSimbolo() == velha[2][i].getSimbolo()) {
                return String.valueOf(velha[0][i].getSimbolo());
            }
        }

        // Verifica as diagonais
        if (velha[0][0].getSimbolo() != ' ' && velha[0][0].getSimbolo() == velha[1][1].getSimbolo() && velha[0][0].getSimbolo() == velha[2][2].getSimbolo()) {
            return String.valueOf(velha[0][0].getSimbolo());
        }

        if (velha[0][2].getSimbolo() != ' ' && velha[0][2].getSimbolo() == velha[1][1].getSimbolo() && velha[0][2].getSimbolo() == velha[2][0].getSimbolo()) {
            return String.valueOf(velha[0][2].getSimbolo());
        }

        // Verifica se houve empate (se não houver mais espaços)
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (velha[i][j].getSimbolo() == ' ') {
                    empate = false;
                    break;
                }
            }
        }

        return empate ? "Empate" : "";
    }

    public static void main(String[] args) {
        new JogoDaVelhaGrafico();  // Cria a interface gráfica
    }
}     //commit and push