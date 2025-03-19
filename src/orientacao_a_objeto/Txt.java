package orientacao_a_objeto;

import java.io.*;

public class Txt {

    public static void criarTxt(String path) throws IOException {
        File archive = new File(path);

        File directory = archive.getParentFile();
        if (directory != null && !directory.exists()) directory.mkdirs();

        if (archive.exists()) System.out.println("\nBoletim já existe!");
        else {
            if (archive.createNewFile()) System.out.println("\nBoletim criado com sucesso!");
            else System.out.println("\nErro ao criar o arquivo " + archive.getPath() + '.');
        }
    }

    // Metodo para escrita no arquivo txt
    public static void escreverNoTxt(String[][] boletim, int count, String delimitador, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        for (int i = 0; i < count; i++) {
            String linha = boletim[i][0] + delimitador +
                    boletim[i][1] + delimitador +
                    boletim[i][2] + delimitador +
                    boletim[i][3] + delimitador +
                    boletim[i][4];

            writer.write(linha);
            writer.newLine();
        }

        System.out.println("Boletim escrito com sucesso!");
        writer.close();
    }

    // Metodo para leitura do arquivo txt
    public static String[][] lerTxt(int maxDisciplinas, int atributes, String delimitador, String path) throws IOException {
        String[][] historico = new String[maxDisciplinas][atributes];
        int count = 0;
        File archive = new File(path);

        if (archive.exists()) {
            System.out.println("Arquivo de dados encontrado: " + archive.getCanonicalPath());
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(delimitador);

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
    public static int qtdLines(String path) throws IOException {
        int count = 0;
        File archive = new File(path);

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
