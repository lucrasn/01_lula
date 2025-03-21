package Parte04;

import java.io.*;

public class Disciplina {
    private static String[][] boletim;
    private static int count;
    private String nomeMateria;
    private final int INDICE;
    private final float NOTA1;
    private final float NOTA2;
    private final float FREQUENCIA;
    private final float MEDIA;
    private final float FINAL;

    public Disciplina() {
        this.nomeMateria = "Materia";
        this.INDICE = count;
        this.NOTA1 = 5;
        this.NOTA2 = 5;
        this.FREQUENCIA = 70;

        this.MEDIA = (this.NOTA1 + this.NOTA2)/ 2;
        this.FINAL = 0;

        boletim[this.INDICE][0] = this.nomeMateria;
        boletim[this.INDICE][2] = String.valueOf(this.NOTA1);
        boletim[this.INDICE][3] = String.valueOf(this.NOTA2);
        boletim[this.INDICE][4] = String.valueOf(this.FREQUENCIA);
        boletim[this.INDICE][5] = status();

        count++;
    }

    public Disciplina(String nomeMateria, float n1, float n2, float f) {
        this.nomeMateria = nomeMateria;
        this.INDICE = count;
        this.NOTA1 = n1;
        this.NOTA2 = n2;
        this.FREQUENCIA = f;

        this.MEDIA = (this.NOTA1 + this.NOTA2)/ 2;

        if (this.MEDIA >= 7) {
            this.FINAL = 0;
        } else {
            this.FINAL = (5 - this.MEDIA * 0.6F) / 0.4F;
        }

        boletim[this.INDICE][0] = this.nomeMateria;
        boletim[this.INDICE][1] = String.valueOf(this.NOTA1);
        boletim[this.INDICE][2] = String.valueOf(this.NOTA2);
        boletim[this.INDICE][3] = String.valueOf(this.FREQUENCIA);
        boletim[this.INDICE][4] = status();

        count++;
    }

    @Override
    public String toString() {
        return formatarVisualizacao(this.INDICE);
    }

    /**
     * Formata o nome da materia.
     *
     * @param nm O nome a ser formatado
     * @return Uma String do nm formatado
     */
    public static String formatarNomeMateria(String nm) {
        nm = nm.toLowerCase();
        String[] nomeMateriaSeparado = nm.split(" ");
        for (int i = 0; i < nomeMateriaSeparado.length; i++) {
            if (!nomeMateriaSeparado[i].isEmpty()) {
                nomeMateriaSeparado[i] = nomeMateriaSeparado[i].substring(0, 1).toUpperCase() + nomeMateriaSeparado[i].substring(1);
            }
        }
        return String.join(" ", nomeMateriaSeparado);
    }

    /**
     * Formata como será printado a materia.
     *
     * @param i Posição da materia no boletim
     * @return Uma String da materia formatada
     */
    private static String formatarVisualizacao(int i) {
        return "\nNome da Matéria: " + boletim[i][0] + "\nNota 1: " + boletim[i][1] + "\nNota 2: " + boletim[i][2] +
                "\nFrequência: " + boletim[i][3] + "%" + "\nStatus: " + boletim[i][4] + "\n\n";
    }

    /**
     * Procurar por materias no boletim
     *
     * @param consulta materia a ser procurada
     * @param i índice do qual compararemos o nome da materia do boletim com a consulta
     * @return valor booleano -> true se achou e false se não
     */
    public static boolean materiaExiste(String consulta, int i) {
        if (count == i) {
            return false;
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return true;
        } else {
            return materiaExiste(consulta, i + 1);
        }
    }
    public static boolean materiaExiste(String consulta) {
        return materiaExiste(consulta, 0);
    }


    /**
     * Procurar por materias no boletim
     *
     * @param consulta materia a ser procurada
     * @param i índice do qual compararemos o nome da materia do boletim com a consulta
     * @return formatarVisualizacao da materia caso seja achado a materia
     */
    public static String buscarMateria(String consulta, int i) {
        if (count == i) {
            return "\nDisciplina não cadastrada!\n";
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return formatarVisualizacao(i);
        } else {
            return buscarMateria(consulta, i + 1);
        }
    }
    public static String buscarMateria(String consulta) {
        return buscarMateria(consulta, 0);
    }

    /**
     * Mostra o status da materia
     *
     * @return Informa se foi aprovado e a nota
     */
    private String status() {
        if (this.FREQUENCIA >= 75) {
            if (this.MEDIA >= 7) {
                return String.format("Aprovado com média: %.2f", this.MEDIA);
            } else if (this.MEDIA >= 4) {
                return String.format("Recuperação, " +
                        "nota necessária para atingir a média: %.2f", this.FINAL);
            } else {
                return "Reprovado por nota, "
                        + String.format("média: %.2f", this.MEDIA);
            }
        } else {
            return "Reprovado por falta";
        }
    }

    /**
     * Criar o arquivo txt
     *
     * @param path Caminho onde será criado o arquivo
     * @throws IOException Caso falhe na criação do arquivo
     */
    public static void criarHistoricoBoletim(String path) throws IOException {
        File archive = new File(path);

        File directory = archive.getParentFile();
        if (directory != null && !directory.exists()) directory.mkdirs();

        if (archive.exists()) System.out.println("\nBoletim já existe!");
        else {
            if (archive.createNewFile()) System.out.println("\nBoletim criado com sucesso!");
            else System.out.println("\nErro ao criar o arquivo " + archive.getPath() + '.');
        }
    }

    /**
     * Escrever no arquivo txt
     *
     * @param path Caminho do arquivo txt
     * @param delimitador Caractere que divide as informações no arquivo
     * @throws IOException Caso não ache o arquivo txt
     */
    public static void escreverHistoricoBoletim(String path, String delimitador) throws IOException {
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

    /**
     * Ler o arquivo txt
     *
     * @param maxDisciplinas Numero maximo de disciplinas do boletim
     * @param atributes Numero de atributos do boletim
     * @param delimitador Caractere que divide as informações no arquivo
     * @param path Caminho até o arquivo
     * @throws IOException Caso não encontre o arquivo txt
     */
    public static void lerHistoricoBoletim(int maxDisciplinas, int atributes, String delimitador, String path) throws IOException {
        String[][] historico = new String[maxDisciplinas][atributes];
        int c = 0;
        File archive = new File(path);

        if (archive.exists()) {
            System.out.println("Arquivo de dados encontrado: " + archive.getCanonicalPath());
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(delimitador);

                historico[c][0] = atributos[0];
                historico[c][1] = atributos[1];
                historico[c][2] = atributos[2];
                historico[c][3] = atributos[3];
                historico[c][4] = atributos[4];

                c++;
            }
            reader.close();
        }

        boletim = historico;
    }

    /**
     * Pegar a quantidade de linhas do arquivo txt
     *
     * @param path O caminho do arquivo
     * @throws IOException Caso não encontre o arquivo txt
     */
    public static void pegarLinhasHistoricoBoletim(String path) throws IOException {
        int n = 0;
        File archive = new File(path);

        if (archive.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            while ((reader.readLine()) != null) {
                n++;
            }
            reader.close();
        }
        count = n;
    }

    public static String[][] getBoletim() {
        return boletim;
    }

    /**
     * Buscar todas as disciplinas
     */
    public static void getBoletimFormatado() {
        for (int i = 0; i < count; i++) {
            System.out.print(formatarVisualizacao(i));
        }
    }

    public static void setBoletim(String[][] boletim) {
        Disciplina.boletim = boletim;
    }

    /**
     * Seta um boletim vazio (matriz vazia)
     *
     * @param m quantidade de linhas
     * @param n quantidade de colunas
     */
    public static void setBoletimVazio(int m, int n) {
        Disciplina.boletim = new String[m][n];
    }

    public static int getCount() {
        return count;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public int getINDICE() {
        return INDICE;
    }

    public float getNOTA1() {
        return NOTA1;
    }

    public float getNOTA2() {
        return NOTA2;
    }

    public float getFREQUENCIA() {
        return FREQUENCIA;
    }

    public float getMEDIA() {
        return MEDIA;
    }

    public float getFINAL() {
        return FINAL;
    }
}
