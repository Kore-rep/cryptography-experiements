package sha;
public class sha1 {

    private static int bitLength = 8;
    private static int messagelength = 512;
    private static int asciiMsgLen;


    public static String hash(String input) {

        /*
         * Preprocessing Step Begin
         */
        // Convert message to an 8 bit binary representation of each ascii value
        String[] binaryArray = convertToBinaryArray(input);
        String[] paddedBinaryArray = leftPadBinary(binaryArray, bitLength);

        // Convert message length to binary and pad to 64 bit valie
        String asciiMsgLenInBinary = Integer.toBinaryString(combineArrayToString(binaryArray).length());
        String paddedAsciiMsgLen = leftPadZeros(asciiMsgLenInBinary, 64);
        
        // Combine the array of 8 bit values into 1 long string
        String combinedString = combineArrayToString(paddedBinaryArray);
        String paddedString = rightPadBinaryString(combinedString); 
        
        // Add message length to long string
        String longString = paddedString + paddedAsciiMsgLen;
        
        /*
         * Preprocessing Step End
         */

        /*
         * Hashing Step Begin
         */
        return longString;
        /*String compressed = compress(padded);

        return hash;*/
    }


    private static String[] convertToBinaryArray(String input) {
        // Convert to ascii and then to Binary
        char[] chars = input.toCharArray();
        String[] binary = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            binary[i] = Integer.toBinaryString((int)chars[i]);
        }
        return binary;

    }

    private static String[] leftPadBinary(String[] binaryList, int len) {
        // add 64 bits of padding to 448 bits to make 512 bits
        String[] res = new String[binaryList.length];
        for (int i = 0; i < binaryList.length; i++) {
            res[i] = leftPadZeros(binaryList[i], len);
        }

        return res;
    }

    private static String rightPadBinaryString(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        while (sb.length() % 512 != 448 ) {
            sb.append("0");
        }
        return sb.toString();
    }

    private static String combineArrayToString(String[] s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        sb.append("1");
        return sb.toString();
    }

    private static String leftPadZeros(String s, int l) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < l - s.length()) {
            sb.append(0);
        }
        sb.append(s);
        return sb.toString();
    }
    
    private String compress() {
        //
        return "1";
    }
}
