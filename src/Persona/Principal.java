/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persona;

/**
 *
 * @author Duban
 */
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el nombre de la persona (o 'salir' para terminar):");
            String nombre = scanner.nextLine();
            if (nombre.equals("salir")) {
                break;
            }

            System.out.println("Ingrese el apellido de la persona:");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese la edad de la persona:");
            int edad = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el género de la persona (Masculino/Femenino):");
            String genero = scanner.nextLine();

            System.out.println("Ingrese el sueldo por hora de la persona:");
            double sueldoHora = Double.parseDouble(scanner.nextLine());

            System.out.println("Ingrese el cargo de la persona:");
            String cargo = scanner.nextLine();

            Persona persona = new Persona(nombre, apellido, edad, genero, sueldoHora, cargo);
            personas.add(persona);
        }

        // a. Cantidad de personas
        long cantidadPersonas = personas.size();
        System.out.println("Cantidad de personas almacenadas: " + cantidadPersonas);

        // b. Promedio de edades
        double promedioEdades = personas.stream()
                .mapToInt(Persona::getEdad)
                .average()
                .orElse(0);
        System.out.println("Promedio de edades: " + promedioEdades);

        // c. Cantidad de personas mayores de edad
        long personasMayoresEdad = personas.stream()
                .filter(persona -> persona.getEdad() >= 18)
                .count();
        System.out.println("Cantidad de personas mayores de edad: " + personasMayoresEdad);

        // d. Personas cuyos nombres empiecen con "A"
        List<Persona> personasConNombreA = personas.stream()
                .filter(persona -> persona.getNombre().startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Personas cuyos nombres empiezan con 'A':");
        personasConNombreA.forEach(persona -> System.out.println(persona.getNombre()));

        // e. Personas cuyos apellidos contengan la letra "M"
        List<Persona> personasConApellidoM = personas.stream()
                .filter(persona -> persona.getApellido().contains("M"))
                .collect(Collectors.toList());
        System.out.println("Personas cuyos apellidos contienen la letra 'M':");
        personasConApellidoM.forEach(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));

        // f. Sueldo por 8 horas de directores masculinos
        System.out.println("Sueldo por 8 horas de directores masculinos:");
        personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Director"))
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Masculino"))
                .map(persona -> "Nombre: " + persona.getNombre() + " " + persona.getApellido() + " Sueldo por 8 horas: $" + (persona.getSueldoHora() * 8))
                .forEach(System.out::println);

        // g. Primera persona desarrolladora y femenina
        Optional<Persona> primeraDesarrolladoraFemenina = personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Desarrollador"))
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Femenino"))
                .findFirst();
        if (primeraDesarrolladoraFemenina.isPresent()) {
            System.out.println("Primera persona desarrolladora y femenina: " + primeraDesarrolladoraFemenina.get().getNombre());
        } else {
            System.out.println("No se encontró ninguna persona desarrolladora y femenina.");
        }

        // h. Persona desarrolladora que más gana por hora
        Optional<Persona> personaMasGana = personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Desarrollador"))
                .max(Comparator.comparingDouble(Persona::getSueldoHora));

        if (personaMasGana.isPresent()) {
            System.out.println("Persona desarrolladora que más gana por hora: " + personaMasGana.get().getNombre());
        } else {
            System.out.println("No se encontró ninguna persona desarrolladora.");
        }

        // i. Todas las mujeres ordenadas por nombre
        System.out.println("Mujeres ordenadas por nombre:");
        personas.stream()
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Femenino"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .forEach(persona -> System.out.println(persona.getNombre()));
    }
}

