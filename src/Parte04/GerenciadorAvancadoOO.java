package Parte04;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Programa para Gerencias as Disciplinas Avançado O.O.
 */
public class GerenciadorAvancadoOO {
    private static final String PATH = "historico/boletim.txt";
    private static final String USER = "historico/alunos.txt";
    private static final String[] DELIMITADOR = new String[]{";", ":"};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        try {
            Disciplina.lerHistoricoBoletim(DELIMITADOR, PATH);
            Disciplina.pegarNumeroDeMateriasHistoricoBoletim(DELIMITADOR[1], PATH);

        } catch (IOException e) {
            System.out.println("\nOcorreu um erro ao ler o historico: " + e.getMessage() + "\n");
        }

        try{
            Aluno.lerRegistroAlunos(DELIMITADOR, USER);
            Aluno.pegarLinhasRegistroDeAlunos(USER);
        } catch (IOException e){
            System.out.println("\nOcorreu um erro ao ler o registro: " + e.getMessage() + "\n");
        }


        Aluno aluno = new Aluno();
        boolean cadastrado = false;
        while (!cadastrado) {
            entrar();
            System.out.print("Digite o número correspondente: ");
            int opcaoInicial = sc.nextInt();
            sc.nextLine();

            switch (opcaoInicial) {
                /*TODO: corrigir a possibilidade de dois alunos se cadastrarem e
                 * o segundo aluno ocupar uma posição maior no arquivo e cadastrar suas
                 * disciplinas numa posição menor no arquivo boletim
                 */

                case 1:
                    System.out.print("\nDigite o nome completo do aluno: ");
                    String nome = Aluno.formatarNomeAluno(sc.nextLine());

                    if (!Aluno.alunoExiste(nome, USER)) {
                        System.out.print("Digite a sua matrícula: ");
                        String matricula = sc.next();
                        try {
                            Aluno.criarRegistroDeAlunos(USER);
                            try {
                                Aluno.cadastrarAluno(USER, nome, matricula, DELIMITADOR[0]);
                                aluno = new Aluno(nome, matricula);
                            } catch (IOException e) {
                                System.out.println("\nOcorreu um erro ao criar o registro: " + e.getMessage());
                            }
                        } catch (IOException e){
                            System.out.println("\nOcorreu um erro ao criar o arquivo: " + e.getMessage());

                        }
                    } else {
                        System.out.println("\nAluno já cadastrado no sistema, tente realizar o Login!\n");
                    }
                    break;
                case 2:
                    System.out.print("\nDigite o nome completo do aluno: ");
                    String nm = Aluno.formatarNomeAluno(sc.nextLine());

                    if (!Aluno.alunoExiste(nm, USER)) {
                        System.out.println("\nAluno não cadastrado, por favor realize seu cadastro antes do Login\n");
                    } else {
                        System.out.print("Digite a sua matrícula: ");
                        String matricula = sc.next();

                        if (Aluno.login(nm, matricula)) {
                            cadastrado = true;

                        }
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida, tente novamente!\n");
            }
        }
        while (cadastrado) {
            menu();
            System.out.print("Digite o número correspondente: ");
            int opcao = sc.nextInt();
            sc.nextLine();

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
                    if (nDisciplinasAluno > 0) {
                        try {
                            Disciplina.criarHistoricoBoletim(PATH);
                            try {
                                Disciplina.escreverHistoricoBoletim(PATH, USER, DELIMITADOR);
                            } catch (IOException e) {
                                System.out.println("\nOcorreu um erro ao escrever no arquivo: " + e.getMessage());
                            }
                        } catch (IOException e) {
                            System.out.println("\nOcorreu um erro ao criar o arquivo: " + e.getMessage());
                        }
                    }
                    System.out.println("Até a próxima!");
                    cadastrado = false;
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
                [4] Deslogar
                ----------------------------""");
    }

    public static void entrar() {
        System.out.println("""
                ----------  ENTRAR  ----------
                [1] Cadastrar Aluno
                [2] Realizar Login
                [3] Sair
                ------------------------------""");
    }
}