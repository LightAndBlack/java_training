package org.example;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main{
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main( String[] args ) {
        logger.setLevel(Level.INFO);
//        Action action = new Action();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nВыберите одно из действий: 0, 1, 2, 3: ");
            try {
                int action = selectAction(scanner.nextInt());
                logger.info("Пользователь выбрал действие: " + action);
                break;
            }
            catch (WrongInputException e) {
                logger.warning("Перехвачено пользовательское исключение: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }

    public static int selectAction(int number) throws WrongInputException{
        if (number < 0 || number > 3) throw new WrongInputException("Ошибка выбора действия");
        return number;
    }
}
