package Model.inGame;

import java.awt.*;

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

    private int rectWidth;
    private int rectHeight;

    public Map(int height, int width, int frameHeight, int frameWidth) {
        this.height = height;
        this.width = width;
        this.terrain = new Terrain [width][height];
        this.buildings = new Building[width][height];
        this.units = new Unit[width][height];
        this.cursor = new Cursor(0,0);

        rectWidth = frameWidth/width;
        rectHeight = frameHeight/height;


        // Temporary fill terrain
        for(int i = 0; i < terrain.length; i++){
            for(int j = 0; j < terrain[0].length; j++){
                if ((i+j)%2 == 0){
                    terrain[i][j] = new Terrain(3,3, "forest");
                }else{
                    terrain[i][j] = new Terrain(3,5, "hills");
                }
            }
        }
    }
    
    public void drawMap(Graphics g){
        for(int i = 0; i < terrain.length; i++){
            for(int j = 0; j < terrain[0].length; j++){
                if (terrain[i][j].getTerrain_type() == "forest"){
                    g.setColor(Color.GREEN);
                    drawRect(i, j, g);
                }else if(terrain[i][j].getTerrain_type() == "hills"){
                    g.setColor(Color.GRAY);
                    drawRect(i, j, g);
                }else{
                    System.out.println("Tile does not have any terrain!");
                }
            }
        }
    }

    private void drawRect(int x, int y, Graphics g){
        g.fillRect(rectWidth*x,rectHeight*y,rectWidth,rectHeight);
    }

    /**
     * Given indexes for a map coordinate (0,0 is top left square), returns the square (Rectangle) that covers the area
     * @param x X-coordinate for the tile
     * @param y Y-coordinate for the tile
     */
    // TODO: Make private
    public Rectangle indexToPixelCoord(int x, int y){
        return new Rectangle(rectWidth*x,rectHeight*y,rectWidth,rectHeight);
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
