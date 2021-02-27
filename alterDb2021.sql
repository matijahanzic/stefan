

ALTER TABLE stefan.design ADD COLUMN revision varchar(50) null

ALTER TABLE stefan.design ADD COLUMN isTokarenje tinyint(1) not null DEFAULT '1'

ALTER TABLE stefan.orders ADD COLUMN businessPartnerId int null

ALTER TABLE stefan.orders ADD CONSTRAINT FK_Orders_BusinessPartner FOREIGN KEY (businessPartnerId) REFERENCES stefan.businesspartner(id); 

ALTER TABLE stefan.businesspartner ADD COLUMN requireShippingDate tinyint(1) not null DEFAULT '1'

ALTER TABLE stefan.orders ADD COLUMN shippingDate date null

UPDATE stefan.businesspartner SET requireShippingDate = 0 WHERE id = 3


CREATE INDEX Design_designNumber ON stefan.design (designNumber);
CREATE INDEX Design_designIdentity ON stefan.design (designIdentity);
CREATE INDEX Design_classMark ON stefan.design (classMark);