package com.jpcnew.SpringLibraryHW.AdditionalTasks;

import java.math.BigInteger;
import java.util.Scanner;
// Cамое большое число Армстронга имеет 39 цифр - 115132219018763992565095597973971522401
// Так как это число превышает лимит значений стандартных числовых типов,
// было принято решение использовать класс BigInteger со строкой в качестве параметра конструктора.

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;

        while ((str = scanner.next()) != null) {
            if(containsOnlyDigits(str)) {
                System.out.println("Введенное число является числом Армстронга? "
                        + isArmstrong(str));
                break;
            }
            else {
                System.out.println("Вводить можно только цифры.");
                scanner.nextLine();
            }
        }
    }

    public static boolean containsOnlyDigits(String str) {
        return str.matches("^[0-9]+$");
    }

    public static boolean isArmstrong(String num) {
        String s;
        String[] numbers = num.split("");
        int l = num.length();

        BigInteger res = BigInteger.valueOf(0);

        for (int i = 0; i < l; i++) {
            res = res.add(new BigInteger(numbers[i]).pow(l));
        }
        s = String.valueOf(res);

        return s.equals(num);
    }
}
