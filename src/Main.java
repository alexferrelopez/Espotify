import Business.*;
import Persistence.SQL.SQLConnector;
import Presentation.Controllers.*;
import Presentation.Views.*;

class Main {

    public static void main(String[] args) {
        SQLConnector.getInstance();
        //Manager
        UserManager userManager = new UserManager();
        MusicManager musicManager = new MusicManager();
        PlaylistManager playlistManager = new PlaylistManager();
        SingerManager singerManager = new SingerManager();
        MusicPlayerManager musicPlayerManager = new MusicPlayerManager();

        //View
        RegisterView registerView = new RegisterView();
        LoginView loginView = new LoginView();
        MenuView menuView = new MenuView();
        HomeView homeView = new HomeView();
        MyMusicListView myMusicListView = new MyMusicListView();
        AccountManagementView accountManagementView = new AccountManagementView();
        MyPlaylistView myPlaylistView = new MyPlaylistView();
        MyMusicDataView myMusicDataView = new MyMusicDataView();
        AddSongToProjectView addSongToProjectView = new AddSongToProjectView();
        AddSongToListView addSongToListView = new AddSongToListView();
        StatisticsView viewEstadisticas = new StatisticsView();
        OtherPlaylistView otherPlaylistView = new OtherPlaylistView();
        SongDataView songDataView = new SongDataView();
        OtherMusicListView otherMusicListView = new OtherMusicListView();
        OtherMusicDataView otherMusicDataView = new OtherMusicDataView();
        SoundbarView soundbarView = new SoundbarView();

        FrameView frameView = new FrameView(registerView, loginView, homeView,
                accountManagementView, viewEstadisticas, myPlaylistView, otherPlaylistView,
                soundbarView, addSongToProjectView, addSongToListView, myMusicListView,
                myMusicDataView, menuView, songDataView, userManager, otherMusicDataView, otherMusicListView);

        //Controller
        MusicPlayerController musicPlayerController = new MusicPlayerController(soundbarView, musicPlayerManager);
        SongDataController songDataController = new SongDataController(musicPlayerController, frameView, songDataView, musicManager, playlistManager);
        HomeController homeController = new HomeController(frameView, homeView, musicManager, songDataController);
        AddSongToProjectController addSongToProjectController = new AddSongToProjectController(frameView, musicPlayerController, homeController, addSongToProjectView, musicManager, singerManager);
        AddSongToListController addSongToListController = new AddSongToListController(frameView, musicManager, playlistManager, addSongToListView);
        RegisterController registerController = new RegisterController(frameView, homeController, userManager, registerView);
        LoginController loginController = new LoginController(frameView, homeController, loginView, userManager);
        AccountManagementController accountManagementController = new AccountManagementController(frameView, loginController, registerController, musicPlayerController, accountManagementView, userManager, musicManager);
        StatisticsController statisticsController = new StatisticsController(frameView, musicManager, viewEstadisticas);
        MyMusicDataController myMusicDataController = new MyMusicDataController(musicPlayerController, frameView, myMusicDataView, playlistManager, musicManager);
        OtherMusicDataController otherMusicDataController = new OtherMusicDataController(musicPlayerController, frameView, otherMusicDataView, playlistManager, musicManager);
        MyMusicListController myMusicListController = new MyMusicListController(frameView, myMusicListView, musicPlayerController, musicManager, myMusicDataController, addSongToListController);
        OtherMusicListController otherMusicListController = new OtherMusicListController(frameView, otherMusicListView, musicPlayerController, musicManager, otherMusicDataController);
        MyPlaylistController myPlaylistController = new MyPlaylistController(frameView, myMusicListController, myPlaylistView, playlistManager);
        OtherPlaylistController otherPlaylistController = new OtherPlaylistController(frameView, otherPlaylistView, playlistManager, otherMusicListController);
        MenuController menuController = new MenuController(frameView, homeController, addSongToProjectController, myPlaylistController, otherPlaylistController, statisticsController);

        songDataController.setHomeController(homeController);
        myMusicDataController.setMyMusicListController(myMusicListController);
        //Asignar Controller a la vista

        frameView.uiControllers(musicPlayerController, registerController, loginController, homeController,
                accountManagementController, statisticsController, myPlaylistController, otherPlaylistController,
                addSongToProjectController, addSongToListController, menuController, songDataController, myMusicListController, myMusicDataController, otherMusicDataController, otherMusicListController);
    }
}
