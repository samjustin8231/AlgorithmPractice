package com.sam.leetcode.arr;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author sunyajun
 * @date 2020/3/1 8:21 PM
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        // 实现一个比较器
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s2.compareTo(s1);
            }
        };
        // int数组转为String[]
        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = nums[i] + "";
        }

        // 将数组按比较器排序
        Arrays.sort(numsStr, comparator);

        // 如果在排序后，最大数字为“0”，则整数为零
        if (numsStr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : numsStr) {
            sb.append(s);
        }
        return sb.toString();
    }
}
