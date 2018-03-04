import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class ImageEditor {

    // Variables
    private String input_filename;  // Input filename
    private File input_file;        // Input file

    private int width;              // Width
    private int height;             // Height

    private JFrame frame;           // Frame
    private BufferedImage image;    // Buffered image
    private ImagePanel imagePanel;  // Image JPanel

    private ActionManager am;


    // Constructor
    public ImageEditor(String filename){

        this.input_filename = filename;
        this.input_file = new File(input_filename);
        am = new ActionManager(image);
        initUI();
        readImage(input_file);

    }


    /* Main method */
    public static void main(String[] args){
        ImageEditor ie = new ImageEditor(args[0]);
    }


    // Read image
    public void readImage(File file){
        try {
            // Get image
            this.image = ImageIO.read(file);
            this.input_file = file;
            this.input_filename = file.getName();
            frame.setIconImage(image);

            // Get height and width
            width = image.getWidth();
            height = image.getHeight();
            System.out.println("Successfully read input file.");
            System.out.println("Height: " + height);
            System.out.println("Width: " + width);

            // Add to image panel
            addImage();

        } catch (IOException e){
            System.out.println("Error: Can't read input file!");
            System.exit(0);
        } catch (IllegalArgumentException e){
        }
    }


    // Write image
    public void writeImage(File output_file){
        try {
            // Write to output file
            ImageIO.write(image, "jpg", output_file);
            System.out.println("Successfully wrote output file: " + output_file.getName());

        } catch (IOException e){
            System.out.println("Error: Can't write output file!");
        } catch (IllegalArgumentException e){
        }
    }


    // Create GUI
    public void initUI() {

        // Create JFrame
        frame = new JFrame("Image Editor v0.0");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create menu bar
        frame.setJMenuBar(new MenuBar(this).create());

    }


    // Display image
    public void addImage() {

        // Create JPanel
        imagePanel = new ImagePanel(image);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

        // Resize window
        frame.pack();
        frame.setSize(width, height);
        frame.setVisible(true);
    }


    // Clear image
    public void clearPanel() {
        frame.remove(imagePanel);
    }


    // Get input file
    public File getInputFile(){
        return input_file;
    }


    // Get input file
    public BufferedImage getImage(){
        return image;
    }


    // Perform action
    public void performAction(BufferedImage image){
        am.add(image);
        this.image = image;
        imagePanel.setImage(image);
    }


    // Undo
    public void undo() {

    }


    // Redo
    public void redo() {

    }

}