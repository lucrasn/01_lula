package orientacao_a_objeto;

import static orientacao_a_objeto.Formatacao.formatView;

public class Busca {
    // Metodo para verifica se a disciplina existe RECURSIVA suport
    static boolean disciplinaExist(String[][] boletim , String consulta, int count) {
        return disciplinaExist(boletim, consulta, count, 0);
    }

    // Metodo para verifica se a disciplina existe RECURSIVA
    static boolean disciplinaExist(String[][] boletim , String consulta, int count, int i) {
        if (count == i) {
            return false;
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return true;
        } else {
            return disciplinaExist(boletim, consulta, count, i + 1);
        }
    }

    // Metodo para procurar pela disciplina consultada SUPORTE
    static String buscaEspecifica(String[][] boletim , String consulta, int count) {
        return buscaEspecifica(boletim, consulta, count, 0);
    }

    // Metodo para procurar pela disciplina consultada RECURSIVA
    static String buscaEspecifica(String[][]
                                          boletim , String consulta, int count, int i) {
        if (count == i) {
            return "\nDisciplina não cadastrada!\n";
        } else if (boletim[i][0].equalsIgnoreCase(consulta)) {
            return formatView(boletim, i);
        } else {
            return buscaEspecifica(boletim, consulta, count, i + 1);
        }
    }
}
