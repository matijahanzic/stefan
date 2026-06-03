UPDATE stefan.design d
SET d.`4k` = d.`3k`,
    d.`8k` = d.`10k`,
    d.`16k` = d.`15k`,
    d.`32k` = d.`20k`,
    d.`64k` = d.`50k`,
    d.`128k` = d.`100k`,
    d.`3k` = NULL,
    d.`10k` = NULL,
    d.`15k` = NULL,
    d.`20k` = NULL,
    d.`50k` = NULL,
    d.`100k` = NULL,
    d.code = 'WH'
WHERE d.code = 'FOPAC' AND
      SUBSTR(d.designNumber, 0, 1) = '3' AND
      CHARACTER_LENGTH(d.designNumber) = 8