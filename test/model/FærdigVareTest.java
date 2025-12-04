package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FærdigVareTest {


    Oprindelse oprindelse = new Oprindelse("gaard", "mark");
    Råvare råvare = new Råvare("test","test",100, LocalDate.of(2025,12,1),oprindelse);
    Medarbejder medarbejder = new Medarbejder(1,"test testerson", 123123);
    Destillering destillering = new Destillering(1,true,1,råvare,medarbejder);
    Leverandør leverandør = new Leverandør("test","test","123123123");
    Fad fad = new Fad(1,200,FadType.Sherry,leverandør);
    Destillat destillat = new Destillat(1,200,50,destillering);

    @BeforeEach
    void setUp(){
        fad.setErAktiv(true);
        fad.setStartLagring(LocalDate.of(2020,12,1));
        fad.setDestillat(destillat);

    }


    @Test
    void udregnAntalFlasker01() {
      FærdigVare fv = new FærdigVare("Fv", 400, fad);
        fad.setLiterIFad(70.3);

        double antalFlasker = fv.udregnAntalFlaskerFraFærdigvare();


        assertEquals(100.0, antalFlasker, 0.0001);
        assertEquals(0.3, fv.getRestVærdi(), 0.0001);
    }
    @Test
    void udregnAntalFlasker02() {
        FærdigVare fv = new FærdigVare("Fv", 400, fad);
        fad.setLiterIFad(0.7);
        double antalFlasker = fv.udregnAntalFlaskerFraFærdigvare();


        assertEquals(1.0, antalFlasker, 0.0001);
        assertEquals(0.0, fv.getRestVærdi(), 0.0001);
    }

    @Test
    void udregnAntalFlasker03() {

        FærdigVare fv = new FærdigVare("Fv", 400, fad);
        fad.setLiterIFad(0.7);
        fv.setMængde(0.9);
        double antalFlasker = fv.udregnAntalFlaskerFraFærdigvare();

        assertEquals(0.0, antalFlasker, 0.0001);
        assertEquals(0.7, fv.getRestVærdi(), 0.0001);

    }
    @Test
    void udregnAntalFlasker04() {
        fad.setLiterIFad(0);
        FærdigVare fv = new FærdigVare("Fv", 400, fad);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, fv::udregnAntalFlaskerFraFærdigvare);
        assertEquals("Fadet er tom", ex.getMessage());
    }

    @Test
    void udregnAntalFlasker05() {

        FærdigVare fv = new FærdigVare("FV", 400, fad);
        fad.setLiterIFad(70);
        fv.setMængde(0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, fv::udregnAntalFlaskerFraFærdigvare);
        assertEquals("Mængden på flasker er 0. Ikke muligt at fylde op.", ex.getMessage());
    }
}