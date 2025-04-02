package Parte04;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private static List<String> registro = new ArrayList<String>();
    private static int alunoCount;
    private final String nome;
    private final String matricula;
    private final int INDICE;

    public String getNome(){
        return this.nome;
    }

    public Aluno() {
        this.INDICE = alunoCount;
        this.nome = null;
        this.matricula = null;

        Disciplina.getDisciplinasCount().add(0);
    }

    public Aluno(String nome, String matricula, int indice){
        this.nome = nome;
        this.matricula = matricula;
        this.INDICE = indice;
    }

    public Aluno(String nome, String matricula){
        this.INDICE = alunoCount;
        this.nome = nome;
        this.matricula = matricula;

        while (Disciplina.getDisciplinasCount().size() <= this.INDICE) {
            Disciplina.getDisciplinasCount().add(0);
        }

        if (registro.size() <= alunoCount){
            // Se o índice não existir, só adiciona o aluno na posição correta
            // Ao invés de linhas vazias, a lista é expandida diretamente com o novo aluno.
            for (int i = registro.size(); i <= alunoCount; i++) {
                registro.add("");  // Adiciona linhas vazias até o índice correto, mas de forma controlada.
            }
        }

        registro.set(alunoCount, this.nome + ";" + this.matricula);

        alunoCount++;
    }

    /**
     * Formata o nome do aluno
     *
     * @param nm  nome do aluno
     * @return string formatada
     */
    public static String formatarNomeAluno(String nm) {
        nm = nm.toLowerCase();
        String[] nomeAlunoSeparado = nm.split(" ");
        for (int i = 0; i < nomeAlunoSeparado.length; i++) {
            if (!nomeAlunoSeparado[i].isEmpty()) {
                nomeAlunoSeparado[i] = nomeAlunoSeparado[i].substring(0, 1).toUpperCase() + nomeAlunoSeparado[i].substring(1);
            }
        }
        return String.join(" ", nomeAlunoSeparado);
    }

    /**
     *  Verifica se existem alunos na memória(List<>registro)
     *  caso não, vai carregar os alunos salvos em arquivo
     *  temporáriamente para a List.
     *
     *  O laço verifica os alunos já cadastrados que foram carregados
     *  para o registro
     *
     * @param nome Nome do aluno
     * @param user Caminho do arquivo
     * @return Verificação se existe ou nâo
     */

    public static boolean alunoExiste(String nome,String user) {
        if (registro.isEmpty()) {
            try {
                lerRegistroAlunos(new String[]{";"}, user);
            } catch (IOException e) {
                System.out.println("Erro ao ler o registro de alunos: " + e.getMessage());
                return false;
            }
        }

        for (String aluno : registro) {
            String[] dados = aluno.split(";");
            if (dados.length >= 1 && dados[0].equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procura a matrícula específica de cada aluno
     * que foi salvo em arquivo para poder realizar login
     * @param nome Nome do aluno que vai ter a matrícula checada
     * @return Retorna a matrícula para verificação
     */

    public static String buscarMatricula(String nome) {
        for (String registroAluno : registro) {
            if (registroAluno != null && !registroAluno.isEmpty()) {
                String[] dados = registroAluno.split(";");
                if (dados.length == 2 && dados[0].equalsIgnoreCase(nome)) {
                    return dados[1];
                }
            }
        }
        return null;
    }

    /**
     * Cria Txt de alunos
     *
     * @param user Caminho do arquivo
     * @throws IOException Caso de erro na criação do arquivo
     */
    public static void criarRegistroDeAlunos(String user) throws IOException {
        File archive = new File(user);

        File directory = archive.getParentFile();
        if (directory != null && !directory.exists()) directory.mkdirs();

        if (archive.exists()) System.out.println("\nRegistro de alunos atualizado!");
        else {
            if (archive.createNewFile()) System.out.println("\nRegistro de alunos criado com sucesso!");
            else System.out.println("\nErro ao criar registro" + archive.getPath() + '.');
        }
    }

    /**
     * Metodo que verifica cadastro e também realiza cadastro.
     * Caso já esteja cadastrado sai do metodo sem adicionar duplicatas
     * Caso seja um novo aluno, o metodo é reponsável por escrever ele no Txt
     *
     * @param user Caminho do arquivo
     * @param nome Nome do aluno cadastrado
     * @param senha Matrícula do aluno(também serve como senha para logar)
     * @param delimitador Lista com dois tipos de delimitadores
     */
    public static void cadastrarAluno(String user, String nome, String senha, String delimitador) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(user));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] dados = line.split(delimitador);
            if (dados.length >= 1 && dados[0].equalsIgnoreCase(nome)) {
                System.out.println("\nAluno já cadastrado! Realize Login\n");
                reader.close();
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(user, true))) {
            writer.write(nome + delimitador + senha);
            writer.newLine();
            System.out.println("\nAluno cadastrado com sucesso!\n");
        } catch (IOException e) {
            System.out.println("\nErro ao cadastrar aluno.\n");
        }

    }

    public static void lerRegistroAlunos(String[] delimitadores, String user) throws IOException {
        File archive = new File(user);

        if (!archive.exists()) {
            System.out.println("Arquivo de dados não encontrado: " + archive.getCanonicalPath());
            return;
        }

        registro.clear();

        System.out.println("Arquivo de dados encontrado: " + archive.getCanonicalPath());
        BufferedReader reader = new BufferedReader(new FileReader(archive));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] atributos = line.split(delimitadores[0]);

            if (atributos.length >= 2) {
                registro.add(atributos[0] + ";" + atributos[1]);
            } else {
                System.out.println("Linha inválida ignorada: " + line);
            }
        }
        alunoCount = registro.size();

        reader.close();
    }

    /**
     * Pegar a quantidade de alunos cadastrador no registro (arquivo .txt)
     *
     * @param user
     * @return
     */
    public static int pegarLinhasRegistroDeAlunos(String user) throws IOException {
        int n = 0;
        File archive = new File(user);

        if (archive.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(archive));

            while ((reader.readLine()) != null) {
                n++;
            }
            reader.close();
        }
        return  n;
    }

    public static Aluno login(String nome, String matricula) {
        String matriculaCorreta = buscarMatricula(nome);
        if (matriculaCorreta != null && matriculaCorreta.equals(matricula)) {
            System.out.println("\nLogin bem-sucedido! Bem-vindo, " + nome + "!\n");
            int indiceAluno = registro.indexOf(nome + ";" + matricula);
            return new Aluno(nome, matricula, indiceAluno);
        }
        System.out.println("\nNome ou matrícula incorretos.\n");
        return null;
    }

    public static List<String> getRegistro() {
        return registro;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getINDICE() {
        return INDICE;
    }

    public static int getAlunoCount(){
        return alunoCount;
    }
}