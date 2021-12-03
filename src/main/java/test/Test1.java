package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
* 282. 给表达式添加运算符
* https://leetcode-cn.com/problems/expression-add-operators/
* */
public class Test1 {
    //dp[i] Map<Long, List<String>>
    //num的前i位，达到Long的所有可能组合
    //O(4^n)
    //其实，貌似是 O(4^n * n)，因为有getValue()

    //因为最多有10位，需要使用Long防止溢出
    //因为有-号，所以也没法做超出某值就去掉的类似剪枝操作

    public List<String> addOperators(String num, int target) {
        char[] cs = num.toCharArray();
        Map<Long, List<Node>>[] dp = new Map[cs.length];

        List<String> ans = new LinkedList<>();

        dp[0] = new HashMap<>();
        List<Node> l = new LinkedList<>();
        l.add(new Node("", 0L, null, String.valueOf(cs[0]), (long) cs[0] - '0'));
        dp[0].put((long) cs[0] - '0', l);
        if (cs.length == 1 && cs[0] - '0' == target) {
            ans.add(dp[0].get((long) cs[0] - '0').toString());
        }

        for (int i = 1; i < cs.length; i++) {
            Map<Long, List<Node>> last = dp[i - 1];
            dp[i] = new HashMap<Long, List<Node>>();
            for (Map.Entry<Long, List<Node>> e : last.entrySet()) {
                long lastValue = e.getKey();
                List<Node> lastList = e.getValue();
                long current = (long) cs[i] - '0';
                for (char c : operators) {
                    for (Node node : lastList) {
                        if (c == ' ') {
                            //有前导0的跳过
                            if (checkPreZero(node.tail)) {
                                continue;
                            }
                        }
                        String str = node.toString();
                        StringBuilder sb = new StringBuilder(str);
                        long total = -1;
                        String appendString = null;
                        Node newNode = null;
                        switch (c) {
                            case '*':
                                total = node.value;
                                if (node.operator == null || node.operator == '+') {
                                    total += node.tailValue * current;
                                } else if (node.operator == '-') {
                                    total -= node.tailValue * current;
                                }
                                appendString = sb.append('*').append(cs[i]).toString();
                                newNode = new Node(node.s, node.value, node.operator, node.tail + '*' + cs[i], node.tailValue * current);
                                break;
                            case '+':
                                total = lastValue + current;
                                appendString = sb.append('+').append(cs[i]).toString();
                                newNode = new Node(str, lastValue, c, String.valueOf(cs[i]), (long) cs[i] - '0');
                                break;
                            case '-':
                                total = lastValue - current;
                                appendString = sb.append('-').append(cs[i]).toString();
                                newNode = new Node(str, lastValue, c, String.valueOf(cs[i]), (long) cs[i] - '0');
                                break;
                            default:
                                if (node.operator == null || node.operator == '+') {
                                    total = node.value + getValue(node.tail + cs[i]);
                                } else {//'-'
                                    total = node.value - getValue(node.tail + cs[i]);
                                }
                                appendString = sb.append(cs[i]).toString();
                                newNode = new Node(node.s, node.value, node.operator, node.tail + cs[i], getValue(node.tail + cs[i]));
                        }
                        if (i == cs.length - 1 && total == (long) target) {
                            ans.add(appendString);
                        }
                        List<Node> list = dp[i].getOrDefault(total, new LinkedList<>());
                        list.add(newNode);
                        dp[i].putIfAbsent(total, list);
                    }
                }
            }
        }
        return ans;
    }

    private long getValue(String s) {
        long pre = 1;
        long current = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                pre = pre * current;
                current = 0;
            } else {
                current = current * 10 + (c - '0');
            }
        }
        return pre * current;
    }

    //不能有前导0
    //最靠后的运算符（其实只会有*）后（或无运算符，开头）是否是'0'
    private boolean checkPreZero(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                return s.charAt(i + 1) == '0';
            }
        }
        return s.charAt(0) == '0';
    }

    //因为存在*号，因此存在运算符优先级问题
    //即，*号优先级高
    //因此，需要保留当前表达式的尾部，即tail
    //Node是一种如 1 + 2*3*45 的形式
    //1 为s，+ 为operator，2*3*45 为tail
    private static class Node {
        String s;
        long value;

        Character operator;
        String tail;//这里只会出现'*'一种符号，及数字。   +，-都会直接替换s,tail，只有*会在tail上增加
        long tailValue;//加速计算

        Node(String s, long value, Character operator, String tail, long tailValue) {
            this.s = s;
            this.value = value;
            this.operator = operator;
            this.tail = tail;
            this.tailValue = tailValue;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(s).append(operator == null ? "" : operator).append(tail);
            return sb.toString();
        }
    }

    //' ' 代表将当前cs[i]附在末尾，无符号
    private static char[] operators = new char[]{'*', '+', '-', ' '};

    public static void main(String[] args) {
        List<String> list = new Test1().addOperators("123456789", 45);
        System.out.println(list.contains("1*2*3*4*5-6-78+9"));
    }

}
