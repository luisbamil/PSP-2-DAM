package JavaDocEjemplo;
/**
 * Representa una calculadora básica.
 * Proporciona operaciones matemáticas simples.
 */
public class Calculadora {

    /**
     * Suma dos números.
     * @param a Primer número
     * @param b Segundo número
     * @return La suma de a y b
     */
    public int sumar(int a, int b) {
        return a + b;
    }

    /**
     * Divide dos números.
     * @param a Dividendo
     * @param b Divisor
     * @return Resultado de la división
     * @throws ArithmeticException Si b es cero
     */
    public double dividir(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return (double) a / b;
    }
}
