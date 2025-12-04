package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.concurrent.atomic.DoubleAdder;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    Leverandør leverandør = new Leverandør("test", "test", "123456789");
    Fad fad = new Fad(1,90.0,FadType.Sherry,leverandør);
    Oprindelse oprindelse = new Oprindelse("gaard", "mark");
    Råvare råvare = new Råvare("test","test",100, LocalDate.of(2025,12,1),oprindelse);
    Medarbejder medarbejder = new Medarbejder(1,"test testerson", "123123");
    Destillering destillering = new Destillering(1,true,100,råvare,medarbejder);
    Destillat destillat = new Destillat(1,0,0,destillering);
    Fad fad2 = new Fad(2,200,FadType.Sherry,leverandør);

   @BeforeEach
   void setUp(){
       fad.setDestillat(destillat);
       fad.setLiterIFad(80);
       destillat.setMaengde(200);

       fad2.setAlkoholProcent(50);
       fad2.setLiterIFad(100);
       fad2.setDestillat(destillat);
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
    void getAngelShare01(){
        double resultat = fad2.getAngelShare(80,40);
        assertEquals(20, resultat, 0.0001);

    }

    @Test
    void getAngelShare02(){

        fad2.setLiterIFad(100);
        fad2.setAlkoholProcent(50);
        assertThrows(IllegalArgumentException.class, () -> fad2.getAngelShare(150,47));

    }

    @Test
    void getAngelShare03(){

        fad2.setLiterIFad(1);
        double resultat = fad2.getAngelShare(0,40);
        assertEquals(1, resultat, 0.0001);

    }

    @Test
    void getAngelShare04(){

        fad2.setLiterIFad(200);
        fad2.setAlkoholProcent(100);
        double resultat = fad2.getAngelShare(200,100);
        assertEquals(0, resultat, 0.0001);
    }

    @Test
    void getAngelShare05(){

        fad2.setLiterIFad(100);
        fad2.setAlkoholProcent(50);
        assertThrows(IllegalArgumentException.class, () -> fad2.getAngelShare(40,60));

    }

    @Test
    void getAngelShare06(){

        fad2.setLiterIFad(100);
        fad2.setAlkoholProcent(50);
        assertThrows(IllegalArgumentException.class, () -> fad2.getAngelShare(-1,47));

    }

    @Test
    void getAngelShare07(){
        fad2.setLiterIFad(100);
        fad2.setAlkoholProcent(50);
        assertThrows(IllegalArgumentException.class, () -> fad2.getAngelShare(40,39));
    }

    @Test
    void erFadKlarTilTapning01(){
       fad.setErAktiv(true);
       fad.setStartLagring(LocalDate.of(2022,12,2));
       assertTrue(fad.erFadKlarTilTapning());
    }

    @Test
    void erFadKlarTilTapning02(){
        fad.setErAktiv(true);
        fad.setStartLagring(LocalDate.of(2015,12,2));
        assertTrue(fad.erFadKlarTilTapning());
    }

    @Test
    void erFadKlarTilTapning03(){
        fad.setErAktiv(false);
        fad.setStartLagring(LocalDate.of(2015,12,2));
        assertFalse(fad.erFadKlarTilTapning());
    }

    @Test
    void erFadKlarTilTapning04(){
        fad.setErAktiv(true);
        fad.setStartLagring(LocalDate.of(2030,12,1));
        assertFalse(fad.erFadKlarTilTapning());
    }
}