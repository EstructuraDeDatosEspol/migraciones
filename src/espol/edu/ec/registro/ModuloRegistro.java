/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.registro;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author SSAM
 */
public class ModuloRegistro {
    private Pane root;
    private LinkedList<RegistroMigrante> listRegistroMigrantes;
    private LinkedList<Persona> listPersonas;
    private HashMap<Integer,Continente> listContinentes;
    private LinkedList<Canton> listCantones;
    private String canton;
    private RegistroMigrante reg;

    public ModuloRegistro(Pane root, Stage stage, boolean guar, boolean mod, boolean eli, boolean textf, boolean cb) {
        this.root = root;
        
        listContinentes = new ReadWriter().cargarContinentes("Continentes.txt");
        listPersonas = new ReadWriter().cargarPersonas("Persona.txt");
        listRegistroMigrantes = new ReadWriter().cargarRegistro("Registros.txt");
        listCantones = referenciarCantones(listContinentes);
        enlazarListas();
        System.out.println(listRegistroMigrantes.getFirst());
        listCantones.get(39).getList().get(0).setAnio_movi(1);
        System.out.println(listRegistroMigrantes.getFirst());
        System.out.println(listPersonas.getLast().getList());
        System.out.println(listCantones.get(39).getList());
        
        Button boton1 = new Button("Guardar");
        boton1.setDisable(guar);
        boton1.setTranslateX(1100);
        boton1.setTranslateY(300);
        Button boton4 = new Button("SobreEscribir");
        boton4.setDisable(mod);
        boton4.setTranslateX(1100);
        boton4.setTranslateY(340);
        Button boton2 = new Button("Eliminar");
        boton2.setDisable(eli);
        boton2.setTranslateX(1100);
        boton2.setTranslateY(380);
        Button boton3 = new Button("Volver");
        boton3.setTranslateX(1100);
        boton3.setTranslateY(420);
        DisenioFormulario disenioFormulario = new DisenioFormulario(root,listCantones, textf, cb);
        if(!cb){
            disenioFormulario.getText27().setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.TAB)){
                    disenioFormulario.getComboBox().setValue("");
                    llenarRegistro(disenioFormulario.getText27().getText(),disenioFormulario);                    
                }                
            });
        }
        
        //cedula
        disenioFormulario.getText10().setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.TAB)){
                llenarCamposPersonas(disenioFormulario.getText10().getText(), disenioFormulario);
            }
        });
        disenioFormulario.getCalendario().setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.TAB)){
                disenioFormulario.getCalendario().setFocusTraversable(true);
            }
        });
        disenioFormulario.getComboBox().setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.TAB)){
                llenarCampoProv(disenioFormulario.getComboBox().getValue().toString(), disenioFormulario);
            }
        });
        disenioFormulario.getText16().setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)  || ke.getCode().equals(KeyCode.TAB)){
                llenarCampoContinente(disenioFormulario.getText16().getText(), disenioFormulario.getText23(), disenioFormulario.getText25());
            }
        });
        disenioFormulario.getText17().setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.TAB)){
                llenarCampoContinente(disenioFormulario.getText17().getText(), disenioFormulario.getText21(), disenioFormulario.getText24());
            }
        });
        disenioFormulario.getText18().setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.TAB)){
                llenarCampoContinente(disenioFormulario.getText18().getText(), disenioFormulario.getText22(), null);
            }
        });
        
        boton1.setOnAction(e -> {
            if(verificarVacio(disenioFormulario))System.out.println("esta vacio");
            else System.out.println("se elimino");
        });
        boton2.setOnAction(e -> {
            if(reg == null)System.out.println("esta vacio");
            else {
                for(RegistroMigrante i: listRegistroMigrantes){
                    if(i.equals(reg)){
                        i.volverNull();
                        break;
                    }
                }
                System.out.println(listRegistroMigrantes.getFirst());
                System.out.println(listPersonas.getLast().getList());
                System.out.println(listCantones.get(39).getList());
                new ReadWriter().eliminarRegistro(listRegistroMigrantes, "Registros.txt");
                System.out.println(listRegistroMigrantes);
            }
        });
        boton3.setOnAction(e -> new PantallaOpciones(stage));
        boton4.setOnAction(e -> {
            if(reg == null)System.out.println("esta vacio");
            else {
                for(RegistroMigrante i: listRegistroMigrantes){
                    if(i.equals(reg)){
                        modificarRegistro(i, disenioFormulario);
                        break;
                    }
                }
                System.out.println(listRegistroMigrantes.getFirst());
                System.out.println(listPersonas.getLast().getList());
                System.out.println(listCantones.get(39).getList());
                new ReadWriter().eliminarRegistro(listRegistroMigrantes, "Registros.txt");
            }
        });
        
        root.getChildren().addAll(boton1,boton4,boton2,boton3);
    }
    
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public LinkedList<RegistroMigrante> getListRegistroMigrantes() {
        return listRegistroMigrantes;
    }

    public void setListRegistroMigrantes(LinkedList<RegistroMigrante> listRegistroMigrantes) {
        this.listRegistroMigrantes = listRegistroMigrantes;
    }

    public LinkedList<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(LinkedList<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    public HashMap<Integer, Continente> getListContinentes() {
        return listContinentes;
    }

    public void setListContinentes(HashMap<Integer, Continente> listContinentes) {
        this.listContinentes = listContinentes;
    }

    public LinkedList<Canton> getListCantones() {
        return listCantones;
    }

    public void setListCantones(LinkedList<Canton> listCantones) {
        this.listCantones = listCantones;
    }
    
    public static LinkedList<Canton> referenciarCantones(HashMap<Integer,Continente> mapaContinentes){
        LinkedList<Canton> lista = new LinkedList<>();
        for(Map.Entry<Integer, Continente> c: mapaContinentes.entrySet()){
            Continente con = c.getValue();//obtengo el continente perteneciente a ese continente
            for(Integer p: con.getMapaPaises().keySet()){
                Pais pais = con.getMapaPaises().get(p);//obtengo el pais perteneciente a ese continente(con clave p)
                for(Integer pr: pais.getMapaProvincias().keySet()){
                    Provincia provincia = pais.getMapaProvincias().get(pr);
                    //obtengo la provincia perteneciente a ese pais(con clave pr)
                    for(Integer can: provincia.getMapaCantones().keySet()){
                        lista.addLast(provincia.getMapaCantones().get(can));
                    }
                }
            }
        }
        return lista;
    }
    
    public final void enlazarListas(){
        for(RegistroMigrante rm: listRegistroMigrantes){
            for(Persona p: listPersonas){
                if(rm.getPersona().equals(p))p.getList().addLast(rm);
            }
            for(Canton c: listCantones){
                if(rm.getCanton().equals(c))c.getList().addLast(rm);
            }
        }
    }
    
    public void llenarCamposPersonas(String cedula, DisenioFormulario d){
        try{
            if(cedula.length()>0){
                int ced = Integer.valueOf(cedula);
                for(Persona p: listPersonas){
                    if(p.getCedula() == (ced)){
                        d.getText11().setText(p.getNombre());
                        d.getText12().setText(p.getApellido());
                        d.getText26().setText(p.getSexo());
                        d.getText13().setText(String.valueOf(p.getAnioNacimiento()));
                        d.getText14().setText(p.getOcupacion());
                        d.getText16().setText(p.getNacionalidad());
                        d.getText20().setText(String.valueOf(p.getEdad()));
                        d.getText23().setText(p.getContinenteNacimiento());
                        d.getText25().setText(p.getSubcontnacionalidad());
                        d.getText15().requestFocus();
                    }
                }
            }
        }catch(NumberFormatException n){
            d.getText10().setText("");
            System.out.println("Solo numero");
        }
    }
    
    public void llenarCampoProv(String canton, DisenioFormulario d){
        if(canton.length()>0){
            for(Canton c: listCantones){
                if(c.getNombre().equals(canton)){
                    Continente cont = listContinentes.get(c.getCodContinente());
                    Pais pais = cont.getMapaPaises().get(c.getCodPais());
                    Provincia pr = pais.getMapaProvincias().get(c.getCodigoProv());
                    d.getText4().setText(pr.getNombre());
                    d.getText2().setText(pais.getNombre());
                }
            }
        }
    }
    
    public void llenarCampoContinente(String pais, TextField tf, TextField tf2){
        if(pais.length()>0){
            for(Map.Entry<Integer, Continente> e: listContinentes.entrySet()){
                for(Map.Entry<Integer, Pais> p: e.getValue().getMapaPaises().entrySet()){
                    if(pais.equals(p.getValue().getNombre())){
                        tf.setText(e.getValue().getNombre());
                        if(tf2!=null)tf2.setText(e.getValue().getMapaSub().get(p.getValue().getCodSubContinente()).getNombre());
                    }
                }
            }
        }
    }
    
    public boolean verificarVacio(DisenioFormulario d){
        LinkedList<String> lista = new LinkedList<>();
        if(d.getText1().getText().length()!=0)lista.addLast(d.getText1().getText());
        if(d.getText2().getText().length()!=0)lista.addLast(d.getText2().getText());
        if(d.getText3().getText().length()!=0)lista.addLast(d.getText3().getText());
        if(d.getComboBox().getValue().toString().length()!=0)lista.addLast(d.getComboBox().getValue().toString());
        if(d.getText4().getText().length()!=0)lista.addLast(d.getText4().getText());
        return d.getText4().getText().length()==0 || d.getCalendario().getValue().toString().length() == 0 ||
            d.getText10().getText().length()==0 || d.getText11().getText().length()==0 ||
            d.getText12().getText().length()==0 || d.getText26().getText().length()==0 ||
            d.getText13().getText().length()==0 || d.getText14().getText().length()==0 ||
            d.getText15().getText().length()==0 || d.getText16().getText().length()==0 ||
            d.getText17().getText().length()==0 || d.getText18().getText().length()==0 ||
            d.getText19().getText().length()==0 || d.getText20().getText().length()==0 ||
            d.getText21().getText().length()==0 || d.getText22().getText().length()==0 ||
            d.getText23().getText().length()==0 || d.getText24().getText().length()==0 ||
            d.getText25().getText().length()==0;
    }
    
    public void llenarRegistro(String cedula, DisenioFormulario d){
        try{
            if(cedula.length()>0){
                int ced = Integer.valueOf(cedula);
                for(Persona p: listPersonas){
                    if(p.getCedula() == ced){
                        System.out.println(p.getList());
                        d.getComboBox2().setItems(FXCollections.observableList(p.getList()));
                        d.getComboBox2().setDisable(false);
                        d.getComboBox2().setOnAction(e -> {
                            d.getText10().setText(cedula);
                            System.out.println("nullpoin");
                            d.getComboBox().setValue(d.getComboBox2().getValue().getCanton().getNombre());
                            System.out.println("exception");
                            llenarCamposPersonas(cedula,d);
                            llenarCampoProv(d.getComboBox2().getValue().getCanton().getNombre(),d);
                            d.getText17().setText(d.getComboBox2().getValue().getPais_procedencia());
                            llenarCampoContinente(d.getComboBox2().getValue().getPais_procedencia(),d.getText21(),d.getText24());
                            d.getText18().setText(d.getComboBox2().getValue().getPais_residencia());
                            llenarCampoContinente(d.getComboBox2().getValue().getPais_residencia(),d.getText22(),null);
                            d.getText1().setText(d.getComboBox2().getValue().getTip_mov());
                            d.getText3().setText(d.getComboBox2().getValue().getVia_transporte());
                            d.getText15().setText(d.getComboBox2().getValue().getMotivo_viaje());
                            d.getText19().setText(d.getComboBox2().getValue().getLugar_proveniente());
                            d.getCalendario().setValue(LocalDate.of(d.getComboBox2().getValue().getAnio_movi(), mesEntero(d.getComboBox2().getValue().getMes_movi()), d.getComboBox2().getValue().getDia_movi()));                            
                            reg = d.getComboBox2().getValue();
                        });
                    }
                }
            }
        }catch(NumberFormatException n){
            d.getText10().setText("");
            System.out.println("Solo numero");
        }
    }
    
    public Integer mesEntero(String mes){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("enero", 1);
        map.put("febrero", 2);
        map.put("marzo", 3);
        map.put("abril", 4);
        map.put("mayo", 5);
        map.put("junio", 6);
        map.put("julio", 7);
        map.put("agosto", 8);
        map.put("septiembre", 9);
        map.put("octubre", 10);
        map.put("noviembre", 11);
        map.put("diciembre", 12);
        return map.get(mes);
    }
    
    public String mesEspaniol(String mes){
        HashMap<String, String> map = new HashMap<>();
        map.put("JANUARY","enero");
        map.put("FEBRUARY","febrero");
        map.put("MARCH","marzo");
        map.put("APRIL","abril");
        map.put("MAY","mayo");
        map.put("JUNE","junio");
        map.put("JULY","julio");
        map.put("AUGUST","agosto");
        map.put("SEPTEMBER","septiembre");
        map.put("OCTOBER","octubre");
        map.put("NOVEMBER","noviembre");
        map.put("DECEMBER","diciembre");
        return map.get(mes);
    }
    
    public void modificarRegistro(RegistroMigrante r, DisenioFormulario d){
        r.setTip_mov(d.getText1().getText());
        r.setVia_transporte(d.getText3().getText());
        r.setCanton(new Canton(d.getComboBox().getValue().toString()));
        r.setProvincia_movi(d.getText4().getText());
        r.setTipo_nacionalidad(d.getText2().getText());
        r.setDia_movi(d.getCalendario().getValue().getDayOfMonth());
        r.setMes_movi(mesEspaniol(d.getCalendario().getValue().getMonth().toString()));
        r.setAnio_movi(d.getCalendario().getValue().getYear());
        //r.setPersona(new Persona(Integer.parseInt(d.getText10().getText()),d.getText11().getText(),d.getText12().getText(),d.getText26().getText(),Integer.parseInt(d.getText13().getText()),d.getText14().getText(),Integer.parseInt(d.getText20().getText()),d.getText16().getText(),d.getText23().getText(),d.getText25().getText()));
        r.setMotivo_viaje(d.getText15().getText());
        r.setPais_procedencia(d.getText17().getText());
        r.setPais_residencia(d.getText18().getText());
        r.setLugar_proveniente(d.getText19().getText());
        r.setCont_procedencia(d.getText21().getText());
        r.setCont_residencia(d.getText22().getText());
        r.setSubcont_procedencia(d.getText24().getText());
    }
}
