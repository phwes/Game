package Model.inGame;

/**
 * Represents the instance of one particular unit on the map (eg. spearman, ballista)
 */

public class Unit {
    private int health_points;
    private int attack_value;
    private final String unit_type;
    private int xCoord;
    private int yCoord;

    public Unit(int health_points, int attack_value, String unit_type, int xCoord, int yCoord) {
        this.health_points = health_points;
        this.attack_value = attack_value;
        this.unit_type = unit_type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getHealth_points() {
        return health_points;
    }

    public int getAttack_value() {
        return attack_value;
    }

    public String getUnit_type() {
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
