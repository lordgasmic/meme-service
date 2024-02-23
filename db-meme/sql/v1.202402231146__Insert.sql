insert into meme (id)
values ('you-are-the-villain.jpeg');

insert into meme_tag (id, uuid, tag)
values ('you-are-the-villain.jpeg', UUID(), 'you are the villain');
insert into meme_tag (id, uuid, tag)
values ('you-are-the-villain.jpeg', UUID(), 'superheroes');

insert into meme_path (id, path)
values ('you-are-the-villain.jpeg', '/images/memes/you-are-the-villain.jpeg');

-- insert into meme (id) values ('aaaaaaaa');
-- insert into meme_tag (id, uuid, tag) values ('aaaaaaa', UUID(), 'art');
-- insert into meme_path (id, path) values ('aaaaaaa', '/images/memes/aaaaaaaa');
