package com.jpcnew.SpringLibraryHW.AdditionalTasks;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(isSimpleNum(num));
    }

    public static boolean isSimpleNum(int num) {
        if(num != 2 && num % 2 == 0) return false;
        else {
            double temp = Math.sqrt(num);
            for (int i = 2; i < temp; i++) {
                if(num % i == 0) return false;
            }
        }
        return true;
    }
}
