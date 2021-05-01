import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;
    private int[] seq = new int[R];
    private static char[] indexToAlpha;
    private static int[] alphaToIndex;

    private MoveToFront() {
        // char c = BinaryStdIn.readChar();

    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char c;
        int charIndex;

        indexToAlpha = new char[R];
        alphaToIndex = new int[R];
        for (int i = 0; i < 256; i++) {
            indexToAlpha[i] = (char) i;
            alphaToIndex[i] = i;
        }

        while (!BinaryStdIn.isEmpty()) {
            c = BinaryStdIn.readChar();
            charIndex = alphaToIndex[c];

            for (int i = charIndex; i > 0; i--) {
                char pos = indexToAlpha[i - 1];
                indexToAlpha[i] = pos;
                alphaToIndex[pos] = i;
            }
            indexToAlpha[0] = c;
            alphaToIndex[c] = 0;
            // StdOut.println(charIndex);
            BinaryStdOut.write(charIndex, 8);
        }
        BinaryStdOut.close();

    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        int index;
        char curChar;

        indexToAlpha = new char[R];
        for (int i = 0; i < 256; i++) {
            indexToAlpha[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            index = BinaryStdIn.readChar();
            curChar = indexToAlpha[index];

            for (int i = index; i > 0; i--) {
                char pos = indexToAlpha[i - 1];
                indexToAlpha[i] = pos;
            }
            indexToAlpha[0] = curChar;
            BinaryStdOut.write(curChar);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        String in = args[0];
        if (in.equals("-")) {
            encode();
        }
        else if (in.equals("+")) {
            decode();
        }
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
