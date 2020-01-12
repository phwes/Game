package Model.inGame;

import Images.Sprites;
import States.Scene;

import java.awt.*;



/**
 * Keeps track of the map and its content.
 */

public class Map {
    private Sprites sprites;

    private int height;
    private int width;
    private Terrain [][] terrain;
    private Building[][] buildings;
    private Unit [][] units;
    public Cursor cursor;

    private int rectWidth;
    private int rectHeight;

    public Map(int height, int width, int frameHeight, int frameWidth) {
        this.height = height;
        this.width = width;
        this.terrain = new Terrain [width][height];
        this.buildings = new Building[width][height];
        this.units = new Unit[width][height];
        this.cursor = new Cursor(0,0);

        sprites = new Sprites();

        rectWidth = frameWidth/width;
        rectHeight = frameHeight/height;


        // Temporary fill terrain (test)
        for(int i = 0; i < terrain.length; i++){
            for(int j = 0; j < terrain[0].length; j++){
                if ((i+j)%2 == 0){
                    terrain[i][j] = new Terrain(3,3, Terrain.FOREST);
                }else{
                    terrain[i][j] = new Terrain(3,5, Terrain.HILLS);
                }
            }
        }

        // Temporary create units (test)
        units[4][2] = new Unit(10,5, Unit.MEN_AT_ARMS, 4,2);
        units[10][10] = new Unit(10,5, Unit.MEN_AT_ARMS, 10,10);
        units[15][8] = new Unit(10,5, Unit.MEN_AT_ARMS, 15,8);

    }

    public void moveCursor(char dir){
        switch (dir){
            case 'u':
                if(cursor.getyCoord() != 0){
                    cursor.setyCoord(cursor.getyCoord()-1);
                }
                break;
            case 'd':
                if(cursor.getyCoord() != height-1){
                    cursor.setyCoord(cursor.getyCoord()+1);
                }
                break;
            case 'l':
                if(cursor.getxCoord() != 0){
                    cursor.setxCoord(cursor.getxCoord()-1);
                }
                break;
            case 'r':
                if(cursor.getxCoord() != width-1){
                    cursor.setxCoord(cursor.getxCoord()+1);
                }
                break;
        }
    }

    public void drawUnits(Graphics g){
        for(int i = 0; i < units.length; i++){
            for(int j = 0; j < units[0].length; j++){
                if (units[i][j] != null){
                    drawUnit(g, units[i][j]);
                }
            }
        }
    }

    private void drawUnit(Graphics g, Unit unit){
        switch (unit.getUnit_type()){
            case Unit.MEN_AT_ARMS:
                Rectangle ref = indexToPixelCoord(unit.getxCoord(), unit.getyCoord());
                g.drawImage(sprites.men_at_arms, ref.x, ref.y, null);
                break;
            case Unit.BOWMAN:

            case Unit.BALLISTA:

            case Unit.TREBUCHET:
        }
    }
    
    public void drawMap(Graphics g){
        for(int i = 0; i < terrain.length; i++){
            for(int j = 0; j < terrain[0].length; j++){
                if (terrain[i][j].getTerrain_type() == Terrain.FOREST){
                    g.setColor(Color.GREEN);
                    drawRect(i, j, g);
                }else if(terrain[i][j].getTerrain_type() == Terrain.HILLS){
                    g.setColor(Color.GRAY);
                    drawRect(i, j, g);
                }else{
                    System.out.println("Tile does not have any terrain!");
                }
            }
        }

        drawUnits(g);
        drawCursor(g);
    }

    private void drawRect(int x, int y, Graphics g){
        g.fillRect(rectWidth*x,rectHeight*y,rectWidth,rectHeight);
    }

    private void drawCursor(Graphics g){
        Rectangle reference = indexToPixelCoord(cursor.getxCoord(), cursor.getyCoord());
        g.drawImage(sprites.tile_cursor, reference.x, reference.y, null);
    }

    /**
     * Given indexes for a map coordinate (0,0 is top left square), returns the square (Rectangle) that covers the area
     * @param x X-coordinate for the tile
     * @param y Y-coordinate for the tile
     */
    // TODO: Make private
    private Rectangle indexToPixelCoord(int x, int y){
        return new Rectangle(rectWidth*x,rectHeight*y,rectWidth,rectHeight);
    }

    /**
     * makes the cursor select a unit.
     */
    public void selectUnit(){
        if(units[cursor.getxCoord()][cursor.getyCoord()] != null && cursor.selectedUnit == null){
            cursor.selectedUnit = units[cursor.getxCoord()][cursor.getyCoord()];
            System.out.println("Selected unit: " + cursor.selectedUnit.getUnit_type());
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

}
