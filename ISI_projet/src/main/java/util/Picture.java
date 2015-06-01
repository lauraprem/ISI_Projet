package util;

/**
 * @author Alexandre
 *         01/06/2015
 */
public enum Picture {
    BACKGROUND("mapsixieme.jpg"),
    ROBOT_CHENILLE("robotChenilles.gif"),
    ROBOT_TOUT_TERRAIN("RobotToutTerrain.gif"),
    ROBOT_A_PATES("robotAPattes.gif"),
    FIRE("Feu.gif");
    private String url;
    private static String dir = "pictures";

    Picture(String url) {
        this.url = url;
    }

    public String getURL() {
        return Picture.class.getClassLoader().getResource(dir + '/' + url).getFile();
    }
}
