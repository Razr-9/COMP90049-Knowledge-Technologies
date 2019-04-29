/**
 * whiteList
 */
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
// package ktproj;
// import ktproj.java;
public class whiteList {

    public static void main(String[] args) throws IOException{
        ArrayList<String> misspell = read("misspell.txt");
        ArrayList<String> dict = read("dict.txt");
        ArrayList<String> correct= read("correct.txt");

        ArrayList<String> oov = new ArrayList<String>();
        ArrayList<String> corr_ = new ArrayList<String>();
        ArrayList<String> spellRight = new ArrayList<String>();
        ArrayList<String> corr_spellRight = new ArrayList<String>();
        
        for (int i = 0; i < misspell.size(); i++) {
            boolean match = false;
            for (int j = 0; j < dict.size(); j++) {
                if (misspell.get(i).equals(dict.get(j))) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                oov.add(misspell.get(i));
                corr_.add(correct.get(i));
            }else{
                spellRight.add(misspell.get(i));
                corr_spellRight.add(correct.get(i));
            }
        }
        String output = "";
        String output_ = "";
        String sR = "";
        String corr_sR = "";
        for (int i = 0; i < oov.size(); i++) {
            output = output + oov.get(i) + "\n";
            output_ = output_ + corr_.get(i) + "\n";
        }
        for (int i = 0; i < spellRight.size(); i++) {
            sR = sR + spellRight.get(i) + "\n";
            corr_sR = corr_sR + corr_spellRight.get(i) + "\n";
        }
        File outPutFile = new File("oov");
        FileWriter fw = new FileWriter(outPutFile);
        fw.write(output);
        fw.close();

        File outPutFile_ = new File("new_correct");
        FileWriter fw_ = new FileWriter(outPutFile_);
        fw_.write(output_);
        fw_.close();

        File mis_spellRight = new File("mis_spellRight.txt");
        FileWriter fw_msR = new FileWriter(mis_spellRight);
        fw_msR.write(sR);
        fw_msR.close();

        File correct_spellRight = new File("correct_spellRight.txt");
        FileWriter fw_csR = new FileWriter(correct_spellRight);
        fw_csR.write(corr_sR);
        fw_csR.close();
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