CREATE DATABASE encontrao;

DROP TABLE IF EXISTS teams_users CASCADE;

DROP TABLE IF EXISTS timeline CASCADE;

DROP TABLE IF EXISTS commissions CASCADE;

DROP TABLE IF EXISTS competitions_teams CASCADE;

DROP TABLE IF EXISTS competitions CASCADE;

DROP TABLE IF EXISTS points CASCADE;

DROP TABLE IF EXISTS campus CASCADE;

DROP TABLE IF EXISTS presentations CASCADE;

DROP TABLE IF EXISTS teams CASCADE;

DROP TABLE IF EXISTS users CASCADE;

DROP TABLE IF EXISTS evaluator_competition CASCADE;

CREATE TABLE IF NOT EXISTS presentations
(
    id                    BIGSERIAL PRIMARY KEY,
    "order"               INTEGER   DEFAULT NULL,
    "date"				  TIMESTAMP NOT NULL,
    entrance_choreography VARCHAR   NULL,
    traditional_dance_1   VARCHAR   NOT NULL,
    traditional_dance_2   VARCHAR   DEFAULT NULL,
    traditional_dance_3   VARCHAR   DEFAULT NULL,
    exit_choreography     VARCHAR   NULL,
    birivas_dances_1      VARCHAR   NULL,
    birivas_dances_2      VARCHAR   NULL,
    birivas_dances_3      VARCHAR   NULL,
    deleted               BOOLEAN   DEFAULT FALSE,
    created               TIMESTAMP DEFAULT NOW(),
    created_by            BIGINT    NULL,
    updated               TIMESTAMP NULL,
    updated_by            BIGINT    NULL
    );


CREATE TABLE IF NOT EXISTS campus
(
    id               BIGSERIAL PRIMARY KEY,
    institution      VARCHAR      NOT NULL,
    coordinator_name VARCHAR(100) NOT NULL,
    description      VARCHAR      NOT NULL,
    entity           VARCHAR      NOT NULL,
    dormitory        VARCHAR      NOT NULL,
    id_presentation  BIGINT       NOT NULL,
    deleted          BOOLEAN   DEFAULT FALSE,
    created          TIMESTAMP DEFAULT NOW(),
    created_by       BIGINT       NULL,
    updated          TIMESTAMP    NULL,
    updated_by       BIGINT       NULL,
    CONSTRAINT fk_presentation FOREIGN KEY (id_presentation) REFERENCES presentations (id)
    );


CREATE TABLE IF NOT EXISTS users
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR     NOT NULL,
    cpf          VARCHAR(11) NOT NULL,
    avatar		 VARCHAR DEFAULT NULL,
    registration VARCHAR     NULL,
    password     VARCHAR     NOT NULL,
    roles        VARCHAR     NOT NULL,
    is_evaluator BOOLEAN   DEFAULT FALSE,
    is_admin     BOOLEAN   DEFAULT FALSE,
    id_campus    BIGINT      NOT NULL,
    deleted      BOOLEAN   DEFAULT FALSE,
    created      TIMESTAMP DEFAULT NOW(),
    created_by   BIGINT      NULL,
    updated      TIMESTAMP   NULL,
    updated_by   BIGINT      NULL,
    CONSTRAINT fk_campus FOREIGN KEY (id_campus) REFERENCES campus (id)
    );


CREATE TABLE IF NOT EXISTS points
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR   NOT NULL,
    type       VARCHAR   NOT NULL,
    description  VARCHAR   NOT NULL,
    icon       VARCHAR   NOT NULL,
    size 	   INTEGER	 NOT NULL,
    latitude   VARCHAR   NOT NULL,
    longitude  VARCHAR   NOT NULL,
    deleted    BOOLEAN   DEFAULT FALSE,
    created    TIMESTAMP DEFAULT NOW(),
    created_by BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL
    );

CREATE TABLE IF NOT EXISTS competitions
(
    id           BIGSERIAL 	  PRIMARY KEY,
    name         VARCHAR      NOT NULL,
    modality     VARCHAR      NOT NULL,
    description  VARCHAR      NOT NULL,
    fecult_event BOOLEAN      DEFAULT FALSE,
    initial_date TIMESTAMP(3) NULL,
    participants INTEGER      NOT NULL,
    commission   BOOLEAN      DEFAULT FALSE,
    id_point     BIGINT       NOT NULL,
    deleted      BOOLEAN   	  DEFAULT FALSE,
    created      TIMESTAMP 	  DEFAULT NOW(),
    created_by   BIGINT		  NULL,
    updated      TIMESTAMP 	  NULL,
    updated_by   BIGINT    	  NULL,
    CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points (id)
    );

CREATE TABLE IF NOT EXISTS teams
(
    id         BIGSERIAL PRIMARY KEY,
    grade      NUMERIC(10, 2),
    deleted    BOOLEAN   DEFAULT FALSE,
    created    TIMESTAMP DEFAULT NOW(),
    created_by BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL
    );

CREATE TABLE IF NOT EXISTS teams_users
(
    id         BIGSERIAL PRIMARY KEY,
    id_user    BIGINT    NOT NULL,
    id_team    BIGINT    NOT NULL,
    deleted    BOOLEAN   DEFAULT FALSE,
    created    TIMESTAMP DEFAULT NOW(),
    created_by BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams (id)
    );

CREATE TABLE IF NOT EXISTS competitions_teams
(
    id             BIGSERIAL PRIMARY KEY,
    id_team        BIGINT    NOT NULL,
    id_competition BIGINT    NOT NULL,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams (id),
    CONSTRAINT fk_competition FOREIGN KEY (id_competition) REFERENCES competitions (id)
    );

CREATE TABLE IF NOT EXISTS commissions
(
    id                    BIGSERIAL PRIMARY KEY,
    id_competitions_teams BIGINT                     NOT NULL,
    id_user               BIGINT                     NULL,
    grade_1               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_2               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_3               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_4               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_5               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_name_1		  VARCHAR 					 NOT NULL,
    grade_name_2		  VARCHAR 					 NOT NULL,
    grade_name_3		  VARCHAR 					 NOT NULL,
    grade_name_4		  VARCHAR 					 NOT NULL,
    grade_name_5		  VARCHAR 					 NULL,
    deleted               BOOLEAN        DEFAULT FALSE,
    created               TIMESTAMP      DEFAULT NOW(),
    created_by            BIGINT                     NULL,
    updated               TIMESTAMP                  NULL,
    updated_by            BIGINT                     NULL,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_competitions_teams FOREIGN KEY (id_competitions_teams) REFERENCES competitions_teams (id)
    );

CREATE TABLE IF NOT EXISTS timeline
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR   NOT NULL,
    required       BOOLEAN   DEFAULT FALSE,
    date           TIMESTAMP NOT NULL,
    type           VARCHAR   NOT NULL,  -- Adicionada a nova coluna aqui
    id_point       BIGINT    NOT NULL,
    id_competition BIGINT    NULL,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points (id),
    CONSTRAINT fk_competition FOREIGN KEY (id_competition) REFERENCES competitions (id)
    );

CREATE TABLE IF NOT EXISTS evaluator_competition (
    id SERIAL PRIMARY KEY,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    competition_id INT REFERENCES competitions(id) ON DELETE CASCADE
);


ALTER SEQUENCE public.campus_id_seq RESTART WITH 1;
ALTER SEQUENCE public.commissions_id_seq RESTART WITH 1;
ALTER SEQUENCE public.competitions_id_seq RESTART WITH 1;
ALTER SEQUENCE public.competitions_teams_id_seq RESTART WITH 1;
ALTER SEQUENCE public.points_id_seq RESTART WITH 1;
ALTER SEQUENCE public.presentations_id_seq RESTART WITH 1;
ALTER SEQUENCE public.teams_id_seq RESTART WITH 1;
ALTER SEQUENCE public.teams_users_id_seq RESTART WITH 1;
ALTER SEQUENCE public.timeline_id_seq RESTART WITH 1;
ALTER SEQUENCE public.users_id_seq RESTART WITH 1;
ALTER SEQUENCE public.evaluator_competition_id_sec RESTART WITH 1;

COPY campus FROM '/home/ec2-user/csv/campus.csv' DELIMITER ',' CSV HEADER;
COPY competitions FROM '/home/ec2-user/csv/competitions.csv' DELIMITER ',' CSV HEADER;
COPY competitions_teams FROM '/home/ec2-user/csv/competitions_teams.csv' DELIMITER ',' CSV HEADER;
COPY points FROM '/home/ec2-user/csv/points.csv' DELIMITER ',' CSV HEADER;
COPY presentations FROM '/home/ec2-user/csv/presentations.csv' DELIMITER ',' CSV HEADER;
COPY teams FROM '/home/ec2-user/csv/teams.csv' DELIMITER ',' CSV HEADER;
COPY teams_users FROM '/home/ec2-user/csv/teams_users.csv' DELIMITER ',' CSV HEADER;
COPY timeline FROM '/home/ec2-user/csv/timeline.csv' DELIMITER ',' CSV HEADER;
COPY users FROM '/home/ec2-user/csv/users.csv' DELIMITER ',' CSV HEADER;
