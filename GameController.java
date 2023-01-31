// import java.lang.Math;
// import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
public class GameController {
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
