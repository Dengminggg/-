package com.dm;

import java.io.*;
import java.util.*;


public class ReadTxtUtils {

    public static void main(String[] args) {
        final int len = 5;
        BufferedReader rd = null;
        try {
            String str = "C:\\Users\\96470\\Desktop\\资料\\五言律诗.txt";
            rd = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str)), "UTF-8"));
            String row = null;
            HashMap<Character, Integer> storage = new HashMap<>();
            while ((row = rd.readLine()) != null) {
                //对每首诗都进行词频统计
                for (int i = 0; i < 8; i++) {
                    char[] chars = row.substring(6 * (i), (6 * i) + len).toCharArray();
                    for (char c : chars) {
                        storage.put(c, !storage.containsKey(c) ? 1 : storage.get(c) + 1);
                    }
                }
            }
            ArrayList<Node> list = new ArrayList<>();
            System.out.println("===统计词频表===");
            for (Map.Entry<Character, Integer> entry : storage.entrySet()) {
                list.add(new Node(entry.getKey(), entry.getValue()));

            }
            Object[] nodes = list.toArray();
            Arrays.sort(nodes, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((Node) o2).i - ((Node) o1).i;
                }
            });
            int sum = 0;
            for (Object node : nodes) {
//                System.out.println(node);
                sum+=((Node)node).i;
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Node {
        Character c;
        Integer i;

        public Node(Character c, Integer i) {
            this.c = c;
            this.i = i;
        }

        @Override
        public String toString() {
            return "| " + c + " |出现得到频次为： " + i;
        }
    }

}
