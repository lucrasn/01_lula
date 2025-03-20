package orientacao_a_objeto;

import java.io.IOException;

public class Calculo {

    private static float nota1;
    private static float nota2;
    private static float freq;
    private static float media;


    public static float mediaNotas() {
        media = (nota1 + nota2) / 2;
        return media;
    }

    public static float notaRecuperacao(){
        return (5 - media * 0.6F) / 0.4F;
    }

    public static String determinarStatus(float media, float freq) throws IOException {
        if (freq >= 75) {
            if (media >= 7) {
                return String.format("Aprovado com média: %.2f", getMedia());
            } else if (media > 4) {
                return String.format("Recuperação, " +
                        "nota necessária para atingir a média: %.2f", notaRecuperacao());
            } else {
                return "Reprovado por nota, "
                        + String.format("média: %.2f", getMedia());
            }
        } else {
            return "Reprovado por falta, "
                    + String.format("média: %.2f", getMedia());
        }
    }

    public static float getNota1() {
        return nota1;
    }

    public static void setNota1(float nt1) {
        nota1 = nt1;
    }

    public static float getNota2() {
        return nota2;
    }

    public static void setNota2(float nt2) {
        nota2 = nt2;
    }

    public static float getFreq() {
        return freq;
    }

    public static void setFreq(float frequencia) {
        freq = frequencia;
    }

    public static float getMedia() {
        return media;
    }
}
