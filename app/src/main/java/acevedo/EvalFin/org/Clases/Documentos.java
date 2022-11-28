package acevedo.EvalFin.org.Clases;

import java.util.ArrayList;

public class Documentos {

    private static ArrayList<Documentos> documentosArrayList;
    private int id;
    private String documento;


    public Documentos(int id, String documento) {
        this.id = id;
        this.documento = documento;
    }

    public static void initDocumentos(){
        documentosArrayList = new ArrayList<>();
        Documentos carnet = new Documentos(0,"Carnet de extranjería");
        Documentos dni = new Documentos(1,"DNI");
        Documentos pasaporte = new Documentos(2,"Pasaporte");
        documentosArrayList.add(dni);
        documentosArrayList.add(carnet);
        documentosArrayList.add(pasaporte);
    }

    public static String[] documentosNames(){
        String[] names = new String[documentosArrayList.size()];
        for(int i = 0; i<documentosArrayList.size();i++){
            names[i]= documentosArrayList.get(i).documento;
        }
        return names;
    }

    public static ArrayList<Documentos> getDocumentosArrayList() {
        return documentosArrayList;
    }

    public static void setDocumentosArrayList(ArrayList<Documentos> documentosArrayList) {
        Documentos.documentosArrayList = documentosArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
