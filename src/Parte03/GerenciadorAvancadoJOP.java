package Parte03;

import java.io.*;
import javax.swing.*;

// Programa para Gerenciar as Disciplinas Avançado com JOP
public class GerenciadorAvancadoJOP {
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

    // Metodo para escrever a menu
    static String menu() {
        return """
                ----------  MENU  ----------
                [1] Adicionar Disciplina
                [2] Consultar Disciplina
                [3] Sair
                ----------------------------""";
    }

    // Metodo para formatar o nome da materia
    static String formatName(String nm) {
        nm = nm.toLowerCase();
        String[] nmSeparado = nm.split(" ");
        for (int i = 0; i < nmSeparado.length; i++) {
            if (!nmSeparado[i].isEmpty()) {
                nmSeparado[i] = nmSeparado[i].substring(0, 1).toUpperCase() + nmSeparado[i].substring(1);
            }
        }
        return String.join(" ", nmSeparado);
    }

    // Metodo para calcular a média da disciplina
    static float mediaNotas(float n1, float n2) {
        return (n1 + n2) / 2;
    }

    // Metodo para determinar o status da disciplina
    static String determinarStatus(float media, float f) {
        if (f >= 75) {
            if (media >= 7) {
                return "Aprovado";
            } else if (media > 4) {
                return "Recuperação";
            } else {
                return "Reprovado por nota";
            }
        } else {
            return "Reprovado por falta";
        }
    }

    // Metodo de formatação de como será mostrada as materias
    static String formatView(String[][] boletim, int i) {
        return "\nMatéria: " + boletim[i][0] + "\nNota 1: " + boletim[i][1] + "\nNota 2: " + boletim[i][2] +
                "\nFrequência: " + boletim[i][3] + "%" + "\nStatus: " + boletim[i][4] + "\n\n";
    }

    // Metodo para verifica se a disciplina existe RECURSIVA suport
    static boolean disciplinaExist(String[][] boletim , String consulta, int count) {
        return disciplinaExist(boletim, consulta, count, 0);
    }

    // Metodo para verifica se a disciplina existe RECURSIVA
    static boolean disciplinaExist(String[][] boletim , String consulta, int count, int i) {
        if (count == i) {
            return false;
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return true;
        } else {
            return disciplinaExist(boletim, consulta, count, i + 1);
        }
    }

    // Metodo para procurar pela disciplina consultada SUPORTE
    static String buscaEspecifica(String[][] boletim , String consulta, int count) {
        return buscaEspecifica(boletim, consulta, count, 0);
    }

    // Metodo para procurar pela disciplina consultada RECURSIVA
    static String buscaEspecifica(String[][]
                                          boletim , String consulta, int count, int i) {
        if (count == i) {
            return "\nDisciplina não cadastrada!\n";
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return formatView(boletim, i);
        } else {
            return buscaEspecifica(boletim, consulta, count, i + 1);
        }
    }

    // Metodo para criação do arquivo txt
    static void criarTxt() throws IOException {
        File archive = new File(PATH);

        File directory = archive.getParentFile();
        if (directory != null && !directory.exists()) directory.mkdirs();

        if (archive.exists()) JOptionPane.showMessageDialog(null, "Boletim já existe!", "Informe:", JOptionPane.WARNING_MESSAGE);
        else {
            if (archive.createNewFile()) JOptionPane.showMessageDialog(null, "Boletim criado com sucesso!", "Informe:", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo " + archive.getPath() + '.', "Erro:", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para escrita no arquivo txt
    static void escreverNoTxt(String[][] boletim, int count) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));

        for (int i = 0; i < count; i++) {
            String linha = boletim[i][0] + DELIMITADOR +
                    boletim[i][1] + DELIMITADOR +
                    boletim[i][2] + DELIMITADOR +
                    boletim[i][3] + DELIMITADOR +
                    boletim[i][4];

            writer.write(linha);
            writer.newLine();
        }

        JOptionPane.showMessageDialog(null, "Boletim escrito com sucesso!");
        writer.close();
    }

    // Metodo para leitura do arquivo txt
    static String[][] lerTxt() throws IOException {
        String[][] historico = new String[MAX_DISCIPLINAS][QTD_ATRIBUTOS];
        int count = 0;
        File archive = new File(PATH);

        if (archive.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo de dados encontrado: " + archive.getCanonicalPath());
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(DELIMITADOR);

                historico[count][0] = atributos[0];
                historico[count][1] = atributos[1];
                historico[count][2] = atributos[2];
                historico[count][3] = atributos[3];
                historico[count][4] = atributos[4];

                count++;
            }
            reader.close();
        }

        return historico;
    }

    // Metodo para pegar a quantidade de linhas do arquivo txt
    static int qtdLines() throws IOException {
        int count = 0;
        File archive = new File(PATH);

        if (archive.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            while ((reader.readLine()) != null) {
                count++;
            }
            reader.close();
        }

        return count;
    }
}
