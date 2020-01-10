package Model.inGame;

/**
 * Keeps track of the map and its content.
 */

public class Map {
    private int height;
    private int width;
    private Terrain [][] terrain;
    private Building[][] buildings;
    private Unit [][] units;
    private Cursor cursor;

    public Map(int height, int width, Terrain[][] terrain, Building[][] buildings, Unit[][] units, Cursor cursor) {
        this.height = height;
        this.width = width;
        this.terrain = terrain;
        this.buildings = buildings;
        this.units = units;
        this.cursor = cursor;
    }

    /**
     * makes the cursor select a unit.
     */
    public void selectUnit(){
        if(units[cursor.getxCoord()][cursor.getyCoord()] != null && cursor.selectedUnit == null){
            cursor.selectedUnit = units[cursor.getxCoord()][cursor.getyCoord()];
        }
    }

    /**
     * Moves a selected unit to the current position of the cursor.
     * @return True if move was completed (valid)
     */
    public Boolean moveUnit(){
        //TODO: Add check if move is valid (probably with help of BFS)
        units[cursor.selectedUnit.getxCoord()][cursor.selectedUnit.getyCoord()] = null;
        units[cursor.getxCoord()][cursor.getyCoord()] = cursor.selectedUnit;
        cursor.selectedUnit.setxCoord(cursor.getxCoord());
        cursor.selectedUnit.setyCoord(cursor.getyCoord());
        cursor.selectedUnit = null;
        return true;
    }

    public Terrain[][] getTerrain() {
        return terrain;
    }

    public Building[][] getBuildings() {
        return buildings;
    }

    public Unit[][] getUnits() {
        return units;
    }
}
