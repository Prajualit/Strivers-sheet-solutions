public class FindPeakGrid {
    public int[] findPeakGrid(int[][] mat) {
        int lowCol = 0;
        int highCol = mat[0].length - 1;

        while (lowCol <= highCol) {
            int midCol = lowCol + (highCol - lowCol) / 2;

            int maxRow = 0;
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][midCol] > mat[maxRow][midCol]) {
                    maxRow = i;
                }
            }

            int peakCandidateValue = mat[maxRow][midCol];

            int leftVal = (midCol > 0) ? mat[maxRow][midCol - 1] : -1;
            int rightVal = (midCol < mat[0].length - 1) ? mat[maxRow][midCol + 1] : -1;

            if (peakCandidateValue > leftVal && peakCandidateValue > rightVal) {
                return new int[]{maxRow, midCol};
            } else if (leftVal > peakCandidateValue) {
                highCol = midCol - 1;
            } else {
                lowCol = midCol + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

    }
}
