package Model.mainMenu;

public class MenuCursor {
    private int markedButton;

    public MenuCursor(int markedButton) {
        this.markedButton = markedButton;
    }

    public void setMarkedButton(int markedButton) {
        this.markedButton = markedButton;
    }

    public int getMarkedButton() {
        return markedButton;
    }
}
