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
        ArrayList<String> correct = read("correct.txt");
        ArrayList<String> mis = read("test.txt");
        ArrayList<String> dict = read("dict.txt");
        
        ArrayList<String> misMatch = new ArrayList<String>();

        // for (int i = 0; i < mis.size(); i++) {
        //     sound.add(Soundex(mis.get(i)));
        // }
        // for (int i = 0; i < dict.size(); i++) {
        //     soundDict.add(Soundex(dict.get(i)));
        // }
        // System.out.println(soundDict);
        long begin = System.currentTimeMillis();
        ArrayList<ArrayList> corr = calGED(mis, dict);
        long end = System.currentTimeMillis();

        int returnNumer = 0; // returned number
        int correctNum = 0; // matched number
        int total = mis.size(); // total number
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
                misMatch.add(mis.get(tem));
            }
        
        }
        // for (int i = 0; i < corr.size(); i++) { 
        //     returnNumer = returnNumer + (corr.get(i)).size();
        // }

        System.out.println("Finished");

        String output = "MisMatch: " + misMatch + "\n" + "Matched: " + correctNum + "\n" + "total correct: " + total + "\n" + "total returned: " + returnNumer + "\n" + "Precision: " + (float)correctNum / returnNumer + "\n" + "Recall: " + (float)correctNum / total + "\n" + "time cost: " + (end - begin) + "ms";
        
        SimpleDateFormat sdf = new SimpleDateFormat();// format time
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a means am/pm 
        Date date = new Date();// current time

        File outPutFile = new File("output_" + sdf.format(date));
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

    // public static String Soundex(String input) {
    //     for (int i = 1; i < input.length(); i++) {
    //         if ("aehiouwy".contains(input.charAt(i))) {
                
    //         }
    //     }
    //     char s = input.charAt(0);
    //     String output = input.replace('a', '1');
    //     return output;
    // }
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

    // public static ArrayList<ArrayList> calGED(ArrayList<String> mis, ArrayList<String> dict) {
    //     ArrayList<ArrayList> corr = new ArrayList<ArrayList>();
    //     for(int i = 0; i < mis.size(); i++){
    //         int max = Integer.MIN_VALUE;
    //         String str = null;
    //         ArrayList<String> pickPool = new ArrayList<String>();

    //         for (int j = 0; j < dict.size(); j++) {
    //             if (max < GED(Soundex(dict.get(j)) , Soundex(mis.get(i)))) {
    //                 max = GED(Soundex(dict.get(j)) , Soundex(mis.get(i)));
    //                 // str = dict.get(j);
    //             }
    //         }
    //         for (int j = 0; j < dict.size(); j++) {
                
    //             if (max == GED(Soundex(dict.get(j)) , Soundex(mis.get(i)))) {
    //                 pickPool.add(dict.get(j)); 
    //             }
    //         }

    //         corr.add(pickPool);
    //      }
         
    //      return corr;
    // }

    public static ArrayList<ArrayList> calGED(ArrayList<String> mis, ArrayList<String> dict) {
        ArrayList<ArrayList> corr = new ArrayList<ArrayList>();
        for(int i = 0; i < mis.size(); i++){
            int max = Integer.MIN_VALUE;
            String str = null;
            ArrayList<String> pickPool = new ArrayList<String>();

            for (int j = 0; j < dict.size(); j++) {
                if (max < GED(dict.get(j) , mis.get(i))) {
                    max = GED(dict.get(j) , mis.get(i));
                    // str = dict.get(j);
                }
            }
            for (int j = 0; j < dict.size(); j++) {
                
                if (max == GED(dict.get(j) , mis.get(i))) {
                    pickPool.add(dict.get(j)); 
                }
            }

            corr.add(pickPool);
         }
         
         return corr;
    }



    //Soundex block

    // public static String removeCharAt(String s, int pos) {
    //     return s.substring(0, pos) + s.substring(pos + 1);
    //  }
    //  public static String removeRepeatChar(String s) {
    //     if (s == null) {
    //         return "";
    //     }
    //     StringBuffer sb = new StringBuffer();
    //     int i = 0;
    //     int len = s.length();
    //     while (i < len) {
    //         char c = s.charAt(i);
    //         sb.append(c);
    //         i++;
    //         while (i < len && s.charAt(i) == c) {
    //             i++;
    //         }
    //     }
    //     return sb.toString();
    // }
    // public static String Soundex(String input) {

    //     String output = input.substring(1, input.length());
    //     char[] zero = "aehiouwyAEHIOUWY".toCharArray();
    //     char[] one = "bfpvBFPV".toCharArray();
    //     char[] two = "cgjkqsxzCGJKQSXZ".toCharArray();
    //     char[] three = "dtDT".toCharArray();
    //     char[] five = "mnMN".toCharArray();
        
    //     for (char x : zero) {
    //         output = output.replace(x, '0');
            
    //     }
    //     for (char x : one) {
    //         output = output.replace(x, '1');
            
    //     }
    //     for (char x : two) {
    //         output = output.replace(x, '2');
            
    //     }
        
    //     for (char x : three) {
    //         output = output.replace(x, '3');
            
    //     }
        
    //     output = output.replace('l', '4');
            
    //     for (char x : five) {
    //         output = output.replace(x, '5');
            
    //     }
        
    //     output = output.replace('r', '6');
        
    //     output = removeRepeatChar(output);
       
    //     output = output.replace("0", "");
        
    //     String result = input.charAt(0) + output;

    //     if (result.length() > 4) {
    //         return result.substring(0, 3);
    //     }else{
    //         return result;
    //     }
        
    // }



}