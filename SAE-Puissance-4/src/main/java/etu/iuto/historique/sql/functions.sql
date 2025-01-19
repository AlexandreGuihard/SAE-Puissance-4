delimiter |
create or replace function getAvailableIdForJoueur() returns int
begin
    declare maxId int;
    select ifnull(max(idJoueur), 1) into maxId from JOUEUR;
    return maxId+1;
end |

create or replace function getAvailableIdForPartie() returns int
begin
    declare maxId int;
    select ifnull(max(idPartie), 1) into maxId from PARTIE;
    return maxId+1;
end |