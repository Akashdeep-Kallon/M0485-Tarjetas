/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MANBIR
 */
public class TarjetaDebito extends Tarjeta {

    private double limiteCredito = 2000;

    public TarjetaDebito(String numero, String titular) {
        super(numero, titular);
    }

    @Override
    public void pagar(double monto) {
        this.limiteCredito -= monto;
    }

    @Override
    public double consultarSaldo() {
       return this.limiteCredito;
    }

}
