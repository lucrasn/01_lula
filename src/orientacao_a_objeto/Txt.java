package orientacao_a_objeto;

import java.io.*;

public class Txt {
    private static final int MAX_DISCIPLINAS = 1000;
    private static final int QTD_ATRIBUTOS = 5;
    private static final String PATH = "historico/boletim.txt";
    private static final String DELIMITADOR = ";";

    public static int getMaxDisciplinas() {
        return MAX_DISCIPLINAS;
    }

    public static int getQtdAtributos() {
        return QTD_ATRIBUTOS;
    }

    public static String getDELIMITADOR() {
        return DELIMITADOR;
    }

    public static String getPATH() {
        return PATH;
    }

    public static void criarTxt() throws IOException {
        File archive = new File(PATH);

        File directory = archive.getParentFile();
        if (directory != null && !directory.exists()) directory.mkdirs();

        if (archive.exists()) System.out.println("\nBoletim já existe!");
        else {
            if (archive.createNewFile()) System.out.println("\nBoletim criado com sucesso!");
            else System.out.println("\nErro ao criar o arquivo " + archive.getPath() + '.');
        }
    }

    // Metodo para escrita no arquivo txt
    public static void escreverNoTxt(String[][] boletim, int count) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));

        for (int i = 0; i < count; i++) {
            String linha = boletim[i][0] + DELIMITADOR +
                    boletim[i][1] + DELIMITADOR +
                    boletim[i][2] + DELIMITADOR +
                    boletim[i][3] + DELIMITADOR +
                    boletim[i][4];

            writer.write(linha);
            writer.newLine();
        }

        System.out.println("Boletim escrito com sucesso!");
        writer.close();
    }

    // Metodo para leitura do arquivo txt
    public static String[][] lerTxt() throws IOException {
        String[][] historico = new String[MAX_DISCIPLINAS][QTD_ATRIBUTOS];
        int count = 0;
        File archive = new File(PATH);

        if (archive.exists()) {
            System.out.println("Arquivo de dados encontrado: " + archive.getCanonicalPath());
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(DELIMITADOR);

                historico[count][0] = atributos[0];
                historico[count][1] = atributos[1];
                historico[count][2] = atributos[2];
                historico[count][3] = atributos[3];
                historico[count][4] = atributos[4];

                count++;
            }
            reader.close();
        }

        return historico;
    }

    // Metodo para pegar a quantidade de linhas do arquivo txt
    public static int qtdLines() throws IOException {
        int count = 0;
        File archive = new File(PATH);

        if (archive.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            while ((reader.readLine()) != null) {
                count++;
            }
            reader.close();
        }
        return count;
    }
}
