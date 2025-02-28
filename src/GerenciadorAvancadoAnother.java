import java.util.Locale;
import java.util.Scanner;


// Programa para Gerenciar as Disciplinas Avançado (Diferente Versão)
public class GerenciadorAvancadoAnother {
    public static void main(String[] args) {
        String[][] boletim = new String[1000][5];
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        boolean flag = true;
        int count = 0;

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

                    if (count == 0 || !disciplinaExist(boletim, materia, count, opcao)) {
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
                    System.out.println("\nAté a próxima!\n");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!\n");
            }
        }
        sc.close();
    }

    // Metodo para escrever a menu
    public static void menu() {
        System.out.println("----------  MENU  ----------");
        System.out.println("[1] Adicionar Disciplina\n[2] Consultar Disciplina\n[3] Exibir Disciplinas\n[4] Sair");
        System.out.println("----------------------------");
    }

    // Metodo para formatar o nome da materia
    public static String formatName(String nm) {
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
    public static float mediaNotas(float n1, float n2) {
        return (n1 + n2) / 2;
    }

    // Metodo para determinar o status da disciplina
    public static String determinarStatus(float media, float f) {
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
    public static String formatView(String[][] boletim, int i) {
        return "\nMatéria: " + boletim[i][0] + "\nNota 1: " + boletim[i][1] + "\nNota 2: " + boletim[i][2] +
                "\nFrequência: " + boletim[i][3] + "%" + "\nStatus: " + boletim[i][4] + "\n\n";
    }

    // Metodo para verifica se a disciplina existe SUPORTE
    public static boolean disciplinaExist(String[][] boletim , String consulta, int count) {
        return disciplinaExist(boletim, consulta, count, 0);
    }

    // Metodo para verifica se a disciplina existe RECURSIVA
    public static boolean disciplinaExist(String[][] boletim , String consulta, int count, int i) {
        if (count == i) {
            return false;
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return true;
        } else {
            return disciplinaExist(boletim, consulta, count, i + 1);
        }
    }

    // Metodo para procurar pela disciplina consultada SUPORTE
    public static String buscaEspecifica(String[][] boletim , String consulta, int count) {
        return buscaEspecifica(boletim, consulta, count, 0);
    }

    // Metodo para procurar pela disciplina consultada RECURSIVA
    public static String buscaEspecifica(String[][] boletim , String consulta, int count, int i) {
        if (count == i) {
            return "\nDisciplina não cadastrada!\n";
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return formatView(boletim, i);
        } else {
            return buscaEspecifica(boletim, consulta, count, i + 1);
        }
    }

    // Metodo para buscar todas as disciplinas
    public static void buscaAll(String[][] boletim, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(formatView(boletim, i));
        }
    }
}
