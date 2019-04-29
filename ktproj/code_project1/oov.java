/**
 * oov
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
public class oov {

    public static void main(String[] args) throws IOException{
        ArrayList<String> misspell = read("oov");
        // ArrayList<String> dict = read("dict.txt");
        ArrayList<String> correct= read("new_correct");

        ArrayList<String> oov_final = new ArrayList<String>();
        ArrayList<String> corr_final = new ArrayList<String>();
        ArrayList<String> no = new ArrayList<String>();
        ArrayList<String> corr_no = new ArrayList<String>();

        String alphs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < misspell.size(); i++) {
            boolean allAlphs = true;
            for (int j = 0; j < misspell.get(i).length(); j++) {
                if (!alphs.contains(misspell.get(i).subSequence(j, j+1))) {
                    allAlphs = false;
                    break;
                }
            }
            if (allAlphs) {
                oov_final.add(misspell.get(i));
                corr_final.add(correct.get(i));
            }else{
                no.add(misspell.get(i));
                corr_no.add(correct.get(i));
            }
        }
        String output = "";
        String output_ = "";
        String no_ = "";
        String correct_no = "";
        for (int i = 0; i < oov_final.size(); i++) {
            output = output + oov_final.get(i) + "\n";
            output_ = output_ + corr_final.get(i) + "\n";
        }
        for (int i = 0; i < no.size(); i++) {
            no_ = no_ + no.get(i) + "\n";
            correct_no = correct_no + corr_no.get(i) + "\n";
        }
        File outPutFile = new File("oov_final.txt");
        FileWriter fw = new FileWriter(outPutFile);
        fw.write(output);
        fw.close();

        File outPutFile_ = new File("correct_final.txt");
        FileWriter fw_ = new FileWriter(outPutFile_);
        fw_.write(output_);
        fw_.close();

        File no_File = new File("misspell_typos.txt");
        FileWriter fw_no = new FileWriter(no_File);
        fw_no.write(no_);
        fw_no.close();
        
        File corr_no_File = new File("correct_typos.txt");
        FileWriter fw_corr_no = new FileWriter(corr_no_File);
        fw_corr_no.write(correct_no);
        fw_corr_no.close();
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