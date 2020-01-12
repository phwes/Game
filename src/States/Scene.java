package States;

/**
 * Keeps track on what scene is displayed
 */

public class Scene {
    private int scene;
    public static final int MAP_SCENE = 1;

    public Scene(int scene) {
        this.scene = scene;
    }

    public int getSceneName() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }
}
