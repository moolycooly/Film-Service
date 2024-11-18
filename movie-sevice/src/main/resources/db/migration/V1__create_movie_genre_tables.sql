CREATE TABLE IF NOT EXISTS genre (
    id BIGSERIAL PRIMARY KEY,
    tmdb_id BIGSERIAL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS movie (
    id BIGSERIAL PRIMARY KEY,
    tmdb_id BIGSERIAL,
    adult BOOLEAN,
    backdrop_path VARCHAR(500),
    original_language VARCHAR(50),
    original_title VARCHAR(500),
    overview TEXT,
    popularity DOUBLE PRECISION,
    poster_path VARCHAR(500),
    release_date VARCHAR(20),
    title VARCHAR(500),
    video BOOLEAN,
    vote_average DOUBLE PRECISION,
    vote_count INTEGER
);

CREATE TABLE IF NOT EXISTS movie_genre (
    movie_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    CONSTRAINT fk_movie
        FOREIGN KEY (movie_id)
        REFERENCES movie (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_genre
        FOREIGN KEY (genre_id)
        REFERENCES genre (id)
        ON DELETE CASCADE
    );
