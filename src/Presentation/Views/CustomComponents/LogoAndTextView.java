package Presentation.Views.CustomComponents;

import Presentation.Url;

import javax.swing.*;

/**
 * Aquesta custom class ens serveix per la vista del Text
 */
public class LogoAndTextView extends JPanel {
    /**
     * Seteja com han de ser aquests components
     *
     * @param lowerText Text de sota
     */
    public LogoAndTextView(String lowerText, int option) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel logo = new EJLabel("Espotyfai", 30, 255, 255, 255, 10, true);
        logo.setIcon(new NoScalingIcon(new ImageIcon(Url.urlLogo)));

        JLabel slogan = new EJLabel(lowerText, 23, 255, 255, 255, 50, true);

        if (option == 1) {
            add(new JLabel(" "));
        }

        add(logo);

        if (option == 1) {
            add(new JLabel(" "));
            add(slogan);
        }
    }
}
