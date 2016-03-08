/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MP2_DFA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Jessa
 */
public class DFA_Final {

    ArrayList<String> commands;
    ArrayList<Character> A;
    ArrayList<Character> B;

    DFA_Final(String fileName) throws FileNotFoundException {
        Scanner read = new Scanner(new File(fileName));
        commands = new ArrayList();
        while (read.hasNextLine()) {
            commands.add(read.nextLine());
        }

    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String filename = "mp2";//scan.nextLine();
        filename = filename + ".in";
        DFA_Final tmp = new DFA_Final(filename);
        String outputFile = filename + ".out";
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
        boolean flag = true;
        int count = 0;
        System.out.println("Lines: " + tmp.commands.size());
        for (int i = 0; i < tmp.commands.size(); i++) {
            flag = true;
            tmp.A = new ArrayList(Arrays.asList('L', 'R', 'C', 'N'));
            tmp.B = new ArrayList();
            String cmd = tmp.commands.get(i);

            for (int j = 0; j < cmd.length(); j++) {
                Character x = cmd.charAt(j);
                if (tmp.A.contains('N')) {
                    if (x.equals('N')) {
                        tmp.A.remove(x);
                        tmp.B.add(x);
                    } else {
                        if (tmp.A.contains(x)) {
                            tmp.A.removeAll(Arrays.asList(x, 'N'));
                            tmp.B.addAll(Arrays.asList(x, 'N'));
                        } else {
                            flag = false;
                        }
                    }
                } else {
                    if (tmp.B.contains('N')) {
                        if (x.equals('N')) {
                            tmp.B.remove(x);
                            tmp.A.add(x);
                        } else {
                            if (tmp.B.contains(x)) {
                                tmp.B.removeAll(Arrays.asList(x, 'N'));
                                tmp.A.addAll(Arrays.asList(x, 'N'));
                            } else {
                                flag = false;
                           
                            }
                        }
                    }
                }
                flag = tmp.check(tmp.A, tmp.B);
                if (flag == false) {
                    System.out.println(i + ": NG");
                    writer.println("NG");
                    break;
                }
            }
            if (tmp.B.containsAll(Arrays.asList('C', 'L', 'R', 'N'))) {
                System.out.println(i + ": OK");
                writer.println("OK");
                count++;
            } else if (flag == true) {
                System.out.println(i + ": NG");
                writer.println("NG");
            }
        }
        writer.close();
    }

    private static boolean check(ArrayList A, ArrayList B) {
        boolean flag = true;
        if ((B.size() == 2 && B.containsAll(Arrays.asList('C', 'R'))) || (A.size() == 2 && A.containsAll(Arrays.asList('C', 'R')))) {
            flag = false;
        } else if ((A.size() == 2 && A.containsAll(Arrays.asList('L', 'R'))) || (B.size() == 2 && B.containsAll(Arrays.asList('L', 'R')))) {
            flag = false;
        } else if ((B.size() == 3 && B.containsAll(Arrays.asList('C', 'L', 'R'))) || (A.size() == 3 && A.containsAll(Arrays.asList('C', 'L', 'R')))) {
            flag = false;
        }
        return flag;
    }
}
