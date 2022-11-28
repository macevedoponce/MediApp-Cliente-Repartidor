package acevedo.EvalFin.org.Clases;

import java.util.ArrayList;

public class Meses {

    private static ArrayList<Meses> mesesArrayList;

    private int id;
    private String mes;

    public Meses(int id, String mes) {
        this.id = id;
        this.mes = mes;
    }

    public static void initMeses(){
        mesesArrayList = new ArrayList<>();
        Meses def = new Meses(0,"");
        Meses enero = new Meses(1,"ene.");
        Meses febrero = new Meses(2,"feb.");
        Meses marzo = new Meses(3,"mar.");
        Meses abril = new Meses(4,"abr.");
        Meses mayo = new Meses(5,"may.");
        Meses junio = new Meses(6,"jun.");
        Meses julio = new Meses(7,"jul.");
        Meses agosto = new Meses(8,"ago.");
        Meses septiembre = new Meses(9,"sep.");
        Meses octubre = new Meses(10,"oct.");
        Meses noviembre = new Meses(11,"nov.");
        Meses diciembre = new Meses(12,"dic.");
        mesesArrayList.add(def);
        mesesArrayList.add(enero);
        mesesArrayList.add(febrero);
        mesesArrayList.add(marzo);
        mesesArrayList.add(abril);
        mesesArrayList.add(mayo);
        mesesArrayList.add(junio);
        mesesArrayList.add(julio);
        mesesArrayList.add(agosto);
        mesesArrayList.add(septiembre);
        mesesArrayList.add(octubre);
        mesesArrayList.add(noviembre);
        mesesArrayList.add(diciembre);
    }

    public static ArrayList<Meses> getMesesArrayList() {
        return mesesArrayList;
    }

    public static String[] mesesNames(){
        String[] names = new String[mesesArrayList.size()];
        for(int i = 0; i<mesesArrayList.size();i++){
            names[i]= mesesArrayList.get(i).mes;
        }
        return names;
    }

    public static void setMesesArrayList(ArrayList<Meses> mesesArrayList) {
        Meses.mesesArrayList = mesesArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}

