package game;

import java.io.File;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Scanner;


public final class FileUtils {

    public static final int FILE_NAME = 0;
    public static final int FILE_SIZE = 1;
    private static final String BASE_FOLDER = "data";
    private static final String GAMES_FOLDER = BASE_FOLDER + File.separator + "games";
    private static final String PLAYER_LIST = BASE_FOLDER + File.separator + "players.txt";

    private FileUtils() {
    }

    // Create data and games folders if not found in working directory
    static void inicialitza() {
        File file = new File(BASE_FOLDER);
        if (!file.exists()) file.mkdir();
        File games = new File(GAMES_FOLDER);
        if (!games.exists()) games.mkdir();
    }

    static String[][] getGameFiles() {
        File gamesFolder = new File(GAMES_FOLDER);
        String[][] logFiles = null;
        File[] filesInfolder = gamesFolder.listFiles();
        if (filesInfolder != null) {
            logFiles = new String[filesInfolder.length][2];
            // Loop a tot el contingut del directori
            for (int i = 0; i < filesInfolder.length; i++) {
                long mida = filesInfolder[i].length();
                logFiles[i][FILE_NAME] = filesInfolder[i].getName();
                logFiles[i][FILE_SIZE] = Long.toString(mida);
            }
        }
        return logFiles;
    }

    /**
     * array 40*2 first field player name, second fiel number of wins
     * returns array with players loaded from PLAYER_LIST file (do nothing
     * if it can't find the file to load from)
     */
    static String[][] loadPlayers() {

        File players = new File(PLAYER_LIST);
        if (players.exists()) {
            String[][] dadesJugadors = new String[40][2];
            try (Scanner input = new Scanner(players)) {
                String temp;
                int pos = 0;
                while (input.hasNextLine()) {
                    temp = input.nextLine();
                    if (!temp.equals(",")) {
                        dadesJugadors[pos] = temp.split(",");
                    } else {
                        dadesJugadors[pos][0] = "";
                        dadesJugadors[pos][1] = "";
                    }
                    pos++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dadesJugadors;
        } else {
            return null;
        }


    }

    static void guardarPartidaEnHistoric(String nomJugador, int numJugades, int resultat) {
        try {
            File ruta = new File(GAMES_FOLDER + File.separator + nomJugador + ".log");
            RandomAccessFile raf = new RandomAccessFile(ruta, "rw");
            raf.seek(raf.length());
            raf.writeInt(numJugades);
            raf.writeInt(resultat);
        } catch (Exception e) {
            System.out.println("NO GAME FILE TO LOAD");
        }
    }

    public static int[][] getPlayerHistory(String nomJugador) {

        int rows;
        int[][] resultat = null;

        try {
            File ruta = new File(GAMES_FOLDER + File.separator + nomJugador + ".log");
            RandomAccessFile raf = new RandomAccessFile(ruta, "r");
            rows = (int) (raf.length() / 8);
            resultat = new int[rows][2];
            int pos = 0;
            while (pos < rows) {
                resultat[pos][0] = raf.readInt();
                resultat[pos][1] = raf.readInt();
                pos++;
            }
        } catch (Exception e) {
            System.out.println("ERROR: ");
        }
        return resultat;
    }

    static void savePlayers(String[][] players) {
        File fitxersJugadors = new File(PLAYER_LIST);
        try {
            PrintStream writer = new PrintStream(fitxersJugadors);
            for (String[] player : players) {
                writer.println(player[0] + "," + player[1]);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
