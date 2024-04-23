import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TilaustenKäsittelyMockitoTest {
    @Mock
    IHinnoittelija hinnoittelijaMock;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testaaKäsittelijäWithMockitoHinnoittelija() {
        // Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 100.0f;
        float alennus = 5.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
        System.out.println( "Loppu saldo: " + loppuSaldo);
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        // Record
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(alennus);
        // Act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelijaMock);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        // Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelijaMock, times(3)).getAlennusProsentti(asiakas, tuote);
    }
    @Test
    public void testaaKäsittelijääHintaYli100() {
        // Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 120.0f;
        float alennus = 5.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus  / 100));
        System.out.println("Loppu saldo: " + loppuSaldo);
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        IHinnoittelija hinnoittelija = new FakeHinnoittelija(alennus);
        // Act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelija);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        // Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
    }
    @Test
    public void testaaKäsittelijääHintaAlle100() {
        // Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 80.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - listaHinta;
        System.out.println("Loppu saldo: " + loppuSaldo);
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        IHinnoittelija hinnoittelija = new FakeHinnoittelija(alennus);
        // Act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelija);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        // Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
    }
}
