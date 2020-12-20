import java.util.LinkedHashMap;
// import java.util.Map;

public class SymbolTable {
    public LinkedHashMap<String,String> symbolTable;

    public SymbolTable() {
        if (symbolTable == null) {
            symbolTable = new LinkedHashMap<>();
            symbolTable.put("SP", "256");
            symbolTable.put("LCL", "2048");
            symbolTable.put("ARG", "2048");
            symbolTable.put("THIS", "2048");
            symbolTable.put("THAT", "2048");
            symbolTable.put("TEMP", "5");
            symbolTable.put("STC", "16");

            symbolTable.put("SCREEN", "16384");
            symbolTable.put("KBD", "24576");

            symbolTable.put("R0", "0");
            symbolTable.put("R1", "1");
            symbolTable.put("R2", "2");
            symbolTable.put("R3", "3");
            symbolTable.put("R4", "4");
            symbolTable.put("R5", "5");
            symbolTable.put("R6", "6");
            symbolTable.put("R6", "7");
            symbolTable.put("R7", "8");
            symbolTable.put("R9", "9");
            symbolTable.put("R10", "10");
            symbolTable.put("R11", "11");
            symbolTable.put("R12", "12");
            symbolTable.put("R13", "13");
            symbolTable.put("R14", "14");
            symbolTable.put("R15", "15");
        }
    }

    public void addEntry(String symbol, String address) {
        symbolTable.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }

    public String getAddress(String symbol) {
        return symbolTable.get(symbol);
    }

    public void replace(String symbol, String address) {
        symbolTable.replace(symbol, address);
    }

    public String writeArithmetic(String command) {
        int spRam = Integer.parseInt(this.getAddress("SP"));
        
        if(command.equals("add")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            int sum;            
            
            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "M=D+A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "M=D+M";
            }
            
            ret += "\n" + "@SP"+ "\n" + "M=M+1";
            
            sum = Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) + Integer.parseInt(this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-2), String.valueOf(sum));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));
            
            return ret;

        }else if(command.equals("sub")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            int sub;
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M+1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=D-A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret +=  "D=D-M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M" + "\n" + "M=D" + "\n" + "@SP"+ "\n" + "M=M+1";
            
            sub = Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) - Integer.parseInt(this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-2), String.valueOf(sub));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));

            return ret;
            
        }else if(command.equals("neg")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            int neg;

            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "M=-A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "M=-M";
            }
            
            ret += "\n" + "@SP"+ "\n" + "M=M+1";
            
            neg = Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) * -1;
            this.replace(String.valueOf(spRam-1), String.valueOf(neg));

            return ret;

        }else if(command.equals("eq")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=D-A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=D-M";
            }
            
            ret += "\n" + "@true" + "\n";
            ret += "D;JEQ" + "\n";
            ret += "@false" + "\n";
            ret += "D;JNE" + "\n";
            ret += "(TRUE)" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=-1" + "\n";
            ret += "@next" + "\n";
            ret += "0;JMP" + "\n";
            ret += "(FALSE)" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=0" + "\n";
            ret += "(NEXT)" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            if(Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) == Integer.parseInt(this.getAddress(String.valueOf(spRam-1)))) {
                this.replace(String.valueOf(spRam-2), "-1");
            }else {
                this.replace(String.valueOf(spRam-2), "0");
            }
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));

            return ret;

        }else if(command.equals("gt")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";

            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=D-A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=D-M";
            }
            
            ret += "\n" + "@true" + "\n";
            ret += "D;JLT" + "\n";
            ret += "@false" + "\n";
            ret += "D;JGE" + "\n";
            ret += "(TRUE)" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=-1" + "\n";
            ret += "@next" + "\n";
            ret += "0;JMP" + "\n";
            ret += "(FALSE)" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=0" + "\n";
            ret += "(NEXT)" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            if(Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) > Integer.parseInt(this.getAddress(String.valueOf(spRam-1)))) {
                this.replace(String.valueOf(spRam-2), "-1");
            }else {
                this.replace(String.valueOf(spRam-2), "0");
            }
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));

            return ret;

        }else if(command.equals("lt")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=D-A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=D-M";
            }
            
            ret += "\n" + "@true" + "\n";
            ret += "D;JGT" + "\n";
            ret += "@false" + "\n";
            ret += "D;JLE" + "\n";
            ret += "(TRUE)" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=-1" + "\n";
            ret += "@next" + "\n";
            ret += "0;JMP" + "\n";
            ret += "(FALSE)" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=0" + "\n";
            ret += "(NEXT)" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            if(Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) < Integer.parseInt(this.getAddress(String.valueOf(spRam-1)))) {
                this.replace(String.valueOf(spRam-2), "-1");
            }else {
                this.replace(String.valueOf(spRam-2), "0");
            }
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));
            
            return ret;

        }else if(command.equals("and")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "M=D&A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "M=D&M";
            }
            
            ret += "\n" + "@SP"+ "\n" + "M=M+1";

            if((Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) == -1) & (Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == -1)) {
                this.replace(String.valueOf(spRam-2), "-1");
            }else {
                this.replace(String.valueOf(spRam-2), "0");
            }
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));
            
            return ret;
            
        }else if(command.equals("or")) {
            String ret = "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
                ret += "\n" + "D=A";
            }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
                ret += "\n" + "D=M";
            }
            
            ret += "\n" + "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M";
            
            if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
                ret += "\n" + "M=D|A";
            }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
                ret += "\n" + "M=D|M";
            }
            
            ret += "\n" + "@SP"+ "\n" + "M=M+1";

            if((Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) == -1) | (Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == -1)) {
                this.replace(String.valueOf(spRam-2), "-1");
            }else {
                this.replace(String.valueOf(spRam-2), "0");
            }
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            spRam = Integer.parseInt(this.getAddress("SP"));
            
            return ret;
            
        }else if(command.equals("not")) {
            

            if(Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == -1) {
                this.replace(String.valueOf(spRam-1), "0");
            }else if(Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == 0) {
                this.replace(String.valueOf(spRam-1), "-1");
            }


            return "@SP" + "\n" + "M=M-1" + "\n" + "@SP" + "\n" + "A=M" + "\n" + "M=!M" + "\n" + "@SP"+ "\n" + "M=M+1";

        }else {
            return "";
        }
    }

    public String writePush(String command, String arg01, int arg02) {
        int spRam = Integer.parseInt(this.getAddress("SP"));
        int lclRam = Integer.parseInt(this.getAddress("LCL"));
        int argRam = Integer.parseInt(this.getAddress("ARG"));
        int thisRam = Integer.parseInt(this.getAddress("THIS"));
        int thatRam = Integer.parseInt(this.getAddress("THAT"));
        int tempRam = Integer.parseInt(this.getAddress("TEMP"));
        int stcRam = Integer.parseInt(this.getAddress("STC"));

        if(arg01.equals("constant")) {
            String ret = null;
            
            ret = "@" + arg02 + "\n";
            ret += "D=A" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            this.addEntry(String.valueOf(spRam), String.valueOf(arg02));
            this.replace("SP", String.valueOf(spRam+1));

            return ret;

        }else if(arg01.equals("local")) {
            String ret = null;
            
            ret = "@" + String.valueOf(lclRam + arg02) + "\n";
            ret += "D=M" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(lclRam + arg02)));
            this.replace("SP", String.valueOf(spRam+1));

            return ret;

        }else if(arg01.equals("argument")) {
            String ret = null;
            
            ret = "@" + String.valueOf(argRam + arg02) + "\n";
            ret += "D=M" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(argRam + arg02)));
            this.replace("SP", String.valueOf(spRam+1));
            
            return ret;

        }else if(arg01.equals("this")) {
            String ret = null;
            
            ret = "@" + String.valueOf(thisRam + arg02) + "\n";
            ret += "D=M" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(thisRam + arg02)));
            this.replace("SP", String.valueOf(spRam+1));
            
            return ret;
            
        }else if(arg01.equals("that")) {
            String ret = null;

            ret = "@" + String.valueOf(thatRam + arg02) + "\n";
            ret += "D=M" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(thatRam + arg02)));
            this.replace("SP", String.valueOf(spRam+1));

            return ret;

        }else if(arg01.equals("pointer")) {
            String ret = null;

            if(arg02 == 0) {
                
                ret = "@THIS" + "\n";
                ret += "D=M" + "\n";
                ret += "@SP" + "\n";
                ret += "A=M" + "\n";
                ret += "M=D" + "\n";
                ret += "@SP" + "\n";
                ret += "M=M+1";

                this.addEntry(String.valueOf(spRam), this.getAddress("THIS"));
                this.replace("SP", String.valueOf(spRam+1));
                
                return ret;
                
            }else if(arg02 == 1) {
                
                ret = "@THAT" + "\n";
                ret += "D=M" + "\n";
                ret += "@SP" + "\n";
                ret += "A=M" + "\n";
                ret += "M=D" + "\n";
                ret += "@SP" + "\n";
                ret += "M=M+1";
                
                this.addEntry(String.valueOf(spRam), this.getAddress("THAT"));
                this.replace("SP", String.valueOf(spRam+1));
                
                return ret;

            }else {
                return "";
            }

        }else if(arg01.equals("temp")) {
            String ret = null;
            
            ret = "@" + String.valueOf(tempRam + arg02) + "\n";
            ret += "D=M" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";

            this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(tempRam + arg02)));
            this.replace("SP", String.valueOf(spRam+1));

            return ret;

        }else if(arg01.equals("static")) {
            String ret = null;
            
            ret = "@" + String.valueOf(stcRam + arg02) + "\n";
            ret += "D=M" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "M=D" + "\n";
            ret += "@SP" + "\n";
            ret += "M=M+1";
            
            this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(stcRam + arg02)));
            this.replace("SP", String.valueOf(spRam+1));
            
            return ret;

        }else {
            return "";
        }

    }

    public String writePop(String command, String arg01, int arg02) {
        int spRam = Integer.parseInt(this.getAddress("SP"));
        int lclRam = Integer.parseInt(this.getAddress("LCL"));
        int argRam = Integer.parseInt(this.getAddress("ARG"));
        int thisRam = Integer.parseInt(this.getAddress("THIS"));
        int thatRam = Integer.parseInt(this.getAddress("THAT"));
        int tempRam = Integer.parseInt(this.getAddress("TEMP"));
        int stcRam = Integer.parseInt(this.getAddress("STC"));


        if(arg01.equals("local")) {
            String ret = null;

            ret = "@SP" + "\n";
            ret += "M=M-1" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "D=M" + "\n";
            ret += "@" + String.valueOf(lclRam + arg02) + "\n";
            ret += "M=D";

            this.addEntry(String.valueOf(lclRam + arg02), this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));

            return ret;
            
        }else if(arg01.equals("argument")) {
            String ret = null;
            
            ret = "@SP" + "\n";
            ret += "M=M-1" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "D=M" + "\n";
            ret += "@" + String.valueOf(argRam + arg02) + "\n";
            ret += "M=D";
            
            this.addEntry(String.valueOf(argRam + arg02), this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));

            return ret;
            
        }else if(arg01.equals("this")) {
            String ret = null;
            
            ret = "@SP" + "\n";
            ret += "M=M-1" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "D=M" + "\n";
            ret += "@" + String.valueOf(thisRam + arg02) + "\n";
            ret += "M=D";
            
            this.addEntry(String.valueOf(thisRam + arg02), this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));

            return ret;
            
        }else if(arg01.equals("that")) {
            String ret = null;
            
            ret = "@SP" + "\n";
            ret += "M=M-1" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "D=M" + "\n";
            ret += "@" + String.valueOf(thatRam + arg02) + "\n";
            ret += "M=D";
            
            this.addEntry(String.valueOf(thatRam + arg02), this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            
            return ret;
            
        }else if(arg01.equals("pointer")) {
            String ret = null;

            if(arg02 == 0) {
                
                ret = "@SP" + "\n";
                ret += "M=M-1" + "\n";
                ret += "@SP" + "\n";
                ret += "A=M" + "\n";
                ret += "D=M" + "\n";
                ret += "@THIS" + "\n";
                ret += "M=D";

                this.replace("THIS" , this.getAddress(String.valueOf(spRam-1)));
                this.replace(String.valueOf(spRam-1), null);
                this.replace("SP", String.valueOf(spRam-1));
                
                return ret;
                
            }else if(arg02 == 1) {

                ret = "@SP" + "\n";
                ret += "M=M-1" + "\n";
                ret += "@SP" + "\n";
                ret += "A=M" + "\n";
                ret += "D=M" + "\n";
                ret += "@THAT" + "\n";
                ret += "M=D";

                this.replace("THAT" , this.getAddress(String.valueOf(spRam-1)));
                this.replace(String.valueOf(spRam-1), null);
                this.replace("SP", String.valueOf(spRam-1));
                
                return ret;

            }else {
                return "";
            }

        }else if(arg01.equals("temp")) {
            String ret = null;
            
            ret = "@SP" + "\n";
            ret += "M=M-1" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "D=M" + "\n";
            ret += "@" + String.valueOf(tempRam + arg02) + "\n";
            ret += "M=D";
            
            this.addEntry(String.valueOf(tempRam + arg02), this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));

            return ret;

        }else if(arg01.equals("static")) {
            String ret = null;
            
            ret = "@SP" + "\n";
            ret += "M=M-1" + "\n";
            ret += "@SP" + "\n";
            ret += "A=M" + "\n";
            ret += "D=M" + "\n";
            ret += "@" + String.valueOf(stcRam + arg02) + "\n";
            ret += "M=D";
            
            this.addEntry(String.valueOf(stcRam + arg02), this.getAddress(String.valueOf(spRam-1)));
            this.replace(String.valueOf(spRam-1), null);
            this.replace("SP", String.valueOf(spRam-1));
            
            return ret;

        }else {
            return "";
        }
    }

}
