import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] boletim = new String[1000][5];
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            boolean flagSub = true;

            while (flagSub) {
                menu();
                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        flagSub = false;
                        System.out.println("Digite o nome da materia: ");
                        String materia = sc.nextLine();
                        materia = materiaFormated(materia);
                        System.out.println(materia);
                        break;
                    case 2:
                        flagSub = false;
                        break;
                    case 3:
                        flagSub = false;
                        break;
                    case 4:
                        flagSub = false;
                        flag = false;
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente\n");
                }

            }
        }
    }

    public static void menu() {
        System.out.println("-=-=-=-=-  MENU  -=-=-=-=-");
        System.out.println("[1] Adicionar Disciplina\n[2] Consultar Disciplina\n[3] Exibir Disciplinas\n[4] Sair");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    

    public static String materiaFormated(String nm) {
        String[] materiaDiv = nm.split(" ");
        for (String name : materiaDiv) {
            // String.valueOf(name.charAt(0)).toUpperCase();
            name = name.replace(name.substring(0, 1), name.substring(0, 1).toUpperCase());
        }
        return String.join(" ", materiaDiv);
    }
}