ALTER TABLE annuaire.detail_inst
ALTER COLUMN url TYPE text;

INSERT INTO annuaire.cat_inst(id_cat_inst,designation,description) VALUES (10,'Organismes gouvernementaux ','Les organismes gouvernementaux sont charges d elaborer des politiques, des reglementations et de superviser et gerer differents ensembles d ecosystemes, les ressources et les composantes de la biodiversite.');
INSERT INTO annuaire.cat_inst(id_cat_inst,designation,description) VALUES (11,'Universites et instituts de recherche','Des efforts seront faits pour renforcer les programmes de recherche sur la taxonomie, les ressources genetiques et l utilisation durable de la biodiversite des matieres connexes.');


INSERT INTO annuaire.cat_rh(
id_cat_rh,designation, description)
VALUES (10,'Chercheurs scientifique','Les chercheurs scientifiques seront consultes afin de faciliter et de contribuer a la compilation et a l evaluation des RG et des CTA');

INSERT INTO annuaire.cat_rh(
id_cat_rh,designation, description)
VALUES (11,'Agriculteurs','Les fournisseurs de ressources genetiques seront consultes afin de faciliter et de contribuer à la compilation et a l evaluation des RG et des CTA.');

INSERT INTO annuaire.detail_inst(id_inst,nom,description,activite,adresse,tel,fax,email,site_web,cat,url)
VALUES (1,'Direction Generale des forets (DFG)','La direction generale des forets (DFG) est un etablissement publique algerien charge de la gestion des forets','Gestion durable des frets','Chemin Doudou Mokhtar N 32 Alger, Algérie','023 20 30 56','023 20 30 56','foret.algerie@gmail.com','http:/www.dfg.org/dz/fr',10,'https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12787.303886201842!2d3.0128717!3d36.7507481!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x652027cda11ccc54!2sDirection%20G%C3%A9n%C3%A9rale%20des%20For%C3%AAts%20-%20Alger!5e0!3m2!1sfr!2sdz!4v1568840655716!5m2!1sfr!2sdz');

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (2,'Ministere de l Agriculture et du Developpement Rural et de la Peche (MADRP)',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (3,'Haut-commissariat pour le developpement de la steppe (HCDS)',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (4,'Ministere de l’Intérieur et des Collectivites locales (MICL)',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (5,'Ministere des Ressource en Eau et de de l Environnement (MREE)',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (6,'Ministere de l Interieur et des Collectivites locales',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (7,'Ministere de la Culture',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (8,'Ministere de l Energie',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (9,'Ministere de l Enseignement Superieur et de la Recherche scientifique',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (10,'Ministere de l Education nationale',10);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (11,'Centre national pour le developpement des ressources biologiques (CNDRB)',11);

INSERT INTO annuaire.detail_inst(id_inst,nom,cat)
VALUES (12,'Agence nationale de developpement de la recherche universitaire',11);

INSERT INTO annuaire.detail_rh (id_rh,nom,prenom,metier,tel,fax,email,site_web,cat) values  (1,'Ahmed','Mohammed','enseignant chercheur a l INA','023 20 30 56','023 20 30 56','ahmed@gmail.com','http:/www.dfg.org/dz/fr', 10);
INSERT INTO ressource.lot(
	id, description)
VALUES (5, 'annuaire');

INSERT INTO ressource.lot(
	id, description)
VALUES (6, 'GED');

INSERT INTO ressource.lot(
	id, description)
	VALUES (0, 'alimentations et agricultures');
	
INSERT INTO ressource.lot(
	id, description)
	VALUES (1, 'animales');
	
	INSERT INTO ressource.lot(
	id, description)
	VALUES (2, 'forestieres');
	
	INSERT INTO ressource.lot(
	id, description)
	VALUES (3, 'marines');
	
	INSERT INTO ressource.lot(
	id, description)
	VALUES (4, 'micro organismes');
	
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (0, 'Un fourrage est, dans le domaine de agriculture, une plante ou un mélange de plantes utilisé pour alimentation des animaux élevage.', 'les fourrages', 0);
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (1, 'horticulture se pratique en milieu rural, périurbain et urbain. horticulture urbaine et périurbaine (HUP), encore peu reconnue, tend à se développer dans les villes en expansion2 ', 'cultures horticoles', 0);
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (2, 'systèmes de culture, agronomie : classification thématique des thèmes et articles pour le thème systèmes de culture, agronomie.', 'cultures agronomiques', 0);
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (3, 'description de classification ', 'classification 1 animal ', 1);
	
		INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (4, 'description de classification', 'classification 2 animal ', 1);
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (5, 'description de classification', 'classification 1 forestieres', 2);
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (6, 'description de classification', 'classification 2 forestieres', 2);
	
	INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (7, 'description de classification', 'classification 1 marines', 3);
	
		INSERT INTO ressource.classification(
	id, description, nom, lot_id)
	VALUES (8, 'description de classification', 'classification 1 micro organisme', 4);
	

/*
	
	

	



INSERT INTO ressource.permission(
	id, description, nom)
	VALUES (0, 'dzdzdzdzd', 'modifier');
	
		INSERT INTO ressource.permission(
	id, description, nom)
	VALUES (1, 'valider une modif', 'valider');
	
		INSERT INTO ressource.permission(
	id, description, nom)
	VALUES (2, 'ajouter une ressources', 'ajouter');

INSERT INTO role_permissions(
	roles_id, permissions_id)
VALUES (0, 0);

INSERT INTO role_permissions(
	roles_id, permissions_id)
VALUES (0, 1);


INSERT INTO role_permissions(
	roles_id, permissions_id)
VALUES (0, 2);

INSERT INTO utilisateur_roles(
	utilisateurs_id, roles_id)
VALUES (0, 0);

*/
