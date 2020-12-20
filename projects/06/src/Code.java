public class Code {

    public static String dest(String str) {

        if(str.startsWith("AMD")) {
            return "111";
        }else if(str.startsWith("MD")) {
            return "011";
        }else if(str.startsWith("M")) {
            return "001";
        }else if(str.startsWith("D")) {
            return "010";
        }else if(str.startsWith("AM")) {
            return "101";
        }else if(str.startsWith("AD")) {
            return "110";
        }else if(str.startsWith("A")) {
            return "100";
        }else {
            return "000";
        }

    }

    public static String jump(String str) {

        if(str.startsWith("JGT")) {
            return "001";
        }else if(str.startsWith("JEQ")) {
            return "010";
        }else if(str.startsWith("JGE")) {
            return "011";
        }else if(str.startsWith("JLT")) {
            return "100";
        }else if(str.startsWith("JNE")) {
            return "101";
        }else if(str.startsWith("JLE")) {
            return "110";
        }else if(str.startsWith("JMP")) {
            return "111";
        }else {
            return "000";
        }

    }

    public static String comp(String str) {

        if(str.startsWith("0")) {
            return "0101010";
        }else if(str.startsWith("D+A")) {
            return "0000010";
        }else if(str.startsWith("1")) {
            return "0111111";
        }else if(str.startsWith("-1")) {
            return "0111010";
        }else if(str.startsWith("!D")) {
            return "0001111";
        }else if(str.startsWith("-A")) {
            return "0110001";
        }else if(str.startsWith("D+1")) {
            return "0011111";
        }else if(str.startsWith("A+1")) {
            return "0110111";
        }else if(str.startsWith("D-1")) {
            return "0001110";
        }else if(str.startsWith("A-1")) {
            return "0110010";
        }else if(str.startsWith("D-A")) {
            return "0010011";
        }else if(str.startsWith("A-D")) {
            return "0000111";
        }else if(str.startsWith("D&A")) {
            return "0000000";
        }else if(str.startsWith("D|A")) {
            return "0010101";
        }else if(str.startsWith("!M")) {
            return "1110001";
        }else if(str.startsWith("-M")) {
            return "1110011";
        }else if(str.startsWith("M+1")) {
            return "1110111";
        }else if(str.startsWith("M-1")) {
            return "1110010";
        }else if(str.startsWith("D+M")) {
            return "1000010";
        }else if(str.startsWith("D-M")) {
            return "1010011";
        }else if(str.startsWith("M-D")) {
            return "1000111";
        }else if(str.startsWith("D&M")) {
            return "1000000";
        }else if(str.startsWith("D|M")) {
            return "1010101";
        }else if(str.startsWith("D")) {
            return "0001100";
        }else if(str.startsWith("A")) {
            return "0110000";
        }else if(str.startsWith("M")) {
            return "1110000";
        }else {
            return "0";
        }
        
    }
}
