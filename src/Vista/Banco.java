/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Model.Tarjeta;
import java.util.ArrayList;

/**
 *
 * @author MANBIR
 */
public class Banco {

    public String nombre;
    public ArrayList<Tarjeta> tarjetas = new ArrayList<>();

    public void agregarTarjeta(Tarjeta t) {
        tarjetas.add(t);
    }

    public void mostrarTarjetas() {
        System.out.println("Saldo final de tarjetas:");
        for (Tarjeta t : tarjetas) {
            System.out.println("--------------------");
            System.out.println("Tarjeta de " + t.getClass().getSimpleName() + " " + t.getTitular() + ": " + t.consultarSaldo());
            System.out.println("--------------------");
        }
    }
}
