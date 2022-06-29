package game;

import java.io.File;


public final class FileUtils {
    private FileUtils(){}

    static void inicialitza(){
        File file = new File("data");
        System.out.println("file directori is" +file);
        if(!file.exists()){
            System.out.println("Data not found, creating data folder...");
            boolean createDir = file.mkdir();
        } else {
            System.out.println("el directori existeix");
        }
    }
}
