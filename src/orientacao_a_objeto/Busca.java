package orientacao_a_objeto;

public class Busca {
    private static String consulta;

    // Metodo para verifica se a disciplina existe RECURSIVA suport
    public static boolean disciplinaExist() {
        return disciplinaExist(consulta, 0); //mudar para 0 dps
        /*  tambem nao funcionava justamente por seu indice inicial ser 0 e tentar puxar o 1
        *   tentei colocar o indice igual a 1 para o codigo rodar, mas nao dava pra
        *   adicionar outra disciplina
        */
    }

    // Metodo para verifica se a disciplina existe RECURSIVA
    public static boolean disciplinaExist(String consulta, int indice) {
        if (Formatacao.getCount() == indice) {
            return false;
        } else if (Formatacao.getBoletim()[indice][0].equalsIgnoreCase(consulta)) {
            return true;
        } else {
            return disciplinaExist(consulta, indice + 1);
        }
    }

    // Metodo para procurar pela disciplina consultada SUPORTE
    public static String buscaEspecifica() {
        return buscaEspecifica(consulta, 0);
    }

    // Metodo para procurar pela disciplina consultada RECURSIVA
    public static String buscaEspecifica(String consulta, int indice) {
        if (Formatacao.getCount() == indice) {
            return "\nDisciplina não cadastrada!\n";
        } else if (Formatacao.getBoletim()[indice][0].equalsIgnoreCase(consulta)) {
            return Formatacao.formatView(indice);
        } else {
            return buscaEspecifica(consulta, indice + 1);
        }
    }

    // Metodo para buscar todas as disciplinas
    public static void buscaAll() {
        for (int i = 0; i < Formatacao.getCount(); i++) {
            System.out.print(Formatacao.formatView(i));
        }
    }

    public static String getConsulta() {
        return consulta;
    }

    public static void setConsulta(String consulta) {
        Busca.consulta = consulta;
    }
}