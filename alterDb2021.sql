

ALTER TABLE stefan.design ADD COLUMN revision varchar(50) null

ALTER TABLE stefan.design ADD COLUMN isTokarenje tinyint(1) not null DEFAULT '1'
