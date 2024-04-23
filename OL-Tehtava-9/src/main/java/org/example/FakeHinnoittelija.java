package org.example;

public class FakeHinnoittelija implements IHinnoittelija {
    private float alennus;

    public FakeHinnoittelija(float alennus) {
        this.alennus = alennus;
    }

    public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
        return alennus;
    }

    @Override
    public void aloita() {
        this.alennus = 0;
    }

    @Override
    public void setAlennusProsentti(Asiakas asiakas, float v) {
        this.alennus = v;
    }

    @Override
    public void lopeta() {
        this.alennus = 0;
    }
}
