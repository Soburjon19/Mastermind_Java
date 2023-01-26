import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class MastermindAlgorithm {
    private String guestCode;
    private String randomCode;
    public static  final String WHITE = "\u001B[37m";
    public static  final String GREEN = "\u001B[32m";
    public static  final String YELLOW = "\u001B[33m";
    public static  final String BLUE = "\u001B[34m";
    public static  final String PURPLE = "\u001B[35m";
    public static  final String CYAN = "\u001B[36m";
    public MastermindAlgorithm(String randomCode, String guestCode) {
        this.guestCode = guestCode;
        this.randomCode = randomCode;
    }

    public void wellPiecesAlgo() {
        int wp = 0;
        for(int i=0; i < this.randomCode.length(); i++) {
            if(this.randomCode.charAt(i) == this.guestCode.charAt(i))
                wp++;
        }
        System.out.printf(CYAN + "--- Well placed pieces: %d ---\n", wp);
    }
    public void misPiecesAlgo() {
        int mp = 0;
        for(int i=0; i < this.randomCode.length(); i++) {
            if(this.randomCode.contains("" + this.guestCode.charAt(i)) && this.randomCode.charAt(i) != this.guestCode.charAt(i))
                mp++;
        }
        System.out.printf(BLUE + "---- Misplaced pieces: %d ----\n", mp);
    }
}

class GameFunctions {
    static String getSecretCode() {
        String code = "";
        while(code.length() < 4) {
            int rand = (int)(Math.random() * 7) + 1;
            if(!(code.contains(""+rand))) {
                code += rand;
            }
        }
        return code;
    }

    static boolean isDuplicated(char arg[]) {
        int count = 0;
        for(int i = 0; i <arg.length - 1; i++) {
            for(int j = i+1; j <arg.length; j++) {  
                if(arg[i] == arg[j]) {  
                    count++; 
                }
            }  
        }
        return count > 0;
    }

    static boolean wrongInput(String text) { 
        int len = text.length();
        String block = "01234567";
        if(len != 4) 
            return true;
        char arr[] = text.toCharArray();  
        for(int i=0; i < arr.length; i++) {
	    char c = arr[i];	
            if(!(block.contains(""+c)) || isDuplicated(arr)) {
                return true;
	        }
        }
	    return false;
    }

}

class GameController {
    public static  final String YELLOW = "\u001B[33m";
    public static  final String RED = "\u001B[31m";
    private String[] args;
    private String secretCode;
    private int rounds;

    public GameController(String[] args) {
        this.args = args;
    }

    public String getSecretCode() {
        return this.secretCode;
    }

    public int getRounds() {
        return this.rounds;
    }

    public void initialize() {
        if (this.args.length > 1){
            ArrayList<String> option_list = new ArrayList<String>();
            option_list.addAll(Arrays.asList(this.args));
            Iterator<String> itr = null;
            itr = option_list.listIterator();    
            while (itr.hasNext()) { 
                String option = itr.next();
                if (option.equals("-t")) {
                    try {
                        this.rounds = Integer.parseInt(itr.next());
                        if(this.rounds < 0 && -15 < this.rounds) {
                            this.rounds *= -1;
                            System.out.printf(YELLOW + "Rounds number set: %d (not -%d)\n", this.rounds, this.rounds);
                        } else if(this.rounds > 15 || this.rounds < 1){
                                System.out.println(RED + "Rounds cannot be more then 15 or less then 1 (15 ≥ rounds ≥ 1)");
                                this.rounds = 10;
                        }
                    } catch (Exception e) {
                        System.out.println(YELLOW + "You should type number of rounds! (-t)\n");
                    };
                }
                
                if (option.equals("-c")) {
                    String code = itr.next();
                    if(GameFunctions.wrongInput(code)) {
                        System.out.printf(RED + "Error in setting secret code: ( -c %s )\n" +
                            "Secret code set automatic by 'generator'\n", code);
                        this.secretCode = GameFunctions.getSecretCode();
                    } else {
                        this.secretCode = code;
                    }
                }
            }
        }
        if (this.secretCode == null) {
            this.secretCode = GameFunctions.getSecretCode();
        }
        if (this.rounds == 0) {
            this.rounds = 10;
        }
    }


}

class GameLoop {
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
                System.out.println(RED + "Wrong input!");
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
