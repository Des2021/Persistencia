import java.io.*;

public class MatrixMultiplier {
    public static double[][] multiplyMatrices(double[][] matriz1, double[][] matriz2) {
        int rows1 = matriz1.length;
        int columns1 = matriz1[0].length;
        int rows2 = matriz2.length;
        int columns2 = matriz2[0].length;

        if (columns1 != rows2) {
            throw new IllegalArgumentException("Las matrices no son compatibles para la multiplicación.");
        }

        double[][] result = new double[rows1][columns2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                double sum = 0;
                for (int k = 0; k < columns1; k++) {
                    sum += (long) matriz1[i][k] * (long) matriz2[k][j];
                    if (Double.isInfinite(sum) || Double.isNaN(sum)) {
                        throw new IllegalArgumentException("Se produjo un desbordamiento durante la multiplicación.");
                    }
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    public static void writeMatrixToFile(double[][] matrix, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (double[] row : matrix) {
                for (double element : row) {
                    writer.print(element + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[][] readMatrixFromFile(String fileName) {
        double[][] matrix = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            int columns = -1; // Variable para almacenar el número de columnas en cada fila
            while ((line = reader.readLine()) != null) {
                String[] elements = line.trim().split("\\s+");
                if (matrix == null) {
                    matrix = new double[elements.length][elements.length];
                    columns = elements.length; // Establecer el número de columnas en la primera fila
                } else if (elements.length != columns) {
                    throw new IllegalArgumentException("Las filas tienen diferentes longitudes en la matriz.");
                }
                for (int col = 0; col < elements.length; col++) {
                    try {
                        matrix[row][col] = Double.parseDouble(elements[col]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El archivo contiene caracteres no numéricos.");
                    }
                }
                row++;
            }
            if (matrix == null) {
                throw new IllegalArgumentException("El archivo no contiene una matriz válida.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static void main(String[] args) {
        String matrixFile1 = "matriz1.txt";
        String matrixFile2 = "matriz2.txt";
        String resultFile = "matrizresultado.txt";

        try {
            double[][] matrix1 = readMatrixFromFile(matrixFile1);
            double[][] matrix2 = readMatrixFromFile(matrixFile2);

            double[][] result = multiplyMatrices(matrix1, matrix2);

            writeMatrixToFile(result, resultFile);

            System.out.println("Matrices multiplicadas y resultado guardado en el archivo: " + resultFile);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


