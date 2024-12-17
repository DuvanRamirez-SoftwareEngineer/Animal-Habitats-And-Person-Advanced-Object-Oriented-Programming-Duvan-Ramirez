/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonaAutomatico;

/**
 *
 * @author Duban
 */
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
        public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();

        // Agrega personas a la lista
        personas.add(new Persona("Juan", "Perez", 25, "Masculino", 20.0, "Desarrollador"));
        personas.add(new Persona("Ana", "Martinez", 30, "Femenino", 25.0, "Desarrollador"));
        personas.add(new Persona("Carlos", "Lopez", 35, "Masculino", 30.0, "Director"));
        personas.add(new Persona("Elena", "Gomez", 40, "Femenino", 35.0, "Director"));

        // a. Cantidad de personas
        long cantidadPersonas = personas.size();
        System.out.println("Cantidad de personas: " + cantidadPersonas);

        // b. Promedio de edades
        double promedioEdades = personas.stream()
                .mapToDouble(Persona::getEdad)
                .average()
                .orElse(0.0);
        System.out.println("Promedio de edades: " + promedioEdades);

        // c. Cantidad de personas mayores de edad
        long personasMayoresEdad = personas.stream()
                .filter(persona -> persona.getEdad() >= 18)
                .count();
        System.out.println("Cantidad de personas mayores de edad: " + personasMayoresEdad);

        // d. Personas cuyos nombres empiezan con "A"
        List<Persona> personasConNombreA = personas.stream()
                .filter(persona -> persona.getNombre().startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Personas cuyos nombres empiezan con 'A':");
        personasConNombreA.forEach(persona -> System.out.println(persona.getNombre()));

        // e. Personas cuyos apellidos contienen la letra "M"
        List<Persona> personasConApellidoM = personas.stream()
                .filter(persona -> persona.getApellido().contains("M"))
                .collect(Collectors.toList());
        System.out.println("Personas cuyos apellidos contienen la letra 'M':");
        personasConApellidoM.forEach(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));

        // f. Sueldo por 8 horas de directores masculinos
        Optional<Double> sueldoDirectoresMasculinos = personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Director"))
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Masculino"))
                .map(persona -> persona.getSueldoHora() * 8)
                .peek(sueldo -> System.out.println("Sueldo por 8 horas: $" + sueldo))
                .findFirst();

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

        // h. Persona desarrolladora con el sueldo más alto por hora
        Optional<Persona> personaMasGana = personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Desarrollador"))
                .max(Comparator.comparingDouble(Persona::getSueldoHora));

        if (personaMasGana.isPresent()) {
            System.out.println("Persona desarrolladora con el sueldo más alto por hora: " + personaMasGana.get().getNombre());
        } else {
            System.out.println("No se encontró ninguna persona desarrolladora.");
        }

        // i. Todas las mujeres ordenadas por su nombre
        List<Persona> mujeresOrdenadasPorNombre = personas.stream()
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Femenino"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .collect(Collectors.toList());
        System.out.println("Mujeres ordenadas por nombre:");
        mujeresOrdenadasPorNombre.forEach(persona -> System.out.println(persona.getNombre()));
    }
}
