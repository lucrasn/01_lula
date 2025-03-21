package Parte04;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Programa para Gerencias as Disciplinas Avançado O.O.
 */
public class GerenciadorAvancadoOO {
    private static final int MAX_DISCIPLINAS = 1000;
    private static final int QTD_ATRIBUTOS = 5;
    private static final String PATH = "historico/boletim.txt";
    private static final String DELIMITADOR = ";";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Disciplina.setBoletimVazio(MAX_DISCIPLINAS, QTD_ATRIBUTOS);

        try {
            Disciplina.lerHistoricoBoletim(MAX_DISCIPLINAS, QTD_ATRIBUTOS, DELIMITADOR, PATH);
            Disciplina.pegarLinhasHistoricoBoletim(PATH);

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
                    String m = Disciplina.formatarNomeMateria(sc.nextLine());

                    if (Disciplina.getCount() == 0 || !Disciplina.materiaExiste(m)) {
                        System.out.print("Digite a nota da 1ª unidade: "); // nota 1
                        float nt1 = sc.nextFloat();

                        System.out.print("Digite a nota da 2ª unidade: "); // nota 2
                        float nt2 = sc.nextFloat();

                        System.out.printf("Qual foi a sua frequência em %s? [0 a 100] ", m); // frequência
                        float f = sc.nextFloat();

                        System.out.print("\n");

                        Disciplina materia = new Disciplina(m, nt1, nt2, f);

                    } else {
                        System.out.println("\nDisciplina já cadastrada! Tente a opção 2\n");
                    }
                    break;
                case 2:
                    if (Disciplina.getCount() == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        System.out.print("Digite o nome da matéria a ser consultada: ");
                        System.out.println(Disciplina.buscarMateria(sc.nextLine()));
                    }
                    break;
                case 3:
                    if (Disciplina.getCount() == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        Disciplina.getBoletimFormatado();
                    }
                    break;
                case 4:
                    if (Disciplina.getCount() > 0 ) {
                        try {
                            Disciplina.criarHistoricoBoletim(PATH);
                            try {
                                Disciplina.escreverHistoricoBoletim(PATH, DELIMITADOR);
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

    /**
     * Escrever o menu
     */
    public static void menu() {
        System.out.println("""
                ----------  MENU  ----------
                [1] Adicionar Disciplina
                [2] Consultar Disciplina
                [3] Exibir Disciplinas
                [4] Sair
                ----------------------------""");
    }
}