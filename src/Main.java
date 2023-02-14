import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        String toDecode = "\\u0d19\\u0e68\\u0600\\u07a4\\u0763\\u0a69\\u01f8";
        String toEncode = "hola";
        String finalEncodedStr = "";
        String finalDecodedStr = "";

        char[] charArr = toEncode.toCharArray();
        finalEncodedStr = unicodeToCode(charArr);
        System.out.println(finalEncodedStr);
        System.out.println("------------------------------------------------------------------");
        // -------------------------------------------------------------------------

        String hex="";
        int countIndex = 0;
        Pattern pat = Pattern.compile("([^u\\\\])");
        Matcher mat = pat.matcher(toDecode);
        while (mat.find()) {
            hex += mat.group();
            countIndex++;
            if(countIndex ==4)
            {
                char temp = (char)Integer.parseInt(hex,16);
                finalDecodedStr+=temp;
                countIndex=0;
                hex="";
            }
        }

        //byte[] bt = finalDecodedStr.getBytes();
        //String s = new String(bt, StandardCharsets.UTF_8);
        System.out.print(finalDecodedStr);
    }

    public static String unicodeToCode ( char[] ch){
        String encodedStr ="";

        for(int i=0; i<ch.length;i++){
            if (ch[i] < 0x10) {
                encodedStr += "\\u000" + Integer.toHexString(ch[i]);
            } else if (ch[i] < 0x100) {
                encodedStr += "\\u00" + Integer.toHexString(ch[i]);
            } else if (ch[i] < 0x1000) {
                encodedStr += "\\u0" + Integer.toHexString(ch[i]);
            }else{
                encodedStr += "\\u" + Integer.toHexString(ch[i]);
            }
        }
        return encodedStr;
    }
}
