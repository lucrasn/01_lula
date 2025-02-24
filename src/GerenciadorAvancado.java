import java.util.Scanner;

// Programa para Gerenciar as Disciplinas Avançado
public class GerenciadorAvancado {
    public static void main(String[] args) {
        String[][] boletim = new String[1000][5];
        Scanner sc = new Scanner(System.in);
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
                    boletim[count][0] = materia = materiaFormated(materia);

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
                    break;
                case 2:
                    if (count == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        System.out.print("Digite o nome da matéria a ser consultada: ");
                        String consulta = sc.nextLine();
                        consulta = materiaFormated(consulta);
                        buscaEspecifica(boletim, count, consulta);
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
    public static String materiaFormated(String nm) {
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
    public static void formatMaterias(String[][] boletim, int i) {
        System.out.println("\nMatéria: " + boletim[i][0]);
        System.out.println("Nota 1: " + boletim[i][1]);
        System.out.println("Nota 2: " + boletim[i][2]);
        System.out.println("Frequência: " + boletim[i][3] + "%");
        System.out.println("Status: " + boletim[i][4] + "\n");
    }

    // Metodo para buscar uma disciplina especifica
    public static void buscaEspecifica(String[][] boletim, int count, String consulta) {
        boolean cadastrada = false;

        for (int i = 0; i < count; i++) {
            if (boletim[i][0].equalsIgnoreCase(consulta)) {
                formatMaterias(boletim, i);
                cadastrada = true;
            }
        }

        if (!cadastrada) {
            System.out.println("Disciplina não encontrada");
        }
    }

    // Metodo para buscar todas as disciplinas
    public static void buscaAll(String[][] boletim, int count) {
        for (int i = 0; i < count; i++) {
            formatMaterias(boletim, i);
        }
    }
}
