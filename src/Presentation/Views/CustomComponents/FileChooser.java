package Presentation.Views.CustomComponents;

import javax.swing.*;

/**
 * Aquesta custom class ens permet configurar el file chooser
 */
public class FileChooser {
    /**
     * Escollir el filechooser
     *
     * @param chooser filechooser
     * @return Retorna quin utilitzarem.
     */
    public static JFileChooser windowsJFileChooser(JFileChooser chooser) {
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            chooser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException |
                 ClassNotFoundException ignored) {
        }
        return chooser;
    }
}
