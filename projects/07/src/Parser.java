public class Parser {

    public static boolean hasMoreCommand(String str) {
        if(str.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    public static String commandType(String str) {
        if(str.startsWith("push")) {
            return "C_PUSH";
        }else if(str.startsWith("pop")) {
            return "C_POP";
        }else if(str.startsWith("lavel")) {
            return "C_LABEL";
        }else if(str.startsWith("goto")) {
            return "C_GOTO";
        }else if(str.startsWith("if")) {
            return "C_IF";
        }else if(str.startsWith("function")) {
            return "C_FUNCTION";
        }else if(str.startsWith("return")) {
            return "C_RETURN";
        }else if(str.startsWith("call")) {
            return "C_CALL";
        }else if(str.startsWith("add")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("sub")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("neg")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("eq")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("gt")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("lt")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("and")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("or")) {
            return "C_ARITHMENTIC";
        }else if(str.startsWith("not")) {
            return "C_ARITHMENTIC";
        }else {
            return "";
        }
    }

    public static String arg1(String str) {
        if(Parser.commandType(str).equals("C_ARITHMENTIC")) {
            return str;
        }else {
            return str.split(" ")[1];
        }
    }

    public static String arg2(String str) {
        return str.split(" ")[2];
    }

    public static String command(String str) {
        return str.split(" ")[0];
    }

}
