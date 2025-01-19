create or replace table JOUEUR(
    idJoueur int primary key,
    nomJoueur varchar(50),
    nbVictoires int,
    nbDefaites int,
    nbNuls int
);

create or replace table PARTIE(
    idPartie int primary key,
    idJoueurRouge int,
    idJoueurJaune int,
    idGagnant int
);

alter table PARTIE add foreign key (idJoueurJaune) references JOUEUR (idJoueur);
alter table PARTIE add foreign key (idJoueurRouge) references JOUEUR (idJoueur);
alter table PARTIE add foreign key (idGagnant) references JOUEUR (idJoueur); 