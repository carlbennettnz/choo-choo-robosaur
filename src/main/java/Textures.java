import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {

    public static final BufferedImage BACKDROP = loadImage("backdrop");
    public static final BufferedImage CRATE = loadImage("crate1");
    public static final BufferedImage DOOR = loadImage("door");
    public static final BufferedImage KEY = loadImage("key");
    public static final BufferedImage ROBOSAUR = loadImage("robosaur");
    public static final BufferedImage ROBOT = loadImage("robot");

    private static BufferedImage loadImage(String name){
        try {
            return ImageIO.read(new File("src/main/res/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(name + " failed to load");
        }
    }
}
