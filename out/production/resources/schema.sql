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

CREATE TABLE organizer (
  id          INT(11) AUTO_INCREMENT NOT NULL,
  name        VARCHAR(255)           NOT NULL,
  email       VARCHAR(255)           NOT NULL,
  phone       VARCHAR(55)            NOT NULL,
  -- TIMESTAMP
  created     TIMESTAMP              NOT NULL,
  created_by  VARCHAR(255)           NOT NULL,
  modified    TIMESTAMP              NOT NULL,
  modified_by VARCHAR(255)           NOT NULL
);

ALTER table organizer
  ADD CONSTRAINT pk_organizer PRIMARY KEY (id);

CREATE TABLE organizer_competition (
  organizer_id   INT(11)      NOT NULL,
  competition_id INT(11)      NOT NULL,
  -- TIMESTAMP
  created        TIMESTAMP    NOT NULL,
  created_by     VARCHAR(255) NOT NULL,
  modified       TIMESTAMP    NOT NULL,
  modified_by    VARCHAR(255) NOT NULL,
);

CREATE OR REPLACE VIEW v_comp_participant_list
  AS
    SELECT
      c.id                 AS competition_id,
      c.name               AS competition_name,
      c.description        AS description,
      cd.name              AS distance_name,
      cd.length            AS length,
      ct.name              AS type_name,
      cp.competitor_number AS competitor_number,
      comp.first_name      AS first_name,
      comp.last_name       AS last_name
    FROM competition c
      INNER JOIN competition_distance cd ON c.id = cd.competition_id
      LEFT OUTER JOIN championship_type ct ON ct.id = cd.championship_type_id
      INNER JOIN competition_participant cp ON cp.competition_distance_id = cd.id
      INNER JOIN competitor comp ON comp.id = cp.competitor_id;
