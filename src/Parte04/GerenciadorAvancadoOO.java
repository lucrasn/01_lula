package Parte04;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

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
            System.out.println("\nOcorreu um erro ao ler o histórico: " + e.getMessage() + "\n");
        }

        try {
            Aluno.lerRegistroAlunos(DELIMITADOR, USER);

            int numAlunos = Aluno.pegarLinhasRegistroDeAlunos(USER);

            while (Disciplina.getBoletim().size() < numAlunos) {
                Disciplina.getBoletim().add(new ArrayList<>());
            }

            while (Disciplina.getCount().size() < numAlunos) {
                Disciplina.getCount().add(0);
            }

        } catch (IOException e) {
            System.out.println("\nOcorreu um erro ao ler o registro: " + e.getMessage() + "\n");
        }

        boolean flag = false;
        while (!flag) {
            Aluno aluno = null;
            boolean cadastrado = false;
            while (!cadastrado) {
                entrar();
                System.out.print("Digite o número correspondente: ");
                int opcaoInicial = sc.nextInt();
                sc.nextLine();

                switch (opcaoInicial) {

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
                                    aluno = Aluno.login(nome, matricula);
                                    if (aluno != null) {
                                        cadastrado = true;
                                    }
                                } catch (IOException e) {
                                    System.out.println("\nErro ao criar o registro: " + e.getMessage());
                                }
                            } catch (IOException e) {
                                System.out.println("\nErro ao criar o arquivo: " + e.getMessage());
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

                            aluno = Aluno.login(nm, matricula);
                            if (aluno != null) {
                                cadastrado = true;
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Até a próxima!");
                        flag = true;
                        cadastrado = false;
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
                        System.out.print("\nDigite o nome da matéria: ");
                        String m = Disciplina.formatarNomeMateria(sc.nextLine());

                        if (nDisciplinasAluno == 0 || !Disciplina.materiaExiste(aluno, m)) {
                            System.out.print("Digite a nota da 1ª unidade: ");
                            float nt1 = sc.nextFloat();

                            System.out.print("Digite a nota da 2ª unidade: ");
                            float nt2 = sc.nextFloat();

                            System.out.printf("Qual foi a sua frequência em %s? [0 a 100]: ", m);
                            float f = sc.nextFloat();

                            System.out.print("\n");

                            new Disciplina(aluno, m, nt1, nt2, f);

                        } else {
                            System.out.println("\nDisciplina já cadastrada! Tente a opção 2\n");
                        }
                        break;

                    case 2:
                        if (nDisciplinasAluno == 0) {
                            System.out.println("\nNenhuma matéria cadastrada!\n");
                        } else {
                            System.out.print("Digite o nome da matéria a ser consultada: ");
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
                                Disciplina.escreverHistoricoBoletim(PATH, USER, DELIMITADOR);
                            } catch (IOException e) {
                                System.out.println("\nErro ao salvar histórico: " + e.getMessage());
                            }
                        }
                        cadastrado = false;
                        break;

                    default:
                        System.out.println("Opção inválida, tente novamente!\n");
                }
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