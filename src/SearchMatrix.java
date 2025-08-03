public class SearchMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int start = 0;
        int end = (rows * cols) - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        int currentRow = 0;
        int currentCol = columns - 1;

        while (currentRow < rows && currentCol >= 0) {
            int currentValue = matrix[currentRow][currentCol];

            if (currentValue == target) {
                return true;
            } else if (currentValue < target) {
                currentRow++;
            } else {
                currentCol--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int[][] matrix2 = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[][] matrix3 = {{1, 4}, {2, 5}};

        System.out.println("Searching for 3: " + searchMatrix(matrix, 3));
        System.out.println("Searching for 13: " + searchMatrix2(matrix2, 13));
        System.out.println("Searching for 13: " + searchMatrix2(matrix3, 2));
    }
}