// First question in http://www.1point3acres.com/bbs/thread-225639-1-1.html

import java.util.*;

public class MinRegexLength {
    public int minCount(String regex) throws Exception {
        int minLength = Integer.MAX_VALUE;
        int count = 0;
        int i = 0;
        
        Stack<Integer> countStack = new Stack<>();
        Stack<Integer> minLenStack = new Stack<>();
        while (i < regex.length()) {
            if (regex.charAt(i) == 'x') {
                count++;
            } else if (regex.charAt(i) == '|') {
                minLength = Math.min(minLength, count);
                count = 0;
            } else if (regex.charAt(i) == '(') {
                minLenStack.push(minLength);
                countStack.push(count);
                count = 0;
            } else if (regex.charAt(i) == ')') {
                count = Math.min(minLength, count);
                count += countStack.pop();
                minLength = minLenStack.pop();
            } else {
                throw new Exception("Unknown Character");
            }
            i++;
        }
        minLength = Math.min(minLength, count);
        return minLength;
    }

    public int minCount2(String regex) {
        int minLength = Integer.MAX_VALUE;
        int count = 0;
        int i = 0;
        while (i < regex.length()){
            if (regex.charAt(i) == 'x') {
                count++;
            } else if (regex.charAt(i) == '|') {
                minLength = Math.min(minLength, count);
                count = 0;
            } else if (regex.charAt(i) == '(') {
                int num = 1;
                int start = i;
                while (i < regex.length() && num != 0) {
                    i++;
                    if (regex.charAt(i) == '(') {
                        num++;
                    } else if (regex.charAt(i) == ')') {
                        num--;
                    }
                }
                count += minCount2(regex.substring(start + 1, i));
            }
            i++;
        }
        minLength = Math.min(minLength, count);
        return minLength;
    }

    public static void main(String[] args) throws Exception {
        MinRegexLength s = new MinRegexLength();

        String[] tests = {
            "xxxx",  // 4
            "xx|xxx",  // 2
            "(xxxx)", // 4
            "(x|)x", // 1
            "((xx|)|x)x(xxxx)" // 5
        };

        
        
        for (String test : tests) {
            System.out.println(s.minCount(test));
        }
    }
}
