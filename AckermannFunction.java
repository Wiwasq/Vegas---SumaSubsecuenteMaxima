/**
 * Implementación iterativa de la función de Ackermann-Péter
 * 
 * Complejidad temporal:
 * - Peor caso: O(m*A(m,n)) en tiempo
 * - Espacio: O(m) debido a la pila utilizada para simular la recursión
 * 
 * Notación asintótica:
 * - La función de Ackermann crece extremadamente rápido, más que cualquier función exponencial
 * - Para m ≥ 4, A(m,n) no es práctico de calcular para n relativamente pequeños
 * - Para m=0: O(1)
 * - Para m=1: O(n)
 * - Para m=2: O(n)
 * - Para m=3: O(2^n)
 * - Para m=4: A(4,n) ≈ 2^(2^(2^...^2)) (una torre de exponentes de altura n+3)
 */

package wiwasq_30;

import java.util.Stack;

public class AckermannFunction {
    
    public static int ackermann(int m, int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(m);
        
        while (!stack.isEmpty()) {
            m = stack.pop();
            
            if (stack.size() > 10000) {
                throw new StackOverflowError("Demasiada recursión simulada");
            }
            
            if (m == 0) {
                n += 1;
            } 
            else if (n == 0) {
                n = 1;
                stack.push(m - 1);
            } 
            else {
                stack.push(m - 1);
                stack.push(m);
                n -= 1;
            }
        }
        
        return n;
    }
    
    public static void main(String[] args) {
        // Ejemplos de uso
        System.out.println("A(0, 0) = " + ackermann(0, 0));  // 1
        System.out.println("A(0, 1) = " + ackermann(0, 1));  // 2
        System.out.println("A(1, 0) = " + ackermann(1, 0));  // 2
        System.out.println("A(1, 1) = " + ackermann(1, 1));  // 3
        System.out.println("A(2, 1) = " + ackermann(2, 1));  // 5
        System.out.println("A(3, 1) = " + ackermann(3, 1));  // 13
        // A(4,1) ya es muy grande (65536)
    }
}
