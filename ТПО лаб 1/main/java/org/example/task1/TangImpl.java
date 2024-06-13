package org.example.task1;

public class TangImpl {

    public static double tan(double x, double n) {
        return sin(x, n) / cos(x, n);
    }

    private static double sin(double x, double n) {
        double ans = 0;
        double prev = 1;
        int i=0;
        while (Math.abs(ans - prev) > n) {
            prev=ans;
            ans += Math.pow(-1, i) / fact(2 * i + 1) * Math.pow(x, 2 * i + 1);
            i++;
        }
        return ans;
    }

    private static double cos(double x, double n) {
        double ans = 0;
        double prev = 1;
        int i=0;
        while (Math.abs(ans - prev) > n) {
            prev=ans;
            ans += Math.pow(-1, i) / fact(2 * i) * Math.pow(x, 2 * i);
            i++;
        }
        return ans;
    }

    private static double fact(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1 || n == 0) return 1;
        return n * fact(n - 1);
    }
}
