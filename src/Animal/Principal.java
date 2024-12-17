/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animal;

/**
 *
 * @author Duban
 */
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Map<String, List<Animal>> clasificacion = new HashMap<>();
        List<Animal> animales = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el nombre del animal (o 'salir' para terminar):");
            String nombre = scanner.nextLine();
            if (nombre.equals("salir")) {
                break;
            }

            System.out.println("Ingrese el tipo del animal (terrestre, aéreo, acuático):");
            String tipo = scanner.nextLine();

            System.out.println("Ingrese el género del animal (masculino, femenino):");
            String genero = scanner.nextLine();

            Animal animal = new Animal(nombre, tipo, genero);
            animales.add(animal);
        }

        for (Animal animal : animales) {
            clasificarAnimal(clasificacion, animal);
        }

        mostrarClasificacion(clasificacion);
    }

    private static void clasificarAnimal(Map<String, List<Animal>> clasificacion, Animal animal) {
        String tipo = animal.getTipo();
        clasificacion.putIfAbsent(tipo, new ArrayList<>());
        clasificacion.get(tipo).add(animal);
    }

    private static void mostrarClasificacion(Map<String, List<Animal>> clasificacion) {
        for (Map.Entry<String, List<Animal>> entry : clasificacion.entrySet()) {
            String tipo = entry.getKey();
            List<Animal> animales = entry.getValue();

            System.out.println(tipo + ":");
            for (Animal animal : animales) {
                System.out.println("\t" + animal.getNombre());
            }
        }
    }
}

