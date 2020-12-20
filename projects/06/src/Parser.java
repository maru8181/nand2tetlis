public class Parser {

    public static boolean hasMoreCommand(String str) {
        if(str.equals("")) {
            return false;
        }else {
            return true;
        }
    }
    
    public static String commandType(String str) {
        if(str.startsWith("@")) {
            return "A_COMMAND";
        }else if(str.startsWith("(")) {
            return "L_COMMAND";
        }else if(str.matches(".{2,}")){
            return "C_COMMAND";
        }else {
            return "";
        }
    }

    //A_COMMANDの時だけ
    public static String symbol(String str) {
        return str.substring(1);
    }

    //C_COMMANDの時だけ
    public static String dest(String str) {
        if(str.startsWith("A=")) {
            return "A";
        }else if(str.startsWith("M=")) {
            return "M";
        }else if(str.startsWith("D=")) {
            return "D";
        }else if(str.startsWith("MD=")) {
            return "MD";
        }else if(str.startsWith("AM=")) {
            return "AM";
        }else if(str.startsWith("AD=")) {
            return "AD";
        }else if(str.startsWith("AMD=")) {
            return "AMD";
        }else {
            return "null";
        }
    }

    //C_COMMANDの時だけ
    public static String jump(String str) {
        if(str.endsWith("JGT")) {
            return "JGT";
        }else if(str.endsWith("JEQ")) {
            return "JEQ";
        }else if(str.endsWith("JGE")) {
            return "JGE";
        }else if(str.endsWith("JLT")) {
            return "JLT";
        }else if(str.endsWith("JNE")) {
            return "JNE";
        }else if(str.endsWith("JLE")) {
            return "JLE";
        }else if(str.endsWith("JMP")) {
            return "JMP";
        }else {
            return "null";
        }
    }

    //C_COMMANDの時だけ
    public static String comp(String str) {

        if(Parser.jump(str).equals("null")) {

            if(str.endsWith("0")) {
                return "0";
            }else if(str.endsWith("D+1")) {
                return "D+1";
            }else if(str.endsWith("A+1")) {
                return "A+1";
            }else if(str.endsWith("D-1")) {
                return "D-1";
            }else if(str.endsWith("A-1")) {
                return "A-1";
            }else if(str.endsWith("D+A")) {
                return "D+A";
            }else if(str.endsWith("D-A")) {
                return "D-A";
            }else if(str.endsWith("A-D")) {
                return "A-D";
            }else if(str.endsWith("D&A")) {
                return "D&A";
            }else if(str.endsWith("D|A")) {
                return "D|A";
            }else if(str.endsWith("M+1")) {
                return "M+1";
            }else if(str.endsWith("M-1")) {
                return "M-1";
            }else if(str.endsWith("D+M")) {
                return "D+M";
            }else if(str.endsWith("D-M")) {
                return "D-M";
            }else if(str.endsWith("M-D")) {
                return "M-D";
            }else if(str.endsWith("D&M")) {
                return "D&M";
            }else if(str.endsWith("D|M")) {
                return "D|M";
            }else if(str.endsWith("!M")) {
                return "!M";
            }else if(str.endsWith("-M")) {
                return "-M";
            }else if(str.endsWith("!D")) {
                return "!D";
            }else if(str.endsWith("!A")) {
                return "!A";
            }else if(str.endsWith("-1")) {
                return "-1";
            }else if(str.endsWith("-D")) {
                return "-D";
            }else if(str.endsWith("-A")) {
                return "-A";
            }else if(str.endsWith("1")) {
                return "1";
            }else if(str.endsWith("M")) {
                return "M";
            }else if(str.endsWith("D")) {
                return "D";
            }else if(str.endsWith("A")) {
                return "A";
            }else {
                return "";
            }
            
        }else {
            
            if(str.matches(".*0;.*")) {
                return "0";
            }else if(str.matches(".*D[+]1;.*")) {
                return "D+1";
            }else if(str.matches(".*A[+]1;.*")) {
                return "A+1";
            }else if(str.matches(".*D-1;.*")) {
                return "D-1";
            }else if(str.matches(".*A-1;.*")) {
                return "A-1";
            }else if(str.matches(".*D[+]A;.*")) {
                return "D+A";
            }else if(str.matches(".*D-A;.*")) {
                return "D-A";
            }else if(str.matches(".*A-D;.*")) {
                return "A-D";
            }else if(str.matches(".*D&A;.*")) {
                return "D&A";
            }else if(str.matches(".*D|A;.*")) {
                return "D|A";
            }else if(str.matches(".*M[+]1;.*")) {
                return "M+1";
            }else if(str.matches(".*M-1;.*")) {
                return "M-1";
            }else if(str.matches(".*D[+]M;.*")) {
                return "D+M";
            }else if(str.matches(".*D-M;.*")) {
                return "D-M";
            }else if(str.matches(".*M-D;.*")) {
                return "M-D";
            }else if(str.matches(".*D&M;.*")) {
                return "D&M";
            }else if(str.matches(".*D|M;.*")) {
                return "D|M";
            }else if(str.matches(".*!M;.*")) {
                return "!M";
            }else if(str.matches(".*-M;.*")) {
                return "-M";
            }else if(str.matches(".*!D;.*")) {
                return "!D";
            }else if(str.matches(".*!A;.*")) {
                return "!A";
            }else if(str.matches(".*-1;.*")) {
                return "-1";
            }else if(str.matches(".*-D;.*")) {
                return "-D";
            }else if(str.matches(".*-A;.*")) {
                return "-A";
            }else if(str.matches(".*1;.*")) {
                return "1";
            }else if(str.matches(".*M;.*")) {
                return "M";
            }else if(str.matches(".*D;.*")) {
                return "D";
            }else if(str.matches(".*A;.*")) {
                return "A";
            }else {
                return "";
            }

        }

    }

}
