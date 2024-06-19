DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS survey;
DROP TABLE IF EXISTS user;

CREATE TABLE vote
(
    id          INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(50),
    description TEXT NOT NULL,
    choices     TEXT NOT NULL
);
CREATE TABLE survey
(
    id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title  VARCHAR(50),
    choice VARCHAR(100)
);
CREATE TABLE user
(
    id       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(10) NOT NULL,
    password VARCHAR(10) NOT NULL,
    role     INT         NOT NULL
);
