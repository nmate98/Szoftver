package cyk;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Algorithm testing whether a word containing terminal letters can be created with the given grammar.
 */
public class CYK_Classes {
    /**
     * Logger object.
     */
    private static Logger logger = LoggerFactory.getLogger(CYK_Classes.class);

    /**
     * Returns the Cartesian-product of {@code bal} and {@code jobb}.
     *
     * @param bal  Left side of the Cartesian-product
     * @param jobb Right side of the Cartesian-product
     * @return the Cartesian-product of {@code bal} and {@code jobb}
     */
    public static String[] komb(String bal, String jobb) {
        String[] bo = bal.substring(1, bal.length() - 1).split(",");
        String[] jo = jobb.substring(1, jobb.length() - 1).split(",");
        ArrayList<String> köztes = new ArrayList<>();
        for (int i = 0; i < bo.length; i++) {
            for (int j = 0; j < jo.length; j++) {
                köztes.add((bo[i] + jo[j]).replace(" ", ""));
            }
        }
        String[] szorzat = köztes.toArray(new String[köztes.size()]);
        return szorzat;
}

    /**
     * Method to check whether the source json file is existing in the correct place.
     */
    public static void createDirectory(){
        if (!new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk").exists()) {
            new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk").mkdir();
            try {
                FileWriter writer = new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk" + File.separator + "szavak.json");
                Gson gson = new Gson();
                String[] adat = {};
                gson.toJson(adat, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Returns a two-dimensional array of the grammar read from the user interface.
     *
     * @param szab grammar read from the interface
     * @return a two-dimensional array of the grammar read from the user interface
     */
    public static String[][] szabalybeolvas(String szab) {
        String[] sor = szab.split("\n");
        String[][] szabaly = new String[sor.length][2];
        for (int i = 0; i < sor.length; i++) {
            szabaly[i] = sor[i].split("-");
        }
        logger.trace("Szabalyok beolvasva");
        return szabaly;
    }

    /**
     * Creates the two-dimensional array for the analysis, and fills the first lane.
     *
     * @param szo     the word, which we currently analyse
     * @param szabaly the grammar used for the test
     * @return Creates the two-dimensional array for the analysis, and fills the first lane
     */
    public static String[][] elsofeltolt(String szo, String[][] szabaly) {
        String[][] vizsgal = new String[szo.length()][szo.length()];
        for (int i = 0; i < szo.length(); i++) {
            Set<Character> előford = new HashSet<>();
            for (int j = 0; j < szabaly.length; j++) {
                String[] sor = szabaly[j][1].split("\\|");
                for (String sor1 : sor) {
                    if (sor1.charAt(0) == szo.charAt(i)) {
                        előford.add(szabaly[j][0].charAt(0));
                    }
                }
                vizsgal[0][i] = előford.toString();
            }
        }
        logger.trace("Tablazat elso sora kitoltve");
        return vizsgal;
    }

    /**
     * Analyses the word and fills the two-dimensional array given as parameter with the result.
     *
     * @param vizsg   two-dimensional matrix for the result
     * @param szo     the word, which we currently analyse
     * @param szabaly the grammar used for the test
     * @return Analyses the word and fills the two-dimensional array given as parameter with the result
     */
    public static String[][] feltolt(String[][] vizsg, String szo, String[][] szabaly) {
        for (int i = 1; i < szo.length(); i++) {
            for (int j = 0; j < szo.length() - i; j++) {
                Set<Character> előford = new HashSet<>();
                for (int k = 0, l = i - 1, m = j + 1; k <= i - 1; k++, l--, m++) {
                    String[] sor = komb(vizsg[k][j], vizsg[l][m]);
                    for (int x = 0; x < szabaly.length; x++) {
                        String[] szabsor = szabaly[x][1].split("\\|");
                        for (String szab1 : szabsor) {
                            for (String szab2 : sor) {
                                if (szab1.equals(szab2) && (szab2.length() == 2)) {
                                    előford.add(szabaly[x][0].charAt(0));
                                }
                            }
                        }
                    }
                }
                vizsg[i][j] = előford.toString();
            }
        }
        logger.trace("Tablazat kitoltve");
        return vizsg;
    }

    /**
     * Tests the grammar given as parameter for the words found in the json file.
     *
     * @param gr Grammar which will be tested
     * @return Tests the grammar given as parameter for the words found in the json file
     * @throws FileNotFoundException if the JSON file does not exist
     */
    public static String[] CheckGrammar(String gr) throws FileNotFoundException {
        String[][] szab = szabalybeolvas(gr);
        Gson gson = new Gson();
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk" + File.separator + "szavak.json";
        Reader reader = new InputStreamReader(new FileInputStream(path));
        String[] szavak = gson.fromJson(reader, String[].class);
        String[] eredmeny = new String[szavak.length + 1];
        int helyes = 0;
        for (int i = 0; i < szavak.length; i++) {
            String[][] vizsg = {};
            vizsg = elsofeltolt(szavak[i], szab);
            vizsg = feltolt(vizsg, szavak[i], szab);
            for (int x = 0; x < vizsg.length; x++) {
            }
            if (vizsg[szavak[i].length() - 1][0].contains("S")) {
                eredmeny[i + 1] = szavak[i] + ": előállítható!\n";
                helyes++;
            } else {
                eredmeny[i + 1] = szavak[i] + ": nem előállítható!\n";
            }
        }
        eredmeny[0] = "Pontosság: " + ((double) (helyes) / (double) (szavak.length) * 100) + "%\n";
        logger.trace("Nyelvtan ellenorizve");
        return eredmeny;
    }

    /**
     * Analyses the word whether it can be created with the given grammar, and returns the analysing matrix.
     *
     * @param gr   Grammar
     * @param word word
     * @return Analyses the word whether it can be created with the given grammar, and returns the analysing matrix
     */
    public static String[][] CheckWord(String gr, String word) {
        String[][] szab = szabalybeolvas(gr);
        String[][] vizsg = elsofeltolt(word, szab);
        vizsg = feltolt(vizsg, word, szab);
        for (int i = 0; i < vizsg.length; i++) {
            for (int j = 0; j < vizsg.length; j++) {
                if (vizsg[i][j] == null) {
                    vizsg[i][j] = "";
                }
            }
        }
        logger.trace("Elemző táblázat kiírva!");
        return vizsg;
    }
    public static String WriteToJson(String be) throws FileNotFoundException {
        Gson gson = new Gson();
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk" + File.separator + "szavak.json";
        Reader reader = new InputStreamReader(new FileInputStream(path));
        String[] szavak = gson.fromJson(reader, String[].class);
        boolean volt = false;
        for(String szo : szavak){
            if(szo.equals(be)){
                volt = true;
            }
        }
        if(!volt) {
            new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk").mkdir();
            try {
                String[] uj = Arrays.copyOf(szavak,szavak.length+1);
                uj[uj.length-1]=be;
                FileWriter writer = new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "cyk" + File.separator + "szavak.json");
                gson.toJson(uj, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("Sikeres fájlbaírás!");
            return "Sikeres";
        }
        else{
            logger.info("Sikertelen fájlbaírás!");
            return "Volt már ilyen szó!";
        }
    }
}

