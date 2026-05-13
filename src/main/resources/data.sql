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
