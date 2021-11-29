insert into ordinateur (idordinateur, type, ram, processeur, marque, capacitedisque) values (1,'test','2Go','core i5','HP','250 Go');

insert into ordinateur (idordinateur, type, ram, processeur, marque, capacitedisque) values (2,'test','4Go','core i5','Lenovo','250 Go');

insert into ordinateur (idordinateur, type, ram, processeur, marque, capacitedisque) values (0, 'test','2Go','core i5','HP','250 Go');

insert into filliere (idfilliere, nom) values (0, 'Mathematiques Appliquees et informatique');

insert into filliere (idfilliere, nom) values (2, 'Multimedia informatiques et Communications');

insert into filliere (idfilliere, nom) values (1, 'Internet et Developpement Application');

insert into etudiant (idetudiant, idfilliere, idordinateur, prenom, nom, email, phone) values (2, 1, 1,'Seck','Fatou','fatou.seck@uvs.edu.sn', '778974532');

insert into etudiant (idetudiant, idfilliere, idordinateur, prenom, nom, email, phone) values (1, 0, 1,'Diop','Aisha','aisha.diop@uvs.edu.sn', '778890098');
	
insert into etudiant (idetudiant, idfilliere, idordinateur, prenom, nom, email, phone) values (0, 2, 2,'Ndiaye','Ousmane','ousmane.ndiaye@uvs.edu.sn', '785432211');