package com.mycompany.foro2poo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Credito {
    private LocalDate fechaSolicitud;
    private String nombre;
    private LocalDate fechaNacimiento;
    private int edad;
    private String sexo;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String municipio;
    private String estado;
    private boolean tieneIdentificacionOficial;
    private boolean tieneCURP;
    private boolean tieneComprobanteDomicilio;
    private String nombreEmpleadoRecibioDocumentos;

    public Credito() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Fecha de la solicitud (formato: AAAA-MM-DD): ");
        this.fechaSolicitud = LocalDate.parse(scanner.nextLine());

        System.out.println("Nombre de la persona: ");
        this.nombre = scanner.nextLine();

        System.out.println("Fecha de nacimiento (formato: AAAA-MM-DD): ");
        this.fechaNacimiento = LocalDate.parse(scanner.nextLine());
        this.edad = Period.between(this.fechaNacimiento, LocalDate.now()).getYears();

        System.out.println("Sexo (M/F): ");
        this.sexo = scanner.nextLine();

        System.out.println("Calle: ");
        this.calle = scanner.nextLine();

        System.out.println("Número exterior: ");
        this.numeroExterior = scanner.nextLine();

        System.out.println("Número interior: ");
        this.numeroInterior = scanner.nextLine();

        System.out.println("Municipio: ");
        this.municipio = scanner.nextLine();

        System.out.println("Estado: ");
        this.estado = scanner.nextLine();

        System.out.println("¿Tiene identificación oficial vigente? (S/N): ");
        this.tieneIdentificacionOficial = scanner.nextLine().equalsIgnoreCase("S");

        System.out.println("¿Tiene CURP? (S/N): ");
        this.tieneCURP = scanner.nextLine().equalsIgnoreCase("S");

        System.out.println("¿Tiene comprobante de domicilio no mayor a tres meses de antigüedad? (S/N): ");
        this.tieneComprobanteDomicilio = scanner.nextLine().equalsIgnoreCase("S");

        System.out.println("Nombre del empleado que recibió los documentos: ");
        this.nombreEmpleadoRecibioDocumentos = scanner.nextLine();
    }

    public boolean esAcreedorAlCredito() {
        if (this.edad < 30 || this.edad > 64) {
            System.out.println("No es candidato al crédito por no cumplir con el requisito de edad.");
            return false;
        }

        if (!this.tieneIdentificacionOficial || !this.tieneCURP || !this.tieneComprobanteDomicilio) {
            System.out.println("No es candidato al crédito por no presentar todos los documentos requeridos.");
            return false;
        }

        return true;
    }

    public double montoDelCredito() {
        if (this.edad >= 30 && this.edad <= 35) {
            return 4500.0;
        } else if (this.edad >= 36 && this.edad <= 40) {
            return 5000.0;
        } else if (this.edad >= 41 && this.edad <= 50) {
            return 5500.0;
        } else if (this.edad >= 51 && this.edad <= 60) {
            return 6000.0;
        } else if (this.edad >= 61 && this.edad <= 64) {
            return 7000.0;
        } else {
            return 0.0;
        }
    }

    public void mostrarFechasDePago() {
        LocalDate fechaPrimerPago = this.fechaSolicitud.plusMonths(3);
        for (int i = 0; i < 12; i++) {
            LocalDate fechaPago = fechaPrimerPago.plusMonths(i);
            System.out.println(fechaPago);
        }
    }

    public static void main(String[] args) {
      Credito credito = new Credito();
      if (credito.esAcreedorAlCredito()) {
          double montoDelCredito = credito.montoDelCredito();
          System.out.println("Felicidades, eres acreedor al crédito.");
          System.out.println("Monto del crédito: $" + montoDelCredito);
          System.out.println("Fechas de pago:");
          credito.mostrarFechasDePago();
      } else {
          System.out.println("Lo sentimos, no eres acreedor al crédito.");
      }
    }
}

