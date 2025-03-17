package orientacao_a_objeto;

import java.io.IOException;

public class Calculo {

    private float nt1;
    private float nt2;
    private float freq;
    private float media;

    public Calculo(){
    }

    public Calculo(float nt1, float nt2, float freq) {
        this.nt1 = nt1;
        this.nt2 = nt2;
        this.freq = freq;
    }

    public float mediaNotas() {
        media = (nt1 + nt2) / 2;
        return media;
    }

    public String determinarStatus(float media, float freq) throws IOException {
        if (freq >= 75) {
            if (media >= 7) {
                return "Aprovado";
            } else if (media > 4) {
                return "Recuperação";
            } else {
                return "Reprovado por nota";
            }
        } else {
            return "Reprovado2 por falta";
        }
    }

    public float getNt1() {
        return nt1;
    }

    public void setNt1(float n1) {
        this.nt1 = n1;
    }

    public float getNt2() {
        return nt2;
    }

    public void setN2(float nt2) {
        this.nt2 = nt2;
    }

    public float getFreq() {
        return freq;
    }

    public void setFreq(float freq) {
        this.freq = freq;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }
}
