package com.sem3.lab4;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        String str;
        String newStr = new String();
        boolean isComment = false;
        try {
            Scanner scan = new Scanner(new File("d://IntelliJ IDEA Projects/Sem3_Lab4/resourses/input.java"));
            Pattern pattern = Pattern.compile("[\t ]*");
            while (scan.hasNextLine()) {
                str = scan.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    if (!isComment && str.charAt(i) == '"' && str.charAt(i - 1) != '\'') {
                        newStr += str.charAt(i);
                        i++;
                        while (i < str.length() && !isEndOfString(str, i)) {
                            newStr += str.charAt(i);
                            i++;
                        }
                    }
                    if (!isComment && i < str.length() - 1 && (str.charAt(i) == '/' && str.charAt(i + 1) == '/')) {
                        break;
                    }
                    if (!isComment && i < str.length() - 1 && str.charAt(i) == '/' && str.charAt(i + 1) == '*') {
                        isComment = true;
                        i += 2;
                    }
                    if (isComment && i < str.length() - 1 && (str.charAt(i) == '*' && str.charAt(i + 1) == '/')) {
                        i += 2;
                        isComment = false;
                    }
                    if (!isComment && i < str.length()) {
                        newStr += str.charAt(i);
                    }
                }
                Matcher matcher = pattern.matcher(newStr);
                if (!matcher.matches()) {
                    System.out.println(newStr);
                }
                newStr = "";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    private static boolean isEndOfString(String str, int index) {
        if (index < str.length() && str.charAt(index) == '"') {
            if (str.charAt(index - 1) == '\\') {
                if (str.charAt(index - 2) == '\\') {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}