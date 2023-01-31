import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
public class GameLoop {
    public static  final String YELLOW = "\u001B[33m";
    public static  final String RED = "\u001B[31m";
    public static  final String GREEN = "\u001B[32m";
    public static  final String WHITE = "\u001B[37m";
    public static  final String PURPLE = "\u001B[35m";
    private int rounds;
    private String secretCode;
    private MastermindAlgorithm mastermindAlgorithm;

    public GameLoop(int rounds, String secretCode) {
        this.rounds = rounds;
        this.secretCode = secretCode;
    }

    public void run() {
        int round = 1;
        String inputCode;
        System.out.println(GREEN + "Will you find the secret code?");
        Scanner input = new Scanner(System.in);
        while (round <= rounds) {
            System.out.printf(YELLOW + "-------🏁  Round %d/%d 🏁 -------\n" + PURPLE + "            ", round, rounds);
            inputCode = input.nextLine();
            this.mastermindAlgorithm = new MastermindAlgorithm(
                this.secretCode,
                inputCode
            );
            if(GameFunctions.wrongInput(inputCode)) {
                System.out.println(RED + "------❌  Wrong input!❌ -------");
                continue;
            } else if (secretCode.equals(inputCode)) {
                System.out.println(GREEN + "Congrats! You did it!🥳  🥳  🥳");
                break;
            } else {
                mastermindAlgorithm.wellPiecesAlgo();
                mastermindAlgorithm.misPiecesAlgo();
            }
            if (round == this.rounds)
                System.out.printf(RED + "-------❌  Game Over ❌ -------\n"+ GREEN +"----- Secret code: %s -----\n", this.secretCode);
            round++;
        }
    }
}
