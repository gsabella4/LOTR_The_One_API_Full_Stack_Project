BEGIN TRANSACTION;

DROP TABLE IF EXISTS movie, quote, character;

CREATE TABLE movie (
	movie_id SERIAL,
	the_one_movie_id CHAR(24) UNIQUE,
	name VARCHAR(50),
	runtime_in_minutes int,
	budget_in_millions int,
	CONSTRAINT PK_movie PRIMARY KEY(movie_id)
);

CREATE TABLE character (
	character_id SERIAL,
	the_one_character_id CHAR(24) UNIQUE,
	name VARCHAR(50),
	race VARCHAR(15),
	wiki_url VARCHAR(100),
CONSTRAINT PK_character PRIMARY KEY (character_id)
);

CREATE TABLE quote (
	quote_id SERIAL,
	the_one_quote_id CHAR(24),
	dialog VARCHAR(200),
	the_one_movie_id CHAR(24),
	the_one_character_id CHAR(24),
	CONSTRAINT PK_quote PRIMARY KEY(quote_id),
	CONSTRAINT FK_quote_movie FOREIGN KEY(the_one_movie_id) REFERENCES movie(the_one_movie_id),
	CONSTRAINT FK_quote_character FOREIGN KEY(the_one_character_id) REFERENCES character(the_one_character_id)
);

COMMIT;