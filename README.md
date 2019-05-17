Cette application est une API de gestion de collègue conçue dans le cadre d'une formation à Diginamic pour le compte de DTA Ingénierie.
Elle est accessible à l'URL suivante:
https://pennecot-collegues-api.herokuapp.com

collegues-api est toujours en développement.
Les opérations actuellement supportées sont les suivantes:

Authentification, étape obligatoire pour accéder aux autres fonctionnalités:
POST /auth
Cette opération nécessite un corps de requête au format json suivant:

{
	"email":"monEmail",
	"password":"monPasse"
}

Elle retourne une réponse http au corps vide.
Un jeu de donnée de test pour la connexion est disponible (comme toutes les fonctionnalités, il pourra être amené à changer). Vous pouvez actuellement vous connecter avec les utilisateurs suivants:
Utilisateur (accés aux fonctions de lecture uniquement):
email: vador@blackStar.com
password: toto

Administrateur (accés aux fonctions de lecture et écriture):
email: palpatine@empire.com
password: titi

Identification de l'utilisateur actuellement connecté:
GET /me
Cette opération renvoie une requête dont le corps indique le collegue actuellement connecté

Recherche des matricules (identifiants) par nom de collègue:
GET /collegues?nom=nomRecherche
Nom de test pour cette requête: Dark

Recherche par matricules (identifiants) de tous les collègues sauvegardés:
GET /collegues/{matricule}
Utilisez la requête précédente pour connaitre les matricules.

Création d'un nouveau collègue:
POST /collegues
Nécessite un corps de requête au format json représentant un collègue:
{
	"nom":"nom",
	"prenoms":"prenom1 prenom2",
	"email": "email",
	"dateDeNaissance": "jj-mm-aaaa",
	"photoUrl": "http://example",
	"password": "password"
}

Modification de l'email d'un collègue:
PATCH /modifyEmail
Nécessite un corps de requête au format json suivant:
{
	"matricule":"matricule",
	"email": "email"
}

Modification de l'url de la photo d'un collègue:
PATCH /modifyPhotoUrl
Nécessite un corps de requête au format json suivant:
{
	"matricule":"matricule",
	"photoUrl": "http://photoUrl"
}

Recherche de tous les url des photos des collègues:
GET /photos
