ALTER TABLE stefan.businesspartner
ADD COLUMN isExternalSource TINYINT(1) NOT NULL DEFAULT '0';

ALTER TABLE orderitems
ADD COLUMN pricePerPartOverride DECIMAL(7, 2);
