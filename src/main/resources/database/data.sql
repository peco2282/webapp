USE web_app;

# INSERT INTO survey

INSERT INTO vote(title,
                 description,
                 choices)
VALUES ('title1', 'name1', 'A,B,C'),
       ('title2', 'name2', 'B,C,D'),
       ('title3', 'name3', 'C,D,E'),
       ('title4', 'name4', 'D,E,F');

INSERT INTO user(name,
                 password,
                 role)
VALUES ('root', 'peco2282', 1),
       ('mapper', 'password', 2),
       ('peco2282', 'peco2282', 3);
