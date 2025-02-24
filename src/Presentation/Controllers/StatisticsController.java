package Presentation.Controllers;

import Business.MusicManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe es la corresponent al controller de les estadístiques que anira enllaçat amb la seva corresponent vista.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class StatisticsController implements ActionListener {
    private final FrameView frameView;
    private final MusicManager musicManager;
    private final StatisticsView statisticsView;

    /**
     * Al constructor de aquest controller li enviarem el controller general per a respectar el WRASP, music manager i la seva vista de estadistiques
     *
     * @param frameView      Controller general del nostre programa
     * @param musicManager   El manager de la musica.
     * @param statisticsView La vista de les estadístiques.
     */
    public StatisticsController(FrameView frameView, MusicManager musicManager, StatisticsView statisticsView) {
        this.frameView = frameView;
        this.musicManager = musicManager;
        this.statisticsView = statisticsView;
    }

    /**
     * Aquesta funcio es la corresponent al action listener de la vista que ens permet canviar a gestionar el compte.
     *
     * @param e Li passarem l'event que ha detectat el canvi.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Try.tryGestionCuenta.equals(e.getActionCommand())) {
            frameView.changePanel(FrameView.changeToGestionCuentaView);
        }
    }

    /**
     * Aquesta funcio ens permet actualitzar les estadistiques.
     */
    public void listGender() {
        statisticsView.updateStatistics(musicManager.listGender());
    }
}
