import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class My_Mastermind {

    public static String secretCode;
    public static String inputCode;
    public static int rounds;

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
    
    static void missPlaced(String code, String input) { 
        int mp = 0;
        for(int i=0; i < code.length(); i++) {
            if(code.contains("" + input.charAt(i)) && code.charAt(i) != input.charAt(i))
                mp++;
        }
        System.out.printf("Misplaced pieces: %d\n", mp);
    }

    static void wellPlaced(String code, String input) {
        int wp = 0;
        for(int i=0; i < code.length(); i++) {
            if(code.charAt(i) == input.charAt(i))
                    wp++;
        }
        System.out.printf("Well placed pieces: %d\n", wp);
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

    public static void setOptions(String[] args) {
        if (args.length > 1 ){
            ArrayList<String> option_list = new ArrayList<String>();
            option_list.addAll(Arrays.asList(args));
            Iterator<String> itr = null;
            itr = option_list.listIterator();    
            while (itr.hasNext()) { 
                String option = itr.next();
                if (option.equals("-t")) {
                    try {
                        rounds = Integer.parseInt(itr.next());
                        if(rounds < 0 && -16 < rounds) {
                            rounds *= -1;
                            System.out.printf("Rounds number set: %d (not -%d)\n",rounds, rounds);
                        } else if(rounds > 16 || rounds < 1){
                                System.out.println("Rounds cannot be more then 15 or less then 1 (15≥rounds≥1)");
                                rounds = 10;
                        }
                    } catch (Exception e) {
                        System.out.println("You should type number of rounds! (-t)\n");
                    };
                }
                
                if (option.equals("-c")) {
                    String code = itr.next();
                    if(wrongInput(code)) {
                        System.out.printf("Error in setting secret code: ( -c %s )\n" +
                            "Secret code set automatic by 'generator'\n", code);
                        secretCode = getSecretCode();
                    } else {
                        secretCode = code;
                    }
                }
            }
        }
        if (secretCode == null) {
            secretCode = getSecretCode();
        }
        if (rounds == 0) {
            rounds = 10;
        }
    }

    public static void run() {
        int round = 1;
        System.out.println("Will you find the secret code?");
        Scanner input = new Scanner(System.in);
        while (round <= rounds) {
            System.out.printf("---\nRound %d/%d\n>", round, rounds);
            inputCode = input.nextLine();
            if(wrongInput(inputCode)) {
                System.out.println("Wrong input!");
                continue;
            } else if (secretCode.equals(inputCode)) {
                System.out.println("Congrats! You did it!");
                break;
            } else {
                wellPlaced(secretCode, inputCode);
                missPlaced(secretCode, inputCode);
            }
            if (round == rounds)
                System.out.printf("Game Over\n"+"Secret code: %s\n", secretCode);
            round++;
            
        }
        
    }
    public static void main(String[] args) {
        setOptions(args);
        run();
    }
}
