package snakeGame;

public class GameScreen {

	private int width, height;
	private char[][] screenMatrix;

	public GameScreen(int width, int height) {
		this.width = width;
		this.height = height;
		this.screenMatrix = new char[this.height][this.width];
	}

	// Fill array with dots
	public void InitScreen() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.screenMatrix[i][j] = '.';
			}
		}
	}

	// Print the screen to console
	public void PrintScreen() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.print(this.screenMatrix[i][j]);
			}
			System.out.println();
		}
	}

	//Screen clear location
	public void ClearScreenLocation(int x, int y) {
		this.screenMatrix[y][x] = '.';
	}

	// Getters
	public int getScreenWidth() {
		return this.width;
	}

	public int getScreenHeight() {
		return this.height;
	}

	public char getObjectOnLocation(int x, int y) {
		return this.screenMatrix[y][x];
	}

	// Setters
	public void setObjectOnLocation(GameObject object, int x, int y) {
		this.screenMatrix[y][x] = object.getSymbol();
	}

	public char[][] getScreenMatrix() {
		return screenMatrix;
	}

	public void clearScreenLose(String texto) {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Si el sistema operativo es Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Si el sistema operativo es Unix o Linux
                Runtime.getRuntime().exec("clear");
            }
			System.out.println(texto);

			//? Cerramos el programa
			System.exit(0);
        } catch (final Exception e) {
            // Manejar excepciones si ocurren
            System.out.println("Error al intentar limpiar la consola: " + e.getMessage());
        }
    }
}
