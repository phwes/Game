package Model.inGame;

/**
 * Represents the instance of one particular unit on the map (eg. spearman, ballista)
 */

public class Unit {
    static final int MEN_AT_ARMS = 0;
    static final int BOWMAN = 1;
    static final int BALLISTA = 2;
    static final int TREBUCHET = 3;
    
    
    private int health_points;
    private int attack_value;
    private final int unit_type;
    private int xCoord;
    private int yCoord;
    private int faction;
    private int movement;

    public Unit(int health_points, int attack_value, int unit_type, int xCoord, int yCoord, int faction, int movement) {
        this.health_points = health_points;
        this.attack_value = attack_value;
        this.unit_type = unit_type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.faction = faction;
        this.movement = movement;
    }

    public int getMovement() {
        return movement;
    }

    public int takeDamage(int incomingDamage){
        health_points -= incomingDamage;
        return health_points;
    }

    public int getFaction() {
        return faction;
    }

    public int getHealth_points() {
        return health_points;
    }

    public int getAttack_value() {
        return attack_value;
    }

    public int getUnit_type() {
        return unit_type;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setHealth_points(int health_points) {
        this.health_points = health_points;
    }

    public void setAttack_value(int attack_value) {
        this.attack_value = attack_value;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
