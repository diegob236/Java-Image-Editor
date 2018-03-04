import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.DataBufferByte;

public class MenuBar {

    ImageEditor ie;

    // Constructor
    public MenuBar(ImageEditor ie){
        this.ie = ie;
    }


    // Create menu bar
    public JMenuBar create() {

        // Menu bar
        JMenuBar menubar = new JMenuBar();

        // File
        JMenu file = new JMenu("File");

        // File -> Open
        JMenuItem open = new JMenuItem("Open");
        open.setToolTipText("Open image");
        open.addActionListener((ActionEvent event) -> {
            final JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(open);
            ie.clearPanel();
            ie.readImage(fc.getSelectedFile());
        });

        // File -> Save
        JMenuItem save = new JMenuItem("Save");
        save.setToolTipText("Save image");
        save.addActionListener((ActionEvent event) -> {
            ie.writeImage(ie.getInputFile());
        });

        // File -> Save As
        JMenuItem saveas = new JMenuItem("Save As");
        saveas.setToolTipText("Save image as another file");
        saveas.addActionListener((ActionEvent event) -> {
            final JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(save);
            ie.writeImage(fc.getSelectedFile());
        });

        // File -> Exit
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("Exit application");
        exit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        // Add File to menubar
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(exit);
        menubar.add(file);

        // Edit
        JMenu edit = new JMenu("Edit");

        // Edit -> Undo
        JMenuItem undo = new JMenuItem("Undo");
        undo.setEnabled(false);
        undo.addActionListener((ActionEvent event) -> {
            ie.undo();
        });

        // Edit -> Redo
        JMenuItem redo = new JMenuItem("Redo");
        redo.setEnabled(false);
        redo.addActionListener((ActionEvent event) -> {
            ie.redo();
        });

        // Edit -> Transform
        JMenu transform = new JMenu("Transform");

        // Edit -> Transform -> Posterize
        JMenuItem posterize = new JMenuItem("Posterize");
        posterize.setToolTipText("Posterize image");
        posterize.addActionListener((ActionEvent event) -> {
            ie.performAction(Effects.posterize(ie.getImage()));
        });

        // Add Edit to menu bar
        edit.add(undo);
        edit.add(redo);
        transform.add(posterize);
        edit.add(transform);
        menubar.add(edit);

        // Return menu bar
        return menubar;

    }

}
