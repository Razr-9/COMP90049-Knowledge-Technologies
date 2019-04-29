// package ktproj;
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
/**
 * ktproj
 */
public class ktproj {

    public static int M = 1;
    public static int I = -1;
    public static int D = -1;
    public static int R = -1;
    public static void main(String[] args) throws IOException {
        ArrayList<String> correct = read("source/test_correct.txt");
        ArrayList<String> mis = read("source/test_mis.txt");
        ArrayList<String> dict = read("source/dict.txt");
        
        String misMatch = "";
        String returned = "";
        String misMatchInCorrect = "";

        long begin = System.currentTimeMillis();
        ArrayList<ArrayList> corr = calGED(mis, dict);// enhanced GED
        // ArrayList<String> corr = calGED(mis, dict);//basic GED
        long end = System.currentTimeMillis();

        int returnNumer = 0; // returned number
        int correctNum = 0; // matched number
        int total = mis.size(); // total number
        //enhanced GED
        for (int i = 0; i < total; i++) {
            int tem = -1;
            boolean match = false;
            
            returnNumer = returnNumer + (corr.get(i)).size();

            for (int j = 0; j < (corr.get(i)).size(); j++) {
                if(correct.get(i).equals((corr.get(i)).get(j))){
                    correctNum ++;
                    match = true;
                    
                }else {
                    tem = i;
                }
            }
            if (!match && tem > 0) {
                misMatch = misMatch + mis.get(i) + "\n";
                misMatchInCorrect = misMatchInCorrect + correct.get(i) + "\n";
            }
        
        }

        // basic GED
        // for (int i = 0; i < total; i++) {
        //         if(correct.get(i).equals(corr.get(i))){
        //             correctNum ++;
        //         }else{
        //             misMatch = misMatch + mis.get(i) + "\n";
        //             misMatchInCorrect = misMatchInCorrect + correct.get(i) + "\n";
        //         }
        // }
        // returnNumer = corr.size();
    
        // for (int i = 0; i < corr.size(); i++) {
        //     returned = returned + corr.get(i) + "\n";
        // }
        System.out.println("Finished");
        for (int i = 0; i < corr.size(); i++) {
            System.out.println(corr.get(i) + "\n");
        }

        String output = "MisMatch: " + misMatch + "\r\n" + "misMatchInCorrect: " + misMatchInCorrect + "\r\n" + "Returned" + returned + "\r\n" + "Matched: " + correctNum + "\n" + "total correct: " + total + "\n" + "total returned: " + returnNumer + "\n" + "Precision: " + (float)correctNum / returnNumer + "\n" + "Recall: " + (float)correctNum / total + "\n" + "time cost: " + (end - begin) + "ms";
        
        SimpleDateFormat sdf = new SimpleDateFormat();// format time
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a means am/pm 
        Date date = new Date();// current time

        File outPutFile = new File("result/GED_basic_test_" + sdf.format(date));
        FileWriter fw = new FileWriter(outPutFile);
        fw.write(output);
        fw.close();
    }

    public static int GED(String target, String misspell) {
        int lq = target.length();
        int lt = misspell.length();
        int[][] F = new int[lq+1][lt+1];
        for(int i=0 ; i<=lq ; i++ ) {
            F[i][0] = i*I;
        }
        for(int j=0 ; j<=lt ; j++ ) {
            F[0][j] = j*D;
        }
        for(int i=1 ; i<=lq ; i++ ){
            for(int j=1 ; j<=lt ; j++){
                int a = F[i-1][j] + I; // insertion
                int b = F[i][j-1] + D; // deletion
                int c = F[i-1][j-1] + (target.charAt(i-1) == misspell.charAt(j-1) ? M : R);
                F[i][j] = Math.max(Math.max(a, b), c);
                
            }
        }
        return F[lq][lt];   
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
    // Soundex + GED
    // public static ArrayList<ArrayList> calGED(ArrayList<String> mis, ArrayList<String> dict) {
    //     ArrayList<ArrayList> corr = new ArrayList<ArrayList>();
    //     for(int i = 0; i < mis.size(); i++){
    //         int max = Integer.MIN_VALUE;
    //         String str = null;
    //         ArrayList<String> pickPool = new ArrayList<String>();

    //         for (int j = 0; j < dict.size(); j++) {
    //             if (max < GED(soundex(dict.get(j)) , soundex(mis.get(i)))) {
    //                 max = GED(soundex(dict.get(j)) , soundex(mis.get(i)));
    //                 // str = dict.get(j);
    //             }
    //         }
    //         for (int j = 0; j < dict.size(); j++) {
                
    //             if (max == GED(soundex(dict.get(j)) , soundex(mis.get(i)))) {
    //                 pickPool.add(dict.get(j)); 
    //             }
    //         }

    //         corr.add(pickPool);
    //      }
         
    //      return corr;
    // }

    //enhanced GED
    public static ArrayList<ArrayList> calGED(ArrayList<String> mis, ArrayList<String> dict) {
        ArrayList<ArrayList> corr = new ArrayList<ArrayList>();
        for(int i = 0; i < mis.size(); i++){
            int max = Integer.MIN_VALUE;
            // String str = null;
            ArrayList<String> pickPool = new ArrayList<String>();

            for (int j = 0; j < dict.size(); j++) {
                if (max < GED(mis.get(i) , dict.get(j))) {
                    max = GED(mis.get(i) , dict.get(j));
                    // str = dict.get(j);
                }
            }
            for (int j = 0; j < dict.size(); j++) {
                
                if (max == GED(mis.get(i) , dict.get(j))) {
                    pickPool.add(dict.get(j)); 
                }
            }

            corr.add(pickPool);
         }
         
         return corr;
    }

    //basic GED
    // public static ArrayList<String> calGED(ArrayList<String> mis, ArrayList<String> dict) {
    //     ArrayList<String> corr = new ArrayList<String>();
    //     for(int i = 0; i < mis.size(); i++){
    //         int max = Integer.MIN_VALUE;
    //         String str = null;
    //         // ArrayList<String> pickPool = new ArrayList<String>();

    //         for (int j = 0; j < dict.size(); j++) {
    //             if (max < GED(dict.get(j) , mis.get(i))) {
    //                 max = GED(dict.get(j) , mis.get(i));
    //                 str = dict.get(j);
    //             }
    //         }
    //         corr.add(str);
    //      }
         
    //      return corr;
    // }



    
    /* 
        Soundex block 
    */

    public static String soundex(String s) {
        char[] x = s.toUpperCase().toCharArray();
        char firstLetter = x[0];

        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {

                case 'B':
                case 'F':
                case 'P':
                case 'V':
                    x[i] = '1';
                    break;

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z':
                    x[i] = '2';
                    break;

                case 'D':
                case 'T':
                    x[i] = '3';
                    break;

                case 'L':
                    x[i] = '4';
                    break;

                case 'M':
                case 'N':
                    x[i] = '5';
                    break;

                case 'R':
                    x[i] = '6';
                    break;

                default:
                    x[i] = '0';
                    break;
            }
        }

        String output = "" + firstLetter;
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i-1] && x[i] != '0')
                output += x[i];

        output = output + "0000";
        return output.substring(0, 4);
    }


}