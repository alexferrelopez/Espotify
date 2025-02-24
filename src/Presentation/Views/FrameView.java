package Presentation.Views;

import Business.UserManager;
import Presentation.UIDefault;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

/**
 * Aquest es el principal controller, aqui ajuntarem totes les vistes amb els seus respectius controllers per els listeners, els panels amb les cards, es com el centre
 * de mando.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class FrameView extends JFrame {

    public static final String changeToLoginView = "changeToLoginView";
    public static final String changeToRegisterView = "changeToReGisterView";
    public static final String changeToMainGUIView = "changeToMainGUIView";
    public static final String changeToGestionCuentaView = "changeToGestionCuentaView";
    public static final String changeToEstadisticasView = "changeToEstadisticasView";
    public static final String changeToMisListasView = "changeToMisListasView";
    public static final String changeToListasDeOtrosUsuariosView = "changeToListasDeOtrosUsuariosView";
    public static final String changeToInfoCancionView = "changeToInfoCancionView";
    public static final String changeToListMyMusicView = "changeToListOtherMusicView";
    public static final String changeToMyMusicDataView = "changeToOtherMusicDataView";
    public static final String changeToListOtherMusicView = "changeToListMyMusicView";
    public static final String changeToOtherMusicDataView = "changeToMyMusicDataView";
    public static final String changeToAddSongToProjectView = "changeToAddSongToProjectView";
    public static final String changeToAddSongToListView = "changeToAddSongToPlaylistView";


    private static final String registerViewS = "RegisterView";
    private static final String loginViewS = "LoginView";
    private static final String mainGUIViewS = "MainGUIView";
    private static final String welcomeView = "WelcomeView";
    private static final String gestionCuentaViewS = "GestionCuentaView";
    private static final String estadisticasViewS = "EstadisticasView";
    private static final String misListasViewS = "MisListasView";
    private static final String listasDeOtrosUsuariosViewS = "ListasDeOtrosUsuariosView";
    private static final String infoCancionViewS = "InfoCancionView";
    private static final String addSongToProjectViewS = "AddSongView";
    private static final String listMyMusicViewS = "ListMyMusicView";
    private static final String myMusicDataViewS = "MyMusicDataView";
    private static final String listOtherMusicViewS = "ListOtherMusicView";
    private static final String otherMusicDataViewS = "OtherMusicDataView";
    private static final String cancionToPlaylistViewS = "CancionToPlaylistView";
    private static final int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;         //Ancho pantalla
    private static final int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;       //Alto pantalla
    private final CardLayout cards;
    private final CardLayout initialCards;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final HomeView homeView;
    private final AccountManagementView accountManagementView;
    private final StatisticsView viewStatistics;
    private final MyPlaylistView myPlaylistView;
    private final OtherPlaylistView otherPlaylistView;
    private final SoundbarView soundbarView;
    private final MyMusicListView myMusicListView;
    private final MyMusicDataView myMusicDataView;
    private final OtherMusicDataView otherMusicDataView;
    private final OtherMusicListView otherMusicListView;
    private final JPanel mainPanel;
    private final MenuView menuView;
    private final SongDataView songDataView;
    private final UserManager userManager;
    private final AddSongToProjectView addSongToProjectView;
    private final AddSongToListView addSongToListView;
    private JPanel initialPanel;

    public FrameView(RegisterView registerView, LoginView loginView, HomeView homeView,
                     AccountManagementView accountManagementView, StatisticsView viewStatistics,
                     MyPlaylistView myPlaylistView, OtherPlaylistView otherPlaylistView,
                     SoundbarView soundbarView, AddSongToProjectView addSongToProjectView, AddSongToListView addSongToListView, MyMusicListView myMusicListView,
                     MyMusicDataView myMusicDataView, MenuView menuView, SongDataView songDataView,
                     UserManager userManager, OtherMusicDataView otherMusicDataView, OtherMusicListView otherMusicListView) {
        new UIDefault();
        this.registerView = registerView;
        this.loginView = loginView;
        this.homeView = homeView;
        this.accountManagementView = accountManagementView;
        this.viewStatistics = viewStatistics;
        this.myPlaylistView = myPlaylistView;
        this.otherPlaylistView = otherPlaylistView;
        this.addSongToProjectView = addSongToProjectView;
        this.addSongToListView = addSongToListView;
        this.myMusicListView = myMusicListView;
        this.myMusicDataView = myMusicDataView;
        this.otherMusicListView = otherMusicListView;
        this.otherMusicDataView = otherMusicDataView;
        this.menuView = menuView;
        this.soundbarView = soundbarView;
        this.songDataView = songDataView;
        this.cards = new CardLayout();
        this.mainPanel = new JPanel(cards);
        this.initialCards = new CardLayout();
        this.userManager = userManager;

        configureWindow();
    }

    private void configureWindow() {
        setTitle("Welcome to Espotyfai");                                            // Titulo
        setSize(widthScreen, heightScreen);                                         //Tamaño de la ventana inicial
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             // Cerrar java cuando se cierre ventana
        setLocationRelativeTo(null);                                                // Mostrar ventana en el centro
        //setUndecorated(true);

        initialPanel = new JPanel(initialCards);
        JPanel inAppView = new JPanel();
        inAppView.setLayout(new BorderLayout());

        mainPanel.add(homeView, mainGUIViewS);
        mainPanel.add(accountManagementView, gestionCuentaViewS);
        mainPanel.add(viewStatistics, estadisticasViewS);
        mainPanel.add(myPlaylistView, misListasViewS);
        mainPanel.add(otherPlaylistView, listasDeOtrosUsuariosViewS);
        mainPanel.add(songDataView, infoCancionViewS);
        mainPanel.add(addSongToProjectView, addSongToProjectViewS);
        mainPanel.add(myMusicListView, listMyMusicViewS);
        mainPanel.add(myMusicDataView, myMusicDataViewS);
        mainPanel.add(otherMusicListView, listOtherMusicViewS);
        mainPanel.add(otherMusicDataView, otherMusicDataViewS);
        mainPanel.add(addSongToListView, cancionToPlaylistViewS);


        inAppView.add(mainPanel, BorderLayout.CENTER);
        inAppView.add(soundbarView, BorderLayout.SOUTH);
        inAppView.add(menuView, BorderLayout.WEST);

        initialPanel.add(loginView, loginViewS);
        initialPanel.add(registerView, registerViewS);
        initialPanel.add(inAppView, welcomeView);
        //getContentPane().add(mainPanel);

        add(initialPanel);
        //pack();
        setVisible(true);
        requestFocusInWindow();
    }

    public void uiControllers(EventListener musicPlayerController, EventListener registerController,
                              EventListener loginController, EventListener mainGUIController,
                              EventListener gestionCuentaController, EventListener statisticsController,
                              EventListener misListasController, EventListener allPlaylistController,
                              EventListener addSongToProjectController, EventListener addSongToListController, EventListener westMenuController,
                              EventListener infoCancionController, EventListener listMyMusicController, EventListener myMusicDataController, EventListener otherMusicDataController, EventListener otherMusicListController) {
        soundbarView.soundbarListener(musicPlayerController);
        registerView.registerListener(registerController);
        loginView.loginListener(loginController);
        homeView.viewInitialListener(mainGUIController);
        accountManagementView.AccountManagementListener(gestionCuentaController);
        viewStatistics.statisticsListener(statisticsController);
        myPlaylistView.playlistListener(misListasController);
        otherPlaylistView.allPlaylistListener(allPlaylistController);
        addSongToProjectView.addSongListener(addSongToProjectController);
        addSongToListView.addSongToListListener(addSongToListController);
        menuView.menuListener(westMenuController);
        songDataView.viewInfoSongListener(infoCancionController);
        myMusicListView.viewMyMusicListListener(listMyMusicController);
        myMusicDataView.viewMyMusicDataListener(myMusicDataController);
        otherMusicDataView.viewOtherMusicDataListener(otherMusicDataController);
        otherMusicListView.viewOtherMusicListListener(otherMusicListController);
    }

    // Cards
    private void changeToRegister() {
        initialCards.show(initialPanel, registerViewS);
    }

    private void changeToLogin() {
        initialCards.show(initialPanel, loginViewS);
    }

    private void changeToInitial() {
        cards.show(mainPanel, mainGUIViewS);
        initialCards.show(initialPanel, welcomeView);
    }

    private void changeToAccountManagement() {
        cards.show(mainPanel, gestionCuentaViewS);
    }

    private void changeToStatistics() {
        cards.show(mainPanel, estadisticasViewS);
    }

    private void changeToPlaylist() {
        cards.show(mainPanel, misListasViewS);
    }

    private void changeToAllPlaylist() {
        cards.show(mainPanel, listasDeOtrosUsuariosViewS);
    }

    private void changeToInfoCancion() {
        cards.show(mainPanel, infoCancionViewS);
    }

    private void changeToAddSongToProject() {
        cards.show(mainPanel, addSongToProjectViewS);
    }

    private void changeToListMyMusic() {
        cards.show(mainPanel, listMyMusicViewS);
    }

    private void changeToMyMusicData() {
        cards.show(mainPanel, myMusicDataViewS);
    }


    private void changeToListOtherMusic() {
        cards.show(mainPanel, listOtherMusicViewS);
    }


    private void changeToOtherMusicData() {
        cards.show(mainPanel, otherMusicDataViewS);
    }

    private void changeToAddSongToList() {
        cards.show(mainPanel, cancionToPlaylistViewS);
    }

    public void changePanel(String panel) {
        switch (panel) {
            case changeToLoginView -> changeToLogin();
            case changeToRegisterView -> changeToRegister();
            case changeToMainGUIView -> changeToInitial();
            case changeToGestionCuentaView -> changeToAccountManagement();
            case changeToEstadisticasView -> changeToStatistics();
            case changeToMisListasView -> changeToPlaylist();
            case changeToListasDeOtrosUsuariosView -> changeToAllPlaylist();
            case changeToInfoCancionView -> changeToInfoCancion();
            case changeToAddSongToProjectView -> changeToAddSongToProject();
            case changeToAddSongToListView -> changeToAddSongToList();
            case changeToListMyMusicView -> changeToListMyMusic();
            case changeToMyMusicDataView -> changeToMyMusicData();
            case changeToListOtherMusicView -> changeToListOtherMusic();
            case changeToOtherMusicDataView -> changeToOtherMusicData();
        }
    }

    public int getIdUser() {
        return userManager.getIdUser();
    }
}