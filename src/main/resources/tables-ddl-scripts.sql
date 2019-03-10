-- DROP TABLE IF EXISTS competition_price;
-- DROP TABLE IF EXISTS competition_distance;
-- DROP TABLE IF EXISTS championship_type;
-- DROP TABLE IF EXISTS competition;
-- DROP TABLE IF EXISTS competition_participant;
-- DROP TABLE IF EXISTS competitor;
-- DROP TABLE IF EXISTS organizer;
-- DROP TABLE IF EXISTS organizer_competition;

CREATE TABLE competition (
  id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255)           NOT NULL,
  start_date  TIMESTAMP              NOT NULL,
  end_date    TIMESTAMP              NULL,
  description TEXT                   NOT NULL,
  address     TEXT                   NOT NULL,
  -- TIMESTAMP
  created_at  DATETIME               NOT NULL,
  created_by  VARCHAR(255)            NOT NULL,
  updated_at DATETIME               NOT NULL,
  updated_by VARCHAR(255)            NOT NULL
);

CREATE TABLE championship_type (
  id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255)           NOT NULL,
  -- TIMESTAMP
  created_at  DATETIME               NOT NULL,
  created_by  VARCHAR(255)            NOT NULL,
  updated_at DATETIME               NOT NULL,
  updated_by VARCHAR(255)           NOT NULL
);

CREATE TABLE competition_distance (
  id                        BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name                      VARCHAR(255)           NOT NULL,
  length                    DECIMAL                NULL,
  championship_type_id      BIGINT(20)                NULL,
  special_notes             TEXT,
  competition_id            BIGINT(20)                NOT NULL,
  start_time                TIMESTAMP              NOT NULL,
  start_numbering           INTEGER                NOT NULL,
  current_competitor_number INTEGER                NULL,
  distance_type_id          BIGINT(20)                NOT NULL,
  -- TIMESTAMP
  created_at  DATETIME               NOT NULL,
  created_by  VARCHAR(255)            NOT NULL,
  updated_at  DATETIME               NOT NULL,
  updated_by  VARCHAR(255)            NOT NULL
);

ALTER TABLE competition_distance
  ADD CONSTRAINT fk_champ_type_id
FOREIGN KEY (championship_type_id) REFERENCES championship_type (id);

ALTER TABLE competition_distance
  ADD CONSTRAINT fk_comp_id
FOREIGN KEY (competition_id) REFERENCES competition (id);

ALTER TABLE competition_distance
  ADD CONSTRAINT fk_comp_type_id
FOREIGN KEY (distance_type_id) REFERENCES competition (id);

CREATE TABLE competition_distance_type (
  id   BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(55) NOT NULL,
  -- TIMESTAMP
  created_at  DATETIME               NOT NULL,
  updated_at  DATETIME               NOT NULL
);

CREATE TABLE competition_price (
  id                      BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  competition_distance_id BIGINT(20)                NOT NULL,
  start_date              TIMESTAMP                 NOT NULL,
  end_date                TIMESTAMP                 NOT NULL,
  price                   DECIMAL                   NOT NULL,
  -- TIMESTAMP
  created_at  DATETIME                              NOT NULL,
  created_by  VARCHAR(255)                           NOT NULL,
  updated_at DATETIME                               NOT NULL,
  updated_by VARCHAR(255)                            NOT NULL
);

ALTER TABLE competition_price
  ADD CONSTRAINT fk_comp_dist_id
FOREIGN KEY (competition_distance_id) REFERENCES competition_distance (id);

CREATE TABLE competitor (
  id                      BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  first_name              VARCHAR(255)           NOT NULL,
  last_name               VARCHAR(255)           NOT NULL,
  email                   VARCHAR(255)           NOT NULL,
  gender                  VARCHAR(10)            NOT NULL,
  date_of_birth           TIMESTAMP              NOT NULL,
  sports_club             VARCHAR(255)           NULL,
  phone                   VARCHAR(55)            NOT NULL,
  newsletter_subscription BIT(1)                 NOT NULL,
  publish_data            BIT(1)                 NOT NULL,
  -- TIMESTAMP
  created_at              DATETIME               NOT NULL,
  created_by              VARCHAR(255)             NULL,
  updated_at              DATETIME               NOT NULL,
  updated_by              VARCHAR(255)             NULL
);

CREATE TABLE competition_participant (
  id                         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  competitor_id              BIGINT(20)                NOT NULL,
  competition_distance_id    BIGINT(20)                NOT NULL,
  participation_count        INT                       NULL,
  payment_fulfilled          BIT(1) DEFAULT 0,
  number_printed             BIT(1) DEFAULT 0,
  envelope_printed           BIT(1) DEFAULT 0,
  championship_participation BIT(1) DEFAULT 0,
  competitor_number          INT                       NULL,
  chip_id                    BIGINT(20)                NULL,
  -- TIMESTAMP
  created_at  DATETIME              NOT NULL,
  created_by  VARCHAR(255)            NULL,
  updated_at  DATETIME              NOT NULL,
  updated_by  VARCHAR(255)            NULL
);

ALTER TABLE competition_participant
  ADD CONSTRAINT fk_competitor
FOREIGN KEY (competitor_id) REFERENCES competitor (id);

ALTER TABLE competition_participant
  ADD CONSTRAINT fk_comp_distance
FOREIGN KEY (competition_distance_id) REFERENCES competition_distance (id);

CREATE TABLE organizer (
  id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255)           NOT NULL,
  email       VARCHAR(255)           NOT NULL,
  phone       VARCHAR(55)            NOT NULL,
  user_id     BIGINT(20) NULL,
  -- TIMESTAMP
  created_at  DATETIME               NOT NULL,
  created_by  VARCHAR(255)             NOT NULL,
  updated_at DATETIME                NOT NULL,
  updated_by VARCHAR(255)              NOT NULL
);

CREATE TABLE organizer_competition (
  organizer_id   BIGINT(20)      NOT NULL,
  competition_id BIGINT(20)      NOT NULL,
  -- TIMESTAMP
  created_at  DATETIME               NOT NULL,
  created_by  VARCHAR(255)            NOT NULL,
  updated_at DATETIME               NOT NULL,
  updated_by VARCHAR(255)            NOT NULL
);

CREATE TABLE authorities (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(55) NOT NULL
);

CREATE TABLE users (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL,
  user_name VARCHAR(15) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
);

CREATE TABLE user_authority (
  user_id BIGINT(20) NOT NULL,
  authority_id BIGINT(20) NOT NULL
);
