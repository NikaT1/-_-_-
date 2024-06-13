package tpo.lab;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVPrinter printer = null;
        Path funcPath = Path.of("src/test/resources/outData/Func.csv");
        try {
            printer = CSVFormat.DEFAULT.builder().setHeader(new String[]{"X", "Результаты модуля (X)"}).build().print(funcPath, java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        Func func  = new Func();
        for (double x = -10; x <= 20; x += 0.1) {
            func.calc(x, printer);
        }
        printer.close();
    }
}
