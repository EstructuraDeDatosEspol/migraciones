/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda.entidades;

import espol.edu.ec.moduloDeRegistro.RegistroMigrante;
import java.util.LinkedList;

/**
 *
 * @author SSAM
 */
public class Persona {
    private int cedula;
    private String nombre;
    private String apellido;
    private String sexo;
    private int anioNacimiento;
    private String ocupacion;
    private int edad;
    private String Nacionalidad;
    private String continenteNacimiento;
    private String subcontNacionalidad;
    private LinkedList<RegistroMigrante> list;

    public Persona(int cedula, String nombre, String apellido, String sexo, int anioNacimiento, String ocupacion, int edad, String paisNacimiento, String continenteNacimiento, String subcontNacionalidad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.anioNacimiento = anioNacimiento;
        this.ocupacion = ocupacion;
        this.edad = edad;
        this.Nacionalidad = paisNacimiento;
        this.continenteNacimiento = continenteNacimiento;
        this.subcontNacionalidad = subcontNacionalidad;
        list = new LinkedList<>();
    }
    
    public Persona(int cedula, String nombre, String apellido, String sexo, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
    }

    public LinkedList<RegistroMigrante> getList() {
        return list;
    }

    public void setList(LinkedList<RegistroMigrante> list) {
        this.list = list;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String paisNacimiento) {
        this.Nacionalidad = paisNacimiento;
    }

    public String getContinenteNacimiento() {
        return continenteNacimiento;
    }

    public void setContinenteNacimiento(String continenteNacimiento) {
        this.continenteNacimiento = continenteNacimiento;
    }

    public String getSubcontnacionalidad() {
        return subcontNacionalidad;
    }

    public void setSubcontnacionalidad(String subcont_nacionalidad) {
        this.subcontNacionalidad = subcont_nacionalidad;
    }
    
    public String texto(){
        StringBuilder sb = new StringBuilder();
        String ced = String.valueOf(cedula);
        if(ced.length()==9){
            StringBuilder sb2 = new StringBuilder();
            sb2.append("0");
            sb2.append(ced);
            ced = sb2.toString();
        }
        sb.append(ced);
        sb.append(",");
        sb.append(nombre);
        sb.append(",");
        sb.append(apellido);
        sb.append(",");
        sb.append(sexo);
        sb.append(",");
        sb.append(anioNacimiento);
        sb.append(",");
        sb.append(ocupacion);
        sb.append(",");
        sb.append(edad);
        sb.append(",");
        sb.append(Nacionalidad);
        sb.append(",");
        sb.append(continenteNacimiento);
        sb.append(",");
        sb.append(subcontNacionalidad);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + ", anioNacimiento=" + anioNacimiento + ", ocupacion=" + ocupacion + ", edad=" + edad + ", paisNacimiento=" + Nacionalidad + ", continenteNacimiento=" + continenteNacimiento + ", subcontNacionalidad=" + subcontNacionalidad + ", list=" + list + '}';
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Persona))return false;
        Persona p = (Persona)o;
        return cedula == (p.cedula);
    }
}