# Thales coding dojo

* Atelier 1 : Mise en place de deux micro-services chacun représentant un domaine fonctionnel (Ressource « collaborateur » et ressource « compétence »)
    * Création des projets et des jar standalone
    * Définition des ressources et de l’API CRUD pour chacune
    * Mise en place des tests unitaire en JUnit 5 associé
    * Bouchonnage pour les tests
    * Externalisation des paramètres
    * Log avec Logback

* Atelier 2 : Amélioration de nos microservices
    * Mise en place de la negociation de contenu sur un endpoint (retour xml ou json en fonction du header Accept)
    * Mise en place de la pagination
    * Mise en place de filtrage sur chacune de nos ressources
    * Mise en place du tri
    * Mise en place des réponses partielles

* Atelier 3 : Ajout de la sécurité
    * Mise en place d’un correlation ID et affichage dans les logs
    * Sécurisation de notre API avec Google OAuth2 api client java
    * Mise en place de rôle sur les API

* Atelier 4 : Ajout d’Actuator et de Spring Boot Admin
    * Mise en place d’Actuator sur le projet
    * Customisation d’Actuator (activation, desactivation de certain endpoint)
    * Ajout d’un endpoint customisé pour avoir des infos custom dans la page de status
    * Mise en place de SBA et visualisation des métrique via SBA
