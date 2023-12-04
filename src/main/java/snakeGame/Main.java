package snakeGame;

import java.util.List;
// import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Font;

public class Main {

	public static void main(String[] args) {
		// Inicializamos el scanner para leer la entrada del usuario
		// Scanner scanner = new Scanner(System.in);

		// Mostramos el menú
		int option;
		do {
			// System.out.println("Menú:");
			// System.out.println("1. Empezar el juego");
			// System.out.println("2. Ver puntajes");
			// System.out.println("3. Salir");
			// System.out.print("Elige una opción: ");
			
			// ? Agregamos la fuentes
			setFuente();

			String menu = "Menú:\n" +
					"1. Empezar el juego\n" +
					"2. Ver puntajes\n" +
					"3. Salir\n" +
					"Elige una opción: ";

			// Leemos la opción del usuario
			option = Integer.parseInt(JOptionPane.showInputDialog(null, menu));

			switch (option) {
				case 1:
					// Empezar el juego
					startGame();
					break;
				case 2:
					// Ver puntajes
					viewScores();
					break;
				case 3:
					// Salir del programa
					JOptionPane.showMessageDialog(null, "¡Hasta luego!");
					// System.out.println("¡Hasta luego!");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige de nuevo.");
					// System.out.println("Opción no válida. Por favor, elige de nuevo.");
			}
		} while (option != 3); // Salir del bucle si la opción es 3
	}

	// Método para iniciar el juego
	private static void startGame() {
		// ? Generamos la posición inicial de la serpiente
		final int SCREEN_WIDTH = 20; // Columns
		final int SCREEN_HEIGHT = 10; // Rows
		final int SNAKE_STARTING_X = SCREEN_WIDTH / 2;
		final int SNAKE_STARTING_Y = SCREEN_HEIGHT / 2;

		// Pantalla Inicial
		GameScreen screen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
		screen.InitScreen();

		// ? Inicializamos los muros
		Muro muro = new Muro('#');
		muro.añadirMurosHorizontales(screen, muro, 0); // First row
		muro.añadirMurosHorizontales(screen, muro, screen.getScreenHeight() - 1); // Last
		// row
		muro.añadirMurosVerticales(screen, muro, 0); // First column
		muro.añadirMurosVerticales(screen, muro, screen.getScreenWidth() - 1); // Last
		// column
		// ? poner la serpiente en un lugar aleatorio
		Snake snake = new Snake('@', SNAKE_STARTING_X, SNAKE_STARTING_Y);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());

		// ? poner la comida en un lugar aleatorio
		Food food = new Food('*');
		food.addRandomFoodNew(screen, food);

		// The game logic starts here
		boolean isRunning = true;
		char input;

		// ! Inicializamos el scanner para leer la entrada del usuario
		// Scanner scanner = new Scanner(System.in);

		// ? Se remplaza el scanner por JOptionPane de Java Swing

		// ? Agregamos la funcionalidad de los nombres
		// System.out.print("Ingresa tu nombre: ");
		String name = JOptionPane.showInputDialog(null, "Ingresa tu nombre: ");
		Player player = new Player(name);

		while (isRunning) {
			String pantalla = screen.getScreenAsString();
			pantalla += "\nIngresa una Movimiento: ";

			// Get input from player and do something
			switch (input = JOptionPane.showInputDialog(null, pantalla).charAt(0)) {
				case 'a':
					snake.move(screen, snake, food, player, "LEFT");
					break;
				case 'd':
					snake.move(screen, snake, food, player, "RIGHT");
					break;
				case 'w':
					snake.move(screen, snake, food, player, "UP");
					break;
				case 's':
					snake.move(screen, snake, food, player, "DOWN");
					break;
				default:
					// ? Si no se pulsa ninguna tecla válida, enviamos un mensaje de error
					// System.out.println("Pulsa una tecla válida");
					JOptionPane.showMessageDialog(null, "Pulsa una tecla válida");
			}
		}
	}

	// Método para ver los puntajes
	private static void viewScores() {
		// Obtener la lista de jugadores desde el archivo JSON
		// System.out.println("Obteniendo puntajes...\n");
		List<Player> players = Player.readPlayerInfo();
		Player masAlto = new Player();
		String puntajes = "";
		// Mostrar los puntajes
		if (players.isEmpty()) {
			// System.out.println("No hay puntajes disponibles.");
			JOptionPane.showMessageDialog(null, "No hay puntajes disponibles.");
		} else {
			// System.out.println("Puntajes:\n");

			for (Player player : players) {
				// ? Obtenemos el puntaje más alto
				if (player.getScore() > masAlto.getScore()) {
					masAlto = player;
				}
				// System.out.println("Nombre: " + player.getName() + ", Puntaje: " + player.getScore());
				puntajes += "Nombre: " + player.getName() + ", Puntaje: " + player.getScore() + "\n";
			}
			// ? Imprimimos un salto de línea para separar los puntajes de la opción del
			// menú
			// System.out.println();
			// ? Imprimimos el puntaje más alto
			// System.out.println("El puntaje más alto es: " + masAlto.getScore());
			puntajes += "\nEl puntaje más alto es: " + masAlto.getScore() + " y es de " + masAlto.getName() + "\n";
			JOptionPane.showMessageDialog(null, puntajes);
		}
	}

	private static void setFuente() {
		// Configura la fuente a una fuente de monoespacio
		Font monospacedFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		// Configura la fuente antes de mostrar el cuadro de diálogo
		UIManager.put("OptionPane.messageFont", monospacedFont);
		UIManager.put("OptionPane.buttonFont", monospacedFont);
	}
}
