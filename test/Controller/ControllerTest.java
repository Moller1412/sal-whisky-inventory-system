package Controller;

import Storage.Storage;
import model.*;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.Control;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Oprindelse oprindelse = new Oprindelse("gaard", "mark");
    Råvare råvare = new Råvare("test","test",100, LocalDate.of(2025,12,1),oprindelse);
    Medarbejder medarbejder = new Medarbejder(1,"test testerson", 123123);
    Destillering destillering = new Destillering(1,true,100,råvare,medarbejder);
    Leverandør leverandør = new Leverandør("test","test","123123123");
    Fad fad = new Fad(1,200,FadType.Sherry,leverandør);
    Destillat destillat = new Destillat(1,200,50,destillering);

    @Test
    void createDestillat01() {
        int før = Storage.getDestillater().size();

        Controller.createDestillat(1, 100, 40, destillering);

        int efter = Storage.getDestillater().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createDestillat02() {
        int før = Storage.getDestillater().size();

        Controller.createDestillat(2, 80, 0, destillering);

        int efter = Storage.getDestillater().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createDestillat03() {
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillat(-3, 90, 60, destillering));
    }

    @Test
    void createDestillat04() {
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillat(3, -10, 60, destillering));
    }

    @Test
    void createDestillat05() {
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillat(4, 90, 105, destillering));
    }

    @Test
    void createDestillat06() {
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillat(5, 90, 62, null));
    }

    @Test
    void createFad01(){
        int før = Storage.getFade().size();

        Controller.createFad(5,90,FadType.Sherry,leverandør);

        int efter = Storage.getFade().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createFad02(){
        int før = Storage.getFade().size();

        Controller.createFad(1,1,FadType.Fondillion,leverandør);

        int efter = Storage.getFade().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createFad03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFad(-2,80,FadType.Bourbon,leverandør));
    }

    @Test
    void createFad04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFad(2,-50,FadType.Bourbon,leverandør));
    }

    @Test
    void createFad05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFad(2,50,null,leverandør));
    }

    @Test
    void createFad06(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFad(2,50,FadType.Bourbon,null));
    }

    @Test
    void addDestillatTilFad01(){
        fad.setErAktiv(false);
        Controller.addDestillatTilFad(fad,destillat);
        assertTrue(fad.isErAktiv());
    }

    @Test
    void addDestillatTilFad02(){
        fad.setErAktiv(false);
        destillat.setMaengde(210);
        assertThrows(RuntimeException.class, () ->Controller.addDestillatTilFad(fad,destillat));
    }

    @Test
    void addDestillatTilFad03(){
        fad.setErAktiv(false);
        assertThrows(IllegalArgumentException.class, () ->Controller.addDestillatTilFad(null,destillat));
    }

    @Test
    void addDestillatTilFad04(){
        fad.setErAktiv(false);
        assertThrows(IllegalArgumentException.class, () ->Controller.addDestillatTilFad(fad,null));
    }

    @Test
    void addDestillatTilFad05(){
        fad.setErAktiv(true);
        assertThrows(IllegalArgumentException.class, () ->Controller.addDestillatTilFad(fad,destillat));
    }


    @Test
    void addFadTilHylde01() {

        Fad fad = new Fad(1,200,FadType.Bourbon, leverandør);
        Hylde hylde = new Hylde(1);
        hylde.setErOptaget(false);

        Controller.addFadTilHylde(fad, hylde);

        assertEquals(hylde, fad.getHylde(), "Fadet skal stå på den valgte hylde");
        assertTrue(hylde.isErOptaget(), "Hylden skal være markeret som optaget");
    }


    @Test
    void addFadTilHylde02() {
        Fad fad = new Fad(1,200,FadType.Bourbon, leverandør);
        Hylde hylde = new Hylde(1);

        hylde.setErOptaget(true);

        assertThrows(IllegalArgumentException.class, () -> Controller.addFadTilHylde(fad,hylde));
    }

    @Test
    void addFadTilHylde03() {

        Fad fad = null;
        Hylde hylde = new Hylde(1);
        hylde.setErOptaget(false);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> Controller.addFadTilHylde(fad, hylde));
    }

    @Test
    void addFadTilHylde04() {
        Fad fad = new Fad(1,200,FadType.Bourbon, leverandør);
        Hylde hylde = null;

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> Controller.addFadTilHylde(fad, hylde));
    }

    @Test
    void createFærdigvare01(){
        int før = Storage.getFærdigvarer().size();

        fad.setStartLagring(LocalDate.of(2020,12,1));
        fad.setErAktiv(true);
        Controller.createFærdigvare("GLØD 3.2",749,fad);


        int efter = Storage.getFærdigvarer().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createFærdigvare02(){
        int før = Storage.getFærdigvarer().size();

        fad.setStartLagring(LocalDate.of(2020,12,1));
        fad.setErAktiv(true);
        Controller.createFærdigvare("GLØD 3.2",0,fad);


        int efter = Storage.getFærdigvarer().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createFærdigvare03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFærdigvare("",749,fad));
    }

    @Test
    void createFærdigvare04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFærdigvare("GLØD 3.2",-1,fad));
    }

    @Test
    void createFærdigvare05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createFærdigvare("GLØD 3.2",749,null));
    }

    @Test
    void createLeverandør01(){
        int før = Storage.getLeverandører().size();

        Controller.createLeverandør("Sall whisky","adresse 12","12345678");


        int efter = Storage.getLeverandører().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createLeverandør02(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør(null,"adresse 12","12345678"));
    }

    @Test
    void createLeverandør03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør("","adresse 12","12345678"));
    }
}