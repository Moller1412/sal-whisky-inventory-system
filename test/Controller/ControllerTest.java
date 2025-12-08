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
    Medarbejder medarbejder = new Medarbejder(5,"test testerson", "123123");
    Destillering destillering = new Destillering(1,true,100,råvare,medarbejder);
    Leverandør leverandør = new Leverandør("test","test","123123123");
    Fad fad = new Fad(5,200,FadType.Sherry,leverandør);
    Destillat destillat = new Destillat(1,200,50,destillering);
    Lager lager = new Lager("Lager", 200);
    Reol reol = new Reol(1, lager);
    Række række = new Række(1, reol);


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
        double rest = destillat.getMaengde() - fad.getStørrelse();
        assertEquals("Der er " + rest + " Til overs",Controller.addDestillatTilFad(fad,destillat));
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
        Hylde hylde = new Hylde(1, række);
        hylde.setErOptaget(false);

        Controller.addFadTilHylde(fad, hylde);

        assertEquals(hylde, fad.getHylde(), "Fadet skal stå på den valgte hylde");
        assertTrue(hylde.isErOptaget(), "Hylden skal være markeret som optaget");
    }


    @Test
    void addFadTilHylde02() {
        Fad fad = new Fad(1,200,FadType.Bourbon, leverandør);
        Hylde hylde = new Hylde(1, række);

        hylde.setErOptaget(true);

        assertThrows(IllegalArgumentException.class, () -> Controller.addFadTilHylde(fad,hylde));
    }

    @Test
    void addFadTilHylde03() {

        Fad fad = null;
        Hylde hylde = new Hylde(1, række);
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

    @Test
    void createLeverandør04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør("Sall whisky",null,"12345678"));
    }

    @Test
    void createLeverandør05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør("Sall whisky","adresse 12",null));
    }

    @Test
    void createLeverandør06(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør("Sall whisky","","12345678"));
    }

    @Test
    void createLeverandør07(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør("Sall whisky","adresse 12",""));
    }

    @Test
    void createLeverandør08(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createLeverandør("Sall whisky","adresse 12","abcdefgh"));
    }

    @Test
    void createDestillering01(){
        int før = Storage.getDestillering().size();

        Controller.createDestillering(1,false,50,råvare,medarbejder);


        int efter = Storage.getDestillering().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createDestillering02(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(0,false,50,råvare,medarbejder));
    }

    @Test
    void createDestillering03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(-1,false,101,råvare,medarbejder));
    }

    @Test
    void createDestillering04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(1,false,0,råvare,medarbejder));
    }

    @Test
    void createDestillering05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(1,false,-30,råvare,medarbejder));
    }

    @Test
    void createDestillering07(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(1,false,-30,null,medarbejder));
    }

    @Test
    void createDestillering08(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(1,false,-30,råvare, null));
    }

    @Test
    void createDestillering09(){

        int før = Storage.getDestillering().size();

        råvare.setMængde(1);
        Controller.createDestillering(1,false,1,råvare,medarbejder);


        int efter = Storage.getDestillering().size();

        assertEquals(før + 1, efter);
    }

    @Test
    void createDestillering10(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createDestillering(1,false,150,råvare, medarbejder));
    }

    @Test
    void createRåvare01(){
        int før = Storage.getRåvarer().size();
        Controller.createRåvare("E3", "Evergreen", 110, LocalDate.of(2025,9,4), oprindelse);
        int efter = Storage.getRåvarer().size();
        assertEquals(før +1, efter);
    }

    @Test
    void createRåvare02(){
        int før = Storage.getRåvarer().size();
        Controller.createRåvare("E5", "Evergreen", 1, LocalDate.of(2025,3,1), oprindelse);
        int efter = Storage.getRåvarer().size();
        assertEquals(før + 1, efter);
    }

    @Test
    void createRåvare03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createRåvare("", "Evergreen", 100, LocalDate.of(2025,8,3), oprindelse));
    }

    @Test
    void createRåvare04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createRåvare("E6", "", 100, LocalDate.of(2025,8,3), oprindelse));
    }

    @Test
    void createRåvare05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createRåvare("E6", "Evergreen", 0, LocalDate.of(2025,8,3), oprindelse));
    }

    @Test
    void createRåvare06(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createRåvare("E6", "Evergreen", 100, LocalDate.of(2026,8,3), oprindelse));
    }

    @Test
    void createRåvare07(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createRåvare("E3", "Evergreen", 100, LocalDate.of(2025,8,3), null));
    }

    @Test
    void createOprindelse01(){
        int før = Storage.getOprindelser().size();
        Controller.createOprindelse("Marken", "Gården");
        int efter = Storage.getOprindelser().size();
        assertEquals(før + 1, efter);
    }

    @Test
    void createOprindelse02(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createOprindelse(null, "Gården"));
    }

    @Test
    void createOprindelse03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createOprindelse("Marken", null));
    }

    @Test
    void createOprindelse04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createOprindelse("", "Gården"));
    }

    @Test
    void createOprindelse05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createOprindelse("Marken", ""));
    }

    @Test
    void createMedarbejder01(){
        int før = Storage.getMedarbejdere().size();
        Controller.createMedarbejder(3,"Jens", "34213421");
        int efter = Storage.getMedarbejdere().size();
        assertEquals(før + 1, efter);
    }

    @Test
    void createMedarbejder02(){
        int før = Storage.getMedarbejdere().size();
        Controller.createMedarbejder(1, "Jens", "34213421");
        int efter = Storage.getMedarbejdere().size();
        assertEquals(før + 1, efter);
    }

    @Test
    void createMedarbejder03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createMedarbejder(0, "Jens", "34213421"));
    }

    @Test
    void createMedarbejder04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createMedarbejder(4, "", "34213421"));
    }

    @Test
    void createMedarbejder05(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createMedarbejder(4, "Jens", ""));
    }

    @Test
    void createMedarbejder06(){
        // medarbejders nr er også 5
        Storage.storeMedarbejder(medarbejder);
        assertThrows(IllegalArgumentException.class, () -> Controller.createMedarbejder(5, "Jens", "34213421"));
    }

    @Test
    void antalLedigePladserPåLager01(){
        Lager lager1 = new Lager("Lager1", 200);
        Reol reol1 = new Reol(1, lager1);
        lager1.addReolTilLager(reol1);

        Række række1 = new Række(1, reol1);
        reol1.addRækkeTilReol(række1);

        Hylde hylde1 = new Hylde(1, række1);
        Hylde hylde2 = new Hylde(2, række1);
        Hylde hylde3 = new Hylde(3, række1);
        række1.addHyldeTilRække(hylde1);
        række1.addHyldeTilRække(hylde2);
        række1.addHyldeTilRække(hylde3);

       int antalPladser = Controller.antalLedigePladserPåLager(lager1);

        assertEquals(3, antalPladser);
    }

    @Test
    void antalLedigePladserPåLager02(){
        Lager lager1 = new Lager("Lager1", 200);
        Reol reol1 = new Reol(1, lager1);
        lager1.addReolTilLager(reol1);

        Række række1 = new Række(1, reol1);
        reol1.addRækkeTilReol(række1);

        Hylde hylde1 = new Hylde(1, række1);
        række1.addHyldeTilRække(hylde1);
        hylde1.setErOptaget(true);

        int antalPladser = Controller.antalLedigePladserPåLager(lager1);

        assertEquals(0, antalPladser);
    }

    @Test
    void antalLedigePladserPåLager03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.antalLedigePladserPåLager(null));
    }

    @Test
    void findFadPåLager01(){
        Lager lager1 = new Lager("Lager 1", 200);
        Reol reol1 = new Reol(1, lager1);
        lager1.addReolTilLager(reol1);


        Række række1 = new Række(1, reol1);
        reol1.addRækkeTilReol(række1);


        Hylde hylde1 = new Hylde(1, række1);
        række1.addHyldeTilRække(hylde1);


        Fad fad1 = new Fad(3, 100, FadType.Bourbon, leverandør);
        Storage.storeFad(fad1);
        Controller.addFadTilHylde(fad1, hylde1);

        String result = Controller.findFadPåLager(3);

        assertTrue(result.contains("Fad ID: 3"));
        assertTrue(result.contains("Lager: Lager 1"));
        assertTrue(result.contains("Reol nummer: 1"));
        assertTrue(result.contains("Række nummer: 1"));
        assertTrue(result.contains("Hylde nummer: 1"));
    }

    @Test
    void findFadPåLager02(){
        Lager lager1 = new Lager("Lager 1", 200);
        Reol reol1 = new Reol(1, lager1);
        lager1.addReolTilLager(reol1);


        Række række1 = new Række(1, reol1);
        reol1.addRækkeTilReol(række1);


        Hylde hylde1 = new Hylde(1, række1);
        række1.addHyldeTilRække(hylde1);


        Fad fad2 = new Fad(1, 100, FadType.Bourbon, leverandør);
        Storage.storeFad(fad2);
        Controller.addFadTilHylde(fad2, hylde1);

        String result = Controller.findFadPåLager(1);

        assertTrue(result.contains("Fad ID: 1"));
        assertTrue(result.contains("Lager: Lager 1"));
        assertTrue(result.contains("Reol nummer: 1"));
        assertTrue(result.contains("Række nummer: 1"));
        assertTrue(result.contains("Hylde nummer: 1"));
    }

    @Test
    void findFadPåLager03(){
        String result = Controller.findFadPåLager(10);
        assertTrue(result.contains("FadID ikke i brug."));

    }

    @Test
    void findFadPåLager04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.findFadPåLager(0));
    }

    @Test
    void createHylde01(){
        int før = Storage.getHylder().size();
        Controller.createHylde(5,række);
        int efter = Storage.getHylder().size();
        assertEquals(før + 1, efter);
    }

    @Test
    void createHylde02(){
        int før = Storage.getHylder().size();
        Controller.createHylde(1,række);
        int efter = Storage.getHylder().size();
        assertEquals(før + 1, efter);
    }

    @Test
    void createHylde03(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createHylde(0, række));
    }

    @Test
    void createHylde04(){
        assertThrows(IllegalArgumentException.class, () -> Controller.createHylde(5, null));
    }
}