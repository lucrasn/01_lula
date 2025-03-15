package orientacao_a_objeto;

public class Formatacao {
    // Metodo para escrever a menu
    static String menu() {
        return """
                ----------  MENU  ----------
                [1] Adicionar Disciplina
                [2] Consultar Disciplina
                [3] Sair
                ----------------------------""";
    }

    // Metodo para formatar o nome da materia
    static String formatName(String nm) {
        nm = nm.toLowerCase();
        String[] nmSeparado = nm.split(" ");
        for (int i = 0; i < nmSeparado.length; i++) {
            if (!nmSeparado[i].isEmpty()) {
                nmSeparado[i] = nmSeparado[i].substring(0, 1).toUpperCase() + nmSeparado[i].substring(1);
            }
        }
        return String.join(" ", nmSeparado);
    }

    // Metodo de formatação de como será mostrada as materias
    static String formatView(String[][] boletim, int i) {
        return "\nMatéria: " + boletim[i][0] + "\nNota 1: " + boletim[i][1] + "\nNota 2: " + boletim[i][2] +
                "\nFrequência: " + boletim[i][3] + "%" + "\nStatus: " + boletim[i][4] + "\n\n";
    }
}
