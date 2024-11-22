package model;

public class Player {
    private String cur_player;

    /**
     * Method 1 of Player in the diagram.
     * Initializes the current player with the given name.
     */
    public Player(String player) {
        cur_player = player;
    }

    /**
     * Method 2 of Player in the diagram.
     * Retrieves the name of the current player.
     * @return the name of the current player
     */
    public String get_cur_player() {
        return cur_player;
    }

    /**
     * Method 3 of Player in the diagram.
     * Sets the current player to the specified player name.
     * @param player the name of the new current player
     */
    public void set_player(String player) {
        cur_player = player;
    }
}
