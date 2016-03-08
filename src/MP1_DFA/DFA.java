/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MP1_DFA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jessa
 */
public class DFA {

    static ArrayList<String> commands;

    private static void displayStates(String A, String B) {
        System.out.println("Bank 1: " + A);
        System.out.println("Bank 2: " + B);
        System.out.println("");
    }

    private static boolean check(String str) {
        //  if (str.indexOf("N") < 0) {
        if ((str.indexOf("R") > -1 && str.indexOf("L") > -1) || (str.indexOf("R")) > -1 && str.indexOf("C") > -1) {

            return false;
        }

        return true;
    }

    DFA(String fileName) throws FileNotFoundException {
        Scanner read = new Scanner(new File(fileName));
        commands = new ArrayList();
        while (read.hasNextLine()) {
            commands.add(read.nextLine());

        }

    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        boolean flag = true;
        int count = 0;
        String C;
        Scanner scan = new Scanner(System.in);

        System.out.println("File Name: ");
        String filename = "mp2";//scan.nextLine();
        filename = filename + ".in";
        DFA dfa = new DFA("mp2.in");
        String outputFile = filename + ".out";
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");

        System.out.println(commands);
        for (int i = 0; i < commands.size(); i++) {
            C = commands.get(i);
            String A = "LRCN";
            String B = "";
            System.out.println("Current: " + C);
            //       System.out.println("Line: "+i);
            for (int j = 0; j < C.length(); j++) {
                char tmp = C.charAt(j);
                switch (tmp) {
                    case 'R':
                        if (A.indexOf("N") > 0) {
                            A = A.replace("R", "");
                            B = B + "R";
                            A = A.replace("N", "");
                            B = B + "N";
                            flag = check(A);
                        } else {
                            B = B.replace("R", "");
                            A = A + "R";
                            B = B.replace("N", "");
                            A = A + "N";
                            flag = check(B);
                        }
                        // displayStates(A, B);
                        break;
                    case 'C':
                        if (A.indexOf("N") > 0) {
                            A = A.replace("C", "");
                            B = B + "C";
                            A = A.replace("N", "");
                            B = B + "N";
                            flag = check(A);
                        } else {
                            B = B.replace("C", "");
                            A = A + "C";
                            B = B.replace("N", "");
                            A = A + "N";
                            flag = check(B);
                        }
                        //   displayStates(A, B);
                        break;
                    case 'L':
                        if (A.indexOf("N") > 0) {
                            A = A.replace("L", "");
                            B = B + "L";
                            A = A.replace("N", "");
                            B = B + "N";
                            flag = check(A);
                        } else {
                            B = B.replace("L", "");
                            A = A + "L";
                            B = B.replace("N", "");
                            A = A + "N";
                            flag = check(B);
                        }
                        //    displayStates(A, B);
                        break;
                    case 'N':
                        if (A.indexOf("N") > 0) {
                            A = A.replace("N", "");
                            B = B + "N";
                        } else {
                            B = B.replace("N", "");
                            A = A + "N";
                        }

                        //   displayStates(A, B);
                        break;
                }
                if (flag == false) {
                    //  System.out.println("FALSE");
                    System.out.println("NG");
                    writer.println("NG");
                    break;
                } else if (j == C.length() - 1) {
                    if (B.equals("") && A.contains("R") && A.contains("L") && A.contains("C") && A.contains("N")) {
                        System.out.println("OK");
                        writer.println("OK");
                        count++;
                    } else {
                        System.out.println("NG");
                        writer.println("NG");
                    }
                }

            }

        }
        System.out.println("count: " + count);
        writer.close();
    }

}
