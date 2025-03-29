package Parte04;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Programa para Gerencias as Disciplinas Avançado O.O.
 */
public class GerenciadorAvancadoOO {
    private static final String PATH = "historico/boletim.txt";
    private static final String[] DELIMITADOR = new String[]{":", ";"};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        try {
            Disciplina.lerHistoricoBoletim(DELIMITADOR, PATH);
            Disciplina.pegarNumeroDeMateriasHistoricoBoletim(DELIMITADOR[0], PATH);

        } catch (IOException e) {
            System.out.println("\nOcorreu um erro ao ler o historico: " + e.getMessage() + "\n");
        }

        boolean flag = true;
        while (flag) {
            menu();
            System.out.print("Digite o número correspondente: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            // TODO: O aluno teria que "logar" primeiro antes de ir adiante no código
            Aluno aluno = new Aluno(); // por enquanto para o codigo não quebrar

            int nDisciplinasAluno = Disciplina.getCount().get(aluno.getINDICE());
            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome da materia: "); // nome da materia
                    String m = Disciplina.formatarNomeMateria(sc.nextLine());


                    if (nDisciplinasAluno == 0 || !Disciplina.materiaExiste(aluno, m)) {
                        System.out.print("Digite a nota da 1ª unidade: "); // nota 1
                        float nt1 = sc.nextFloat();

                        System.out.print("Digite a nota da 2ª unidade: "); // nota 2
                        float nt2 = sc.nextFloat();

                        System.out.printf("Qual foi a sua frequência em %s? [0 a 100] ", m); // frequência
                        float f = sc.nextFloat();

                        System.out.print("\n");

                        Disciplina materia = new Disciplina(aluno, m, nt1, nt2, f);

                    } else {
                        System.out.println("\nDisciplina já cadastrada! Tente a opção 2\n");
                    }
                    break;
                case 2:
                    if (nDisciplinasAluno == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        System.out.print("Digite o nome da matéria a ser consultada: ");
                        System.out.println(Disciplina.buscarMateria(aluno, sc.nextLine()));
                    }
                    break;
                case 3:
                    if (nDisciplinasAluno == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        Disciplina.getBoletimFormatado(aluno);
                    }
                    break;
                case 4:
                    if (nDisciplinasAluno > 0 ) {
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