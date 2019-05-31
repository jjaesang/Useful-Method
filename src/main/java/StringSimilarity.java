import java.util.HashSet;

/**
 * String Similarity
 *
 *
 */
public class StringSimilarity {

    public static double jaccardSimilarity(String similar1, String similar2) {
        HashSet<String> h1 = new HashSet<String>();
        HashSet<String> h2 = new HashSet<String>();

        for (String s : similar1.split("\\s+")) {
            h1.add(s);
        }

        for (String s : similar2.split("\\s+")) {
            h2.add(s);
        }

        int sizeh1 = h1.size();
        //Retains all elements in h3 that are contained in h2 ie intersection
        h1.retainAll(h2);
        //h1 now contains the intersection of h1 and h2


        h2.removeAll(h1);
        //h2 now contains unique elements

        //Union
        int union = sizeh1 + h2.size();
        int intersection = h1.size();

        return (double) intersection / union;

    }

    private static int min(int a, int b, int c) {
        return (Math.min(Math.min(a, b), c));
    }

    public static int editDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = min((d[i - 1][j] + 1), (d[i][j - 1] + 1),
                            (d[i - 1][j - 1] + 1));
                }
            }
        }
        return (d[m][n]);
    }

}
