package tinkoff.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int num1 = Integer.parseInt(bufferedReader.readLine());
            int num2 = Integer.parseInt(bufferedReader.readLine());

            System.out.println(num1 + num2);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
