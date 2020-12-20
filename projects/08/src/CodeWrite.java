public class CodeWrite {
    // public String writeArithmetic(String command) {
    //     int spRam = Integer.parseInt(this.getAddress("SP"));
        
    //     if(command.equals("add")) {
    //         String ret = "";
    //         int sum;

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D+A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D+M";
    //         }else {
    //             ret += "";
    //         }
            
    //         sum = Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) + Integer.parseInt(this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-2), String.valueOf(sum));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));

    //         return ret;

    //     }else if(command.equals("sub")) {
    //         String ret = "";
    //         int sub;

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-M";
    //         }else {
    //             ret += "";
    //         }

    //         sub = Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) - Integer.parseInt(this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-2), String.valueOf(sub));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));

    //         return ret;

    //     }else if(command.equals("neg")) {
    //         String ret = "";
    //         int neg;

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=-A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=-M";
    //         }else {
    //             ret = "";
    //         }

    //         neg = Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) * -1;
    //         this.replace(String.valueOf(spRam-1), String.valueOf(neg));

    //         return ret;

    //     }else if(command.equals("eq")) {
    //         String ret = "";

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-M";
    //         }else {
    //             ret += "";
    //         }

    //         ret += "\n" + "@true" + "\n";
    //         ret += "D;JEQ" + "\n";
    //         ret += "@false" + "\n";
    //         ret += "D;JNE" + "\n";
    //         ret += "(TRUE)" + "\n";
    //         ret += "@true" + "\n";
    //         ret += "D=-1" + "\n";
    //         ret += "@next" + "\n";
    //         ret += "0;JMP" + "\n";
    //         ret += "(FALSE)" + "\n";
    //         ret += "@false" + "\n";
    //         ret += "D=0" + "\n";
    //         ret += "(NEXT)";

    //         if(Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) == Integer.parseInt(this.getAddress(String.valueOf(spRam-1)))) {
    //             this.replace(String.valueOf(spRam-2), "-1");
    //         }else {
    //             this.replace(String.valueOf(spRam-2), "0");
    //         }
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));
            
    //         return ret;

    //     }else if(command.equals("gt")) {
    //         String ret = "";

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-M";
    //         }else {
    //             ret += "";
    //         }

    //         ret += "\n" + "@true" + "\n";
    //         ret += "D;JGT" + "\n";
    //         ret += "@false" + "\n";
    //         ret += "D;JLE" + "\n";
    //         ret += "(TRUE)" + "\n";
    //         ret += "@true" + "\n";
    //         ret += "D=-1" + "\n";
    //         ret += "@next" + "\n";
    //         ret += "0;JMP" + "\n";
    //         ret += "(FALSE)" + "\n";
    //         ret += "@false" + "\n";
    //         ret += "D=0" + "\n";
    //         ret += "(NEXT)";

    //         if(Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) > Integer.parseInt(this.getAddress(String.valueOf(spRam-1)))) {
    //             this.replace(String.valueOf(spRam-2), "-1");
    //         }else {
    //             this.replace(String.valueOf(spRam-2), "0");
    //         }
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));

    //         return ret;

    //     }else if(command.equals("lt")) {
    //         String ret = "";

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D-M";
    //         }else {
    //             ret += "";
    //         }

    //         ret += "\n" + "@true" + "\n";
    //         ret += "D;JLT" + "\n";
    //         ret += "@false" + "\n";
    //         ret += "D;JGE" + "\n";
    //         ret += "(TRUE)" + "\n";
    //         ret += "@true" + "\n";
    //         ret += "D=-1" + "\n";
    //         ret += "@next" + "\n";
    //         ret += "0;JMP" + "\n";
    //         ret += "(FALSE)" + "\n";
    //         ret += "@false" + "\n";
    //         ret += "D=0" + "\n";
    //         ret += "(NEXT)";

    //         if(Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) < Integer.parseInt(this.getAddress(String.valueOf(spRam-1)))) {
    //             this.replace(String.valueOf(spRam-2), "-1");
    //         }else {
    //             this.replace(String.valueOf(spRam-2), "0");
    //         }
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));

    //         return ret;

    //     }else if(command.equals("and")) {
    //         String ret = "";

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D&A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D&M";
    //         }else {
    //             ret += "";
    //         }

    //         if((Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) == -1) & (Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == -1)) {
    //             this.replace(String.valueOf(spRam-2), "-1");
    //         }else {
    //             this.replace(String.valueOf(spRam-2), "0");
    //         }
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));

    //         return ret;

    //     }else if(command.equals("or")) {
    //         String ret = "";

    //         if(this.getAddress(String.valueOf(spRam-2)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=A";
    //         }else if(this.getAddress(String.valueOf(spRam-2)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-2)) + "\n" + "D=M";
    //         }else {
    //             ret = "";
    //         }

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D|A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret += "\n" + "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=D|M";
    //         }else {
    //             ret += "";
    //         }

    //         if((Integer.parseInt(this.getAddress(String.valueOf(spRam-2))) == -1) | (Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == -1)) {
    //             this.replace(String.valueOf(spRam-2), "-1");
    //         }else {
    //             this.replace(String.valueOf(spRam-2), "0");
    //         }
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //         spRam = Integer.parseInt(this.getAddress("SP"));

    //         return ret;

    //     }else if(command.equals("not") && (257 <= spRam) && (spRam <= 2047)) {
    //         String ret = "";

    //         if(this.getAddress(String.valueOf(spRam-1)).matches("[0-9]{1,}")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=!A";
    //         }else if(this.getAddress(String.valueOf(spRam-1)).matches("[a-zA-Z].*")) {
    //             ret = "@" + this.getAddress(String.valueOf(spRam-1)) + "\n" + "D=!M";
    //         }else {
    //             ret = "";
    //         }

    //         if(Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == -1) {
    //             this.replace(String.valueOf(spRam-1), "0");
    //         }else if(Integer.parseInt(this.getAddress(String.valueOf(spRam-1))) == 0) {
    //             this.replace(String.valueOf(spRam-1), "-1");
    //         }

    //         return ret;

    //     }else {
    //         return "";
    //     }
    // }

    // public void writePush(String command, String arg01, int arg02) {
    //     int spRam = Integer.parseInt(this.getAddress("SP"));
    //     int lclRam = Integer.parseInt(this.getAddress("LCL"));
    //     int argRam = Integer.parseInt(this.getAddress("ARG"));
    //     int thisRam = Integer.parseInt(this.getAddress("THIS"));
    //     int thatRam = Integer.parseInt(this.getAddress("THAT"));
    //     int tempRam = Integer.parseInt(this.getAddress("TEMP"));
    //     int stcRam = Integer.parseInt(this.getAddress("STC"));

    //     if(arg01.equals("constant")) {
    //         this.addEntry(String.valueOf(spRam), String.valueOf(arg02));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }else if(arg01.equals("local")) {
    //         this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(lclRam + arg02)));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }else if(arg01.equals("argument")) {
    //         this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(argRam + arg02)));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }else if(arg01.equals("this")) {
    //         this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(thisRam + arg02)));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }else if(arg01.equals("that")) {
    //         this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(thatRam + arg02)));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }else if(arg01.equals("pointer")) {
    //         if(arg02 == 0) {
    //             this.addEntry(String.valueOf(spRam), this.getAddress("THIS"));
    //             this.replace("SP", String.valueOf(spRam+1));
    //         }else if(arg02 == 1) {
    //             this.addEntry(String.valueOf(spRam), this.getAddress("THAT"));
    //             this.replace("SP", String.valueOf(spRam+1));
    //         }
    //     }else if(arg01.equals("temp")) {
    //         this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(tempRam + arg02)));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }else if(arg01.equals("static")) {
    //         this.addEntry(String.valueOf(spRam), this.getAddress(String.valueOf(stcRam + arg02)));
    //         this.replace("SP", String.valueOf(spRam+1));
    //     }
    
    // }

    // public void writePop(String command, String arg01, int arg02) {
    //     int spRam = Integer.parseInt(this.getAddress("SP"));
    //     int lclRam = Integer.parseInt(this.getAddress("LCL"));
    //     int argRam = Integer.parseInt(this.getAddress("ARG"));
    //     int thisRam = Integer.parseInt(this.getAddress("THIS"));
    //     int thatRam = Integer.parseInt(this.getAddress("THAT"));
    //     int tempRam = Integer.parseInt(this.getAddress("TEMP"));
    //     int stcRam = Integer.parseInt(this.getAddress("STC"));


    //     if(arg01.equals("local")) {
    //         this.addEntry(String.valueOf(lclRam + arg02), this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //     }else if(arg01.equals("argument")) {
    //         this.addEntry(String.valueOf(argRam + arg02), this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //     }else if(arg01.equals("this")) {
    //         this.addEntry(String.valueOf(thisRam + arg02), this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //     }else if(arg01.equals("that")) {
    //         this.addEntry(String.valueOf(thatRam + arg02), this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //     }else if(arg01.equals("pointer")) {
    //         if(arg02 == 0) {
    //             this.replace("THIS" , this.getAddress(String.valueOf(spRam-1)));
    //             this.replace(String.valueOf(spRam-1), null);
    //             this.replace("SP", String.valueOf(spRam-1));
    //         }else if(arg02 == 1) {
    //             this.replace("THAT" , this.getAddress(String.valueOf(spRam-1)));
    //             this.replace(String.valueOf(spRam-1), null);
    //             this.replace("SP", String.valueOf(spRam-1));
    //         }
    //     }else if(arg01.equals("temp")) {
    //         this.addEntry(String.valueOf(tempRam + arg02), this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //     }else if(arg01.equals("static")) {
    //         this.addEntry(String.valueOf(stcRam + arg02), this.getAddress(String.valueOf(spRam-1)));
    //         this.replace(String.valueOf(spRam-1), null);
    //         this.replace("SP", String.valueOf(spRam-1));
    //     }
    
    // }
}
