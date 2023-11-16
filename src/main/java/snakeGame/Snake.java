package snakeGame;

public class Snake extends GameObject {

	public Snake(char symbol, int xStartingLocation, int yStartingLocation) {
		setSymbol(symbol);
		setX(xStartingLocation);
		setY(yStartingLocation);
	}
	
	public void moveLeft(GameScreen screen, Snake snake, Food food) {
		if (screen.getScreenMatrix()[snake.getY()][snake.getX() - 1] == '*') {
			food.addRandomFood(screen, food);
		}
		snake.setX(getX() - 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX() + 1, snake.getY());
	}
	
	public void moveRight(GameScreen screen, Snake snake, Food food) {
		if (screen.getScreenMatrix()[snake.getY()][snake.getX() + 1] == '*') {
			food.addRandomFood(screen, food);
		}
		snake.setX(getX() + 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX() - 1, snake.getY());
	}
	
	public void moveUp(GameScreen screen, Snake snake, Food food) {
		if (screen.getScreenMatrix()[snake.getY() - 1][snake.getX()] == '*') {
			food.addRandomFood(screen, food);
		}	
		snake.setY(getY() - 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX(), snake.getY() + 1);
	}
	
	public void moveDown(GameScreen screen, Snake snake, Food food) {

		if (screen.getScreenMatrix()[snake.getY() + 1][snake.getX()] == '*') {
			food.addRandomFood(screen, food);
		}
		snake.setY(getY() + 1);
		screen.setObjectOnLocation(snake, snake.getX(), snake.getY());
		screen.ClearScreenLocation(snake.getX(), snake.getY() - 1);
	}
}