package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    Leverandør leverandør = new Leverandør("test", "test", 123456789);
    Fad fad = new Fad(1,90.0,FadType.Sherry,leverandør);
    Oprindelse oprindelse = new Oprindelse("gaard", "mark");
    Råvare råvare = new Råvare("test","test",100, LocalDate.of(2025,12,1),oprindelse);
    Medarbejder medarbejder = new Medarbejder(1,"test testerson", 123123);
    Destillering destillering = new Destillering(true,1,råvare,medarbejder);
    Destillat destillat = new Destillat(1,0,0,destillering);

   @BeforeEach
   void setUp(){

       fad.setDestillat(destillat);
       fad.setLiterIFad(80);

   }

    @Test
    void addVandTilFad01() {

        fad.setLiterIFad(85);
        double resultat = fad.addVandTilFad(5);

        assertEquals(90, resultat, 0.0001);
    }

    @Test
    void addVandTilFad02() {
        double resultat = fad.addVandTilFad(5);
        assertEquals(85, resultat, 0.0001);
    }

    @Test
    void addVandTilFad03() {
        assertThrows(IllegalArgumentException.class, () -> fad.addVandTilFad(15));
    }

    @Test
    void addVandTilFad04() {
        assertThrows(IllegalArgumentException.class, () -> fad.addVandTilFad(-3));
    }


    @Test
    void getAngelShare(){

    }
}