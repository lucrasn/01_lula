package Parte02;

import java.util.Scanner;
import java.util.Locale;
import java.io.*;

// Programa para Gerenciar as Disciplinas Avançado
public class GerenciadorAvancado {
    static final int MAX_DISCIPLINAS = 1000;
    static final int QTD_ATRIBUTOS = 5;
    static final String PATH = "historico/boletim.txt";
    static final String DELIMITADOR = ";";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        String[][] boletim = new String[MAX_DISCIPLINAS][QTD_ATRIBUTOS];
        int count = 0;

        try {
            String[][] historico = lerTxt();
            count = qtdLines();

            for (int i = 0; i < count; i++) {
                for (int j = 0; j < QTD_ATRIBUTOS; j++) boletim[i][j] = historico[i][j];
            }

        } catch (IOException e) {
            System.out.println("\nOcorreu um erro ao ler o historico: " + e.getMessage() + "\n");
        }

        boolean flag = true;
        while (flag) {
            menu();
            System.out.print("Digite o número correspondente: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome da materia: "); // nome da materia
                    String materia = sc.nextLine();
                    materia = formatName(materia);

                    if (count == 0 || !disciplinaExist(boletim, materia, count).exist()) {
                        boletim[count][0] = materia;
                        System.out.print("Digite a nota da 1ª unidade: "); // nota 1
                        float nt1 = sc.nextFloat();
                        boletim[count][1] = String.format("%.1f", nt1);

                        System.out.print("Digite a nota da 2ª unidade: "); // nota 2
                        float nt2 = sc.nextFloat();
                        boletim[count][2] = String.format("%.1f", nt2);

                        System.out.printf("Qual foi a sua frequência em %s? [0 a 100] ", materia); // frequência
                        float freq = sc.nextFloat();
                        boletim[count][3] = String.format("%.0f", freq);

                        System.out.print("\n");

                        String status = determinarStatus(mediaNotas(nt1, nt2), freq); // Status
                        boletim[count][4] = status;

                        count++;
                    } else {
                        System.out.println("\nDisciplina já cadastrada! Tente a opção 2\n");
                    }
                    break;
                case 2:
                    if (count == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        System.out.print("Digite o nome da matéria a ser consultada: ");
                        String consulta = sc.nextLine();
                        consulta = formatName(consulta);
                        System.out.println(buscaEspecifica(boletim, consulta, count));
                    }
                    break;
                case 3:
                    if (count == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        buscaAll(boletim, count);
                    }
                    break;
                case 4:
                    if (count > 0 ) {
                        try {
                            criarTxt();
                            try {
                                escreverNoTxt(boletim, count);
                            } catch (IOException e) {
                                System.out.println("\nOcorreu um erro ao escrever no arquivo: " + e.getMessage());
                            }
                        } catch (IOException e) {
                            System.out.println("\nOcorreu um erro ao criar o arquivo: " + e.getMessage());
                        }
                    }
                    System.out.println("Até a próxima!");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!\n");
            }
        }
        sc.close();
    }

    // Metodo para escrever a menu
    public static void menu() throws IOException {
        System.out.println("----------  MENU  ----------");
        System.out.println("[1] Adicionar Disciplina\n[2] Consultar Disciplina\n[3] Exibir Disciplinas\n[4] Sair");
        System.out.println("----------------------------");
    }

    // Metodo para formatar o nome da materia
    public static String formatName(String nm) throws IOException {
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
    public static float mediaNotas(float n1, float n2) throws IOException {
        return (n1 + n2) / 2;
    }

    // Metodo para determinar o status da disciplina
    public static String determinarStatus(float media, float f) throws IOException {
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
    public static String formatView(String[][] boletim, int i) throws IOException {
        return "\nMatéria: " + boletim[i][0] + "\nNota 1: " + boletim[i][1] + "\nNota 2: " + boletim[i][2] +
                "\nFrequência: " + boletim[i][3] + "%" + "\nStatus: " + boletim[i][4] + "\n\n";
    }

    // Classe para servir de tupla (boolena, int)
    public record Verify(boolean exist, int indice) {}

    // Metodo para verificar se a disciplina ja existe
    public static Verify disciplinaExist(String[][] boletim , String consulta, int count) throws IOException {
        return disciplinaExist(boletim, consulta, count, 0);
    }

    // Metodo para verificar se a disciplina ja existe RECURSIVA
    public static Verify disciplinaExist(String[][] boletim , String consulta, int count, int i) throws IOException {
        if (count == i) {
            return new Verify(false, count);
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return new Verify(true, i);
        } else {
            return disciplinaExist(boletim, consulta, count, i + 1);
        }
    }

    // Metodo para buscar uma disciplina especifica
    public static String buscaEspecifica(String[][] boletim , String consulta, int count) throws IOException {
        Verify vy = disciplinaExist(boletim, consulta, count);
        if (!vy.exist()) {
            return "\nDisciplina não cadastrada!\n";
        } else {
            return formatView(boletim, vy.indice());
        }
    }

    // Metodo para buscar todas as disciplinas
    public static void buscaAll(String[][] boletim, int count) throws IOException {
        for (int i = 0; i < count; i++) {
            System.out.print(formatView(boletim, i));
        }
    }

    // Metodo para criação do arquivo txt
    public static void criarTxt() throws IOException {
        File archive = new File(PATH);

        File directory = archive.getParentFile();
        if (directory != null && !directory.exists()) directory.mkdirs();

        if (archive.exists()) System.out.println("\nBoletim já existe!");
        else {
            if (archive.createNewFile()) System.out.println("\nBoletim criado com sucesso!");
            else System.out.println("\nErro ao criar o arquivo " + archive.getPath() + '.');
        }
    }

    // Metodo para escrita no arquivo txt
    public static void escreverNoTxt(String[][] boletim, int count) throws IOException {
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

        System.out.println("Boletim escrito com sucesso!");
        writer.close();
    }

    // Metodo para leitura do arquivo txt
    public static String[][] lerTxt() throws IOException {
        String[][] historico = new String[MAX_DISCIPLINAS][QTD_ATRIBUTOS];
        int count = 0;
        File archive = new File(PATH);

        if (archive.exists()) {
            System.out.println("Arquivo de dados encontrado: " + archive.getCanonicalPath());
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
    public static int qtdLines() throws IOException {
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
