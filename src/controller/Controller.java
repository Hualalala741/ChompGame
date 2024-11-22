package controller;

import model.ChocolateBar;
import model.Player;
import view.View;

/**
 * Control the game logic, and 实例化，Player ChocolateBar and View class
 *
 */
public class Controller {
    private Player player;
    private ChocolateBar chocolateBar;

    /**
     * Method 1 of Controller in diagram.
     * Initializes and starts the game by setting up the Model and View components.
     * The game requires the user to specify decide the size of chocolate bar.
     * Two players are initialized with default names as "player1", "player2"
     * and the game begins with "player1" as the first player.
     * @param rows the number of rows in the chocolate bar
     * @param cols the number of cols in the chocolate bar
     */
    public void start_game(int rows,int cols) {
        this.player = new Player("player1");
        this.chocolateBar = new ChocolateBar(rows,cols);
        View view= new View(this,chocolateBar);
    }

    /**
     * Method 2 of Controller in diagram.
     * Receives the clicked row and col by player, and handles the click event.
     * Checks if the clicked square is "active" and performs the necessary actions:
     * 1. Updates the chocolate bar board.
     * 2. Switches to the next player.
     * @param row row index of the clicked square
     * @param col the column index of the clicked square
     */
    public void handleClick(int row,int col){
        String[][]board = chocolateBar.getBoard();
        if(board[row][col].equals("active")){
            chocolateBar.update_board(row,col);
            // if action is legal
            switch_player();
        }
    }

    /**
     * Method 3 of Controller in diagram.
     * Switches the current player to the other player.
     * We default there are the two players are "player1" and "player2"
     */
    public void switch_player(){
        if(player.get_cur_player().equals("player1")){
            player.set_player("player2");
        }else{
            player.set_player("player1");
        }
    }

    /**
     * An additional method not included in diagram.
     * Retrieves the winner name. Used to show the winner information when game is over.
     * The winner is determined as the player who is not the current player.
     * @return winner The name of the winner ("player1" or "player2").
     */
    public String getWinner(){
        if(player.get_cur_player().equals("player1")){
            return "player2";
        }else{
            return "player1";
        }
    }

    /**
     * An additional method not included in diagram.
     * To make the game logic more complete, an option to restart the game is added after the game ends.
     * Restarts the game with the same board size.
     */
    public void restart_game(){
        start_game(chocolateBar.getRows(), chocolateBar.getCols());
    }

    public Player getPlayer() {
        return player;
    }
}
