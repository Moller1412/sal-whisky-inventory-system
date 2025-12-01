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
}