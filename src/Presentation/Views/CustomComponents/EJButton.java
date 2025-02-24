package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que ens permet customitzar el JButton
 */
public class EJButton extends JButton {
    /**
     * Constructor nombre 1
     *
     * @param isBotonGenerico decideixes si es generic o no
     * @param isMenuBar       Es la barra del menu.
     */
    public EJButton(boolean isBotonGenerico, boolean isMenuBar) {
        decorate(isBotonGenerico, isMenuBar);
    }

    /**
     * Constructor nombre 2
     *
     * @param text            Text del boto.
     * @param isBotonGenerico Es generic?
     * @param isMenuBar       Es la barra del menu
     */
    public EJButton(String text, boolean isBotonGenerico, boolean isMenuBar) {
        super(text);
        decorate(isBotonGenerico, isMenuBar);
    }

    /**
     * Constructor nombre 3
     *
     * @param text                Text del boto
     * @param icon                Icone del boto
     * @param isBotonGenerico     Es un boto generic?.
     * @param isMenuBar           Es la del menu?.
     * @param isAccountManagement Es management de account?.
     */
    public EJButton(String text, Icon icon, boolean isBotonGenerico, boolean isMenuBar, boolean isAccountManagement) {
        super(text, icon);
        decorate(isBotonGenerico, isMenuBar);
        if (isAccountManagement) {
            setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 30));
        }
    }

    /**
     * Decorar el boto segons convingui
     *
     * @param isBotonGenerico Es generic?
     * @param isMenuBar       Es del menu?
     */
    private void decorate(boolean isBotonGenerico, boolean isMenuBar) {
        setFont(new Font("Apple Casual", Font.BOLD, 15));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.white);
        setBorder(BorderFactory.createEmptyBorder());
        if (isBotonGenerico) {
            setHorizontalTextPosition(SwingConstants.CENTER);
        } else if (isMenuBar) {
            setFont(new Font("Apple Casual", Font.BOLD, 12));
        }
    }
}
