DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS authors;
CREATE TABLE authors (
                                       id INT NOT NULL PRIMARY KEY,
                                       nick VARCHAR(15),
                                       name VARCHAR(255)
);
CREATE TABLE posts (
    id INT NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    content VARCHAR(255),
    author_id INT REFERENCES authors,
    status VARCHAR(255) default 'DRAFT',
    version INTEGER
);

CREATE TABLE comments (
    id INT NOT NULL PRIMARY KEY,
    content VARCHAR(255),
    post_id INT REFERENCES posts ON DELETE CASCADE,
    author_id INT REFERENCES authors,
    version INTEGER
);



INSERT INTO authors(id, nick,name) VALUES ( 1,'monster','vasya pupkin' );
INSERT INTO authors(id, nick,name) VALUES ( 2,'romashka','masha romanova' );
INSERT INTO authors(id, nick,name) VALUES ( 3,'afrodita','fekla sidorova' );
INSERT INTO authors(id, nick,name) VALUES ( 4,'ivan fedorovich','ivan fedorovich pahomov' );
INSERT INTO posts(id, title, content, author_id, version) VALUES ( 1, 'tits' ,'i love big tits', 1, 1);
INSERT INTO posts(id, title, content, author_id, version) VALUES ( 5, 'girls' ,'i love girls with big tits', 1, 1);
INSERT INTO posts(id, title, content, author_id, version) VALUES ( 2, 'ukraine' ,'our ukrainian partners are directed by our american partners!', 4, 2);
INSERT INTO posts(id, title, content, author_id, version) VALUES ( 6, 'america' ,'americans have destroyed our great USSR!', 4, 1);
INSERT INTO posts(id, title, content, author_id, version, status) VALUES ( 3, 'beauty' ,'everybody says i am the most beautiful girl in universe', 3, 1, 'RELEASE');
INSERT INTO posts(id, title, content, author_id, version) VALUES ( 4, 'dinner' ,'how to cook the pork under mayo and cheese', 2, 1);
INSERT INTO comments(id, content, post_id, author_id, version) VALUES ( 1, '+100', 1, 4,1 );
INSERT INTO comments(id, content, post_id, author_id, version) VALUES ( 2, 'show your tits!', 3, 1, 1 );
INSERT INTO comments(id, content, post_id, author_id, version) VALUES ( 3, 'show your tits!', 4, 1, 1 );
INSERT INTO comments(id, content, post_id, author_id, version) VALUES ( 4, 'show your tits!', 2, 1, 1 );
INSERT INTO comments(id, content, post_id, author_id, version) VALUES ( 5, 'americans suck', 2, 1, 1 );
INSERT INTO comments(id, content, post_id, author_id, version) VALUES ( 6, 'do you put onion to pork under mayo?', 4, 2, 1 );