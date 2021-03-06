/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.moduloDeRegistro;

import espol.edu.ec.tda.entidades.Canton;
import java.time.LocalDate;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author SSAM
 */
public class DisenioFormulario {
    private Pane root;
    private TextField text1;
    private TextField text2;
    private TextField text3;
    private TextField text4;
    private DatePicker calendario;
    private TextField text10;
    private TextField text11;
    private TextField text12;
    private TextField text13;
    private TextField text14;
    private TextField text15;
    private TextField text16;
    private TextField text17;
    private TextField text18;
    private TextField text19;
    private TextField text20;
    private TextField text21;
    private TextField text22;
    private TextField text23;
    private TextField text24;
    private TextField text25;
    private TextField text26;
    private ComboBox comboBox;
    private TextField text27;
    private ComboBox<RegistroMigrante> comboBox2;

    public DisenioFormulario(Pane root,LinkedList<Canton> listCantones, boolean textf, boolean cb) {
        this.root = root;
        root.getStylesheets().add(DisenioFormulario.class.getResource("/espol/edu/ec/recursos/assets/estilo.css").toExternalForm());
        int n = 70;
        int espacio = 45;
        int enX = 150;
        int enXTexto = 350;
        int enX2 = 650;
        int enXTexto2 = 850;
        Label label1 = new Label("Tipo de Movimiento:");
        label1.setTranslateX(enX);
        label1.setTranslateY(n);
        text1 = new TextField();
        text1.setDisable(textf);
        text1.setTranslateX(enXTexto);
        text1.setTranslateY(n);
        
        Label label3 = new Label("Via Transporte:");
        label3.setTranslateX(enX);
        label3.setTranslateY(n+=espacio);        
        text3 = new TextField();
        text3.setDisable(textf);
        text3.setTranslateX(enXTexto);
        text3.setTranslateY(n);
        
        Label label5 = new Label("Canton Movimiento:");
        label5.setTranslateX(enX);
        label5.setTranslateY(n+=espacio);
        comboBox = new ComboBox(FXCollections.observableList(listCantones));
        comboBox.setPromptText("Canton");
        comboBox.setEditable(true);
        comboBox.setTranslateX(enXTexto);
        comboBox.setTranslateY(n);
        comboBox.setValue("Guayaquil");
        comboBox.setDisable(textf);
        
        Label label4 = new Label("Provincia Movimiento:");
        label4.setTranslateX(enX);
        label4.setTranslateY(n+=espacio);
        text4 = new TextField();
        text4.setDisable(textf);
        text4.setTranslateX(enXTexto);
        text4.setTranslateY(n);
        
        Label label2 = new Label("Pais Movimiento:");
        label2.setTranslateX(enX);
        label2.setTranslateY(n+=espacio);
        text2 = new TextField();
        text2.setDisable(textf);
        text2.setTranslateX(enXTexto);
        text2.setTranslateY(n);
        
        Label label6 = new Label("Fecha Movimiento:");
        label6.setTranslateX(enX);
        label6.setTranslateY(n+=espacio);
        calendario = new DatePicker();
        calendario.setTranslateX(enXTexto);
        calendario.setTranslateY(n);
        calendario.setValue(LocalDate.now());
        calendario.setDisable(textf);
        
        Label label10 = new Label("Cedula:");
        label10.setTranslateX(enX);
        label10.setTranslateY(n+=espacio);
        text10 = new TextField();
        text10.setDisable(!cb);
        text10.setTranslateX(enXTexto);
        text10.setTranslateY(n);
        
        
        Label label11 = new Label("Nombre:");
        label11.setTranslateX(enX);
        label11.setTranslateY(n+=espacio);
        text11 = new TextField();
        text11.setDisable(!cb);
        text11.setTranslateX(enXTexto);
        text11.setTranslateY(n);
        
        Label label12 = new Label("Apellido:");
        label12.setTranslateX(enX);
        label12.setTranslateY(n+=espacio);
        text12 = new TextField();
        text12.setDisable(!cb);
        text12.setTranslateX(enXTexto);
        text12.setTranslateY(n);
        
        Label label26 = new Label("Sexo:");
        label26.setTranslateX(enX);
        label26.setTranslateY(n+=espacio);
        text26 = new TextField();
        text26.setDisable(!cb);
        text26.setTranslateX(enXTexto);
        text26.setTranslateY(n);
        
        Label label13 = new Label("Anio Nacimiento:");
        label13.setTranslateX(enX);
        label13.setTranslateY(n+=espacio);
        text13 = new TextField();
        text13.setDisable(!cb);
        text13.setTranslateX(enXTexto);
        text13.setTranslateY(n);
        
        Label label14 = new Label("Ocupacion Migrante:");
        label14.setTranslateX(enX);
        label14.setTranslateY(n+=espacio);
        text14 = new TextField();
        text14.setDisable(!cb);
        text14.setTranslateX(enXTexto);
        text14.setTranslateY(n);
        
        Label label15 = new Label("Motivo viaje:");
        label15.setTranslateX(enX);
        label15.setTranslateY(n+=espacio);
        text15 = new TextField();
        text15.setDisable(textf);
        text15.setTranslateX(enXTexto);
        text15.setTranslateY(n);
        
        Label label16 = new Label("Pais Nacimiento:");
        label16.setTranslateX(enX2);
        label16.setTranslateY(n=70);
        text16 = new TextField();
        text16.setDisable(!cb);
        text16.setTranslateX(enXTexto2);
        text16.setTranslateY(n);
        
        Label label17 = new Label("Pais Procedencia:");
        label17.setTranslateX(enX2);
        label17.setTranslateY(n+=espacio);
        text17 = new TextField();
        text17.setDisable(textf);
        text17.setTranslateX(enXTexto2);
        text17.setTranslateY(n);
        
        Label label18 = new Label("Pais Residencia:");
        label18.setTranslateX(enX2);
        label18.setTranslateY(n+=espacio);
        text18 = new TextField();
        text18.setDisable(textf);
        text18.setTranslateX(enXTexto2);
        text18.setTranslateY(n);
        
        Label label19 = new Label("Lugar Procedencia:");
        label19.setTranslateX(enX2);
        label19.setTranslateY(n+=espacio);
        text19 = new TextField();
        text19.setDisable(textf);
        text19.setTranslateX(enXTexto2);
        text19.setTranslateY(n);
        
        Label label20 = new Label("Edad:");
        label20.setTranslateX(enX2);
        label20.setTranslateY(n+=espacio);
        text20 = new TextField();
        text20.setDisable(!cb);
        text20.setTranslateX(enXTexto2);
        text20.setTranslateY(n);
        
        Label label21 = new Label("Continente Procedencia:");
        label21.setTranslateX(enX2);
        label21.setTranslateY(n+=espacio);
        text21 = new TextField();
        text21.setDisable(textf);
        text21.setTranslateX(enXTexto2);
        text21.setTranslateY(n);
        
        Label label22 = new Label("Continente Residencia:");
        label22.setTranslateX(enX2);
        label22.setTranslateY(n+=espacio);
        text22 = new TextField();
        text22.setDisable(textf);
        text22.setTranslateX(enXTexto2);
        text22.setTranslateY(n);
        
        Label label23 = new Label("Continente Nacimiento:");
        label23.setTranslateX(enX2);
        label23.setTranslateY(n+=espacio);
        text23 = new TextField();
        text23.setDisable(!cb);
        text23.setTranslateX(enXTexto2);
        text23.setTranslateY(n);
        
        Label label24 = new Label("Subcontinente Procedencia:");
        label24.setTranslateX(enX2);
        label24.setTranslateY(n+=espacio);
        text24 = new TextField();
        text24.setDisable(textf);
        text24.setTranslateX(enXTexto2);
        text24.setTranslateY(n);
        
        Label label25 = new Label("Subcontinente Nacimiento:");
        label25.setTranslateX(enX2);
        label25.setTranslateY(n+=espacio);
        text25 = new TextField();
        text25.setDisable(!cb);
        text25.setTranslateX(enXTexto2);
        text25.setTranslateY(n);
        
        Label label27 = new Label("Buscar por cédula:");
        label27.setTranslateX(enX);
        label27.setTranslateY(n=20);
        text27 = new TextField();
        text27.setDisable(!textf && cb);
        text27.setTranslateX(enXTexto);
        text27.setTranslateY(n);
        comboBox2 = new ComboBox<RegistroMigrante>();
        comboBox2.setPromptText("Registros");
        comboBox2.setTranslateX(enXTexto+200);
        comboBox2.setTranslateY(n);
        comboBox2.setDisable(!textf && cb);
        
        
        root.getChildren().addAll(label27,label1,label3,label4,label2,label5,label6,label10,label11,label12,
        label13,label14,label15,label16,label17,label18,label19,label20,label21,label22,label23,label24,label25,label26,
        text27,comboBox2,text1,text3,comboBox,text4,text2,calendario,text10,text11,text12,text26,text13,text14,text15,
        text16,text17,text18,text19,text20,text21,text22,text23,text24,text25);
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public TextField getText1() {
        return text1;
    }

    public void setText1(TextField text1) {
        this.text1 = text1;
    }

    public TextField getText2() {
        return text2;
    }

    public void setText2(TextField text2) {
        this.text2 = text2;
    }

    public TextField getText3() {
        return text3;
    }

    public void setText3(TextField text3) {
        this.text3 = text3;
    }

    public TextField getText4() {
        return text4;
    }

    public void setText4(TextField text4) {
        this.text4 = text4;
    }

    public DatePicker getCalendario() {
        return calendario;
    }

    public void setCalendario(DatePicker calendario) {
        this.calendario = calendario;
    }

    public TextField getText10() {
        return text10;
    }

    public void setText10(TextField text10) {
        this.text10 = text10;
    }

    public TextField getText11() {
        return text11;
    }

    public void setText11(TextField text11) {
        this.text11 = text11;
    }

    public TextField getText12() {
        return text12;
    }

    public void setText12(TextField text12) {
        this.text12 = text12;
    }

    public TextField getText13() {
        return text13;
    }

    public void setText13(TextField text13) {
        this.text13 = text13;
    }

    public TextField getText14() {
        return text14;
    }

    public void setText14(TextField text14) {
        this.text14 = text14;
    }

    public TextField getText15() {
        return text15;
    }

    public void setText15(TextField text15) {
        this.text15 = text15;
    }

    public TextField getText16() {
        return text16;
    }

    public void setText16(TextField text16) {
        this.text16 = text16;
    }

    public TextField getText17() {
        return text17;
    }

    public void setText17(TextField text17) {
        this.text17 = text17;
    }

    public TextField getText18() {
        return text18;
    }

    public void setText18(TextField text18) {
        this.text18 = text18;
    }

    public TextField getText19() {
        return text19;
    }

    public void setText19(TextField text19) {
        this.text19 = text19;
    }

    public TextField getText20() {
        return text20;
    }

    public void setText20(TextField text20) {
        this.text20 = text20;
    }

    public TextField getText21() {
        return text21;
    }

    public void setText21(TextField text21) {
        this.text21 = text21;
    }

    public TextField getText22() {
        return text22;
    }

    public void setText22(TextField text22) {
        this.text22 = text22;
    }

    public TextField getText23() {
        return text23;
    }

    public void setText23(TextField text23) {
        this.text23 = text23;
    }

    public TextField getText24() {
        return text24;
    }

    public void setText24(TextField text24) {
        this.text24 = text24;
    }

    public TextField getText25() {
        return text25;
    }

    public void setText25(TextField text25) {
        this.text25 = text25;
    }

    public TextField getText26() {
        return text26;
    }

    public void setText26(TextField text26) {
        this.text26 = text26;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public TextField getText27() {
        return text27;
    }

    public void setText27(TextField text27) {
        this.text27 = text27;
    }

    public ComboBox<RegistroMigrante> getComboBox2() {
        return comboBox2;
    }

    public void setComboBox2(ComboBox<RegistroMigrante> comboBox2) {
        this.comboBox2 = comboBox2;
    }
}