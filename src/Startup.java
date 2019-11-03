import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Startup {
    public static void main(String[] args) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",  args[0]);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        //TimeUnit.MICROSECONDS.sleep(30); 
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}
