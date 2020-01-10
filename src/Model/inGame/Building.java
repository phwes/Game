package Model.inGame;

public class Building {
    private String building_type;
    private int income;
    private int fortification;

    public Building(String building_type, int income, int fortification) {
        this.building_type = building_type;
        this.income = income;
        this.fortification = fortification;
    }

    public String getBuilding_type() {
        return building_type;
    }

    public int getIncome() {
        return income;
    }

    public int getFortification() {
        return fortification;
    }
}
