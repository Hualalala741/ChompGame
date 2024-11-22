package view;

import controller.Controller;
import model.ChocolateBar;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * View class display the game board and enable user interactions.
 */
public class View extends JFrame {

    private JPanel boardPanel;
    private ChocolateBar board;
    private Controller controller;



    /**
     * Method 1 of View in the diagram. (Equal to the initialize method in diagram)
     * This method is responsible for initializing the game window,
     * setting up the game board, and adding the necessary mouse listener for user interaction.
     * It also sets up the display properties.
     *
     * @param controller The controller used to manage the game logic.
     * @param board The game board, contains the game state.
     */
    public View(Controller controller,ChocolateBar board) {
        this.board = board;
        this.controller=controller;
        setTitle("Chomp Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintGameBoard(g);
            }
        };

        boardPanel.setPreferredSize(new Dimension(400, 400));
        add(boardPanel, BorderLayout.CENTER);

        // Mouse listener for handling clicks on the board
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
                if (board.if_endgame()==2) {
                    //if end shows winner info
                    String winner=controller.getWinner();
                    displayWinner(winner);
                }else if(board.if_endgame()==1){
                    Player player=controller.getPlayer();
                    String winner=player.get_cur_player();
                    displayWinner(winner);
                }
            }
        });

        setVisible(true);
    }

    /**
     * Method 2 of View in the diagram.
     * Draws the game board on the provided Graphics object.
     * This method renders each cell of the chocolate bar based on its state (eaten or uneaten)
     * and visually represents the poisoned piece with a distinct color.
     *
     * @param g The Graphics object used for drawing the game board.
     */
    public void paintGameBoard (Graphics g){
        int rows=board.getRows();
        int cols=board.getCols();
        int cellWidth=boardPanel.getWidth()/cols;
        int cellHeight=boardPanel.getHeight()/rows;
        for (int row=0; row<rows; row++) {
            for (int col=0; col<cols; col++) {
                if (board.getBoard()[row][col].equals("inactive")) {
                    g.setColor(Color.WHITE); // Eaten piece color
                } else if (row==rows-1 && col==0) {
                    g.setColor(new Color(190,0,0));
                } else {
                    g.setColor(new Color(102,51,0)); // Uneaten piece color
                }
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
            }
        }

    }

    /**
     * Method 3 of View in the diagram.
     * Handles mouse click events on the game board. Determines the clicked cell based on
     * the mouse position and updates the game state accordingly. Refreshes the board
     * and redraws the game after processing the click.
     * @param x
     * @param y
     */
    public void handleClick ( int x, int y){
        int rows=board.getRows();
        int cols=board.getCols();
        int cellWidth=boardPanel.getWidth() / cols;
        int cellHeight=boardPanel.getHeight() / rows;
        int clickedRow=y/cellHeight;
        int clickedCol=x/cellWidth;
        // Update the game state with the clicked position
        controller.handleClick(clickedRow, clickedCol);
        updateGameBoard();
        repaint();
    }

    /**
     * Method 4 of View in the diagram.
     * Update the board according to the ChocolateBarModel board
     */
    public void updateGameBoard (){
        repaint();
    }

    /**
     * Method 5 of View in the diagram.
     * Displays a dialog box announcing the winner and
     * provides options to stop the game or restart the game.
     * If the user chooses to restart, the controller's restart_game method is invoked.
     *
     * @param winner the name of the player who won the game
     */
    public void displayWinner (String winner){
        int option=JOptionPane.showOptionDialog(
                this,
                winner+" has won the game! Would you like to restart?",
        "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"restart","cancel"},
                "restart");
        if(option==JOptionPane.YES_OPTION){
            controller.restart_game();
        }else{
            System.exit(0);
        }

    }
}
