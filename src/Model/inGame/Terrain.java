package Model.inGame;

public class Terrain {
    private int fortification;
    private int movement_penalty;
    private String terrain_type;

    public Terrain(int fortification, int movement_penalty, String terrain_type) {
        this.fortification = fortification;
        this.movement_penalty = movement_penalty;
        this.terrain_type = terrain_type;
    }

    public int getFortification() {
        return fortification;
    }

    public int getMovement_penalty() {
        return movement_penalty;
    }

    public String getTerrain_type() {
        return terrain_type;
    }
}
