import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Test3 {

    @Test
    public void testMultiplyMatrices() {
        // Cargar las matrices desde los archivos
        double[][] matrix1 = MatrixMultiplier.readMatrixFromFile("matriz1.txt");
        double[][] matrix2 = MatrixMultiplier.readMatrixFromFile("matriz2.txt");

        // Calcular el resultado esperado
        double[][] expected = {
                {21, 18, 18},
                {48, 45, 48},
                {75, 72, 78}
        };

        // Multiplicar las matrices utilizando el método de la clase MatrixMultiplier
        double[][] result = MatrixMultiplier.multiplyMatrices(matrix1, matrix2);

        // Verificar que el resultado coincida con el esperado
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testReadMatrixFromFile() {
        // Cargar la matriz desde el archivo
        double[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[][] matrix = MatrixMultiplier.readMatrixFromFile("matriz1.txt");

        // Verificar que la matriz cargada coincida con la esperada
        Assert.assertTrue(Arrays.deepEquals(expected, matrix));
    }
}

