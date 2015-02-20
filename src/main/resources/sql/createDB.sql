DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
CREATE EXTENSION postgis;
CREATE EXTENSION btree_gist;

CREATE FUNCTION formatPoint(lat NUMERIC, lng NUMERIC) RETURNS TEXT
AS
  $$
  SELECT format('SRID=4326;POINT(%2$s %1$s)', lat, lng)
  $$
LANGUAGE SQL;

CREATE FUNCTION distance(lat1 NUMERIC, lng1 NUMERIC, lat2 NUMERIC, lng2 NUMERIC) RETURNS DOUBLE PRECISION
AS
  $$
  SELECT ST_Distance(formatPoint(lat1, lng1) :: GEOGRAPHY, formatPoint(lat2, lng2) :: GEOGRAPHY);
  $$
LANGUAGE SQL;

CREATE FUNCTION distance(lat NUMERIC, lng NUMERIC, p TEXT) RETURNS DOUBLE PRECISION
AS
  $$
  SELECT ST_Distance(formatPoint(lat, lng) :: GEOGRAPHY, p :: GEOGRAPHY);
  $$
LANGUAGE SQL;

CREATE FUNCTION distance(p1 TEXT, p2 TEXT) RETURNS DOUBLE PRECISION
AS
  $$
  SELECT ST_Distance(p1 :: GEOGRAPHY, p2 :: GEOGRAPHY);
  $$
LANGUAGE SQL;


CREATE TABLE users (
  id            BIGSERIAL             NOT NULL PRIMARY KEY,
  firstName     VARCHAR(1000)         NOT NULL,
  lastName      VARCHAR(1000)         NOT NULL,
  login         VARCHAR(200)          NOT NULL,
  facebookToken VARCHAR(200)                   DEFAULT NULL UNIQUE,
  appleToken    VARCHAR(200)                   DEFAULT NULL UNIQUE,
  lat           DOUBLE PRECISION      NOT NULL CHECK (lat > -90 AND lat <= 90),
  lng           DOUBLE PRECISION      NOT NULL CHECK (lng > -180 AND lng <= 180),
  location      GEOMETRY(POINT, 4326) NOT NULL,
  hp            INT                   NOT NULL DEFAULT 100,
  level         SMALLINT              NOT NULL DEFAULT 1,
  type          SMALLINT              NOT NULL DEFAULT 0
);

CREATE INDEX users_location_idx ON users USING GIST (location);

CREATE TABLE history (
  date TIMESTAMP NOT NULL
);

CREATE TABLE abilities (
  userId      BIGINT   NOT NULL,
  abilityType SMALLINT NOT NULL,
  abilityId   SMALLINT NOT NULL,
  FOREIGN KEY (userId) REFERENCES users (id)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
);

INSERT INTO users (firstName, lastName, login, facebookToken, appleToken, lat, lng, location, hp, level, type)
VALUES ('Дмитрий', 'Бекузаров', 'Dima', 'facebook123', 'apple123', 50.0260317, 36.2249179,
        'SRID=4326;POINT(36.2249179 50.0260317)', 100, 2, 2);