package Storage;

import Controller.Storage;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage, Serializable {
    private final List<Destillat> destillater = new ArrayList<>();
    private final List<Fad> fade = new ArrayList<>();
    private final List<Destillering> destilleringer = new ArrayList<>();
    private final List<Leverandør> leverandører = new ArrayList<>();
    private final List<FærdigVare> færdigvarer = new ArrayList<>();
    private final List<Medarbejder> medarbejdere = new ArrayList<>();
    private final List<Råvare> råvarer = new ArrayList<>();
    private final List<Oprindelse> oprindelser = new ArrayList<>();
    private final List<Lager> lagere = new ArrayList<>();
    private final List<Reol> reoler = new ArrayList<>();
    private final List<Række> rækker = new ArrayList<>();
    private final List<Hylde> hylder = new ArrayList<>();


    @Override
    public List<Destillat> getDestillat() {
        return List.copyOf(destillater);
    }

    @Override
    public void storeDestillat(Destillat destillat) {
destillater.add(destillat);
    }

    @Override
    public void deleteDestillat(Destillat destillat) {
destillater.remove(destillat);
    }

    @Override
    public List<Destillering> getDestillering() {
        return List.copyOf(destilleringer);
    }

    @Override
    public void storeDestillering(Destillering destillering) {
destilleringer.add(destillering);
    }

    @Override
    public void deleteDestillering(Destillering destillering) {
destilleringer.remove(destillering);
    }

    @Override
    public List<Fad> getFad() {
        return List.copyOf(fade);
    }

    @Override
    public void storeFad(Fad fad) {
fade.add(fad);
    }

    @Override
    public void deleteFad(Fad fad) {
fade.remove(fad);
    }

    @Override
    public List<FærdigVare> getFærdigvare() {
        return List.copyOf(færdigvarer);
    }

    @Override
    public void storeFærdigvare(FærdigVare færdigVare) {
færdigvarer.add(færdigVare);
    }

    @Override
    public void deleteFærdigvare(FærdigVare færdigVare) {
færdigvarer.remove(færdigVare);
    }

    @Override
    public List<Hylde> getHylde() {
        return List.copyOf(hylder);
    }

    @Override
    public void storeHylde(Hylde hylde) {
hylder.add(hylde);
    }

    @Override
    public void deleteHylde(Hylde hylde) {
hylder.remove(hylde);
    }

    @Override
    public List<Lager> getLager() {
        return List.copyOf(lagere);
    }

    @Override
    public void storeLager(Lager lager) {
lagere.add(lager);
    }

    @Override
    public void deleteLager(Lager lager) {
lagere.remove(lager);
    }

    @Override
    public List<Leverandør> getLeverandør() {
        return List.copyOf(leverandører);
    }

    @Override
    public void storeLeverandør(Leverandør leverandør) {
leverandører.add(leverandør);
    }

    @Override
    public void deleteLeverandør(Leverandør leverandør) {
leverandører.remove(leverandør);
    }

    @Override
    public List<Medarbejder> getMedarbejder() {
        return List.copyOf(medarbejdere);
    }

    @Override
    public void storeMedarbejder(Medarbejder medarbejder) {
medarbejdere.add(medarbejder);
    }

    @Override
    public void deleteMedarbejder(Medarbejder medarbejder) {
medarbejdere.remove(medarbejder);
    }

    @Override
    public List<Oprindelse> getOprindelse() {
        return List.copyOf(oprindelser);
    }

    @Override
    public void storeOprindelse(Oprindelse oprindelse) {
oprindelser.add(oprindelse);
    }

    @Override
    public void deleteOprindelse(Oprindelse oprindelse) {
oprindelser.remove(oprindelse);
    }

    @Override
    public List<Reol> getReol() {
        return List.copyOf(reoler);
    }

    @Override
    public void storeReol(Reol reol) {
reoler.add(reol);
    }

    @Override
    public void deleteReol(Reol reol) {
reoler.remove(reol);
    }

    @Override
    public List<Råvare> getRåvare() {
        return List.copyOf(råvarer);
    }

    @Override
    public void storeRåvare(Råvare råvare) {
råvarer.add(råvare);
    }

    @Override
    public void deleteRåvare(Råvare råvare) {
råvarer.remove(råvare);
    }

    @Override
    public List<Række> getRækker() {
        return List.copyOf(rækker);
    }

    @Override
    public void storeRække(Række række) {
rækker.add(række);
    }

    @Override
    public void deleteRække(Række række) {
rækker.remove(række);
    }

    public static ListStorage loadStorage(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)
        ) {
            Object obj = objIn.readObject();
            ListStorage storage = (ListStorage) obj;
            System.out.println("Storage loaded from file " + fileName);
            return storage;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error deserializing storage");
            System.out.println(ex);
            return null;
        }
    }

    public static void saveStorage(String fileName, Storage storage) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)
        ) {
            objOut.writeObject(storage);
            System.out.println("Storage saved in file " + fileName);
        } catch (IOException ex) {
            System.out.println("Error serializing storage");
            System.out.println(ex);
            throw new RuntimeException();
        }
    }
}
