package util;

import java.sql.Date;
import java.util.Scanner;

import static util.Color.*;

public class Utility {
    public static Scanner scanner = new Scanner(System.in);
    public static int getInt(String message) {
        System.out.print(YELLOW+message+RESET);
        return scanner.nextInt();
    }
    public static String getString(String message) {
        System.out.print(YELLOW+message+RESET);
        return scanner.next();
    }
    public static Long getLong(String message) {
        System.out.print(YELLOW+message+RESET);
        return scanner.nextLong();
    }
    public static Double getDouble(String message) {
        System.out.print(YELLOW+message+RESET);
        return scanner.nextDouble();
    }
}
