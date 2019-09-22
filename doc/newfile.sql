

alter table ARTICLE add checkInfo varchar(255);

UPDATE ARTICLE set checkInfo='审核失败' where ischecked=99
