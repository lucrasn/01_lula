package Parte04;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Disciplina {
    private static List<ArrayList<ArrayList<String>>> boletim;
    private static List<Integer> count; // a gente agora tem um contador diferente para cada aluno
    private String nomeMateria;
    private final int INDICE;
    private final float NOTA1;
    private final float NOTA2;
    private final float FREQUENCIA;
    private final float MEDIA;
    private final float FINAL;

    public Disciplina() {
        Aluno aluno = new Aluno();

        boletim = new ArrayList<ArrayList<ArrayList<String>>>();
        count = new ArrayList<Integer>();
        count.add(0);

        this.nomeMateria = null;
        this.INDICE = count.get(aluno.getINDICE());
        this.NOTA1 = 5;
        this.NOTA2 = 5;
        this.FREQUENCIA = 70;

        this.MEDIA = (this.NOTA1 + this.NOTA2)/ 2;
        this.FINAL = 0;

        boletim.get(aluno.getINDICE()).get(this.INDICE).add(this.nomeMateria);
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.NOTA1));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.NOTA2));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.FREQUENCIA));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(status());

        int incremento = count.get(aluno.getINDICE()) + 1;
        count.set(aluno.getINDICE(), incremento);
    }

    public Disciplina(Aluno aluno) {
        boletim = new ArrayList<ArrayList<ArrayList<String>>>();
        count = new ArrayList<Integer>();
        count.add(0);

        this.nomeMateria = null;
        this.INDICE = count.get(aluno.getINDICE());
        this.NOTA1 = 5;
        this.NOTA2 = 5;
        this.FREQUENCIA = 70;

        this.MEDIA = (this.NOTA1 + this.NOTA2)/ 2;
        this.FINAL = 0;

        boletim.get(aluno.getINDICE()).get(this.INDICE).add(this.nomeMateria);
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.NOTA1));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.NOTA2));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.FREQUENCIA));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(status());

        int incremento = count.get(aluno.getINDICE()) + 1;
        count.set(aluno.getINDICE(), incremento);
    }

    public Disciplina(Aluno aluno, String nomeMateria, float n1, float n2, float f) {
        boletim = new ArrayList<ArrayList<ArrayList<String>>>();
        count = new ArrayList<Integer>();
        count.add(0);

        this.nomeMateria = nomeMateria;
        this.INDICE = count.get(aluno.getINDICE());
        this.NOTA1 = n1;
        this.NOTA2 = n2;
        this.FREQUENCIA = f;

        this.MEDIA = (this.NOTA1 + this.NOTA2)/ 2;

        if (this.MEDIA >= 7) {
            this.FINAL = 0;
        } else {
            this.FINAL = (5 - this.MEDIA * 0.6F) / 0.4F;
        }

        boletim.get(aluno.getINDICE()).get(this.INDICE).add(this.nomeMateria);
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.NOTA1));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.NOTA2));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(String.valueOf(this.FREQUENCIA));
        boletim.get(aluno.getINDICE()).get(this.INDICE).add(status());

        int incremento = count.get(aluno.getINDICE()) + 1;
        count.set(aluno.getINDICE(), incremento);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nomeMateria='" + nomeMateria + '\'' +
                ", NOTA1=" + NOTA1 +
                ", NOTA2=" + NOTA2 +
                ", FREQUENCIA=" + FREQUENCIA +
                ", MEDIA=" + MEDIA +
                ", FINAL=" + FINAL +
                '}';
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
     * @param aluno O aluno que terá a disciplina formatada
     * @param i Posição da materia no boletim
     * @return Uma String da materia formatada
     */
    private static String formatarVisualizacao(Aluno aluno, int i) {
        List<ArrayList<String>> boletimAluno = boletim.get(aluno.getINDICE());

        return "\nNome da Matéria: " + boletimAluno.get(i).getFirst() +
                "\nNota 1: " + boletimAluno.get(i).get(1) +
                "\nNota 2: " + boletimAluno.get(i).get(2) +
                "\nFrequência: " + boletimAluno.get(i).get(3) + "%" +
                "\nStatus: " + boletimAluno.get(i).getLast() + "\n\n";
    }

    /**
     * Procurar por materias no boletim
     *
     * @param aluno O aluno de onde iremos procurar as materias
     * @param consulta materia a ser procurada
     * @param i índice do qual compararemos o nome da materia do boletim com a consulta
     * @return valor booleano -> true se achou e false se não
     */
    public static boolean materiaExiste(Aluno aluno, String consulta, int i) {
        if (count.get(aluno.getINDICE()) == i) {
            return false;
        } else if (boletim.get(aluno.getINDICE()).get(i).getFirst().equalsIgnoreCase(consulta)) {
            return true;
        } else {
            return materiaExiste(aluno, consulta, i + 1);
        }
    }
    public static boolean materiaExiste(Aluno aluno, String consulta) {
        return materiaExiste(aluno, consulta, 0);
    }


    /**
     * Procurar por materias no boletim
     *
     * @param aluno O aluno de onde iremos procurar as materias
     * @param consulta materia a ser procurada
     * @param i índice do qual compararemos o nome da materia do boletim com a consulta
     * @return formatarVisualizacao da materia caso seja achado a materia
     */
    public static String buscarMateria(Aluno aluno, String consulta, int i) {
        if (count.get(aluno.getINDICE()) == i) {
            return "\nDisciplina não cadastrada!\n";
        } else if (boletim.get(aluno.getINDICE()).get(i).getFirst().equalsIgnoreCase(consulta)) {
            return formatarVisualizacao(aluno, i);
        } else {
            return buscarMateria(aluno, consulta, i + 1);
        }
    }
    public static String buscarMateria(Aluno aluno,String consulta) {
        return buscarMateria(aluno, consulta, 0);
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
     * @param delimitadores Lista com dois elementos onde cada elemento é um caractere que divide as informações no arquivo
     * @throws IOException Caso não ache o arquivo txt
     */
    public static void escreverHistoricoBoletim(String path, String[] delimitadores) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        int nAlunos = Aluno.pegarLinhasRegistroDeAlunos(delimitadores[1], path); // isso eu to supondo que nesse metodo você vai usar o delimitador ';'

        // TODO: tu vai ter que fazer dois for aqui pra poder fazer um boletim.get(i).get(j)... tlgd
        for (int i = 0; i < nAlunos; i++) {
            String linha = boletim.get(i).getFirst() + delimitador +
                    boletim.get(i).get(1) + delimitador +
                    boletim.get(i).get(2) + delimitador +
                    boletim.get(i).get(3) + delimitador +
                    boletim.get(i).getLast();

            writer.write(linha);
            writer.newLine();
        }

        System.out.println("Boletim escrito com sucesso!");
        writer.close();
    }

    /**
     * Ler o arquivo txt
     *
     * @param delimitadores Lista com dois elementos onde cada elemento é um caractere que divide as informações no arquivo
     * @param path Caminho até o arquivo
     * @throws IOException Caso não encontre o arquivo txt
     */
    public static void lerHistoricoBoletim(String[] delimitadores, String path) throws IOException {
        List<ArrayList<ArrayList<String>>> historico = new ArrayList<ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> historicoAluno = new ArrayList<ArrayList<String>>();
        File archive = new File(path);

        if (archive.exists()) {
            System.out.println("Arquivo de dados encontrado: " + archive.getCanonicalPath());
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            String line;
            while ((line = reader.readLine()) != null) {
                int c = 0;
                String[] materias = line.split(delimitadores[0]);

                for (String dados : materias) {
                    String[] atributos = dados.split(delimitadores[1]);

                    historicoAluno.get(c).add(atributos[0]);
                    historicoAluno.get(c).add(atributos[1]);
                    historicoAluno.get(c).add(atributos[2]);
                    historicoAluno.get(c).add(atributos[3]);
                    historicoAluno.get(c).add(atributos[4]);

                    c++;
                }

                historico.add(historicoAluno);
            }
            reader.close();
        }

        boletim = historico;
    }

    /**
     * Pegar a quantidade de materias que cada aluno tem cadastrada no historico (arquivo .txt)
     *
     * @param delimitador Caractere que divide as materias no arquivo
     * @param path O caminho do arquivo
     * @throws IOException Caso não encontre o arquivo txt
     */
    public static void pegarNumeroDeMateriasHistoricoBoletim(String delimitador, String path) throws IOException {
        File archive = new File(path);
        int nAlunos = 0;

        if (archive.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            String line;
            while ((line = reader.readLine()) != null) {
                int nMaterias = 0;
                String[] materias = line.split(delimitador);

                for (String dados : materias) {
                    nMaterias++;
                }

                count.set(nAlunos, nMaterias);
                nAlunos++;
            }
            reader.close();
        }
    }

    public static List<ArrayList<ArrayList<String>>> getBoletim() {
        return boletim;
    }

    /**
     * Buscar todas as disciplinas
     */
    public static void getBoletimFormatado(Aluno aluno) {
        for (int i = 0; i < count.get(aluno.getINDICE()); i++) {
            System.out.print(formatarVisualizacao(aluno, i));
        }
    }

    public static void setBoletim(List<ArrayList<ArrayList<String>>> boletim) {
        Disciplina.boletim = boletim;
    }

    public static List<Integer> getCount() {
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
