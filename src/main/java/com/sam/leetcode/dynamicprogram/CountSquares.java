package com.sam.leetcode.dynamicprogram;

/**
 * @author sunyajun
 * @date 2020/3/1 9:56 PM
 */
public class CountSquares {

    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        // dp[i][j]: 以(i, j)为右下角的正方形的
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int maxLength = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            maxLength = Math.max(maxLength, dp[i][0]);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] - '0';
            maxLength = Math.max(maxLength, dp[0][i]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    //
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        return maxLength * maxLength;
    }
}
