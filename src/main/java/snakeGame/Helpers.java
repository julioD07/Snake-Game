package snakeGame;

import java.util.Scanner;

public class Helpers {
    public static String MoverSerpiente(GameScreen screen, Snake snake, Food food) {
        // definimos el scanner
        Scanner scanner = new Scanner(System.in);
        String movimiento = "";
        char input;
        //
        switch (input = scanner.nextLine().charAt(0)) {
            case 'a':
                snake.moveLeft(screen, snake, food);
                movimiento = "Movimiento valido";
                break;
            case 'd':
                snake.moveRight(screen, snake, food);
                movimiento = "Movimiento valido";
                break;
            case 'w':
                snake.moveUp(screen, snake, food);
                movimiento = "Movimiento valido";
                break;
            case 's':
                snake.moveDown(screen, snake, food);
                movimiento = "Movimiento valido";
                break;
            default:
                // ? Si no se pulsa ninguna tecla válida, enviamos un mensaje de error
                System.out.println("Pulsa una tecla válida");
                movimiento = "Movimiento no valido";
        }
        scanner.close();
        return movimiento;
    }
}
