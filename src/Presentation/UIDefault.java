package Presentation;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * Classe per tenir la UI predeterminada
 */
public class UIDefault {
    /**
     * Constructor que inicia la configuracio.
     */
    public UIDefault() {
        UIManager();
    }

    /**
     * Funcio que te totes les propietats.
     */
    private void UIManager() {
        UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 100));
        UIManager.put("OptionPane.background", new ColorUIResource(20, 20, 20));
        UIManager.put("Panel.background", new ColorUIResource(20, 20, 20));
        UIManager.put("OptionPane.messageFont", new Font("Apple Casual", Font.BOLD, 12));
        UIManager.put("OptionPane.buttonFont", new Font("Apple Casual", Font.BOLD, 12));
        UIManager.put("Button.background", new Color(253, 202, 205));
        // UIManager.put("Button.foreground", Color.white);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
    }
}
