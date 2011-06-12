-- Il faut commencer par cette requete avant celle des département
-- car ils ont une relation avec la table region NOT NULL

INSERT INTO region(nom) VALUES ('Alsace') 
,('Aquitaine')
,('Auvergne')  
,('Basse Normandie')   
,('Bourgogne')   
,('Bretagne')   
,('Centre')   
,('Champagne Ardenne')   
,('Corse')   
,('Franche Comte')   
,('Haute Normandie')   
,('Ile de France')   
,('Languedoc Roussillon')   
,('Limousin')   
,('Lorraine')   
,('Midi-Pyrénées')   
,('Nord Pas de Calais')   
,('Provence Alpes Côte d''Azur')   
,('Pays de la Loire')   
,('Picardie')   
,('Poitou Charente')   
,('Rhone Alpes');  

-- Il faut commencer par la requete des régions avant celle ci
-- car les departements ont une relation avec la table region NOT NULL

INSERT INTO departement(nom,numero,region_id) VALUES ('Ain'                     ,'1','22'),
('Aisne'                   ,'2','20'),
('Allier'                  ,'3','3'),
('Alpes de haute provence' ,'4','18'),  
('Hautes alpes'            ,'5','18'), 
('Alpes maritimes'         ,'6','18'),  
('Ardèche'                 ,'7','22'),
('Ardennes'                ,'8','8'),
('Ariège'                  ,'9','16'),
('Aube'                    ,'10','8'),
('Aude'                    ,'11','13'),
('Aveyron'                 ,'12','16'),
('Bouches du rhône'        ,'13','18' ),
('Calvados'                ,'14','4'),
('Cantal'                  ,'15','3'),
('Charente'                ,'16','21'),
('Charente maritime'       ,'17','21'),  
('Cher'                    ,'18','7'),
('Corrèze'                 ,'19','14'),
('Côte d''or'               ,'21','5'),
('Côtes d''Armor'           ,'22','6'),  
('Creuse'                  ,'23','14'),
('Dordogne'                ,'24','2'),
('Doubs'                   ,'25','10'),
('Drôme'                   ,'26','22'),
('Eure'                    ,'27','11'),
('Eure et Loir'            ,'28','7'), 
('Finistère'               ,'29','6'),
('Gard'                    ,'30','13'),
('Haute garonne'           ,'31','16'),  
('Gers'                    ,'32','16'),
('Gironde'                 ,'33','2'),
('Hérault'                 ,'34','13'),
('Ile et Vilaine'          ,'35','6'),  
('Indre'                   ,'36','7'),
('Indre et Loire'          ,'37','7'),  
('Isère'                   ,'38','22'),
('Jura'                    ,'39','10'),
('Landes'                  ,'40','2'),
('Loir et Cher'            ,'41','7'),  
('Loire'                   ,'42','22'),
('Haute loire'             ,'43','3'),
('Loire Atlantique'        ,'44','19'),  
('Loiret'                  ,'45','7'),
('Lot'                     ,'46','16'),
('Lot et Garonne'          ,'47','2'),  
('Lozère'                  ,'48','13'),
('Maine et Loire'          ,'49','19'),  
('Manche'                  ,'50','4'),
('Marne'                   ,'51','8'),
('Haute Marne'             ,'52','8'),  
('Mayenne'                 ,'53','19'),
('Meurthe et Moselle'      ,'54','15'), 
('Meuse'                   ,'55','15'),
('Morbihan'                ,'56','6'),
('Moselle'                 ,'57','15'),
('Nièvre'                  ,'58','5'),
('Nord'                    ,'59','17'),
('Oise'                    ,'60','20'),
('Orne'                    ,'61','4'),
('Pas de Calais'           ,'62','17'),  
('Puy de Dôme'             ,'63','3'),  
('Pyrénées Atlantiques'    ,'64','2'),       
('Hautes Pyrénées'         ,'65','16'),  
('Pyrénées Orientales'     ,'66','13'),  
('Bas Rhin'                ,'67','1'),
('Haut Rhin'               ,'68','1'),
('Rhône'                   ,'69','22'),
('Haute Saône'             ,'70','10' ), 
('Saône et Loire'          ,'71','5'),  
('Sarthe'                  ,'72','19'),
('Savoie'                  ,'73','22'),
('Haute Savoie'            ,'74','22'),
('Paris'                   ,'75','12'),
('Seine Maritime'          ,'76','11'),
('Seine et Marne'          ,'77','12'), 
('Yvelines'                ,'78','12'),
('Deux Sèvres'             ,'79','21'), 
('Somme'                   ,'80','20'),
('Tarn'                    ,'81','16'),
('Tarn et Garonne'         ,'82','16'),  
('Var'                     ,'83','18'),
('Vaucluse'                ,'84','18'),
('Vendée'                  ,'85','19'),
('Vienne'                  ,'86','21'),
('Haute Vienne'            ,'87','14'), 
('Vosge'                   ,'88','15'),
('Yonne'                   ,'89','5'),
('Territoire de Belfort'   ,'90','10'),  
('Essonne'                 ,'91','12'),
('Haut de seine'           ,'92','12'),  
('Seine Saint Denis'       ,'93','12'),  
('Val de Marne'            ,'94','12'),  
('Val d''Oise'              ,'95','12'),
('Corse du Sud'            ,'2a','9'), 
('Haute Corse'             ,'2b','9');
   
INSERT INTO categorie(nom, parente_id)
VALUES ('Toutes catégories', null);

INSERT INTO categorie(nom, parente_id)
VALUES ('Vehicules', 1),
('Immobilier', 1),
('Multimedia', 1),
('Maison', 1),
('Loisirs', 1),
('Emploi et Services', 1),
('Autres', 1);

	-- Vehicules --
INSERT INTO categorie(nom, parente_id)
VALUES ('Caravaning', 2),
('Equipement Auto', 2),
('Equipement Caravaning', 2),
('Equipement Moto', 2),
('Motos', 2),
('Nautisme', 2),
('Utilitaires', 2),
('Voitures', 2);
	    
    -- IMMOBILIER --
INSERT INTO categorie(nom, parente_id)
VALUES ('Bureaux et Commerces', 3),
('Colocations', 3),
('Locations', 3),
('Locations de vacances', 3),
('Ventes immobilières', 3);
    
    -- MULTIMEDIA --
INSERT INTO categorie(nom, parente_id)
VALUES ('Consoles et Jeux vidéo', 4),
('Image et Son', 4),
('Informatique', 4),
('Téléphonie', 4);
   
    -- MAISON --
INSERT INTO categorie(nom, parente_id)
VALUES ('Accessoires et Bagagerie', 5),
('Ameublement', 5),
('Arts de la table', 5),
('Bricolage / Jardinage', 5),
('Décoration', 5),
('Electroménager', 5),
('Equipement bébé', 5),
('Linge de maison', 5),
('Montres et Bijoux', 5),
('Vêtements', 5);
 
    -- LOISIRS --
INSERT INTO categorie(nom, parente_id)
VALUES ('Animaux', 6),    
('CD / Musique', 6),
('Collection', 6),
('DVD / Films', 6),
('Instruments de musique', 6),
('Jeux et Jouets', 6),
('Livres', 6),
('Sports et Hobbies', 6),
('Vins et Gastronomie', 6);
    
    -- EMPLOI et SERVICES --
INSERT INTO categorie(nom, parente_id)
VALUES ('Billetterie', 7),   
('Cours particuliers', 7),   
('Emploi', 7),    
('Evénements', 7),   
('Matériel professionnel', 7),   
('Services', 7);
    
	-- Autres --
