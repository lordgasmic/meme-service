create or replace view meme_path_vw as
select *
from meme_path;
grant select, insert, update, delete on web.meme_path_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.meme_path_vw to 'web_appl'@'%';
