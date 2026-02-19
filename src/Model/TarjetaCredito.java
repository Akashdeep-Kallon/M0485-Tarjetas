/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MANBIR
 */
public class TarjetaCredito extends Tarjeta {

    private double limiteCredito = 2000;

    public TarjetaCredito(String numero, String titular) {
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
