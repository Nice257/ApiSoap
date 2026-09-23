# API SOAP — Intégration Interopérabilité (UPG TIC/GL4)

Projet d'examen du cours **API SOAP : Intégration Interopérabilité (SOAP / REST)**. Ce dépôt contient le **système Legacy Spring Boot** qui expose un service SOAP de portail fournisseur. L'application Node.js/MongoDB est développée dans un dépôt séparé et consomme ce service SOAP.

## Équipe & répartition des tâches

### 👤 Personne 1 — Backend Spring Boot (Contrat SOAP)

Responsabilités :

* Créer et vérifier les tables SQL : `supplier` et `supplier_catalog`
* Écrire le contrat **XSD** avec les types corrects (`decimal`, `int`, namespaces)
* Implémenter `SupplierEndpoint.java`
* Configurer le WSDL (`WebServiceConfig.java`)
* Lancer le serveur et vérifier l'accès au WSDL

**Livrable :** Serveur SOAP fonctionnel (`/ws/mon-service.wsdl`).

---

### 👤 Personne 2 — Client Node.js (Consommation SOAP)

Responsabilités :

* Installer la librairie `soap`
* Développer `supplierSoapClient.js`
* Effectuer l'appel asynchrone à `getSupplierCatalogAndPricing`
* Transformer proprement la réponse XML en objet JSON
* Gérer les erreurs `SOAP Fault`

**Livrable :** Client SOAP testé et prêt à être intégré.

---

### 👤 Personne 3 — Intégration, Réutilisation & Livrables

Responsabilités :

* Adapter `productSyncService.js` en réutilisant le modèle Mongoose `Product`
* Synchroniser les produits du fournisseur vers MongoDB
* Préparer la collection SoapUI / Postman (requête, réponse et cas d'erreur)
* Gérer le `.gitignore` et le `README`
* Coordonner les commits et les push Git
* Tester les erreurs :

  * Spring Boot indisponible
  * Fournisseur inexistant (`SOAP Fault`)

**Livrable :** Intégration complète entre SOAP et l'application REST.

---

## Technologies utilisées

* Java 17 / Spring Boot
* Spring Web Services (SOAP)
* Spring Data JPA
* H2 Database
* WSDL / XSD
* Maven

## Architecture

`Spring Boot (SOAP Server) → WSDL → Node.js (SOAP Client) → MongoDB`

## Lancement du projet

### Compiler

```bash
mvn clean install
```

### Démarrer

```bash
mvnw.cmd spring-boot:run
```

### WSDL

```text
http://localhost:8080/ws/mon-service.wsdl
```

### Console H2

```text
http://localhost:8080/h2-console
```

* JDBC URL : `jdbc:h2:mem:supplierportal`
* User : `sa`
* Password : *(vide)*

## Test SOAP

Envoyer une requête **POST** vers :

```text
http://localhost:8080/ws
```

avec le body XML correspondant à l'opération `getSupplierCatalogAndPricing`.

## Critères respectés

* Contrat WSDL/XSD correctement typé (`decimal`, `int`)
* Appel SOAP asynchrone
* Transformation XML → JSON propre
* Réutilisation de la logique métier
* Gestion des erreurs via `SOAP Fault`

---

**Université Polytechnique de Gitega — TIC/GL4 (2025–2026)**
