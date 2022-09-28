
package SHA;
public class Sha1 {

    private static int BIT_LENGTH = 8;
    private static int LENGTH_SIZE = 64;
    private static int CHUNK_SIZE = 512;
    private static int PRE_LENGTH_SIZE = CHUNK_SIZE - LENGTH_SIZE;
    private static int asciiMsgLen;

    enum PaddingSide {
        LEFT,
        RIGHT
    } 
    /**
     * Perform a SHA1 hash
     * @param input String to be hashed
     * @return Hash output
     */
    public static String hash(String input) {

        /*
         * Preprocessing Step Begin
         */
        // Convert message to an 8 bit binary representation of each ascii value
        String[] binaryArray = convertToBinaryArray(input);
        
        String[] paddedBinaryArray = leftPadBinary(binaryArray, BIT_LENGTH);
        // Convert message length to binary and pad to 64 bit valie
        String asciiMsgLenInBinary = Integer.toBinaryString(combineArrayToString(binaryArray).length());
        String paddedAsciiMsgLen = padStringWithZeros(asciiMsgLenInBinary, 64, PaddingSide.LEFT);
        
        // Combine the array of 8 bit values into 1 long string
        String combinedString = combineArrayToString(paddedBinaryArray);
        int targetLength = calculateTargetLength(combinedString);
        String paddedString = padStringWithZeros(combinedString, targetLength, PaddingSide.RIGHT);
        
        // Add message length to long string
        String longString = paddedString + paddedAsciiMsgLen;

        // Split longString into 512 char chunks
        String[] chunks =  splitString(longString, CHUNK_SIZE);
        for (String str: chunks)
            System.out.println(str);
            System.out.println();
        /*
         * Preprocessing Step End
         */

        /*
         * Hashing Step Begin
         */
        System.out.println(chunks.length);
        System.out.println(longString.length());
        return longString;
        /*String compressed = compress(padded);

        return hash;*/
    }

    /**
     * Convert a string into an array of binary representations of character ascii values
     * @param input String to be converted
     * @return A String array of binary
     */
    private static String[] convertToBinaryArray(String input) {
        // Convert to ascii and then to Binary
        char[] chars = input.toCharArray();
        String[] binary = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            binary[i] = Integer.toBinaryString((int)chars[i]);
        }
        return binary;
    }

    private static int calculateTargetLength(String s)
    {
        int len = s.length();
        int rem = len % CHUNK_SIZE;
        int amountToAdd = 0;
        if (rem > PRE_LENGTH_SIZE) {
            amountToAdd = PRE_LENGTH_SIZE + (CHUNK_SIZE - rem);
        } else {
            amountToAdd = PRE_LENGTH_SIZE - rem;
        }
        return len + amountToAdd;
    }

    /**
     * Pad the left side of each value in an array of binary numbers with 0s till it is a specified length;
     * @param binaryList List of binary numbers
     * @param len Desired number of bits
     * @return Modified array
     */
    private static String[] leftPadBinary(String[] binaryList, int len) {
        String[] res = new String[binaryList.length];
        for (int i = 0; i < binaryList.length; i++) {
            res[i] = padStringWithZeros(binaryList[i], len, PaddingSide.LEFT);
        }

        return res;
    }

    private static String padStringWithZeros(String s, int targetLength, PaddingSide side)
    {
        StringBuilder sb = new StringBuilder();
        if (side == PaddingSide.RIGHT) {
            sb.append(s);
        }
        int discrepancy = Math.abs(s.length() - targetLength);
        for (int i = 0; i < discrepancy; i++)
        {
            sb.append("0");
        }
        if (side == PaddingSide.LEFT)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Combine an array of strings into one long string
     * @param arrayForCombining Array of strings to be combined 
     * @return Combined String
     */
    private static String combineArrayToString(String[] arrayForCombining) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayForCombining.length; i++) {
            sb.append(arrayForCombining[i]);
        }
        sb.append("1");
        return sb.toString();
    }

    private static String[] splitString(String stringForSplitting, int charactersPerChunk)
    {
        int numChunks = stringForSplitting.length() / charactersPerChunk;
        String[] res = new String[numChunks];
        for (int i = 0; i < numChunks; i++)
        {
            res[i] = stringForSplitting.
                substring(
                    i*charactersPerChunk, 
                    Math.min((i*charactersPerChunk) + charactersPerChunk, stringForSplitting.length())
                );
        }
        return res;
    }

    
    
    private String compress() {
        //
        return "1";
    }
}
