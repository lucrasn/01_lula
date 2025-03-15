package orientacao_a_objeto;

import javax.swing.*;
import java.io.IOException;


// Programa para Gerenciar as Disciplinas Avançado OO
public class GerenciadorAvancadoOO {
    static final int MAX_DISCIPLINAS = 1000;
    static final int QTD_ATRIBUTOS = 5;
    static final String PATH = "historico/boletim.txt";
    static final String DELIMITADOR = ";";

    public static void main(String[] args) {
        String[][] boletim = new String[MAX_DISCIPLINAS][QTD_ATRIBUTOS];
        int count = 0;

        try {
            String[][] historico = lerTxt();
            count = qtdLines();

            for (int i = 0; i < count; i++) {
                for (int j = 0; j < QTD_ATRIBUTOS; j++) boletim[i][j] = historico[i][j];
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler o historico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        boolean flag = true;
        while (flag) {
            JOptionPane.showMessageDialog(null, menu(), "Menu", JOptionPane.INFORMATION_MESSAGE);
            String opcaoStr = JOptionPane.showInputDialog("Digite o número correspondente: ");
            int opcao = Integer.parseInt(opcaoStr);

            switch (opcao) {
                case 1:
                    String materia = JOptionPane.showInputDialog("Digite o nome da materia: "); // nome da materia
                    materia = formatName(materia);

                    if (count == 0 || !disciplinaExist(boletim, materia, count)) {
                        boletim[count][0] = materia;

                        String nt1Str = JOptionPane.showInputDialog("Digite a nota da 1ª unidade: "); // nota 1
                        float nt1 = Float.parseFloat(nt1Str);
                        boletim[count][1] = String.format("%.1f", nt1);

                        String nt2Str = JOptionPane.showInputDialog("Digite a nota da 2ª unidade: "); // nota 2
                        float nt2 = Float.parseFloat(nt2Str);
                        boletim[count][2] = String.format("%.1f", nt2);

                        String mensagem = String.format("Qual foi a sua frequência em %s? [0 a 100] ", materia);
                        String freqStr = JOptionPane.showInputDialog(mensagem); // frequência
                        float freq = Float.parseFloat(freqStr);
                        boletim[count][3] = String.format("%.0f", freq);

                        String status = determinarStatus(mediaNotas(nt1, nt2), freq); // Status
                        boletim[count][4] = status;

                        count++;
                    } else {
                        JOptionPane.showMessageDialog(null, "Disciplina já cadastrada! Tente a opção 2", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2:
                    if (count == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhuma matéria cadastrada!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String consulta = JOptionPane.showInputDialog(null, "Digite o nome da matéria a ser consultada: ", "Matéria?", JOptionPane.QUESTION_MESSAGE);
                        consulta = formatName(consulta);
                        JOptionPane.showMessageDialog(null, buscaEspecifica(boletim, consulta, count), "Resultado:", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 3:
                    if (count > 0 ) {
                        try {
                            criarTxt();
                            try {
                                escreverNoTxt(boletim, count);
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao escrever no arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Até a próxima!", "Bye Bye", JOptionPane.PLAIN_MESSAGE);
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
