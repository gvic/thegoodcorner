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
