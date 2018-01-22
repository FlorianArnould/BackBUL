DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS activity CASCADE;
DROP TABLE IF EXISTS post CASCADE;

DROP TABLE IF EXISTS capteur CASCADE;
DROP TABLE IF EXISTS stats CASCADE;

CREATE TABLE category(
	id INTEGER  PRIMARY KEY NOT NULL,
	name varchar(250) NOT NULL
);
CREATE TABLE activity(
	id SERIAL PRIMARY KEY NOT NULL,
    idcat INTEGER REFERENCES category(id),
	title varchar(250) NOT NULL,
	description TEXT NOT NULL,
	name varchar(250) NOT NULL,
	phone varchar(15) NULL,
	email varchar(250) NULL,
    gpxLong  FLOAT ,
    gbxLat FLOAT ,
    url_image TEXT 
);
CREATE TABLE post(
	title varchar(250) NOT NULL,
	description TEXT NOT NULL,
	name varchar(250) NOT NULL,
	phone varchar(15),
	email varchar(250),
    constraint key primary key(name, title),
    gpsLong  FLOAT ,
    gbsLat FLOAT 
);
CREATE TABLE capteur(
	id SERIAL PRIMARY KEY NOT NULL,
    date DATE ,
    indPluie  FLOAT ,
    indPol FLOAT ,
    indTemp Float
);

CREATE TABLE stats(
    id SERIAL PRIMARY KEY NOT NULL,
	service  varchar(250) NOT NULL,
	long  FLOAT NOT NULL,
    lat  FLOAT NOT NULL,
	date bigint
);

INSERT INTO Stats(service,long,lat,date) values('actualites',49,0.23,240620171650);
INSERT INTO Stats(service,long,lat,date) values('annonces',49,0.23,240620171650);
INSERT INTO Stats(service,long,lat,date)  values('actualites',49,0.23,240620171650);
INSERT INTO Stats(service,long,lat,date)  values('annonces',49,0.23,240620171650);

INSERT INTO category(id,name) values(1,'outdoor');
INSERT INTO category(id,name) values(2,'cinema');
INSERT INTO category(id,name) values(3,'monument');
INSERT INTO category(id,name) values(4,'shop');

INSERT INTO activity(title,idcat,description,name,phone,email,url_image,gpxLong,gbxLat) values('Chateau Ducal',3,'Le château de Caen est un ensemble fortifié du centre-ville ancien de Caen. Fondé vers 1060 par Guillaume le Conquérant, il connait de nombreux aménagements au fil des siècles. Avec 5,5 hectares, c’est l’un des plus grands châteaux d’Europe2','mairie caen','0224124212','mairie@caen.fr','http://dome1.ensicaen.fr:8080/chateau.jpg',49.1866994,-0.3625547000000324);
INSERT INTO activity(title,idcat,description,name,phone,email,url_image,gpxLong,gbxLat) values('Eglise Saint Pierre',3,'Dénommée successivement Saint-Pierre de Darnetal, Saint-Pierre-sous-Caen, Saint-Pierre-du-Châtel, Saint-Pierre-en-Rive, cette église, souvent appelée à tort par les touristes « la cathédrale », était le plus grand édifice religieux de Bourg-le-Roi ; un soin particulier fut donc apporté à son développement. C’est dans cette église que se déroulaient les principales cérémonies publiques2. Par exemple, quand Henri IV abjure la religion protestante, mettant ainsi fin aux guerres de religion, c’est dans l’église Saint-Pierre qu''est chanté le Te Deum en présence des représentants civils et religieux de toute la cité.','mairie caen','0224124212','mairie@caen.fr','http://dome1.ensicaen.fr:8080/egliseSP.jpg',49.184017,-0.36064940000005663);
INSERT INTO activity(title,idcat,description,name,phone,email,url_image,gpxLong,gbxLat) values('Abbaye aux Dames',3,'L''abbaye aux Dames est un monastère de moniales bénédictines fondé au xie siècle à Caen, en région Normandie (France). C''est l''une des deux grandes abbayes de la ville de Caen. Son église abbatiale de la Trinité abrite depuis 1083 le tombeau de Mathilde de Flandre, épouse de Guillaume le Conquérant.
L''abbaye est le siège du Conseil régional de Normandie.','mairie caen','0224124212','mairie@caen.fr','http://dome1.ensicaen.fr:8080/abbaye.jpg',49.186541,-0.351996999999983);

INSERT INTO activity(title,idcat,description,name,phone,email,url_image,gpxLong,gbxLat) values('Eglise saint jean',3,'L''église Saint-Jean de Caen est l''église paroissiale du quartier Saint-Jean à Caen. Ce monument fait l’objet d’un classement au titre des monuments historiques par la liste de 18401.,En plein centre ville l''église saint Jean est au centre d''une certaine vie caennaise. Construite sur des marais, l''église a un côté qui penche, c''est près du choeur que se trouve le monument aux morts de la ville.','mairie caen','0224124212','mairie@caen.fr','http://dome1.ensicaen.fr:8080/eglise-saint-jean.jpg',49.1865405,-0.351996999999983);
INSERT INTO activity(title,idcat,description,name,phone,email,url_image,gpxLong,gbxLat) values('Quartier du Vaugueux',3,'Une belle rue et des ruelles du moyen age pavées qui nous mènent sur la place St Sauveur il faut faire la rue dans les deux sens pour bien apprécier, et regarder ,les cafés les boutiques ','mairie caen','0224124212','mairie@caen.fr','http://dome1.ensicaen.fr:8080/qVaugueux.jpg',49.18676130000001,-0.3602690000000166);

INSERT INTO activity(title,idcat,description,name,url_image,gpxLong,gbxLat) values ('SOLDES 50 %',4,'Votre magasin Propose des soldes allant a 50%','Magasin Kix','http://dome1.ensicaen.fr:8080/soldes.jpg',49.1822441,-0.365000);
INSERT INTO activity(title,idcat,description,name,phone,email,url_image,gpxLong,gbxLat) values('LAZER GAME',4,'Le Laser Game  de Caen est le seul jeu 100% Laser en Normandie (meilleure précision) et dispose de 3 Salles de jeu labyrinthiques avec étages ','mairie caen','0665124212','lazer@gtlazerg.fr','http://dome1.ensicaen.fr:8080/lazerG.jpg',49.1769572,-0.3512537);


INSERT INTO post(title,description,name,gpslong,gbsLat) values('Chat Perdu','je cherche mon chat (couleur noir ) qui a ete perdu avant hier sur la rue saint jean ' ,'Julie',49.1866994,-0.3625547000000324);
INSERT INTO post(title,description,name,phone,gpslong,gbsLat) values('colocation','bonjour , jeune etudiant je cherche une colocation a partir de mai' ,'Sami','0665124422',49.1866994,-0.3625547000000324);
INSERT INTO post(title,description,name,phone,gpslong,gbsLat) values('Cours de Math','bonjour , je propose des cours de math pour lycéens' ,'Axel','0622124512',49.1866994,-0.3625547000000324);
INSERT INTO post(title,description,name,phone,email,gpslong,gbsLat) values('Location Voiture','Saluut je mets  ma voiture en location pour le mois de Mars' ,'Henri','0665266111','henri@gmail.com',49.18676130000001,-0.3602690000000166);
INSERT INTO post(title,description,name,gpslong,gbsLat) values('Maison à vendre','Bonjour , ' ,'Maison rénovée ,possédant une véranda en dur , cuisine aménagée cheminée avec insert chaudière neuve ainsi qu''une isolation de 2 ans',49.18676130000001,-0.3602690000000166);
INSERT INTO post(title,description,name,gpslong,gbsLat) values('Charmant Studio','bonjour ,appartement à louer 14 rue saint jean 14000 Caen à partir du 1er février 2018.' ,'Marie',49.18676130000001,-0.3602690000000166);
INSERT INTO post(title,description,name,gpslong,gbsLat) values('Garage','Loue garage environ 3m par 5m 13 rue  du 11 novembre' ,'Jean Claude',49.18676130000001,-0.3602690000000166);
INSERT INTO post(title,description,name,phone,gpslong,gbsLat) values('Table ronde en merisier','coucou , j''offre une table ronde adresse : 52 rue saint jean' ,'Camille','0778124411',49.186541,-0.351996999999983);
INSERT INTO post(title,description,name,phone,gpslong,gbsLat) values('sous location','bonjour , jeune etudiant je cherche une colocation a partir de mai' ,'Maxime','0665125212',49.186541,-0.351996999999983);
INSERT INTO post(title,description,name,phone,gpslong,gbsLat) values('ID Lost','Hello I lost my id card ( Name: Jonny Miller) if anyone find it please contact me' ,'Jhonny','0044665124512',49.18676130000001,-0.3602690000000166);
INSERT INTO post(title,description,name,phone,gpslong,gbsLat) values('Match de foot','Bonjour on joue un match de foot le dimanche matin a 10 h rejoignez nous' ,'Kevin','0648124522',49.18676130000001,-0.3602690000000166);
