package orientacao_a_objeto;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

// Programa para Gerenciar as Disciplinas Avançado OO
public class GerenciadorAvancadoOO {
    private static final int MAX_DISCIPLINAS = 1000;
    private static final int QTD_ATRIBUTOS = 5;
    private static final String PATH = "historico/boletim.txt";
    private static final String DELIMITADOR = ";";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Formatacao.setBoletimClean(MAX_DISCIPLINAS, QTD_ATRIBUTOS);


        try {
            Formatacao.setBoletim(Txt.lerTxt(MAX_DISCIPLINAS, QTD_ATRIBUTOS, DELIMITADOR, PATH));
            Formatacao.setCount(Txt.qtdLines(PATH));

        } catch (IOException e) {
            System.out.println("\nOcorreu um erro ao ler o historico: " + e.getMessage() + "\n");
        }

        boolean flag = true;
        while (flag) {
            Formatacao.menu();
            System.out.print("Digite o número correspondente: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome da materia: "); // nome da materia
                    Formatacao materia = new Formatacao(Formatacao.formatName(sc.nextLine()));

                    Busca.setConsulta(materia.getNomeMateria());
                    if (Formatacao.getCount() == 0 || !Busca.disciplinaExist()) {
                        Formatacao.getBoletim()[Formatacao.getCount()][0] = materia.getNomeMateria(); // iniciava como 1 automaticamente -> count ++
                        System.out.print("Digite a nota da 1ª unidade: "); // nota 1
                        float nt1 = sc.nextFloat();
                        Calculo.setNota1(nt1);

                        System.out.print("Digite a nota da 2ª unidade: "); // nota 2
                        float nt2 = sc.nextFloat();
                        Calculo.setNota2(nt2);

                        System.out.printf("Qual foi a sua frequência em %s? [0 a 100] ", materia.getNomeMateria()); // frequência
                        float freq = sc.nextFloat();
                        Calculo.setFreq(freq);

                        System.out.print("\n");

                        String status = Calculo.determinarStatus(Calculo.mediaNotas(), Calculo.getFreq());

                        int pos = Formatacao.getCount();    // posição atual para inserir
                        Formatacao.getBoletim()[pos][0] = materia.getNomeMateria();

                        // Formatacao.getBoletim()[Formatacao.getCount()][3] -> getCount pegava posição 1


                        Formatacao.getBoletim()[pos][1] = String.format("%.1f", Calculo.getNota1());
                        Formatacao.getBoletim()[pos][2] = String.format("%.1f", Calculo.getNota2());
                        Formatacao.getBoletim()[pos][3] = String.format("%.0f", Calculo.getFreq());
                        Formatacao.getBoletim()[pos][4] = status; // Status

                        Formatacao.incrementoCount();
                        // agora inicializa como 0, no final da adição de dados da matéria o count da classe Formatação é atualizado

                    } else {
                        Formatacao.setCount(Formatacao.getCount() - 1);
                        System.out.println("\nDisciplina já cadastrada! Tente a opção 2\n");
                    }
                    break;
                case 2:
                    if (Formatacao.getCount() == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        System.out.print("Digite o nome da matéria a ser consultada: ");
                        Busca.setConsulta(Formatacao.formatName(sc.nextLine()));
                        System.out.println(Busca.buscaEspecifica());
                    }
                    break;
                case 3:
                    if (Formatacao.getCount() == 0) {
                        System.out.println("\nNenhuma matéria cadastrada!\n");
                    } else {
                        Busca.buscaAll();
                    }
                    break;
                case 4:
                    if (Formatacao.getCount() > 0 ) {
                        try {
                            Txt.criarTxt(PATH);
                            try {
                                Txt.escreverNoTxt(Formatacao.getBoletim(), Formatacao.getCount(), DELIMITADOR, PATH);
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
}