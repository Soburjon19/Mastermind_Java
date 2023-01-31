import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class My_Mastermind {

    public static void main(String[] args) {
        GameController gameController = new GameController(args);
        gameController.initialize();
        GameLoop gameLoop = new GameLoop(
            gameController.getRounds(),
            gameController.getSecretCode()
        );
        gameLoop.run();
    }
}
