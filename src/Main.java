import java.util.Scanner;

// Programa para Gerenciar as Disciplinas
public class Main {
    public static void main(String[] args) {
        String[][] boletim = new String[1000][5];
        Scanner sc = new Scanner(System.in);
        boolean flagMain = true;

        while (flagMain) {
            boolean flagSub = true;

            while (flagSub) {
                menu();
                System.out.print("Digite o número correspondente: ");
                int opcao = sc.nextInt();
                sc.nextLine(); // Sabe Allan vou precisar ver o vídeo que tu viu para eu entender o porquê disso

                switch (opcao) {
                    case 1:
                        flagSub = false;

                        System.out.print("Digite o nome da materia: ");
                        String materia = sc.nextLine();
                        materia = materiaFormated(materia);
                        System.out.println(materia);

                        // System.out.print("Digite a nota da");

                        break;
                    case 2:
                        flagSub = false;

                        break;
                    case 3:
                        flagSub = false;

                        break;
                    case 4:
                        flagSub = false;
                        flagMain = false;
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente\n");
                }

            }
        }
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
        /*Ele não tava deixando a letra maiúscula antes por que quando a gente faz "for (String name : nmSeparado)"
        * esse "name" não vai ser de fato cada elemento do array, mas sim uma cópia.
        * Então a gente até botava a primeira letra maiúscula mas a informação se perdia porque a gente não tava explicitamente
        * alterando os elementos do array! Por isso tive q mudar o raciocínio um pouco.*/
        for (int i = 0; i < nmSeparado.length; i++) {
            if (!nmSeparado[i].isEmpty()) { // Nunca se sabe
                nmSeparado[i] = nmSeparado[i].substring(0, 1).toUpperCase() + nmSeparado[i].substring(1);
            }
        }
        return String.join(" ", nmSeparado);
    }
}