CREATE SEQUENCE coordinate_seq START WITH 1 INCREMENT BY 1; 

CREATE TABLE coordinate(
	id INTEGER NOT NULL,
	x NUMERIC NOT NULL,
	y NUMERIC NOT NULL,
	poiName VARCHAR(50),
	PRIMARY KEY(id)
);

INSERT INTO coordinate(id,x,y,poiName) VALUES
(1,27,12,'Lanchonete'),
(2,31,18,'Posto'),
(3,15,12,'Joalheria'),
(4,19,21,'Floricultura'),
(5,12,8,'Pub'),
(6,23,6,'Supermercado'),
(7,28,2,'Churrascaria');

ALTER TABLE coordinate ALTER COLUMN id SET DEFAULT nextval('coordinate_seq');
