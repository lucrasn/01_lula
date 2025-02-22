import java.util.Scanner;

// Programa para Gerenciar as Disciplinas
public class Main {
    public static void main(String[] args) {
        String[][] boletim = new String[1000][5];
        Scanner sc = new Scanner(System.in);
        boolean flagMain = true;
        int count = 0; // Contador para checar se tem matérias cadastradas

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

                        System.out.print("Digite a nota da 1ª unidade: ");
                        float nt1 = sc.nextFloat();

                        System.out.print("Digite a nota da 2ª unidade: ");
                        float nt2 = sc.nextFloat();

                        System.out.printf("Qual foi a sua frequência em %s? [0 a 100] ", materia);
                        float freq = sc.nextFloat();

                        count =+ 1; //teste

                        break;
                    case 2:
                        flagSub = false;
                        if(count == 0){
                            System.out.println("Nenhuma matéria cadastrada");
                        } else {
                            busca(boletim, count, sc);
                        }

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
        /*Ele não tava deixando a letra maiúscula antes porque quando a gente faz "for (String name : nmSeparado)"
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


    /* Caso exista pelo menos 1 matéria cadastrada (else do case 2) a classe vai
    solicitar o nome da matéria e buscar se ela foi adicionada anteriormente, caso a matéria não esteja cadastrada
    o usuário será informado e deverá informar uma disciplina cadastrada
    Tambem irá printar todos os dados relacionados a disciplina
     */
    public static String busca(String boletim[][], int count, Scanner sc) {

        System.out.println("Digite o nome da matéria a ser consultada");
        String consulta = sc.nextLine();
        consulta = materiaFormated(consulta);

        boolean cadastrada = false;
        for (int i = 0; i < count; i++) {
            if (boletim[i][0].equalsIgnoreCase(consulta)) {
                System.out.println("Matéria: " + boletim[i][0]);
                System.out.println("Nota 1: " + boletim[i][1]);
                System.out.println("Nota 2: " + boletim[i][2]);
                System.out.println("Frequência: " + boletim[i][3] + "%");
                System.out.println("Status: " + boletim[i][4] + "\n");
                cadastrada = true;
            }
        }
        if (!cadastrada) {
            System.out.println("Disciplina não encontrada");
        }
        return busca(boletim, count, sc);
    }


    // Metodo para calcula a média da disciplina
    public static float mediaNotas(float n1, float n2) {
        return (n1 + n2) / 2;
    }

    // Metodo para formatar a frequência
//    public static float freqFormated(float f) {
//
//    }

    // Metodo para determinar o status da disciplina
//    public static String determinarStatus(float media, float f) {
//        if (f >= 75) {
//
//        }
//    }
}