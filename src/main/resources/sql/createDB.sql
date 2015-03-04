DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
CREATE EXTENSION postgis;
CREATE EXTENSION btree_gist;

CREATE FUNCTION formatPoint(lat NUMERIC, lng NUMERIC) RETURNS TEXT
AS
  $$
  SELECT format('SRID=4326;POINT(%2$s %1$s)', lat, lng)
  $$
LANGUAGE SQL STABLE;

CREATE FUNCTION distance(lat1 NUMERIC, lng1 NUMERIC, lat2 NUMERIC, lng2 NUMERIC) RETURNS DOUBLE PRECISION
AS
  $$
  SELECT ST_Distance(formatPoint(lat1, lng1) :: GEOGRAPHY, formatPoint(lat2, lng2) :: GEOGRAPHY);
  $$
LANGUAGE SQL STABLE;

CREATE FUNCTION distance(lat NUMERIC, lng NUMERIC, p TEXT) RETURNS DOUBLE PRECISION
AS
  $$
  SELECT ST_Distance(formatPoint(lat, lng) :: GEOGRAPHY, p :: GEOGRAPHY);
  $$
LANGUAGE SQL STABLE;

CREATE FUNCTION distance(p1 TEXT, p2 TEXT) RETURNS DOUBLE PRECISION
AS
  $$
  SELECT ST_Distance(p1 :: GEOGRAPHY, p2 :: GEOGRAPHY);
  $$
LANGUAGE SQL STABLE;

CREATE TABLE users (
  id                       BIGSERIAL             NOT NULL PRIMARY KEY,
  firstName                VARCHAR(1000)         NOT NULL,
  lastName                 VARCHAR(1000)         NOT NULL,
  login                    VARCHAR(200)          NOT NULL,
  facebookToken            VARCHAR(200)                   DEFAULT NULL UNIQUE,
  appleToken               VARCHAR(200)                   DEFAULT NULL,
  lang                     VARCHAR(10)           NOT NULL,
  lat                      DOUBLE PRECISION      NOT NULL CHECK (lat > -90 AND lat <= 90),
  lng                      DOUBLE PRECISION      NOT NULL CHECK (lng > -180 AND lng <= 180),
  location                 GEOMETRY(POINT, 4326) NOT NULL,
  hp                       INT                   NOT NULL DEFAULT 100,
  xp                       INT                   NOT NULL DEFAULT 0,
  type                     SMALLINT              NOT NULL DEFAULT 0,
  isOnline                 BOOLEAN               NOT NULL DEFAULT TRUE,
  isDead                   BOOLEAN               NOT NULL DEFAULT FALSE,
  visibility               INT                   NOT NULL,
  abilityAttackFactor      FLOAT                 NOT NULL DEFAULT 1,
  abilityDefenseFactor     FLOAT                 NOT NULL DEFAULT 1,
  temperatureAttackFactor  FLOAT                 NOT NULL DEFAULT 1,
  temperatureDefenseFactor FLOAT                 NOT NULL DEFAULT 1,
  sunAttackFactor          FLOAT                 NOT NULL DEFAULT 1,
  sunDefenseFactor         FLOAT                 NOT NULL DEFAULT 1
);

CREATE INDEX users_location_idx ON users USING GIST (location);

CREATE TABLE history (
  date TIMESTAMP NOT NULL
);

CREATE TABLE user_abilities (
  userId    BIGINT       NOT NULL,
  abilityId VARCHAR(100) NOT NULL,
  FOREIGN KEY (userId) REFERENCES users (id)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
);

CREATE VIEW users_public AS
  SELECT
    id,
    firstName,
    lastName,
    login,
    appleToken,
    lang,
    lat,
    lng,
    location,
    hp,
    type,
    visibility,
    abilityAttackFactor,
    abilityDefenseFactor,
    temperatureAttackFactor,
    temperatureDefenseFactor,
    sunAttackFactor,
    sunDefenseFactor
  FROM users
  WHERE isDead = FALSE AND isOnline = TRUE;


INSERT INTO users (firstName, lastName, login, facebookToken, appleToken, lang, lat, lng, location, hp, xp, type, visibility)
VALUES ('Дмитрий1', 'Бекузаров1', 'Dima1', 'facebook1',
        '<927d54e5 88a56875 bed2f8a7 490278c0 c28c573e 8c6db3a8 ee2d8351 5bf31048>', 'en', 50.0260317, 36.2249179,
        'SRID=4326;POINT(36.2249179 50.0260317)', 100, 1000, 0, 50),
  ('Дмитрий2', 'Бекузаров2', 'Dima2', 'facebook2',
   '<927d54e5 88a56875 bed2f8a7 490278c0 c28c573e 8c6db3a8 ee2d8351 5bf31048>', 'ru', 50.0260313, 36.2249173,
   'SRID=4326;POINT(36.2249173 50.0260313)', 100, 0, 1, 25),
  ('Дмитрий3', 'Бекузаров3', 'Dima3', 'facebook3',
   '<927d54e5 88a56875 bed2f8a7 490278c0 c28c573e 8c6db3a8 ee2d8351 5bf31048>', 'en', 50.0260613, 36.2249473,
   'SRID=4326;POINT(36.2249473 50.0260613)', 100, 0, 2, 100);
;
