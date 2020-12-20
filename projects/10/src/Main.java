import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(args[0].replace(".jack", ".xml"));
        String line = null;
        List<String> token = new ArrayList<>();
        List<String> tokenAll = new ArrayList<>();
        String str = null;
        String linetr2 = null;
        String linetr3 = null;
        String output = "";

        while((line = br.readLine()) != null) {
            String linetr = line.trim();

            if(linetr.startsWith("//")) {
                continue;
            }else if(linetr.equals("")) {
                continue;
            }

            if(linetr.contains("//")) {
                int last = linetr.indexOf("//");
                linetr2 = linetr.substring(0, last);
                linetr2 = linetr2.trim();
            }else {
                linetr2 = linetr;
            }

            if(linetr2.contains("\"")) {
                int first = linetr2.indexOf("\"");
                int last = linetr2.lastIndexOf("\"");
                str = linetr2.substring(first+1, last);
                linetr3 = linetr2.replaceAll("\".*\"", "\"str\"");
            }else {
                linetr3 = linetr2;
            }

            String line2 = linetr3.replace("{", " { ");
            String line3 = line2.replace("}", " } ");
            String line4 = line3.replace("(", " ( ");
            String line5 = line4.replace(")", " ) ");
            String line6 = line5.replace("[", " [ ");
            String line7 = line6.replace("]", " ] ");
            String line8 = line7.replace(".", " . ");
            String line9 = line8.replace(",", " , ");
            String line10 = line9.replace(";", " ; ");
            String line11 = line10.replace("+", " + ");
            String line12 = line11.replace("-", " - ");
            String line13 = line12.replace("*", " * ");
            String line14 = line13.replace("/", " / ");
            String line15 = line14.replace("&", " & ");
            String line16 = line15.replace("|", " | ");
            String line17 = line16.replace("<", " < ");
            String line18 = line17.replace(">", " > ");
            String line19 = line18.replace("=", " = ");
            String line20 = line19.replace("~", " ~ ");
            String line21 = line20.replaceAll(" {2,}", " ");
            String linefinal = line21.trim();

            token = Arrays.asList(linefinal.split(" "));

            for(String t : token) {
                if(t.equals("\"str\"")) {
                    t = "\"" + str + "\"";
                }
                tokenAll.add(t);
            }
            
        }
        
        for(int i=0; i<tokenAll.size(); i++) {
            // String tokenType = JackTokenizer.tokenType(tokenAll.get(i));

            if(tokenAll.get(i).equals("class")) {
                output += "<tokens>" + "\n" + "<keyword> class </keyword>" + "\n" + "<identifier>" + tokenAll.get(i+1) + "</identifier>" + "\n" + "<symbol> { </symbol>" + "\n" + "classVarDec" + "\n" + "subroutineDec" + "\n" + "<symbol> } </symbol>" + "\n" + "</tokens>";
            }else if(tokenAll.get(i).equals("static") | tokenAll.get(i).equals("filed")) {
                if(tokenAll.get(i+3).equals(",")) {
                    output = output.substring(0,output.indexOf("classVarDec")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<keyword>" + tokenAll.get(i+1) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+2) + "</identifier>" + "\n" + "<symbol> , </symbol>" + "\n" + "<identifier>" + tokenAll.get(i+4) + "</identifier>" + "\n" + "<symbol> ; </symbol>"+ "\n" + output.substring(output.indexOf("classVarDec"));
                }else {
                    output = output.substring(0,output.indexOf("classVarDec")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<keyword>" + tokenAll.get(i+1) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+2) + "</identifier>" + "\n" + "<symbol> ; </symbol>" + "\n" + output.substring(output.indexOf("classVarDec"));
                }
            }else if((tokenAll.get(i).equals("int") | tokenAll.get(i).equals("char") | tokenAll.get(i).equals("boolean")) && tokenAll.get(i-1).equals("(")) {
                if(tokenAll.get(i+2).equals(",")) {
                    output = output.substring(0,output.indexOf("paramenterList")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+1) + "</identifier>" + "\n" + "<symbol> , </symbol>" + "\n" + "<keyword>" + tokenAll.get(i+3) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+4) + "</identifier>" + "\n" + output.substring(output.indexOf("paramenterList")+15);
                }else {
                    output = output.substring(0,output.indexOf("paramenterList")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+1) + "</identifier>" + "\n" + output.substring(output.indexOf("paramenterList")+15);
                }
            }else if(tokenAll.get(i).equals("constructor") | tokenAll.get(i).equals("function") | tokenAll.get(i).equals("method")) {
                if(tokenAll.get(i+4).equals(")")) {
                    output = output.substring(0,output.indexOf("subroutineDec")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<keyword>" + tokenAll.get(i+1) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+2) + "</identifier>" + "\n" + "<symbol> ( </symbol>" + "\n" + "<symbol> ) </symbol>" + "\n" + "subroutineBody" + "\n" + output.substring(output.indexOf("subroutineDec"));
                }else {
                    output = output.substring(0,output.indexOf("subroutineDec")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<keyword>" + tokenAll.get(i+1) + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+2) + "</identifier>" + "\n" + "<symbol> ( </symbol>" + "\n" + "paramenterList" + "\n" + "<symbol> ) </symbol>" + "\n" + "subroutineBody" + "\n" + output.substring(output.indexOf("subroutineDec"));
                }
            }else if(tokenAll.get(i).equals("var")) {
                if(tokenAll.get(i+3).equals(",")) {
                    output = output.substring(0,output.indexOf("varDec")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<keyword>" + "type" + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+2) + "</identifier>" + "\n" + "<symbol> , </symbol>" + "\n" + "<identifier>" + tokenAll.get(i+4) + "</identifier>" + "\n" + "<symbol> ; </symbol>"+ "\n" + output.substring(output.indexOf("varDec"));
                }else {
                    output = output.substring(0,output.indexOf("varDec")) + "<keyword>" + tokenAll.get(i) + "</keyword>" + "\n" + "<keyword>" + "type" + "</keyword>" + "\n" + "<identifier>" + tokenAll.get(i+2) + "</identifier>" + "\n" + "<symbol> ; </symbol>" + "\n" + output.substring(output.indexOf("varDec"));
                }
            }else if(tokenAll.get(i-1).equals(")") && tokenAll.get(i).equals("{")) {
                output = output.substring(0,output.indexOf("subroutineBody")) + "<symbol> { </symbol>" + "\n" + "varDec" + "\n" + "statements" + "\n" + "<symbol> } </symbol>" + "\n" + output.substring(output.indexOf("subroutineBody")+15);
            }
            System.out.println(tokenAll.get(i));
        }
        
        System.out.println(output);

        br.close();
        fw.close();
    }
}
