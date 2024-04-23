
package org.example;

public class TilaustenKäsittely {
	
    private IHinnoittelija hinnoittelija;

    public void setHinnoittelija(IHinnoittelija hinnoittelija) {
        this.hinnoittelija = hinnoittelija;
    }

    /*
    public void käsittele(Tilaus tilaus) {
        float alennusProsentti = hinnoittelija.getAlennusProsentti(
                tilaus.getAsiakas(), tilaus.getTuote());
        float alennusHinta = tilaus.getTuote().getHinta()
                * (1 - (alennusProsentti / 100));
        tilaus.getAsiakas().setSaldo(
                tilaus.getAsiakas().getSaldo() - alennusHinta);
    }*/
    public void käsittele(Tilaus tilaus) {
        Asiakas asiakas = tilaus.getAsiakas();
        Tuote tuote = tilaus.getTuote();
        hinnoittelija.aloita();
        float prosentti = hinnoittelija.getAlennusProsentti(asiakas, tuote);
        if (tuote.getHinta() >= 100) {
            hinnoittelija.setAlennusProsentti(asiakas, prosentti + 5);
            System.out.println("Hinta yli 100, alennusprosentti: " + hinnoittelija.getAlennusProsentti(asiakas, tuote));
        }
        prosentti = hinnoittelija.getAlennusProsentti(asiakas, tuote);
        System.out.println("Alennusprosentti: " + prosentti);
        float alennusHinta = tuote.getHinta() * (1 - (prosentti / 100));
        System.out.println("Alennettu hinta: " + alennusHinta);
        asiakas.setSaldo(asiakas.getSaldo() - alennusHinta);
        hinnoittelija.lopeta();
    }

}
