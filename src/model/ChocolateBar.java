package model;

public class ChocolateBar {
    private String [][] Board;
    private int rows;
    private int cols;

    /**
     * Method 1 of ChocolateBar in diagram.
     * Initializes the chocolate bar with the specified number of rows and columns.
     * Each piece of the chocolate bar is set to "active" to represent uneaten pieces.
     *
     * @param rows
     * @param cols
     */
    public ChocolateBar(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        Board = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Board[i][j] = "active";
            }
        }
    }

    /**
     * Method 2 of ChocolateBar in diagram.
     * Return the board
     * @return a 2D array representing the chocolate bar board
     */
    public String[][] getBoard() {
        return Board;
    }

    /**
     * Method 3 of ChocolateBar in diagram.
     * @param row
     * @param col
     */
    public void update_board (int row, int col) {
        for (int i = row; i >= 0; i--) {
            for (int j = col; j <Board[i].length; j++) {
                if (Board[i][j].equals("active")) {
                    Board[i][j] = "inactive";
                }
            }
        }
    }

    /**
     * Method 4 of ChocolateBar in diagram.
     * [Why Changed]Here, I changed the return type. In the diagram, the return type was boolean,
     * but I considered that there are two game-over scenarios:
     * 1. Only the poisoned piece is left, and the next player is guaranteed to lose.
     * 2. The current player eats the poisoned piece directly, resulting in their loss.
     * Since a boolean cannot represent all three possible outcomes (including the game not being over),
     * I changed the return type to int.
     *
     * Determines whether the game has ended based on the current state of the chocolate bar board.
     *
     * @return an integer representing the game's status:
     * - 0: The game is not over; there are still active pieces other than the bottom-left piece.
     * - 1: The game has ended, and the next player is the winner (bottom-left piece has been eaten).
     * - 2: The game has ended, and the current player is the winner (no active pieces remain except the bottom-left piece).
     */
    public int if_endgame(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if((i==rows-1 && j==0) && Board[i][j].equals("inactive")){
                    return 1; //end and the next player is the winner
                }else if((i!=rows-1 || j!=0)&& Board[i][j].equals("active")) {
                    //if there are at lease one cell except the bottom-left is active, then the game is not over
                    return 0;//not end
                }
            }
        }
        return 2;//end and the next player is the winner
    }


    /**
     * Added an additional getter method for convenience in implementing certain logic.
     * @return rows and cols of board.
     */
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
}
