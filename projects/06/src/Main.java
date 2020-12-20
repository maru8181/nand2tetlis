import java.io.*;
import java.util.ArrayList;
import java.util.List;
// import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        // String dir_path = "/Users/mil575045.shuichi-marutani/Desktop/nand2tetris/projects/06";
        // String extension = ".asm";   
        // for(String f: file_search(dir_path, extension)) {
        //     System.out.println(f);
        // }

        FileReader fr = new FileReader("../pong/PongL.asm");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("../pong/PongL.hack");
        String line = null;
        String binary;
        List<String> commands = new ArrayList<>();
        SymbolTable ram = new SymbolTable();
        SymbolTable rom = new SymbolTable();


        long ramCount = 16;
        long romCount = 0;

        BufferedReader br2 = new BufferedReader(new FileReader("../pong/PongL.asm"));
        String line0 = null;

        while((line0 = br2.readLine()) != null) {
            String line2 = line0.replace(" ", "");
            String line3 = line2.replace("　", "");
            String line4 = line3.replaceAll("//.*", "");

            String b = Parser.commandType(line4);

            System.out.println("========");

            if(b.equals("A_COMMAND")) {
                // if(ram.contains(line4)) {
                //     ram.addEntry(line4, ram.getAddress(line4));
                // }else {
                    rom.addEntry(line4, String.valueOf(romCount));
                    romCount++;
                // }
            }

            if(b.equals("C_COMMAND")) {
                rom.addEntry(line4, String.valueOf(romCount));
                romCount++;
            }

            if(b.equals("L_COMMAND")) {
                rom.addEntry(line4, String.valueOf(romCount));
            }
            
            String li = rom.getAddress(line4);
            System.out.println(b);
            System.out.println(line4);
            System.out.println(li);
        }
        
        br2.close();

        
        while((line = br.readLine()) != null) {
            String line2 = line.replace(" ", "");
            String line3 = line2.replace("　", "");
            String line4 = line3.replaceAll("//.*", "");

            Boolean a = Parser.hasMoreCommand(line4);
            String b = Parser.commandType(line4);

            System.out.println("========");

            if(b.equals("A_COMMAND")) {
                String c = Parser.symbol(line4);

                if(c.matches("[0-9]*")) {
                    long ci = Long.parseLong(c);

                    if(ci > 0) {
                        long i = nisinsuu(ci);
                        String si = String.valueOf(i);
                        System.out.println(si);
                        commands.add(si);
                    }else if(ci == 0) {
                        long i0 = 0;
                        String si0 = String.valueOf(i0);
                        System.out.println(si0);
                        commands.add(si0);
                    }
                }else {
                    if(rom.contains(sinbolu(line4))) {
                        long c1 = Long.parseLong(rom.getAddress(sinbolu(line4)));
                        System.out.println(rom.getAddress(line4));
                        long i = nisinsuu(c1);
                        String si = String.valueOf(i);
                        System.out.println(i);
                        commands.add(si);
                    }else if(ram.contains(line4)) {
                        long c2 = Long.parseLong(ram.getAddress(line4));
                        System.out.println(ram.getAddress(line4));
                        long i2 = nisinsuu(c2);
                        String si2 = String.valueOf(i2);
                        System.out.println(i2);
                        commands.add(si2);
                    }else {
                        ram.addEntry(line4, String.valueOf(ramCount));
                        long ad = nisinsuu(ramCount);
                        String si = String.valueOf(ad);
                        System.out.println(ad);
                        commands.add(si);
                        ramCount++;
                    }
                }
            }

            if(b.equals("C_COMMAND")) {
                String d = Parser.dest(line4);
                String e = Parser.jump(line4);
                String f = Parser.comp(line4);
                System.out.println("dest");
                System.out.println(d);
                System.out.println("jump");
                System.out.println(e);
                System.out.println("comp");
                System.out.println(f);

                String destbit = Code.dest(d);
                String jumpbit = Code.jump(e);
                String compbit = Code.comp(f);
                
                System.out.println("destbit");
                System.out.println(destbit);
                System.out.println("jumpbit");
                System.out.println(jumpbit);
                System.out.println("compbit");
                System.out.println(compbit);

                binary = "111" + compbit + destbit + jumpbit;
                System.out.println(binary);
                
                commands.add(binary);

            }

            System.out.println(line4);
            System.out.println(a);
            System.out.println(b);
            System.out.println("========");
            
        }
        
        System.out.println(commands);

        for(String c: commands) {
            String c2 = juurokuketa(c);
            fw.write(c2);
            fw.write("\n");
        }

        br.close();
        fw.close();

    }

    public static long nisinsuu(long juu) {
        String numstr = "";
        if(juu == 0) {
            numstr = "0";
        }

        while (juu > 0) {
            long rem = juu % 2;
            if (rem == 1) {
                numstr = "1" + numstr;
            } else {
                numstr = "0" + numstr;
            }
            juu = juu / 2;
        }

        long i = Long.parseLong(numstr);
        return i;
    }

    public static String juurokuketa(String suuzi) {

        long a = suuzi.length();
        long b = 16 - a;
        String c = "";

        for(long i=0; i<b; i++) {
            c += "0";
        }
        return c+suuzi;
    }

    public static String sinbolu(String attomaku) {
        String str1 = attomaku.substring(1);
        String str2 = "(" + str1 + ")";
        return str2;
    }

    // public static List<String> file_search(String path, String extension){

    //     List<String> fileName = new ArrayList<>();

    //     File dir = new File(path);
    //     File files[] = dir.listFiles();
    //     for(int i=0; i<files.length; i++){
    //         String file_name = files[i].getName();
    //         if(files[i].isDirectory()){
    //             file_search(path+"/"+file_name, extension);
    //         }else{
    //             if(file_name.endsWith(extension)){
    //                 fileName.add(path+"/"+file_name);
    //             }
    //         }
    //     }
    //     return fileName;
    // }
}
