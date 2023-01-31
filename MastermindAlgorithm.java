public class MastermindAlgorithm {
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

