create or replace view meme_vw as select * from meme;
grant select, insert, update, delete on web.meme_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.meme_vw to 'web_appl'@'%';
create or replace view meme_tag_vw as select * from meme_tag;
grant select, insert, update, delete on web.meme_tag_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.meme_tag_vw to 'web_appl'@'%';
create or replace view meme_path_vw as select * from meme_path;
grant select, insert, update, delete on web.meme_path_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.meme_path_vw to 'web_appl'@'%';
