create table dpoo_2122_s2_c2.Singer
(
    ID_Singer int auto_increment
        primary key,
    Name      text not null
)
    engine = InnoDB;

create table dpoo_2122_s2_c2.User
(
    ID_User  int auto_increment
        primary key,
    Login    text not null,
    Email    text not null,
    Password text not null
)
    engine = InnoDB;

create table dpoo_2122_s2_c2.Music
(
    ID_Music  int auto_increment
        primary key,
    ID_Owner  int  not null,
    ID_Singer int  not null,
    Name      text not null,
    Gender    text not null,
    Album     text not null,
    URL       text not null,
    constraint Owner
        foreign key (ID_Singer) references dpoo_2122_s2_c2.Singer (ID_Singer)
            on update cascade on delete cascade,
    constraint User
        foreign key (ID_Owner) references dpoo_2122_s2_c2.User (ID_User)
            on update cascade on delete cascade
)
    engine = InnoDB;

create index Singer
    on dpoo_2122_s2_c2.Music (ID_Singer);

create table dpoo_2122_s2_c2.PlayList
(
    ID_PlayList int auto_increment
        primary key,
    ID_User     int  not null,
    Name        text not null,
    constraint PlayList_ibfk_1
        foreign key (ID_User) references dpoo_2122_s2_c2.User (ID_User)
            on update cascade on delete cascade
)
    engine = InnoDB;

create table dpoo_2122_s2_c2.MusicPlayList
(
    ID_PlayList int not null,
    ID_Music    int not null,
    constraint MusicPlayList_ibfk_1
        foreign key (ID_Music) references dpoo_2122_s2_c2.Music (ID_Music)
            on update cascade on delete cascade,
    constraint MusicPlayList_ibfk_2
        foreign key (ID_PlayList) references dpoo_2122_s2_c2.PlayList (ID_PlayList)
            on update cascade on delete cascade
)
    engine = InnoDB;

create index ID_User
    on dpoo_2122_s2_c2.PlayList (ID_User);

