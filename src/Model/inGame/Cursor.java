package Model.inGame;

public class Cursor {
    private int xCoord;
    private int yCoord;
    private Map map;

    //  TODO: Make selectedUnit getter/setter
    private Unit selectedUnit;

    public Cursor(int xCoord, int yCoord, Map map) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.map = map;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        if(selectedUnit == null){
            map.cleanMovementMap();
        }else{
            map.calcMovement(selectedUnit.getxCoord(), selectedUnit.getyCoord(), 'n', selectedUnit.getMovement());
        }
        this.selectedUnit = selectedUnit;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
