public class JackTokenizer {

    public static String tokenType(String str) {

        if(str.equals("class") | str.equals("constructor") | str.equals("function") | str.equals("method") | str.equals("field") | str.equals("static") | str.equals("var") | str.equals("int") | str.equals("char") | str.equals("boolean") | str.equals("void") | str.equals("true") | str.equals("false") | str.equals("null") | str.equals("this") | str.equals("let") | str.equals("do") | str.equals("if") | str.equals("else") | str.equals("while") | str.equals("return")) {
            return "KEYWORD";
        }else if(str.equals("{") | str.equals("}") | str.equals("(") | str.equals(")") | str.equals("[") | str.equals("]") | str.equals(".") | str.equals(",") | str.equals(";") | str.equals("+") | str.equals("-") | str.equals("*") | str.equals("/") | str.equals("&") | str.equals("|") | str.equals("<") | str.equals(">") | str.equals("=") | str.equals("~")) {
            return "SYMBOL";
        }else if(str.matches("\\d")) {
            int i = Integer.parseInt(str);
            if(0 <= i & i <= 32767){
                return "INT_CONST";
            }
            return "error";
        }else if(str.matches("[a-zA-Z_]\\w*")) {
            return "IDENTIFIER";
        }else if(str.matches("\".*\"")) {
            return "STRING_CONST";
        }else {
            return "error";
        }
    }

    

}