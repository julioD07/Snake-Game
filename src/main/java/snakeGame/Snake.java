package snakeGame;

//? Creamos la clase Snake que hereda de GameObject
public class Snake extends GameObject {

	private short puntaje = 0;
	private String direction;

	public Snake(char symbol, int xStartingLocation, int yStartingLocation) {
		// ? Seteamos el símbolo de la serpiente desde el metodo heredado de GameObject
		setSymbol(symbol);
		// ? Seteamos la posición inicial de la serpiente desde el metodo heredado de
		// GameObject
		setX(xStartingLocation);
		setY(yStartingLocation);
	}

	public short getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(short puntaje, Player player) {
		player.setScore(puntaje);
		if (puntaje >= 5) {
			System.out.println("Ganaste, tu puntaje es: " + puntaje + " puntos");
			Player.savePlayerInfo(player);
			System.exit(0);
		}
		this.puntaje = puntaje;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void moveLeft(GameScreen screen, Snake snake, Food food, Player player) {
		snake.setDirection("LEFT");
		if (willSnakeHitWall(snake, screen)) {
			// ! Realiza acciones específicas cuando la serpiente choca con la pared al
			// moverse a la derecha
			Player.savePlayerInfo(player);
			screen.clearScreenLose("Perdiste, tu puntaje es: " + snake.getPuntaje() + " puntos");
		}

		if (screen.getScreenMatrix()[snake.getY()][snake.getX() - 1] == '*') {
			food.addRandomFoodNew(screen, food);
			snake.setPuntaje((short) (snake.getPuntaje() + 1), player);
		}
		snake.setX(getX() - 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX() + 1, snake.getY());
	}

	public void moveRight(GameScreen screen, Snake snake, Food food, Player player) {
		snake.setDirection("RIGHT");
		if (willSnakeHitWall(snake, screen)) {
			// Realiza acciones específicas cuando la serpiente choca con la pared al
			// moverse a la derecha
			Player.savePlayerInfo(player);
			screen.clearScreenLose("Perdiste, tu puntaje es: " + snake.getPuntaje() + " puntos");
		}

		if (screen.getScreenMatrix()[snake.getY()][snake.getX() + 1] == '*') {
			food.addRandomFoodNew(screen, food);
			snake.setPuntaje((short) (snake.getPuntaje() + 1), player);
		}
		snake.setX(getX() + 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX() - 1, snake.getY());
	}

	public void moveUp(GameScreen screen, Snake snake, Food food, Player player) {
		snake.setDirection("UP");
		if (willSnakeHitWall(snake, screen)) {
			// Realiza acciones específicas cuando la serpiente choca con la pared al
			// moverse a la derecha
			Player.savePlayerInfo(player);
			screen.clearScreenLose("Perdiste, tu puntaje es: " + snake.getPuntaje() + " puntos");
		}

		if (screen.getScreenMatrix()[snake.getY() - 1][snake.getX()] == '*') {
			food.addRandomFoodNew(screen, food);
			snake.setPuntaje((short) (snake.getPuntaje() + 1), player);
		}
		snake.setY(getY() - 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX(), snake.getY() + 1);

	}

	public void moveDown(GameScreen screen, Snake snake, Food food, Player player) {
		snake.setDirection("DOWN");
		if (willSnakeHitWall(snake, screen)) {
			// Realiza acciones específicas cuando la serpiente choca con la pared al
			// moverse a la derecha
			Player.savePlayerInfo(player);
			screen.clearScreenLose("Perdiste, tu puntaje es: " + snake.getPuntaje() + " puntos");
		}

		if (screen.getScreenMatrix()[snake.getY() + 1][snake.getX()] == '*') {
			food.addRandomFoodNew(screen, food);
			snake.setPuntaje((short) (snake.getPuntaje() + 1), player);
		}
		snake.setY(getY() + 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX(), snake.getY() - 1);
	}

	// ? Funcion para validar si la serpiente se choca con la pared
	private boolean willSnakeHitWall(Snake snake, GameScreen screen) {
		// Obtiene la nueva posición de la cabeza de la serpiente después del movimiento
		int newHeadX = snake.getX();
		int newHeadY = snake.getY();

		// Actualiza la nueva posición según la dirección del movimiento (por ejemplo,
		// si es izquierda, resta 1 a la coordenada X)
		// Asegúrate de tener métodos en tu clase Snake para obtener la dirección del
		// movimiento actual
		// (por ejemplo, snake.getDirection() podría devolver "LEFT", "RIGHT", "UP", o
		// "DOWN")
		switch (snake.getDirection()) {
			case "LEFT":
				newHeadX -= 1;
				break;
			case "RIGHT":
				newHeadX += 1;
				break;
			case "UP":
				newHeadY -= 1;
				break;
			case "DOWN":
				newHeadY += 1;
				break;
			default:
				// Manejo de la dirección desconocida (puedes agregar lógica adicional si es
				// necesario)
				break;
		}

		// Verifica si la nueva posición de la cabeza de la serpiente contiene un muro
		if (screen.getObjectOnLocation(newHeadX, newHeadY) == '#') {
			return true; // La serpiente chocará con la pared
		}

		return false; // La serpiente no chocará con la pared
	}
}