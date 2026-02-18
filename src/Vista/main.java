/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Vista;

import Model.Tarjeta;
import Model.TarjetaCredito;
import Model.TarjetaDebito;
import java.util.Scanner;

/**
 *
 * @author MANBIR
 */
public class main {

    /**
     * @param args the command line arguments
     */
    static Banco bc = new Banco();
    static int countD = 0;
    static int countC = 0;

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        int opc;
        System.out.println("---Menu Principal---");
        System.out.println("1. Crear tarjetas");
        System.out.println("2. Realizar pagos");
        System.out.println("3. Mostrar el saldo final de cada tarjeta");
        System.out.println("4. Salir");
        System.out.println("--------------------");

        System.out.print("Ingrese una opcion: ");
        opc = scanner.nextInt();
        return opc;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del banco: ");
        bc.nombre = sc.nextLine();

        int opc;

        do {
            opc = menu();
            switch (opc) {
                case 1:
                    if (countD < 2 || countC < 2) {
                        System.out.print("Debes de almenos crear dos tipo de tarjeta de cada tipo.");
                        while (countD < 2 || countC < 2) {
                            addTarjeta();
                        }
                    } else {
                        addTarjeta();
                    }
                    break;
                    
                case 2:
                    pagar();
                    break;
                case 3:
                    bc.mostrarTarjetas();
                    break;

                case 4:
                    System.out.println("Exit, programa finalizado.");
                    break;

                default:
                    System.err.print("Error, la opcion no existe.");
                    break;
            }
        } while (opc != 4);
    }

    public static void addTarjeta() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introducir titular");
        String titular = sc.nextLine();
        System.out.print("Introducir número de tarjeta");
        String numero = comproveNumero();
        if (numero != null) {
            System.out.print("Elegir tipo de tarjeta (debito o credito): ");
            String tipoTarjeta = sc.nextLine();
            switch (tipoTarjeta.toLowerCase()) {
                case "debito":
                    Tarjeta debito = new TarjetaDebito(numero, titular);
                    bc.agregarTarjeta(debito);
                    if (countD < 2) {
                        countD++;
                    }
                    System.out.print("Tarjeta de debito creado correctamente.");
                    break;
                case "credito":
                    Tarjeta credito = new TarjetaCredito(numero, titular);
                    bc.agregarTarjeta(credito);
                    if (countC < 2) {
                        countC++;
                    }
                    System.out.print("Tarjeta de credito creado correctamente.");
                    break;
                default:
                    System.err.print("Error, la opcion no existe.");
                    addTarjeta();
                    break;
            }
        } else {
            System.err.print("Error, ya existe el numero de tarjeta.");
        }
    }

    public static void pagar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Numero del titular de la tarjeta: ");
        String numeroTitualar = comproveNumero();
        System.out.print("Numero del destinatario de la tarjeta: ");
        String numeroDestinatario = comproveNumero();

        if (numeroTitualar != null && numeroDestinatario != null) {
            System.out.print("Monto a enviar: ");
            double monto = sc.nextDouble();
            boolean comproveMonto = false;
            for (Tarjeta t : bc.tarjetas) {
                if (t.getNumero().equalsIgnoreCase(numeroTitualar)) {
                    if (t.consultarSaldo() >= monto) {
                        comproveMonto = true;
                        t.pagar(monto);
                        System.out.println("Tarjeta de " + t.getClass().getSimpleName() + "-> Se ha descontado " + monto);
                        break;
                    } else {
                        System.err.print("Error. El monto es mayor al saldo de la tarjeta.");
                        break;
                    }
                }
            }
            if (comproveMonto) {
                for (Tarjeta t : bc.tarjetas) {
                    if (t.getNumero().equalsIgnoreCase(numeroDestinatario)) {
                        t.addSaldo(monto);
                        System.out.println("Tarjeta de " + t.getClass().getSimpleName() + "-> Se ha cargado " + monto);
                        break;
                    }
                }
            }
        } else {
            System.err.print("Error. Una de las dos cuentas no existe.");
        }
    }

    public static String comproveNumero() {
        Scanner sc = new Scanner(System.in);
        String titular = sc.nextLine();

        for (Tarjeta t : bc.tarjetas) {
            if (t.getNumero().equalsIgnoreCase(titular)) {
                return null;
            }
        }
        return titular;
    }
}