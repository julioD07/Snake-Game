package snakeGame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private short score;

    // Constructor, getters, setters...
    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getScore() {
        return this.score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public static void savePlayerInfo(Player player) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Obtener la ruta completa del archivo
            String filePath = System.getProperty("user.dir") + File.separator + "players.json";
            File file = new File(filePath);
            System.out.println(file.getAbsolutePath());
    
            // Si el archivo no existe o está vacío, crea uno nuevo y escribe el nuevo jugador
            if (!file.exists() || file.length() == 0) {
                file.createNewFile();
                objectMapper.writeValue(file, new Player[]{player});
            } else {
                // Si el archivo existe y no está vacío, lee la lista actual de jugadores
                Player[] players = objectMapper.readValue(file, Player[].class);
    
                // Agrega el nuevo jugador a la lista
                Player[] updatedPlayers = new Player[Math.min(players.length + 1, 5)];
                System.arraycopy(players, 0, updatedPlayers, 0, Math.min(players.length, 5 - 1));
                updatedPlayers[updatedPlayers.length - 1] = player;
    
                // Guarda la lista actualizada en el archivo
                objectMapper.writeValue(file, updatedPlayers);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar/leer el archivo de jugadores: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza completa del error
        }
    }
    
    // Método para leer los jugadores y sus puntajes desde el archivo JSON
    public static List<Player> readPlayerInfo() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "players.json");

            // Si el archivo no existe, devuelve una lista vacía
            if (!file.exists()) {
                return new ArrayList<>();
            }

            // Lee la lista de jugadores desde el archivo
            return objectMapper.readValue(file, new TypeReference<List<Player>>() {});
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de jugadores: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    

}
