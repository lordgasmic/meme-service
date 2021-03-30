create table meme(
    id nvarchar(255) NOT NULL,
    CONSTRAINT meme_pk PRIMARY KEY (id)
);
create table meme_tag(
    id nvarchar(255) NOT NULL,
    uuid nvarchar(255) NOT NULL,
    tag nvarchar(255),
    CONSTRAINT meme_tag_pk PRIMARY KEY (id,uuid)
);
create table meme_path(
    id nvarchar(255) NOT NULL,
    path nvarchar(255) NOT NULL,
    CONSTRAINT meme_path_pk PRIMARY KEY (id)
);
create table meme_request(
    id int NOT NULL AUTO_INCREMENT,
    name nvarchar(255) NOT NULL,
    CONSTRAINT meme_request_pk PRIMARY KEY (id)
);
