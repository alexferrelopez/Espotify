package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe ens permet crear JText fields customitzats.
 */
public class EJTextField extends JTextField {
    /**
     * Constructor de la classe
     *
     * @param columns               Columnes que ha de tenir
     * @param text                  Quin text hi posarem
     * @param isCheckFields         Comprovar els camps.
     * @param isMusicFields         Camps de musica.
     * @param isLoginRegisterFields Si es de login o register
     * @param isLogin               Si es de login.
     */
    public EJTextField(int columns, String text, boolean isCheckFields, boolean isMusicFields, boolean isLoginRegisterFields, boolean isLogin) {
        super(text, columns);
        decorateFields(isCheckFields, isMusicFields, isLoginRegisterFields, isLogin);
    }

    /**
     * Constructor de la classe
     *
     * @param columns               Columnes que ha de tenir.
     * @param isCheckFields         Comprovar els camps.
     * @param isMusicFields         Camps de musica.
     * @param isLoginRegisterFields Si es de login o register
     * @param isLogin               Si es de login.
     */
    public EJTextField(int columns, boolean isCheckFields, boolean isMusicFields, boolean isLoginRegisterFields, boolean isLogin) {
        super(columns);
        decorateFields(isCheckFields, isMusicFields, isLoginRegisterFields, isLogin);
    }

    private void decorateFields(boolean isCheckFields, boolean isMusicFields, boolean isLoginRegisterFields, boolean isLogin) {
        setFont(new Font("Apple Casual", Font.BOLD, 12));
        setForeground(new Color(175, 175, 175));
        setBackground(Color.darkGray);
        setOpaque(true);

        if (isCheckFields) {
            setMinimumSize(new Dimension(50, 10));
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        } else if (isLoginRegisterFields) {
            setMaximumSize(new Dimension(300, 40));
            if (isLogin) {
                setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
            } else {
                setBorder(BorderFactory.createEmptyBorder(8, 25, 10, 0));
            }

        } else if (isMusicFields) {
            setFont(new Font("Apple Casual", Font.BOLD, 14));
            setMaximumSize(new Dimension(300, 200));
            setBorder(BorderFactory.createEmptyBorder(8, 25, 0, 0));
        }
    }

}
