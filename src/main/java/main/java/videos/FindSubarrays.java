package videos;

import java.util.HashMap;
import java.util.Map;

public class FindSubarrays {
    public static void main(String[] args) {
    }

    public int numberOfSubarrays(final String a, final int p, final int q) {
//        System.out.println("MY TRY");
        final int length = a.length();
        final int[] sum = new int[length];
        getSums(a, length, sum);
        final int L = p + q;
        //count of possible combinations (diff, difference)
        final Map<Integer, Integer>[] count = new HashMap[L];
        int difference = 0;
        for (int i = 0; i < L; i++) {
            count[i] = new HashMap<>();
        }
        for (int i = 0; i < a.length(); i++) {//f[0]*q-f[1]*p = 0
            difference = difference + (a.charAt(i) == '0' ? p : -q);
            final int offset = i % L;
            count[offset].put(difference, count[offset].getOrDefault(difference, 0) + 1);
        }
//        System.out.println("SUMS: " + Arrays.toString(sum));
//        System.out.println("COUNTS: " + count);
        int total = 0;
        for (int i = 0; i < L; i++) {
            for (Map.Entry<Integer, Integer> entry : count[i].entrySet()) {
                int v = entry.getValue();
                if (entry.getKey() == 0 && i == L - 1) {
                    v++;
                }
                total += (v * (v - 1)) / 2;
//                System.out.println("TOTAL: " + total + " " + i + " " + v);
            }
        }
        return total;
    }

    public int bruteForce(final String a, final int p, final int q) {
        final int length = a.length();
        final int[] sum = new int[length];
        getSums(a, length, sum);
        final int L = p + q;
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 1; i - j * L >= -1; j++) {
                final int end = i - j * L;
                if (end == -1) {
                    if (sum[i] == p * j) {
//                        System.out.println(0 + " " + i + " " + 0);
                        count++;
                    }
                } else if (sum[i] - sum[end] == p * j) {
//                    System.out.println(((end + 1) % L) + " " + i + " " + (end + 1));
                    count++;
                }
            }
        }
        return count;
    }

    private void getSums(String a, int length, int[] sum) {
        sum[0] = a.charAt(0) - '0';
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] + a.charAt(i) - '0';
        }
    }
}
