# Vegas---SumaSubsecuenteMaxima

/*
Función tiempo: T(n)=a⋅n+b (dependiendo de las constantes específicas del codigo y la implementacion del ‘LinkedList‘)
Función Asíntota: T(n) ∈ O(n)(complejidad lineal).
*/
    
Suma de Subsecuencia Maxima
package SubsecuenciaMaxima;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SubsecuenciaMaxima {
    
    public static void LimpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i=0; i<50; i++) System.out.println();
        }
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> listaSM = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char opcion;
        
        do {
            LimpiarPantalla();
            System.out.println("\n\tLISTA DE SUBSECUENCIA MAXIMA [-100, 100]");
            System.out.println("Concepcion de la lista de numeros");
            System.out.println("1.- Generar una LISTASM de NUMEROS ENTEROS aleatorios");
            System.out.println("2.- Agregar un numero manualmente a la lista");
            System.out.println("3.- Eliminar un numero manualmente de la lista");
            System.out.println("4.- Calcular la SUMA MAXIMA de Subsecuencia");
            System.out.println("5.- Salir");
            System.out.println("\n\tLista de Subsecuencia Maxima");
            if (listaSM.isEmpty()) {
                System.out.println("La lista esta vacia.");
            } else {
                for (int i = 0; i < listaSM.size(); i++) {
                    System.out.println("Indice " + i + ": " + listaSM.get(i));
                }
            }
            System.out.print("\nDigite una opcion: ");
            
            String input = scanner.next();
            opcion = input.length() > 0 ? input.toLowerCase().charAt(0) : ' ';
            
            switch (opcion) {
                case '1':
                    listaSM.clear();
                    System.out.print("Digite un numero para determinar el tamanyo de la listaSM: ");
                    int cantidad = scanner.nextInt();
                    for (int i = 0; i < cantidad; i++) {
                        int numeroAleatorio = random.nextInt(201) - 100;
                        listaSM.add(numeroAleatorio);
                    }
                    break;
                    
                case '2':
                    System.out.print("Digite el numero que desea agregar a la lista: ");
                    int numeroAgregar = scanner.nextInt();
                    System.out.print("Digite la posicion (0 a " + listaSM.size() + ") donde desea insertarlo: ");
                    int posicion = scanner.nextInt();
                    if (posicion >= 0 && posicion <= listaSM.size()) {
                        listaSM.add(posicion, numeroAgregar);
                        System.out.println("¡Numero agregado!");
                    } else {
                        System.out.println("Posicion invalida.");
                    }
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                
                case '3':
                    if (listaSM.isEmpty()) {
                        System.out.println("La lista esta vacia. No se puede eliminar.");
                    } else {
                        System.out.println("Lista actual con indices:");
                        for (int i = 0; i < listaSM.size(); i++) {
                            System.out.println(i + ": " + listaSM.get(i));
                        }
                        System.out.print("Digite el indice (0 a " + (listaSM.size() - 1) + ") del numero a eliminar: ");
                        int indiceEliminar = scanner.nextInt();
                        if (indiceEliminar >= 0 && indiceEliminar < listaSM.size()) {
                            int valorEliminado =  listaSM.remove(indiceEliminar);
                            System.out.println("Elemento " + valorEliminado + " en posicion " + indiceEliminar + " eliminado.");
                        } else {
                            System.out.println("Indice invalido.");
                        }
                    }
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                    
                case '4':
                    if (listaSM.isEmpty()) {
                        System.out.println("Lista vacia. NO se puede calcular la Suma de Subsecuencia Maxima.");
                    } else {
                        Resultado resultado = sumaSubsecuenteMaxima(listaSM);
                        System.out.println("Suma maxima de subsecuencia: " + resultado.sumaSubMax);
                        if (resultado.inicio != -1) {
                            System.out.println("Desde el indice \'" + resultado.inicio + "\' hasta el indice \'" + resultado.fin + "\'");
                        } else {
                            System.out.println("No hay subsecuencia positiva.");
                        }
                        
                    }
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                    
                case '5':
                System.out.println("Saliendo del programa...");
                break;

                default:
                System.out.println("Opción no válida.");
                System.out.println("Presiona Enter para continuar...");
                scanner.nextLine();
                scanner.nextLine();
                break;
            }
            
        } while (opcion != '5');
    }
   
    
    static class Resultado {
        int sumaSubMax;
        int inicio;
        int fin;
        
        Resultado(int sumaSubMax, int inicio, int fin) {
            this.sumaSubMax = sumaSubMax;
            this.inicio = inicio;
            this.fin = fin;
        }
    }
    
    public static Resultado sumaSubsecuenteMaxima(LinkedList<Integer> listaSM) {
        if (listaSM.isEmpty()) return new Resultado(0, -1, -1);
        
        int sumaSubMax = listaSM.get(0);
        int sumaActual = listaSM.get(0);
        int inicio = 0, fin = 0, tempInicio = 0;
        
        for (int i = 1; i < listaSM.size(); i++) {
            int aux = listaSM.get(i);

            if (sumaActual + aux < aux) {
                sumaActual = aux;
                tempInicio = i;
            } else {
                sumaActual += aux;
            }

            if (sumaActual > sumaSubMax) {
                sumaSubMax = sumaActual;
                inicio = tempInicio;
                fin = i;
            }
        }
        
        if (sumaSubMax <= 0) {
            int max = listaSM.get(0);
            int pos = 0;
            for (int i = 1; i < listaSM.size(); i++) {
                if (listaSM.get(i) > max) {
                    max = listaSM.get(i);
                    pos = i;
                }
            }
            return new Resultado (max, pos, pos);
        }
        
        return new Resultado(sumaSubMax, inicio, fin);
    }
    
}
