CREATE TABLE `businesspartner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) NOT NULL,
  `displayName` varchar(1024) NOT NULL,
  `printInd` tinyint(1) not null DEFAULT '0',
  `printRow1`  varchar(4096) NOT NULL,
  `printRow2`  varchar(4096)  NULL,
  `printRow3`  varchar(4096) NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table stefan.bills
add column businessPartnerId int null


ALTER TABLE stefan.bills
ADD CONSTRAINT FK_BusinessPartner
FOREIGN KEY (businessPartnerId) REFERENCES stefan.businesspartner(id); 


insert into stefan.businesspartner(name,displayname,printInd, printRow1,printRow2,printRow3)
values ('Focke Packaging Solutions GmbH','Focke Packaging Solutions GmbH',1,
'Industriestr. 1','D-26671 Barßel','EORI-Nr. : DE3456110')

insert into stefan.businesspartner(name,displayname,printInd, printRow1,printRow2,printRow3)
values ('H.-H. Focke GmbH & Co','H.-H. Focke GmbH & Co',0,
'KG für Maschinenbau','Auguste - Viktoria - Allee 15A','D-13403 BERLIN')

insert into stefan.businesspartner(name,displayname,printInd, printRow1,printRow2,printRow3)
values ('Focke & Co. (GmbH & Co. KG)','Focke & Co. (GmbH & Co. KG)',0,
'Max-Planck-Straße 110','D-27283 VERDEN',NULL)

update bills 
set businessPartnerId = (select id from businesspartner where printind = 1)
where idbill > 0


alter TABLE `businesspartner`
add `city` varchar(1024) null

update stefan.businesspartner
set city = 'Barssel'
where id = 1

update stefan.businesspartner
set city = 'Berlin'
where id = 2

update stefan.businesspartner
set city = 'Verden'
where id = 3

alter table stefan.billitems add column itemOrderNumber int not null DEFAULT '0'

UPDATE stefan.billitems as b

inner join (
select idBillItem,
 
		  (SELECT count(*)
 
		   FROM stefan.billitems as i

		   WHERE i.idBillItem <= p.idBillItem AND i.idBill = p.idBill) as itemNumber

	   from stefan.billitems as p    

) as A on b.idBillItem = A.idBillItem

SET b.itemOrderNumber = A.itemNumber
