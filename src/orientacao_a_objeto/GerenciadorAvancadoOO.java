package orientacao_a_objeto;

import java.io.IOException;
import java.util.Scanner;

// Programa para Gerenciar as Disciplinas Avançado OO
public class GerenciadorAvancadoOO {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[][] boletim = new String[Txt.getMaxDisciplinas()][Txt.getQtdAtributos()];
        int count = 0;

        try {
            String[][] historico = Txt.lerTxt();
            count = Txt.qtdLines();

            for (int i = 0; i < count; i++) {
                for (int j = 0; j < Txt.getQtdAtributos(); j++) boletim[i][j] = historico[i][j];
            }

        } catch (IOException e) {
            System.out.println("\nOcorreu um erro ao ler o historico: " + e.getMessage() + "\n");
        }

        boolean flag = true;
        while (flag) {
            System.out.println(menu());
            System.out.print("Digite o número correspondente: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome da materia: "); // nome da materia
                    String materia = sc.nextLine();
                    materia = formatName(materia);
                    Calculo dados;

                    if (count == 0 || !disciplinaExist(boletim, materia, count)) {
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
                        dados = new Calculo(nt1, nt2, freq);

                        String status = dados.determinarStatus(dados.mediaNotas(), freq); // Status
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
                    if (count > 0 ) {
                        try {
                            Txt.criarTxt();
                            try {
                                Txt.escreverNoTxt(boletim, count);
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
    static String menu() {
        return """
                ----------  MENU  ----------
                [1] Adicionar Disciplina
                [2] Consultar Disciplina
                [3] Sair
                ----------------------------""";
    }
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
}
