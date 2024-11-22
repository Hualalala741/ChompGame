import controller.Controller;
import model.ChocolateBar;
import view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start_game(6,6);
    }
}