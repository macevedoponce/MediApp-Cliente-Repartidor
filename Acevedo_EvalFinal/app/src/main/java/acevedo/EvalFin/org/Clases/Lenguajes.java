package acevedo.EvalFin.org.Clases;

import java.util.ArrayList;

public class Lenguajes {

    private static ArrayList<Lenguajes> lenguajesArrayList;
    private int id;
    private String lenguaje;

    public Lenguajes() {
    }

    public Lenguajes(int id, String lenguaje) {
        this.id = id;
        this.lenguaje = lenguaje;
    }

    public static ArrayList<Lenguajes> getLenguajesArrayList() {
        return lenguajesArrayList;
    }

    public static void initLenguajes(){
        lenguajesArrayList = new ArrayList<>();
        Lenguajes es = new Lenguajes(0,"Español");
        Lenguajes en = new Lenguajes(1,"Ingles");
        lenguajesArrayList.add(es);
        lenguajesArrayList.add(en);
    }

    public static String[] LenguajesNames(){
        String[] names = new String[lenguajesArrayList.size()];
        for(int i = 0; i<lenguajesArrayList.size();i++){
            names[i]= lenguajesArrayList.get(i).lenguaje;
        }
        return names;
    }

    public static void setLenguajesArrayList(ArrayList<Lenguajes> lenguajesArrayList) {
        Lenguajes.lenguajesArrayList = lenguajesArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }
}
