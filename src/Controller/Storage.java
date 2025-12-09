package Controller;

import model.*;

import java.util.List;

public interface Storage {
List<Destillat> getDestillat();
void storeDestillat(Destillat destillat);
void deleteDestillat(Destillat destillat);

List<Destillering> getDestillering();
void storeDestillering(Destillering destillering);
void deleteDestillering(Destillering destillering);

List<Fad> getFad();
void storeFad(Fad fad);
void deleteFad(Fad fad);

List<FærdigVare> getFærdigvare();
void storeFærdigvare(FærdigVare færdigVare);
void deleteFærdigvare(FærdigVare færdigVare);

List<Hylde> getHylde();
void storeHylde(Hylde hylde);
void deleteHylde(Hylde hylde);

List<Lager> getLager();
void storeLager(Lager lager);
void deleteLager(Lager lager);

List<Leverandør> getLeverandør();
void storeLeverandør(Leverandør leverandør);
void deleteLeverandør(Leverandør leverandør);

List<Medarbejder> getMedarbejder();
void storeMedarbejder(Medarbejder medarbejder);
void deleteMedarbejder(Medarbejder medarbejder);

List<Oprindelse> getOprindelse();
void storeOprindelse(Oprindelse oprindelse);
void deleteOprindelse(Oprindelse oprindelse);

List<Reol> getReol();
void storeReol(Reol reol);
void deleteReol(Reol reol);

List<Råvare> getRåvare();
void storeRåvare(Råvare råvare);
void deleteRåvare(Råvare råvare);

List<Række> getRækker();
void storeRække(Række række);
void deleteRække(Række række);
}
