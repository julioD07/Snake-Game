package snakeGame;

public class Muro extends GameObject {

	public Muro() {
		setSymbol('#');
	}

	public Muro(char symbol) {
		setSymbol(symbol);
	}

	//? Generamos los muros horizontales
	public void añadirMurosHorizontales(GameScreen screen, Muro muro, int rowNumber) {
		for (int i = 0; i < screen.getScreenWidth(); i++) {
			screen.setObjectOnLocation(muro, i, rowNumber);
		}
	}

	//? Generamos los muros verticales
	public void añadirMurosVerticales(GameScreen screen, Muro muro, int columnNumber) {
		for (int i = 0; i < screen.getScreenHeight(); i++) {
			screen.setObjectOnLocation(muro, columnNumber, i);
		}
	}
}
