INSERT INTO genre (name) VALUES ('Action');
INSERT INTO genre (name) VALUES ('Comedy');
INSERT INTO genre (name) VALUES ('Drama');
INSERT INTO genre (name) VALUES ('Science Fiction');
INSERT INTO genre (name) VALUES ('Horror');

INSERT INTO director (name, birth_year, nationality) VALUES ('Christopher Nolan', 1970, 'British-American');
INSERT INTO director (name, birth_year, nationality) VALUES ('Ridley Scott', 1937, 'British');
INSERT INTO director (name, birth_year, nationality) VALUES ('Bong Joon-ho', 1969, 'South Korean');
INSERT INTO director (name, birth_year, nationality) VALUES ('Tom Holland', 1943, 'American');

INSERT INTO movie (title, director_id, release_year, rating, genre_id) VALUES ('Inception', 1, 2010, 9.3, 4);
INSERT INTO movie (title, director_id, release_year, rating, genre_id) VALUES ('Interstellar', 1, 2014, 9.5, 4);
INSERT INTO movie (title, director_id, release_year, rating, genre_id) VALUES ('Gladiator', 2, 2000, 8.5, 1);
INSERT INTO movie (title, director_id, release_year, rating, genre_id) VALUES ('Parasite', 3, 2019, 9.0, 3);
INSERT INTO movie (title, director_id, release_year, rating, genre_id) VALUES ('Fright Night', 4, 1985, 7.2, 5);

INSERT INTO artist (name, birth_year, nationality) VALUES ('Leonardo DiCaprio', 1974, 'American');
INSERT INTO artist (name, birth_year, nationality) VALUES ('Joseph Gordon-Levitt', 1981, 'American');
INSERT INTO artist (name, birth_year, nationality) VALUES ('Matthew McConaughey', 1969, 'American');
INSERT INTO artist (name, birth_year, nationality) VALUES ('Song Kang-ho', 1967, 'South Korean');
INSERT INTO artist (name, birth_year, nationality) VALUES ('Russell Crowe', 1964, 'New Zealander');

INSERT INTO movie_character (name, description, movie_id) VALUES ('Cobb', 'Ladrao especializado em extrair segredos por sonhos.', 1);
INSERT INTO movie_character (name, description, movie_id) VALUES ('Arthur', 'Parceiro de Cobb nas operacoes de inception.', 1);
INSERT INTO movie_character (name, description, movie_id) VALUES ('Cooper', 'Piloto enviado para procurar um novo lar para a humanidade.', 2);
INSERT INTO movie_character (name, description, movie_id) VALUES ('Kim Ki-taek', 'Pai da familia Kim em Parasite.', 4);
INSERT INTO movie_character (name, description, movie_id) VALUES ('Maximus', 'General romano traido e transformado em gladiador.', 3);

INSERT INTO movie_cast (movie_id, artist_id, character_id, cast_role) VALUES (1, 1, 1, 'LEAD');
INSERT INTO movie_cast (movie_id, artist_id, character_id, cast_role) VALUES (1, 2, 2, 'SUPPORTING');
INSERT INTO movie_cast (movie_id, artist_id, character_id, cast_role) VALUES (2, 3, 3, 'LEAD');
INSERT INTO movie_cast (movie_id, artist_id, character_id, cast_role) VALUES (4, 4, 4, 'LEAD');
INSERT INTO movie_cast (movie_id, artist_id, character_id, cast_role) VALUES (3, 5, 5, 'LEAD');
