package snakeGame;

public class Food extends GameObject {

	public Food(char symbol) {
		setSymbol(symbol);
	}

	// ? Generamos la comida en una posición aleatoria evitando que se genere en un
	// muro
	public void addRandomFood(GameScreen screen, Food food) {
		screen.setObjectOnLocation(food, (int) (Math.random() * (screen.getScreenWidth() - 1)),
				(int) (Math.random() * (screen.getScreenHeight() - 1)));
	}

	public void addRandomFoodNew(GameScreen screen, Food food) {
		int x, y;

		x = (int) (Math.random() * (screen.getScreenWidth() - 2)) + 1;
		y = (int) (Math.random() * (screen.getScreenHeight() - 2)) + 1;

		screen.setObjectOnLocation(food, x, y);

		// ? Hacemos un bucle para validar si es un muro
		while (screen.getObjectOnLocation(x, y) == '#') {

			x = (int) (Math.random() * (screen.getScreenWidth() - 2)) + 1;
			y = (int) (Math.random() * (screen.getScreenHeight() - 2)) + 1;

			screen.setObjectOnLocation(food, x, y);
		}

		// screen.setObjectOnLocation(food, x, y);
	}

}
