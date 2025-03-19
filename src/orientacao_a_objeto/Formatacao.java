package orientacao_a_objeto;

import java.util.Arrays;

public class Formatacao {
    private static String[][] boletim;
    private static int count;
    private String nomeMateria;

    public Formatacao() {
        this.nomeMateria = "Materia 01";
        count++;
    }

    public Formatacao(String nomeMateria) {
        this.nomeMateria = nomeMateria;
        //count++;
        /*
        *  </3
        *  fazer isso deixa o indice 0 vazio e pula direto para o 1
        *  e ao cadastrar uma materia (direto no indice 1) todos os atributos ficavam null
        *  botei o contador dentro do prórpio case 1, a cada nova materia inserida + 1
        */
    }

    public static void incrementoCount() {
        count++;
    }


    @Override
    public String toString() {
        return "Formatacao{" +
                "boletim=" + Arrays.toString(boletim) +
                ", nomeMateria='" + nomeMateria + '\'' +
                '}';
    }

    // Metodo para escrever a menu
    public static void menu() {
        System.out.println("""
                ----------  MENU  ----------
                [1] Adicionar Disciplina
                [2] Consultar Disciplina
                [3] Exibir Disciplinas
                [4] Sair
                ----------------------------""");
    }

    // Metodo para formatar o nome da materia
    public static String formatName(String nm) {
        nm = nm.toLowerCase();
        String[] nomeMateriaSeparado = nm.split(" ");
        for (int i = 0; i < nomeMateriaSeparado.length; i++) {
            if (!nomeMateriaSeparado[i].isEmpty()) {
                nomeMateriaSeparado[i] = nomeMateriaSeparado[i].substring(0, 1).toUpperCase() + nomeMateriaSeparado[i].substring(1);
            }
        }
        return String.join(" ", nomeMateriaSeparado);
    }

    // Metodo de formatação de como será mostrada as materias
    public static String formatView(int indice) {
        return "\nNome da Matéria: " + boletim[indice][0] + "\nNota 1: " + boletim[indice][1] + "\nNota 2: " + boletim[indice][2] +
                "\nFrequência: " + boletim[indice][3] + "%" + "\nStatus: " + boletim[indice][4] + "\n\n";
    }

    public static String[][] getBoletim() {
        return boletim;
    }

    public static void setBoletim(String[][] boletim) {
        Formatacao.boletim = boletim;
    }

    public static void setBoletimClean(int i, int j) {
        Formatacao.boletim = new String[i][j];
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Formatacao.count = count;
    }
}