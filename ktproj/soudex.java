/**
 * soudex
 */
public class soudex {

    public static void main(String[] args) {
        String a = "talkin";
        System.out.println(Soundex(a));
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
     }
     public static String removeRepeatChar(String s) {
        if (s == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            sb.append(c);
            i++;
            while (i < len && s.charAt(i) == c) {
                i++;
            }
        }
        return sb.toString();
    }
    public static String Soundex(String input) {

        String output = input.substring(1, input.length());
        char[] zero = "aehiouwyAEHIOUWY".toCharArray();
        char[] one = "bfpvBFPV".toCharArray();
        char[] two = "cgjkqsxzCGJKQSXZ".toCharArray();
        char[] three = "dtDT".toCharArray();
        char[] five = "mnMN".toCharArray();
        
        for (char x : zero) {
            output = output.replace(x, '0');
            
        }
        for (char x : one) {
            output = output.replace(x, '1');
            
        }
        for (char x : two) {
            output = output.replace(x, '2');
            
        }
        
        for (char x : three) {
            output = output.replace(x, '3');
            
        }
        
        output = output.replace('l', '4');
            
        for (char x : five) {
            output = output.replace(x, '5');
            
        }
        
        output = output.replace('r', '6');
        
        output = removeRepeatChar(output);
       
        output = output.replace("0", "");
        
        String result = input.charAt(0) + output;

        if (result.length() > 4) {
            return result.substring(0, 3);
        }else{
            return result;
        }
        
    }
}