package Controller;

import Storage.Storage;
import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Oprindelse oprindelse = new Oprindelse("gaard", "mark");
    Råvare råvare = new Råvare("test","test",100, LocalDate.of(2025,12,1),oprindelse);
    Medarbejder medarbejder = new Medarbejder(1,"test testerson", 123123);
    Destillering destillering = new Destillering(true,1,råvare,medarbejder);
    Leverandør leverandør = new Leverandør("test","test",123123123);
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
    void addDestillatTilFad(){
        fad.setErAktiv(false);
        Controller.addDestillatTilFad(fad,destillat);
        assertTrue(fad.isErAktiv());
    }
}