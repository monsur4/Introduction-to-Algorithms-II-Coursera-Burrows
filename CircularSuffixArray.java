import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    private CircularSuffix[] circularSuffixes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException("An argument to the constructor is null");
        int length = s.length();
        circularSuffixes = new CircularSuffix[length];
        for (int i = 0; i < length; i++) {
            circularSuffixes[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(circularSuffixes);
        // Quick3way.sort(circularSuffixes);
    }

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private final int firstIndex;
        private final String s;
        private final int length;

        private CircularSuffix(String s, int firstIndex) {
            this.firstIndex = firstIndex;
            this.s = s;
            this.length = s.length();
        }

        public int compareTo(CircularSuffix o) {
            if (this.equals(o)) return 0;
            for (int i = 0; i < length; i++) {
                if (s.charAt((firstIndex + i) % length) < s.charAt((o.firstIndex + i) % length))
                    return -1;
                if (s.charAt((firstIndex + i) % length) > s.charAt((o.firstIndex + i) % length))
                    return +1;
            }
            return this.s.length() - o.s.length();
        }


    }

    // length of s
    public int length() {
        return circularSuffixes.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > circularSuffixes.length - 1) throw new IllegalArgumentException(
                "An argument to the method is outside its prescribed range");
        return circularSuffixes[i].firstIndex;
    }

    // unit testing (required)
    public static void main(String[] args) {
        String text = args[0];
        // Quick3string.sort(args);

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
        StdOut.println("Length = " + circularSuffixArray.length());
        StdOut.print("Sorted indices = ");
        for (int i = 0; i < text.length(); i++) {
            StdOut.print(circularSuffixArray.index(i));
        }
    }
}
