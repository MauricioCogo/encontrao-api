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


CREATE TABLE IF NOT EXISTS presentations
(
    id                    BIGSERIAL PRIMARY KEY,
    "order"               INTEGER   DEFAULT NULL,
    entrance_choreography VARCHAR   NULL,
    traditional_dance_1   VARCHAR   NOT NULL,
    traditional_dance_2   VARCHAR   DEFAULT NULL,
    traditional_dance_3   VARCHAR   DEFAULT NULL,
    traditional_dance_extra   VARCHAR   DEFAULT NULL,
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



-- Tabela presentations
INSERT INTO presentations (
    "order", "entrance_choreography", "traditional_dance_1", "traditional_dance_2",
    "traditional_dance_3", "exit_choreography", "birivas_dances_1", "birivas_dances_2",
    "birivas_dances_3", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
    (0, 'GiraGira na vitrola', 'Anú', 'Tatu de castanholas', '', '', '', '', '', FALSE, NOW(), 3, NULL, NULL);

-- Tabela campus
INSERT INTO campus (
    "institution", "coordinator_name", "description", "entity", "dormitory", "id_presentation",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
    ('IFFar - SVS', 'Eduardo Feitoza', 'Instituto Federal Farroupilha - Campus São Vicente do Sul', 'NTG Trempe da Saudade', 'Predio B', 1, FALSE, NOW(), 1, NULL, NULL);


INSERT INTO users (
    "name", "cpf", "registration", "password", "roles", "is_evaluator", "is_admin",
    "id_campus", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Ana Beatriz Silva', '1', '1', '1', 'Professor', TRUE, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Carlos Alberto Souza', '23456789012', '2023008910', '23456789012', 'Admin', FALSE, TRUE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Fernanda Costa', '34567890123', '2023002345', '34567890123', 'Secretária', FALSE, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Juliana Pereira', '45678901234', '2023006789', '45678901234', 'Estudante', TRUE, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Roberto Lima', '56789012345', '2023001122', '56789012345', 'Professor', FALSE, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Tatiane Mendes', '67890123456', '2023003344', '67890123456', 'Estudante', TRUE, TRUE, 1, FALSE, NOW(), 1, NULL, NULL);

-- Tabela users
INSERT INTO users (
    "name", "cpf", "registration", "password", "roles", "is_evaluator", "is_admin",
    "id_campus", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
    ('Mauricio Carvalho Cogo', '05415375040', '2023001236', '05415375040', 'Estudante', FALSE, TRUE, 1, FALSE, NOW(), 1, NULL, NULL);

-- Tabela points
INSERT INTO points (
    "name", "type", "description", "icon", "latitude", "longitude",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Dança de salão', 'Prova','NTG', 'trofeuC.png', '-29.704415687121692', '-54.69817036876705', FALSE, NOW(), 1, NULL, NULL),
      ('Revezamento do mate', 'Prova', 'Garagem', 'trofeuC.png', '-29.704374820999202', '-54.69770526684055', FALSE, NOW(), 1, NULL, NULL),
      ('Triatlo campeiro', 'Prova', 'Garagem','trofeuC.png', '-29.704334518267306', '-54.69768606687848', FALSE, NOW(), 1, NULL, NULL),
      ('Tetarfe', 'Prova', 'Garagem', 'trofeuC.png', '-29.704296995027534', '-54.69766366693321', FALSE, NOW(), 1, NULL, NULL),
      ('Tiro de laço vaca parada', 'Prova', 'Garagem', 'trofeuC.png', '-29.70425394454995', '-54.69764128484854', FALSE, NOW(), 1, NULL, NULL),
      ('Pescaria gaúcha', 'Prova', 'Açude', 'trofeuC.png', '-29.70604223241599', '-54.69716708050202', FALSE, NOW(), 1, NULL, NULL),
      ('Truco', 'Prova','NTG', 'trofeuC.png', '-29.704346952030676', '-54.69807047483297', FALSE, NOW(), 1, NULL, NULL),
      ('Arroz Carreteiro', 'Prova', 'NTG', 'trofeuC.png', '-29.704470995951734', '-54.69804298867797', FALSE, NOW(), 1, NULL, NULL),
      ('Artilharia gaúcha', 'Prova', 'Campo', 'trofeuC.png', '-29.702773175390206', '-54.695393463220356', FALSE, NOW(), 1, NULL, NULL),
      ('Cabo de guerra', 'Prova','Campo', 'trofeuC.png', '-29.702455062590637', '-54.695073312707784', FALSE, NOW(), 1, NULL, NULL),
      ('Bocha campeira', 'Prova', 'Campo', 'trofeuC.png', '-29.70212000404092', '-54.69482839582078', FALSE, NOW(), 1, NULL, NULL),
      ('NTG', 'Local', 'Núcleo de Tradições Gaúchas Trempe da Saudade', 'alfinete.png', '-29.70419844938545', '-54.69796388301659', FALSE, NOW(), 1, NULL, NULL),
      ('Refeitório', 'Local', 'Local de refeições, com menu saudável e mesas para interação social.', 'alfinete.png', '-29.703473889166226', '-54.69717865359698', FALSE, NOW(), 1, NULL, NULL),
      ('Convivencia', 'Local', 'Local de convivencia dos estudantes', 'alfinete.png', '-29.702676486972212', '-54.696222011426585', FALSE, NOW(), 1, NULL, NULL),
      ('CTG', 'Local', 'CTG Cancela da Fronteira', 'alfinete.png', '-29.685610487306235', '-54.676470859503844', FALSE, NOW(), 1, NULL, NULL),
      ('Abertura', 'Local', 'Abertura Oficial do 30º Encontrão!', 'alfinete.png', '-29.693054480787954', '-54.6782872971689', FALSE, NOW(), 1, NULL, NULL),
      ('IFFar-SVS', 'Local', 'IFFar São Vicente do Sul', 'instituto.png', '-29.702115796438793', '-54.696880623638656', FALSE, NOW(), 1, NULL, NULL),
      ('Dormintórios', 'Local', 'Local para dormir, oferecendo um espaço tranquilo para relaxamento.', 'alfinete.png', '-29.693054480787954', '-54.6782872971689', FALSE, NOW(), 1, NULL, NULL);

-- Tabela competitions
INSERT INTO competitions (
    "name", "description", "modality", "fecult_event", "initial_date", "participants", "commission", "id_point",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Dança de salão', 'Forme uma dupla, dance duas das seguintes danças: Chote, Milonga, Chamamé, Rancheira, Valsa, Marzuca, ou Bugio, em dois minutos cada, e conquiste a maior pontuação para vencer!', 'Padrão', FALSE, '2024-11-15 13:30:00', 2, TRUE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Revezamento do mate', 'Forme uma equipe de quatro (dois peões e duas prendas), ceve, tome e passe o mate em revezamento. A equipe que completar a prova no menor tempo vence!', 'Padrão', FALSE, '2024-11-15 13:30:00', 4, FALSE, 2, FALSE, NOW(), 1, NULL, NULL),
      ('Triatlo campeiro', 'Forme uma equipe de seis (três peões e três prendas) e complete as três etapas: nó de lenço, corrida do saco e debulhar milho. A equipe que finalizar todas as etapas primeiro vence!', 'Padrão', FALSE, '2024-11-15 14:30:00', 6, FALSE, 3, FALSE, NOW(), 1, NULL, NULL),
      ('Tetarfe', 'Participe em duplas (um peão e uma prenda) e acumule pontos em lançamentos seguindo o Regulamento de Esportes Campeiros do MTG. Vence a equipe com a maior pontuação total. Em caso de empate, será considerado o maior desempenho em argola, ferradura e tava, nessa ordem.', 'Padrão', FALSE, '2024-11-15 13:30:00', 2, FALSE, 4, FALSE, NOW(), 1, NULL, NULL),
      ('Tiro de laço vaca parada', 'Forme uma dupla, laçe as aspas da vaca parada a 8 metros (homens) ou 2 metros (mulheres) em três tentativas, e vença acertando mais laçadas válidas.', 'Masculino, Feminino e Patrão/Patroa', FALSE, '2024-11-16 08:00:00', 2, FALSE, 5, FALSE, NOW(), 1, NULL, NULL),
      ('Pescaria gaúcha', 'Forme uma dupla com um peão e uma prenda, monte sua vara de pescar, busque sua isca e pesque o peixe mais pesado para vencer!', 'Padrão', FALSE, '2024-11-16 08:00:00', 2, FALSE, 6, FALSE, NOW(), 1, NULL, NULL),
      ('Truco', 'Forme uma equipe de três e participe das partidas eliminatórias de Truco Gaudério. A competição segue o Regulamento de Esportes Campeiros do MTG, com formato “melhor de três partidas”. A equipe vencedora será a campeã!', 'Masculino e Feminino', FALSE, '2024-11-16 08:00:00', 3, FALSE, 7, FALSE, NOW(), 1, NULL, NULL),
      ('Arroz Carreteiro', 'Forme uma equipe (um peão e uma prenda) e prepare arroz carreteiro para 40 pessoas. Os ingredientes e utensílios serão fornecidos, mas os temperos são de sua escolha para destacar sua identidade regional.', 'Padrão', FALSE, '2024-11-16 09:00:00', 2, TRUE, 8, FALSE, NOW(), 1, NULL, NULL),
      ('Artilharia gaúcha', 'Forme uma equipe de quatro (dois peões e duas prendas) e arremesse pedras com um bodoque para atingir os alvos. Cada acerto vale um ponto e a equipe com mais pontos vence. Em caso de empate, cada participante terá mais um disparo. Traga seu bodoque tradicional!', 'Padrão', FALSE, '2024-11-16 13:30:00', 4, FALSE, 9, FALSE, NOW(), 1, NULL, NULL),
      ('Cabo de guerra', 'Forme uma equipe de sete (mínimo de duas prendas) e participe do cabo de guerra com uma corda de 14 metros. Puxe a corda para seu lado e faça com que pelo menos um adversário ultrapasse a marca no chão. A equipe vencedora será a campeã!', 'Padrão', FALSE, '2024-11-16 14:30:00', 7, FALSE, 10, FALSE, NOW(), 1, NULL, NULL),
      ('Bocha campeira', 'Forme uma equipe de três (dois peões e uma prenda) e jogue bocha campeira seguindo o Regulamento de Esportes Campeiros do MTG. As partidas são disputadas em duas passadas, com uma passada extra em caso de empate. A equipe com mais pontos vence!', 'Padrão', FALSE, '2024-11-16 13:30:00', 3, FALSE, 11, FALSE, NOW(), 1, NULL, NULL);

-- Tabela teams
INSERT INTO teams (
    "grade", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      (95.5, FALSE, NOW(), 1, NULL, NULL),
      (88.0, FALSE, NOW(), 2, NULL, NULL),
      (92.3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela teams_users
INSERT INTO teams_users (
    "id_user", "id_team", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      (1, 1, FALSE, NOW(), 1, NULL, NULL),
      (2, 1, FALSE, NOW(), 1, NULL, NULL),
      (3, 2, FALSE, NOW(), 1, NULL, NULL),
      (4, 2, FALSE, NOW(), 1, NULL, NULL);

-- Tabela competitions_teams
INSERT INTO competitions_teams (
    id_team,
    id_competition,
    created_by,
    updated_by
) VALUES
      (1, 1, NULL, NULL),
      (1, 2, NULL, NULL),
      (1, 3, NULL, NULL),
      (1, 4, NULL, NULL),
      (1, 5, NULL, NULL),
      (2, 8, NULL, NULL);

-- Tabela commission
INSERT INTO commissions (
    id_competitions_teams,
    id_user,
    grade_1,
    grade_2,
    grade_3,
    grade_4,
    grade_5,
    grade_name_1,
    grade_name_2,
    grade_name_3,
    grade_name_4,
    grade_name_5,
    created_by,
    updated_by
) VALUES
      (1, 1, null, null, null, null, NULL, 'Technical', 'Creativity', 'Execution', 'Originality', NULL, 7, 7),

      (2, NULL , null, null, null, null, NULL, 'adS', 'ASD', 'adsd', 'asd', 'asd', 7, NULL),

      (2, 3, 7.5, 8.5, 9.0, 9.5, 8.0, 'Technical', 'Creativity', 'Execution', 'Teamwork', 'Originality', 7, 7);

-- Tabela timeline
INSERT INTO timeline (
    "name", "required", "date", "id_point", "id_competition", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Abertura oficial do 30º Encontrão', TRUE, '2024-11-15 00:09:00', 1, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Timeline Event B', TRUE, '2024-07-25 00:00:00', 2, 2, FALSE, NOW(), 2, NULL, NULL),
      ('Timeline Event C', FALSE, '2024-07-26 00:00:00', 3, 3, FALSE, NOW(), 3, NULL, NULL);






\copy users (name, cpf, avatar, registration, password, roles, id_campus)
    FROM 'C:/Users/Meu Computador/Documents/encontrão.csv'
    DELIMITER ','
    CSV HEADER;





















SELECT
    c.institution AS institution_name,               -- Nome da instituição
    comp.name AS competition_name,                   -- Nome da competição
    array_agg(u.name) AS participants                -- Participantes (nomes dos usuários)
FROM
    campus c                                         -- Tabela das instituições
        JOIN
    users u ON u.id_campus = c.id                    -- Relaciona os usuários à instituição
        JOIN
    teams_users tu ON tu.id_user = u.id              -- Relaciona usuários às equipes
        JOIN
    teams t ON tu.id_team = t.id                     -- Relaciona equipes aos times
        JOIN
    competitions_teams ct ON ct.id_team = t.id       -- Relaciona times às competições
        JOIN
    competitions comp ON ct.id_competition = comp.id -- Relaciona competições
WHERE
    c.deleted = FALSE AND u.deleted = FALSE AND
    t.deleted = FALSE AND comp.deleted = FALSE
GROUP BY
    c.institution, comp.name                         -- Agrupa por instituição e competição
ORDER BY
    c.institution, comp.name;                        -- Ordena por instituição e competição



SELECT u.*, c.institution AS campus
FROM users u
         JOIN campus c ON u.id_campus = c.id
WHERE u.cpf = :cpf AND u.deleted = FALSE;


SELECT
    u.id AS id,
    u."name" AS name,
    u.cpf AS cpf,
    u.registration AS registration,
    u.avatar AS avatar,
    u.is_evaluator AS isEvaluator,
    u.is_admin AS isAdmin,
    u.roles AS roles,
    c.id AS campusId,
    c.institution AS campus
FROM users u
         JOIN campus c ON u.id_campus = c.id
WHERE u.cpf = :cpf AND u.deleted = FALSE;


-- Seleciona todas as competições que têm comissão
SELECT *
FROM competitions
WHERE commission = TRUE;

-- Seleciona informações dos estudantes, times e competições com base na comissão
SELECT
    array_agg(u.name) AS participants,
    t.id AS "ID time",
    c.id AS competition_id,
    cam.institution AS campus_name
FROM
    users u
        JOIN
    teams_users tu ON u.id = tu.id_user
        JOIN
    teams t ON tu.id_team = t.id
        JOIN
    competitions_teams ct ON t.id = ct.id_team
        JOIN
    competitions c ON ct.id_competition = c.id
        JOIN
    campus cam ON u.id_campus = cam.id
WHERE
    c.commission = TRUE
  AND c.id = 1
GROUP BY
    t.id, c.id, cam.institution;

-- Seleciona informações da comissão e grades relacionadas ao time e competição
SELECT DISTINCT
    co.id AS "id da comissão",
    t.id AS "ID do time",
    co.grade_name_1,
    co.grade_name_2,
    co.grade_name_3,
    co.grade_name_4
FROM
    users u
        JOIN
    teams_users tu ON u.id = tu.id_user
        JOIN
    teams t ON tu.id_team = t.id
        JOIN
    competitions_teams ct ON t.id = ct.id_team
        JOIN
    competitions c ON ct.id_competition = c.id
        JOIN
    commissions co ON ct.id = co.id_competitions_teams
WHERE
    u.deleted = FALSE
  AND t.deleted = FALSE
  AND c.deleted = FALSE
  AND co.id_user IS NOT NULL
  AND t.id = 2;


-- Seleciona o ID dos times associados a uma competição específica
SELECT t.id
FROM competitions_teams ct
         JOIN teams t ON t.id = ct.id_team
WHERE ct.id_competition = :competitionId;

-- Seleciona informações do usuário com base no ID do time
SELECT
    u.id AS "id",
    u.name AS "name",
    u.registration AS "registration",
    u.roles AS "roles"
FROM teams_users tu
         JOIN users u ON u.id = tu.id_user
WHERE tu.id = :teamId;

-- Seleciona informações de múltiplos usuários com base em IDs de times
SELECT
    u.id AS "id",
    u.name AS "name",
    u.registration AS "registration",
    u.roles AS "roles"
FROM teams_users tu
         JOIN users u ON u.id = tu.id_user
WHERE tu.id IN (:teamIds);

-- Seleciona o ID dos times associados a uma competição específica
SELECT t.id
FROM competitions_teams ct
         JOIN teams t ON t.id = ct.id_team
WHERE ct.id_competition = :competitionId;

-- Seleciona todas as competições
SELECT *
FROM competitions c;

SELECT
    t.id AS team_id,
    t.grade,
    t.created,
    t.created_by,
    t.updated,
    t.updated_by,
    array_agg(u.name) AS participants
FROM
    teams t
        JOIN
    teams_users tu ON tu.id_team = t.id
        JOIN
    users u ON u.id = tu.id_user
WHERE
    t.deleted = FALSE
  AND tu.deleted = FALSE
GROUP BY
    t.id;

