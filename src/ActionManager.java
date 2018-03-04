import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ActionManager implements Runnable {

    LinkedList<BufferedImage> actions;

    public ActionManager(BufferedImage image) {
        actions = new LinkedList<BufferedImage>();
        actions.add(image);
    }

    public void add(BufferedImage image){
        actions.add(image);
    }

    @Override
    public void run() {
        while (true){

        }
    }

}
