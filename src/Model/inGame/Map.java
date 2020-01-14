package Model.inGame;

import Images.Sprites;

import java.awt.*;
import java.util.Arrays;


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
    private Boolean [][] availableMovement;
    public Cursor cursor;

    private int rectWidth;
    private int rectHeight;


    public Map(int height, int width, int frameHeight, int frameWidth) {
        this.height = height;
        this.width = width;
        this.terrain = new Terrain [width][height];
        this.buildings = new Building[width][height];
        this.units = new Unit[width][height];
        this.availableMovement = new Boolean[width][height];
        // Initialize matrix values to false
        cleanMovementMap();
        this.cursor = new Cursor(0,0, this);

        sprites = new Sprites();

        rectWidth = frameWidth/width;
        rectHeight = frameHeight/height;


        // Temporary fill terrain (test)
        for(int i = 0; i < terrain.length; i++){
            for(int j = 0; j < terrain[0].length; j++){
                if ((i+j)%2 == 0){
                    terrain[i][j] = new Terrain(3,1, Terrain.FOREST);
                }else{
                    terrain[i][j] = new Terrain(3,1, Terrain.HILLS);
                }
            }
        }

        // Temporary create units (test)
        units[4][2] = new Unit(10,5, Unit.MEN_AT_ARMS, 4,2, 0, 4);
        units[10][10] = new Unit(10,5, Unit.MEN_AT_ARMS, 10,10, 1, 4);
        units[15][8] = new Unit(10,5, Unit.MEN_AT_ARMS, 15,8, 2, 4);

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
        if(cursor.getSelectedUnit() != null){ drawMovement(g);}
        drawCursor(g);
    }

    private void drawMovement(Graphics g){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(availableMovement[x][y]){
                    Rectangle ref = indexToPixelCoord(x,y);
                    g.drawImage(sprites.available_movement, ref.x, ref.y, null);
                }
            }
        }
    }

    // TODO: Refactor, implement better algorithm and cleaner code. (Maybe same algorithm but with initializer function?)
    public void calcMovement(int x, int y, char from, int movement){
        if(movement < 0){
            return;
        }
        switch (from){
            case 'n':
                if(x != 0){
                    calcMovement(x-1,y,'r', movement);
                }if(y != 0){
                calcMovement(x,y-1,'d', movement);
                }if(x != width-1){
                    calcMovement(x+1,y,'l', movement);
                }if(y != height-1){
                    calcMovement(x,y+1,'u', movement);
                }
                break;
            case 'u':
                availableMovement[x][y] = true;
                if(x != 0){
                    calcMovement(x-1,y,'r', movement - terrain[x][y].getMovement_penalty());
                }if(x != width-1){
                    calcMovement(x+1,y,'l', movement - terrain[x][y].getMovement_penalty());
                }if(y != height-1){
                    calcMovement(x,y+1,'u', movement - terrain[x][y].getMovement_penalty());
                }
                break;
            case 'd':
                availableMovement[x][y] = true;
                if(x != 0){
                    calcMovement(x-1,y,'r', movement - terrain[x][y].getMovement_penalty());
                }if(y != 0){
                    calcMovement(x,y-1,'d', movement - terrain[x][y].getMovement_penalty());
                }if(x != width-1){
                    calcMovement(x+1,y,'l', movement - terrain[x][y].getMovement_penalty());
                }
                break;
            case 'l':
                availableMovement[x][y] = true;
                if(y != 0){
                    calcMovement(x,y-1,'d', movement - terrain[x][y].getMovement_penalty());
                }if(x != width-1){
                    calcMovement(x+1,y,'l', movement - terrain[x][y].getMovement_penalty());
                }if(y != height-1){
                    calcMovement(x,y+1,'u', movement - terrain[x][y].getMovement_penalty());
                }
                break;
            case 'r':
                availableMovement[x][y] = true;
                if(x != 0){
                    calcMovement(x-1,y,'r', movement - terrain[x][y].getMovement_penalty());
                }if(y != 0){
                calcMovement(x,y-1,'d', movement - terrain[x][y].getMovement_penalty());
                }if(y != height-1){
                    calcMovement(x,y+1,'u', movement - terrain[x][y].getMovement_penalty());
                }
                break;
            default:
                System.out.println("calcMovement has gone rouge!");
        }

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
        if(units[cursor.getxCoord()][cursor.getyCoord()] != null && cursor.getSelectedUnit() == null){
            cursor.setSelectedUnit(units[cursor.getxCoord()][cursor.getyCoord()]);
            System.out.println("Selected unit: " + cursor.getSelectedUnit().getUnit_type());
        }
    }

    /**
     * Moves a selected unit to the current position of the cursor.
     * @return True if move was completed (valid)
     */
    public Boolean moveUnit(){
        //TODO: Add check if move is valid (probably with help of BFS)

        // If there is room for the unit
        if((units[cursor.getxCoord()][cursor.getyCoord()] == null) & availableMovement[cursor.getxCoord()][cursor.getyCoord()]){
            units[cursor.getSelectedUnit().getxCoord()][cursor.getSelectedUnit().getyCoord()] = null;
            units[cursor.getxCoord()][cursor.getyCoord()] = cursor.getSelectedUnit();
            cursor.getSelectedUnit().setxCoord(cursor.getxCoord());
            cursor.getSelectedUnit().setyCoord(cursor.getyCoord());
            cursor.setSelectedUnit(null);
        }else if(!availableMovement[cursor.getxCoord()][cursor.getyCoord()]){
            cursor.setSelectedUnit(null);
            System.out.println("Invalid movement!");
        }else if(isEnemy(cursor.getSelectedUnit(), units[cursor.getxCoord()][cursor.getyCoord()])){
            attackUnit(cursor.getSelectedUnit(), units[cursor.getxCoord()][cursor.getyCoord()]);
        }else{
            System.out.println("Unknown movement_error!");
        }
        return true;
    }

    /**
     * Tells if the units are enemies (and therefore attackable)
     * @param attacker The unit who wished to attack
     * @param defender The unit who it wants to attack
     * @return true if they are enemies
     */
    private Boolean isEnemy(Unit attacker, Unit defender){
        // TODO: Add a team system
        return (attacker.getFaction() != defender.getFaction());
    }

    public void attackUnit(Unit attacker, Unit defender){
        if(defender.takeDamage(attacker.getAttack_value()) <= 0){
            killUnit(defender);
            // If not ranged, move unit
            if(attacker.getUnit_type() == Unit.MEN_AT_ARMS){
                moveUnit();
            }
        }else{
            System.out.println("Soldier has " + defender.getHealth_points() + " HP left.");
        }
    }

    private void killUnit(Unit unit){
        units[unit.getxCoord()][unit.getyCoord()] = null;
    }

    public void cleanMovementMap(){
        for (Boolean [] bol: availableMovement
        ) {
            Arrays.fill(bol, false);
        }
    }
}
