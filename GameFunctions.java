public class  GameFunctions {
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
