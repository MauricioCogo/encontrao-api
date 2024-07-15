CREATE TABLE IF NOT EXISTS presentations (
                                             "id" BIGSERIAL PRIMARY KEY,
                                             "entrance_choreography" VARCHAR NULL,
                                             "traditional_dance_1" VARCHAR NOT NULL,
                                             "traditional_dance_2" VARCHAR NOT NULL,
                                             "traditional_dance_3" VARCHAR NOT NULL,
                                             "exit_choreography" VARCHAR NULL,
                                             "birivas_dances_1" VARCHAR NULL,
                                             "birivas_dances_2" VARCHAR NULL,
                                             "birivas_dances_3" VARCHAR NULL
);


CREATE TABLE IF NOT EXISTS campus (
                                      "id" BIGSERIAL PRIMARY KEY,
                                      "institution" VARCHAR NOT NULL,
                                      "coordinator_name" VARCHAR(11) NOT NULL,
    "registration" VARCHAR NOT NULL,
    "entity" VARCHAR NOT NULL,
    "dormitory" VARCHAR NOT NULL,
    "id_presentation" BIGINT NOT NULL,
    CONSTRAINT fk_presentation FOREIGN KEY (id_presentation) REFERENCES presentations(id)
    );


CREATE TABLE IF NOT EXISTS users (
                                     "id" BIGSERIAL PRIMARY KEY,
                                     "name" VARCHAR NOT NULL,
                                     "cpf" VARCHAR(11) NOT NULL,
    "registration" VARCHAR NULL,
    "password" VARCHAR NOT NULL,
    "roles" VARCHAR NOT NULL,
    "is_evaluator" BOOLEAN DEFAULT FALSE,
    "is_admin" BOOLEAN DEFAULT FALSE,
    "id_campus" BIGINT NOT NULL,
    CONSTRAINT fk_campus FOREIGN KEY (id_campus) REFERENCES campus(id)
    );


CREATE TABLE IF NOT EXISTS points (
                                      "id" BIGSERIAL PRIMARY KEY,
                                      "name" VARCHAR NOT NULL,
                                      "type" VARCHAR NOT NULL,
                                      "descption" VARCHAR NOT NULL,
                                      "icon" VARCHAR NOT NULL,
                                      "latitude" DOUBLE PRECISION NOT NULL,
                                      "longitude" DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS competitions (
                                            "id" BIGSERIAL PRIMARY KEY,
                                            "name" VARCHAR NOT NULL,
                                            "modality" VARCHAR NOT NULL,
                                            "descption" VARCHAR NOT NULL,
                                            "fecult_event" BOOLEAN DEFAULT FALSE,
                                            "participants" INTEGER NOT NULL,
                                            "commission" BOOLEAN DEFAULT FALSE,
                                            "id_point" BIGINT NOT NULL,
                                            CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points(id)
    );

CREATE TABLE IF NOT EXISTS teams (
                                     "id" BIGSERIAL PRIMARY KEY,
                                     "grade" NUMERIC(10,2)
    );

CREATE TABLE IF NOT EXISTS teams_users (
                                           "id" BIGSERIAL PRIMARY KEY,
                                           "id_user" BIGINT NOT NULL,
                                           "id_team" BIGINT NOT NULL,
                                           CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams(id)
    );

CREATE TABLE IF NOT EXISTS competitions_teams (
                                                  "id" BIGSERIAL PRIMARY KEY,
                                                  "id_team" BIGINT NOT NULL,
                                                  "id_competition" BIGINT NOT NULL,
                                                  CONSTRAINT fk_team FOREIGN KEY ("id_team") REFERENCES teams("id"),
    CONSTRAINT fk_competition FOREIGN KEY ("id_competition") REFERENCES competitions("id")
    );

CREATE TABLE IF NOT EXISTS commission (
                                          "id" BIGSERIAL PRIMARY KEY,
                                          "id_competitions_teams" BIGINT NOT NULL,
                                          "id_user" BIGINT NOT NULL,
                                          "grade_1" NUMERIC(10,2) DEFAULT 0.0,
    "grade_2" NUMERIC(10,2) DEFAULT 0.0,
    "grade_3" NUMERIC(10,2) DEFAULT 0.0,
    "grade_4" NUMERIC(10,2) DEFAULT 0.0,
    "grade_5" NUMERIC(10,2) DEFAULT 0.0,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT fk_competitions_teams FOREIGN KEY (id_competitions_teams) REFERENCES competitions_teams(id)
    );

CREATE TABLE IF NOT EXISTS timeline (
                                        "id" BIGSERIAL PRIMARY KEY,
                                        "name" VARCHAR NOT NULL,
                                        "required" BOOLEAN DEFAULT FALSE,
                                        "date" TIMESTAMP NOT NULL,
                                        "id_point" BIGINT NOT NULL,
                                        "id_competition" BIGINT NOT NULL,
                                        CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points(id),
    CONSTRAINT fk_competition FOREIGN KEY ("id_competition") REFERENCES competitions("id")
    );
