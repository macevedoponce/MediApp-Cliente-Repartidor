package acevedo.EvalFin.org.Clases;

import java.util.ArrayList;

public class Generos {
    private static ArrayList<Generos> generosArrayList;

    private int id;
    private String genero;

    public Generos(int id, String genero) {
        this.id = id;
        this.genero = genero;
    }

    public static void initGeneros(){
        generosArrayList = new ArrayList<>();
        Generos masculino = new Generos(0,"Femenino");
        Generos femenino = new Generos(1,"Masculino");
        Generos pref_no_decirlo = new Generos(2,"Prefiero no decirlo");
        generosArrayList.add(masculino);
        generosArrayList.add(femenino);
        generosArrayList.add(pref_no_decirlo);
    }

    public static String[] generosNames(){
        String[] names = new String[generosArrayList.size()];
        for(int i = 0; i<generosArrayList.size();i++){
            names[i]= generosArrayList.get(i).genero;
        }
        return names;
    }

    public static ArrayList<Generos> getGenerosArrayList() {
        return generosArrayList;
    }

    public static void setGenerosArrayList(ArrayList<Generos> generosArrayList) {
        Generos.generosArrayList = generosArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
