DROP TABLE IF EXISTS competition_price;
DROP TABLE IF EXISTS competition_distance;
DROP TABLE IF EXISTS championship_type;
DROP TABLE IF EXISTS competition;
DROP TABLE IF EXISTS competition_participant;
DROP TABLE IF EXISTS competitor;

CREATE TABLE competition (
  id          INT(11) AUTO_INCREMENT NOT NULL,
  name        VARCHAR(255)           NOT NULL,
  start_date  TIMESTAMP              NOT NULL,
  end_date    TIMESTAMP              NULL,
  description TEXT                   NOT NULL,
  address     TEXT                   NOT NULL,
  -- TIMESTAMP
  created     TIMESTAMP              NOT NULL,
  created_by  VARCHAR(255)           NOT NULL,
  modified    TIMESTAMP              NOT NULL,
  modified_by VARCHAR(255)           NOT NULL
);

ALTER TABLE competition
  ADD CONSTRAINT pk_comp PRIMARY KEY (id);

CREATE TABLE championship_type (
  id          INT(11) AUTO_INCREMENT NOT NULL,
  name        VARCHAR(255)           NOT NULL,
  -- TIMESTAMP
  created     TIMESTAMP              NOT NULL,
  created_by  VARCHAR(255)           NOT NULL,
  modified    TIMESTAMP              NOT NULL,
  modified_by VARCHAR(255)           NOT NULL
);

ALTER TABLE championship_type
  ADD CONSTRAINT pk_champ_type PRIMARY KEY (id);

CREATE TABLE competition_distance (
  id                   INT(11) AUTO_INCREMENT NOT NULL,
  name                 VARCHAR(255)           NOT NULL,
  length               DECIMAL                NULL,
  championship_type_id INT(11)                NULL,
  special_notes        TEXT,
  competition_id       INT(11)                NOT NULL,
  start_time           TIMESTAMP              NOT NULL,
  start_numbering      INTEGER                NULL,
  -- TIMESTAMP
  created              TIMESTAMP              NOT NULL,
  created_by           VARCHAR(255)           NOT NULL,
  modified             TIMESTAMP              NOT NULL,
  modified_by          VARCHAR(255)           NOT NULL
);

ALTER TABLE competition_distance
  ADD CONSTRAINT pk_comp_dist PRIMARY KEY (id);

ALTER TABLE competition_distance
  ADD CONSTRAINT fk_champ_type_id
FOREIGN KEY (championship_type_id) REFERENCES championship_type (id);

ALTER TABLE competition_distance
  ADD CONSTRAINT fk_comp_id
FOREIGN KEY (competition_id) REFERENCES competition (id);

CREATE TABLE competition_price (
  id                      INT(11) AUTO_INCREMENT NOT NULL,
  competition_distance_id INT(11)                NOT NULL,
  start_date              TIMESTAMP              NOT NULL,
  end_date                TIMESTAMP              NOT NULL,
  price                   DECIMAL                NOT NULL,
  -- TIMESTAMP
  created                 TIMESTAMP              NOT NULL,
  created_by              VARCHAR(255)           NOT NULL,
  modified                TIMESTAMP              NOT NULL,
  modified_by             VARCHAR(255)           NOT NULL
);

ALTER TABLE competition_price
  ADD CONSTRAINT pk_comp_price PRIMARY KEY (id);

ALTER TABLE competition_price
  ADD CONSTRAINT fk_comp_dist_id
FOREIGN KEY (competition_distance_id) REFERENCES competition_distance (id);

CREATE TABLE competitor (
  id                      INT(11) AUTO_INCREMENT NOT NULL,
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
  created                 TIMESTAMP              NOT NULL,
  created_by              VARCHAR(255)           NOT NULL,
  modified                TIMESTAMP              NOT NULL,
  modified_by             VARCHAR(255)           NOT NULL
);

ALTER TABLE competitor
  ADD CONSTRAINT pk_competitor PRIMARY KEY (id);

CREATE TABLE competition_participant (
  id                      INT(11) AUTO_INCREMENT NOT NULL,
  competitor_id           INT(11)                NOT NULL,
  competition_distance_id INT(11)                NOT NULL,
  participation_count     INT                    NULL,
  payment_fulfilled       BIT(1) DEFAULT 0,
  number_printed          BIT(1) DEFAULT 0,
  envelope_printed        BIT(1) DEFAULT 0,
  competitor_number       INT                    NULL,
  chip_id                 INT(11)                NULL,
  -- TIMESTAMP
  created                 TIMESTAMP              NOT NULL,
  created_by              VARCHAR(255)           NOT NULL,
  modified                TIMESTAMP              NOT NULL,
  modified_by             VARCHAR(255)           NOT NULL
);

ALTER TABLE competition_participant
  ADD CONSTRAINT pk_comp_part PRIMARY KEY (id);

ALTER TABLE competition_participant
  ADD CONSTRAINT fk_competitor
FOREIGN KEY (competitor_id) REFERENCES competitor (id);

ALTER TABLE competition_participant
  ADD CONSTRAINT fk_comp_distance
FOREIGN KEY (competition_distance_id) REFERENCES competition_distance (id);
