USE web_app;
INSERT INTO vote(
                 title,
                 name,
                 time
)
VALUES
    ('title1', 'name1', 1),
    ('title2', 'name2', 4),
    ('title3', 'name3', 9),
    ('title4', 'name4', 16);

INSERT INTO user(
                 name,
                 password,
                 role
)
VALUES
    ("root", "peco2282", 0),
    ("user", "password", 2),
    ("peco2282", "peco2282", 3);

SELECT id, name, password, role FROM user;
