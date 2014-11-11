package kg;

public class Utilities {

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }

    public static int[] stringArrayToIntArray(String[] strings){
        int[] a = new int[strings.length];
        for(int index = 0;index<strings.length;index++){
            a[index] = Integer.parseInt(strings[index]);
        }
        return a;
    }
}
