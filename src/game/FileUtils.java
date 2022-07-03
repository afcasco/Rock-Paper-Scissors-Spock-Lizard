package game;

import java.io.File;
import java.util.Scanner;


public final class FileUtils {

    public static final int FILE_NAME = 0;
    public static final int FILE_SIZE = 1;
    private static final String BASE_FOLDER = "data";
    private static final String GAMES_FOLDER = BASE_FOLDER + File.separator + "games";
    private static final String PLAYER_LIST = BASE_FOLDER + File.separator + "players.txt";

    private FileUtils() {
    }
    // redundant and not needed
/*    static void inicialitza() {
        File file = new File(BASE_FOLDER);
        System.out.println("Data folder set to: " + file);
        if (!file.exists()) {
            System.out.println("Data not found, creating data folder...");
            file.mkdir();
        } else {
            System.out.println("Data folder found!");
        }
        File games = new File(GAMES_FOLDER);
        if (!games.exists()) {
            System.out.println("Creating games folder");
            games.mkdir();
        } else {
            System.out.println("Previous game data found!");
        }
    }*/

    static String[][] getGameFiles() {
        File gamesFolder = new File(GAMES_FOLDER);
        File[] filesInfolder = gamesFolder.listFiles();
        if(filesInfolder!=null){
            String[][] logFiles = new String[filesInfolder.length][2];

            // Loop a tot el contingut del directori
            for (int i = 0; i < filesInfolder.length; i++) {
                long mida = filesInfolder[i].length();
                logFiles[i][FILE_NAME] = filesInfolder[i].getName();
                logFiles[i][FILE_SIZE] = Long.toString(mida);
            }

            return logFiles;
        } else {
            return null;
        }

    }

    /**
     * @param dadesJugadors array 40*2 first field player name, second fiel number of wins
     *                      returns array with players loaded from PLAYER_LIST file (do nothing
     *                      if it can't find the file to load from)
     */
    static void loadPlayers(String[][] dadesJugadors) {
        File players = new File(PLAYER_LIST);
        if (players.exists()) {
            try (Scanner input = new Scanner(players)) {
                String temp;
                int pos = 0;
                boolean stop = false;
                while (input.hasNextLine() && !stop) {
                    temp = input.nextLine();
                    if (!temp.equals(",")) {
                        dadesJugadors[pos] = temp.split(",");
                    } else {
                        stop = true;
                    }
                    pos++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void guardarPartidaEnHistoric(String nomJugador, int numJugades, int resultat) {


    }

    static int[][] getHistoricJugador(String nomJugador) {
        return new int[0][0];

    }
}
