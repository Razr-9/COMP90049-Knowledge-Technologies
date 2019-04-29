import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
/**
 * exactMatch
 */
public class exactMatch {

    public static void main(String[] args) throws IOException{
        ArrayList<String> a = read("source/misspell_typos.txt");
        ArrayList<String> b = read("source/correct_typos.txt");

        String notMatchInMis = "";
        String notMatchInCorr = "";
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                notMatchInMis = notMatchInMis + a.get(i) + "\n"; 
                notMatchInCorr = notMatchInCorr + b.get(i) + "\n"; 
                count++;
            }
        }
        notMatchInMis = notMatchInMis + "notMatchNumber: " + count + "\n";
        File outPutMis = new File("result/notMatchInMis_typos.txt");
        FileWriter fw = new FileWriter(outPutMis);
        fw.write(notMatchInMis);
        fw.close();

        File outPutCorr = new File("result/notMatchInCorr_typos.txt");
        FileWriter fw_ = new FileWriter(outPutCorr);
        fw_.write(notMatchInCorr);
        fw_.close();
    }

    public static ArrayList<String> read(String path) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            File file = new File(path);
            java.util.Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                arrayList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}