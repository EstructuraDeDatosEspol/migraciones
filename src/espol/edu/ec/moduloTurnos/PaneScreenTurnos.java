/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.moduloTurnos;

import espol.edu.ec.tda.SimplyLinkedListCircular;
import espol.edu.ec.tda.Const;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import espol.edu.ec.tda.Video;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class PaneScreenTurnos {
    
    private final StackPane backPane;
    private final VBox pane;
    private boolean isRun;
    private final SimplyLinkedListCircular<Video> videos;
    private final MediaView reproductor;
    private final VBox panel;
    private int index;
    
    public PaneScreenTurnos() {
        
        videos = new SimplyLinkedListCircular<>();
        backPane = new StackPane();
        pane = new VBox();
        panel = new VBox();
        reproductor = new MediaView();
        backPane.setAlignment(Pos.CENTER); 
        Rectangle fondo = new Rectangle(Const.MAX_WIDTH - 30, Const.MAX_HEIGHT - 30);
        fondo.setArcHeight(70); 
        fondo.setFill(Color.WHITE); 
        fondo.setArcWidth(70); 
        backPane.getChildren().addAll(fondo, pane);
        isRun = true;
        index = 1;
        topPane();
        cargarListaVideos();
        centerPane();
        bottomPane();
    }
    
    private void topPane(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(Const.MAX_HEIGHT/20, Const.MAX_WIDTH/30, Const.MAX_HEIGHT/30 , Const.MAX_WIDTH/30)); 
        LocalTime reloj = LocalTime.now();
        String fileImage = PaneScreenTurnos.class.getResource("/espol/edu/ec/recursos/assets/deb.png").toExternalForm();
        ImageView deb = new ImageView(new Image(fileImage, Const.MAX_WIDTH/10, Const.MAX_HEIGHT/10, true, true));
        Text text = new Text();
        text.setText(reloj.format(DateTimeFormatter.ofPattern("HH:mm")));
        hbox.getChildren().addAll(deb,text);
        hbox.setSpacing(Const.MAX_WIDTH - text.getText().length()*25 - deb.getImage().getWidth() - Const.MAX_WIDTH/30);
        pane.getChildren().add(hbox);
        text.setFill(Color.color(0.408, 0.718, 0.898)); 
        text.setFont(Font.font(38)); 
        text.setStyle(Const.FONT_BOLD); 
        Runnable run = () -> {
            long seconds = reloj.getSecond();
            seconds = (60 - seconds)*1000;
            while(isRun){
                sleep(seconds);
                LocalTime lt = LocalTime.now();
                Platform.runLater(() -> text.setText(lt.format(DateTimeFormatter.ofPattern("HH:mm"))));
                seconds = 1000;    
            }
        };
        
        Thread t = new Thread(run);
        t.start();
    }
    
    private void centerPane() {
        double width = Const.MAX_WIDTH/2 - Const.MAX_WIDTH/31;
        double height = Const.MAX_HEIGHT/1.5;
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 0, 0, Const.MAX_WIDTH/30));
        hbox.setSpacing(Const.MAX_WIDTH/80);  
        pane.getChildren().add(hbox);
        centerLeftPane(hbox, width, height);
        centerRightPane(hbox, width, height);
    }
    
    private void centerLeftPane(HBox hbox, double width, double height) {
        StackPane content = new StackPane();
        Rectangle background = new Rectangle(width, height);
        background.setFill(Color.BLACK);
        content.setAlignment(Pos.CENTER);
        content.getChildren().add(background);
        hbox.getChildren().add(content);
        
        reproductor.setSmooth(true);
        reproductor.setPreserveRatio(true); 
        reproductor.setFitHeight(height);
        reproductor.setFitWidth(width);
        content.getChildren().add(reproductor);
        Iterator<Video> i = videos.iterator();

        Runnable media = () -> {
            while(isRun){
                Video v = i.next();
                Platform.runLater(() -> reproductor.setMediaPlayer(v.getMedia()));
                try{
                    v.getMedia().play();
                    sleep(v.getDuration());
                    v.getMedia().stop();
                }catch(NullPointerException ex){
                    
                }
            } 
        };
        Thread t = new Thread(media);
        t.start();
    }
    
    private void centerRightPane(HBox hbox, double width, double height) { 
        panel.setSpacing(((height/4)/5)/3);
        Rectangle r1 = new Rectangle(width/2, (height/4)-(height/4)/5);
        r1.setFill(Color.color(0.408, 0.718, 0.898));
        Rectangle r2 = new Rectangle(width/2, (height/4)-(height/4)/5);
        r2.setFill(Color.color(0.392, 0.529, 0.667));
        Text turno = new Text("TURNO");
        Text puesto = new Text("PUESTO");
        turno.setStyle(Const.FONT_BOLD);
        puesto.setStyle(Const.FONT_BOLD); 
        turno.setFill(Color.WHITE);
        puesto.setFill(Color.WHITE); 
        turno.setFont(Font.font("Arial", r1.getHeight()/1.8));
        puesto.setFont(Font.font("Arial", r1.getHeight()/1.8));
        panel.getChildren().add(new HBox(new StackPane(r1, turno), new StackPane(r2, puesto)));
        hbox.getChildren().add(panel);
        for(int i=0; i<3; i++) {
            Rectangle r3 = new Rectangle(width/2, (height/4));
            r3.setFill(Color.color(0.408, 0.718, 0.898));
            Rectangle r4 = new Rectangle(width/2, (height/4));
            r4.setFill(Color.color(0.392, 0.529, 0.667));
            panel.getChildren().add(new HBox(new StackPane(r3), new StackPane(r4)));
        } 
    }
    
    private void bottomPane() {
        double width = Const.MAX_WIDTH - Const.MAX_WIDTH/19.5;
        StackPane barra = new StackPane();
        Rectangle r = new Rectangle(width, Const.MAX_HEIGHT/17);
        r.setFill(Color.color(0.408, 0.718, 0.898));
        VBox box = new VBox();
        Text m = new Text("H o r a r i o   d e   a t e n c i ó n   d e   L u n e s   a   V i e r n e s   d e   1 0   a   1 8   h s / S á b a d o s");
        m.setFont(Font.font("Arial", 27)); 
        m.setFill(Color.WHITE); 
        m.setStyle(Const.FONT_BOLD);
        HBox hbox = new HBox(m);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(0, 0, 0, Const.MAX_WIDTH/27));
        barra.getChildren().addAll(r, hbox);
        Rectangle re = new Rectangle(r.getWidth(), r.getHeight()/2.5);
        re.setFill(Color.WHITE);
        re.setTranslateX(Const.MAX_WIDTH/30); 
        box.getChildren().addAll(re, barra);
        pane.getChildren().add(box);
    }
    
    public boolean isFull() {
        return index == 4 || index == PaneModulo1.ATENCION.size() + 1;
    }
     
    public void atender(Turno turno) {
        panel.getChildren().remove(new TurnoPane(turno));
        index--;
        addTurno(PaneModulo1.SECCION_TURNO.getNext(), false);
//        int puestos = PaneModulo1.ATENCION.size();
//        boolean puestoEnPantalla = false;
//        if(puestos >= 2 && puestos < 5){
//            for(Node n: panel.getChildren()) 
//                if(n instanceof TurnoPane){ 
//                    Puesto puesto = ((TurnoPane)n).getTurno().getPuesto();
//                    Turno t = PaneModulo1.SECCION_TURNO.viewNext();
//                    if(t != null && puesto.equals(t.getPuesto()))
//                        puestoEnPantalla = true;
//                }
//        }
//        if(!puestoEnPantalla)
//            addTurno(PaneModulo1.SECCION_TURNO.getNext(), false);
//        else
//            addTurno(null, false);
    }
    
    public boolean addTurno(Turno turno, boolean inicio) {
        double width = (Const.MAX_WIDTH/2 - Const.MAX_WIDTH/31)/2;
        double height = (Const.MAX_HEIGHT/1.5)/4;
        if(turno == null) {
            Rectangle r3 = new Rectangle(width, height);
            r3.setFill(Color.color(0.408, 0.718, 0.898));
            Rectangle r4 = new Rectangle(width, height);
            r4.setFill(Color.color(0.392, 0.529, 0.667));
            panel.getChildren().add(new HBox(new StackPane(r3), new StackPane(r4)));
            return false;
        }
        TurnoPane tPane = new TurnoPane(turno, width, height);
        if(isFull()){
            index++;
            return panel.getChildren().add(tPane);
        }
        if(inicio)
            panel.getChildren().remove(index);
        panel.getChildren().add(index, tPane);
        if(index < 4)
            index++;
        return true;
    }
    
    private void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public Pane getRoot(){
        return backPane;
    }
    
    public void endRun(){
        isRun = false;
    }
    
    private void cargarListaVideos() {
        String file = new File("").getAbsolutePath();
        int i = file.indexOf("migraciones");
        file = Paths.get(file.substring(0, i + 11), "src", "espol", "edu", "ec", "recursos", "files", "videos.txt").toString();
       
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine()) != null) {
                String[] data = line.split(",");
                videos.addLast(new Video(new MediaPlayer(
                                new Media(PaneScreenTurnos.class.getResource(data[0]).toExternalForm())), 
                                Long.parseLong(data[1])));
            }
        }catch(IOException ex) {
            Logger.getLogger(PaneScreenTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
