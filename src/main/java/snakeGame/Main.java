package snakeGame;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//? Generamos la posición inicial de la serpiente
		final int SCREEN_WIDTH = 20; // Columns
		final int SCREEN_HEIGHT = 10; // Rows
		final int SNAKE_STARTING_X = SCREEN_WIDTH / 2;
		final int SNAKE_STARTING_Y = SCREEN_HEIGHT / 2;

		// Pantalla Inicial
		GameScreen screen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
		screen.InitScreen();

		//? Inicializamos los muros
		Muro muro = new Muro('#');
		muro.añadirMurosHorizontales(screen, muro, 0); // First row
		muro.añadirMurosHorizontales(screen, muro, screen.getScreenHeight() - 1); // Last
																		// row
		muro.añadirMurosVerticales(screen, muro, 0); // First column
		muro.añadirMurosVerticales(screen, muro, screen.getScreenWidth() - 1); // Last
																		// column
		//? poner la serpiente en un lugar aleatorio
		Snake snake = new Snake('@', SNAKE_STARTING_X, SNAKE_STARTING_Y);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());

		//? poner la comida en un lugar aleatorio
		Food food = new Food('*');
		food.addRandomFoodNew(screen, food);

		// The game logic starts here
		boolean isRunning = true;

		while (isRunning) {
			screen.PrintScreen();
			Helpers.MoverSerpiente(screen, snake, food);
		}
	}

}
