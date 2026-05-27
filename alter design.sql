alter table design
add column 8k decimal(7,2) DEFAULT NULL;


alter table design
add column 16k decimal(7,2) DEFAULT NULL;

alter table design
add column 32k decimal(7,2) DEFAULT NULL;

alter table design
add column 64k decimal(7,2) DEFAULT NULL;

alter table design
add column 128k decimal(7,2) DEFAULT NULL;

alter table design
add column code varchar(50) DEFAULT 'FOPAC';


alter table design
add column calcIsFerting tinyint(1) DEFAULT NULL;


alter table design
add column calcMinPoKom decimal(7,2) DEFAULT NULL;

alter table design
add column calcStezanjeTok decimal(7,2) DEFAULT NULL;

alter table design
add column calcStezanjeGlod decimal(7,2) DEFAULT NULL;

alter table design
add column calcSatnica decimal(7,2) DEFAULT NULL;

alter table design
add column calcEurPoKom decimal(7,2) DEFAULT NULL;


alter table design
add column posao1k decimal(7,2) DEFAULT NULL;


alter table design
add column posao2k decimal(7,2) DEFAULT NULL;

alter table design
add column posao3k decimal(7,2) DEFAULT NULL;

alter table design
add column posao4k decimal(7,2) DEFAULT NULL;

alter table design
add column posao5k decimal(7,2) DEFAULT NULL;

alter table design
add column posao8k decimal(7,2) DEFAULT NULL;

alter table design
add column posao10k decimal(7,2) DEFAULT NULL;

alter table design
add column posao15k decimal(7,2) DEFAULT NULL;

alter table design
add column posao16k decimal(7,2) DEFAULT NULL;

alter table design
add column posao20k decimal(7,2) DEFAULT NULL;

alter table design
add column posao32k decimal(7,2) DEFAULT NULL;

alter table design
add column posao50k decimal(7,2) DEFAULT NULL;

alter table design
add column posao64k decimal(7,2) DEFAULT NULL;

alter table design
add column posao100k decimal(7,2) DEFAULT NULL;

alter table design
add column posao128k decimal(7,2) DEFAULT NULL;

alter table design
add column posao200k decimal(7,2) DEFAULT NULL;

alter table design
add column posao500k decimal(7,2) DEFAULT NULL;


﻿ALTER TABLE stefan.businesspartner
ADD COLUMN isExternalSource TINYINT(1) NOT NULL DEFAULT 0


