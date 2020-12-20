import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(args[0].replace(".vm", ".asm"));
        String line = null;
        String arg01 = null;
        int arg02 = 0;
        SymbolTable ram = new SymbolTable();

        while((line = br.readLine()) != null) {
            
            System.out.println("===============");

            // String line2 = line.replace(" ", "");
            String line2 = line.replaceAll("//.*", "");

            if(Parser.hasMoreCommand(line2) == false) {
                continue;
            }

            String command = Parser.command(line2);
            String commandType = Parser.commandType(line2);

            if(!(commandType.equals("C_RETURN"))) {
                arg01 = Parser.arg1(line2);
                // System.out.println(arg01);
            }

            if((commandType.equals("C_PUSH"))|(commandType.equals("C_POP"))|(commandType.equals("C_FUNCTION"))|(commandType.equals("C_CALL"))) {
                arg02 = Integer.parseInt(Parser.arg2(line2));
                // System.out.println(arg02);
            }

            // ram.addEntry("256", "10");
            // ram.addEntry("257", "5");
            // ram.replace("SP", "258");

            String line3 = null;

            if(commandType.equals("C_ARITHMENTIC")) {
                line3 = ram.writeArithmetic(line2);
            }else if(commandType.equals("C_PUSH")) {
                line3 = ram.writePush(command, arg01, arg02);
            }else if(commandType.equals("C_POP")) {
                line3 = ram.writePop(command, arg01, arg02);
            }

            // System.out.println(line3);
            // System.out.println(commandType);
            // System.out.println(ram.getAddress("SP"));
            // System.out.println(ram.getAddress("256"));
            // System.out.println(ram.getAddress("257"));
            // System.out.println(ram.getAddress("258"));
            // System.out.println(ram.getAddress("6"));
            System.out.println("===============");

            fw.write(line3 + "\n");
            fw.write("\n");
        }
        fw.write("(END)");
        fw.write("\n");
        fw.write("@end");
        fw.write("\n");
        fw.write("0;JMP");

        br.close();
        fw.close();
    }

}