package com.f_lin.future.solution.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/20 10:44
 */
public class DynamicPlanning {


    public static void main(String[] args) {
        String fTest = "hello";
        String sTest = "gelslo";

        System.out.println(longestCommonSunStrCount(fTest, sTest));

        System.out.println(longestCommonSubSequenceCount(fTest, sTest));
    }


    //最长公共子串
    private static int longestCommonSunStrCount(String fStr, String sStr) {
        int[][] grid = new int[fStr.length()][sStr.length()];
        int longestNum = 0;

        for (int i = 0; i < fStr.length(); i++) {
            char fChar = fStr.charAt(i);
            for (int j = 0; j < sStr.length(); j++) {
                char sChar = sStr.charAt(j);
                if (fChar == sChar) {
                    int commonCount = 0;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        commonCount = grid[i - 1][j - 1] + 1;
                    }
                    grid[i][j] = commonCount;
                    if (commonCount > longestNum) {
                        longestNum = commonCount;
                    }
                }
            }
        }
        return longestNum;
    }

    //最长公共子序列
    private static int longestCommonSubSequenceCount(String fStr, String sStr) {
        int[][] grid = new int[fStr.length()][sStr.length()];
        int longestNum = 0;

        for (int i = 0; i < fStr.length(); i++) {
            char fChar = fStr.charAt(i);
            for (int j = 0; j < sStr.length(); j++) {
                char sChar = sStr.charAt(j);

                int sameCount = 0;
                int differentCount = 0;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sameCount = grid[i - 1][j - 1] + 1;
                    differentCount = grid[i - 1][j - 1] > grid[i][j - 1] ? grid[i - 1][j - 1] : grid[i][j - 1];
                }

                if (fChar == sChar) {
                    grid[i][j] = sameCount;
                    if (sameCount > longestNum) {
                        longestNum = sameCount;
                    }
                } else {
                    grid[i][j] = differentCount;
                }
            }
        }
        return longestNum;
    }


    private static Map<String, TargetInfo> initTargets() {
        Map<String, TargetInfo> targets = new HashMap<>();
        TargetInfo walter = new TargetInfo("walter", 5f, 5);
        TargetInfo bread = new TargetInfo("bread", 1f, 3);
        TargetInfo banana = new TargetInfo("banana", 0.5f, 2);
        TargetInfo apple = new TargetInfo("apple", 1f, 2);
        targets.put(walter.getName(), walter);
        targets.put(bread.getName(), bread);
        targets.put(banana.getName(), banana);
        targets.put(apple.getName(), apple);
        return targets;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TargetInfo {
        private String name;
        private Float weight;
        private Integer worth;
    }
}
