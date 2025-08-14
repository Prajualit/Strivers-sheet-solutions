public class Exist {

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean search(char[][] board, String word, int index, int row, int col) {

        if (index == word.length()) {
            return true;
        }
        if (row >= board.length || col >= board[0].length || row < 0 || col < 0 || board[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = search(board, word, index + 1, row + 1, col) ||
                search(board, word, index + 1, row - 1, col) ||
                search(board, word, index + 1, row, col + 1) ||
                search(board, word, index + 1, row, col - 1);

        board[row][col] = temp;

        return found;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCCED"));
    }
}
