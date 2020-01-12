package Model.inGame;

public class Terrain {
    private int fortification;
    private int movement_penalty;
    private int terrain_type;

    public static final int FOREST = 0;
    public static final int HILLS = 1;
    public static final int MOUNTAIN = 2;
    public static final int SEA = 3;

    public Terrain(int fortification, int movement_penalty, int terrain_type) {
        this.fortification = fortification;
        this.movement_penalty = movement_penalty;
        this.terrain_type = terrain_type;
    }

    public int getTerrain_type() {
        return terrain_type;
    }

    public int getFortification() {
        return fortification;
    }

    public int getMovement_penalty() {
        return movement_penalty;
    }

}
