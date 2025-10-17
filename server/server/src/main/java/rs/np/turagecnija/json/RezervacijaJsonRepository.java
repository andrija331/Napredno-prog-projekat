/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.turagecnija.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import rs.np.turagencija.domen.Rezervacija;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RezervacijaJsonRepository {

    private static final String FILE_PATH = "data/rezervacije.json";
    private final Gson gson;

    public RezervacijaJsonRepository() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }

        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Rezervacija> ucitajSve() {
        try ( FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Rezervacija>>() {
            }.getType();
            List<Rezervacija> rezervacije = gson.fromJson(reader, listType);
            return rezervacije != null ? rezervacije : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void sacuvajSve(List<Rezervacija> rezervacije) {
        try ( FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(rezervacije, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
