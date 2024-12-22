# Rendu 




---

### Fonctionnalités

1 - La **génératon des tuiles**

- [ ] Niveau 1 : avec une orientation initiale fixe
- [x] Niveau 2 : avec une orientation initiale aléatoire

2 - Le **génération du board** avec les tuiles :

- [ ] Niveau 1 : placées de façon fixe (toujours le même board de départ)
- [ ] Niveau 2 : placées toutes de façon aléatoire
- [x] Niveau 3 : placées de façon fixe ou aléatoire comme dans le jeu de société

3 - L'**ajout des objectifs** sur les tuiles :

- [ ] Niveau 1 : placés de façon fixe
- [ ] Niveau 2 : placés de façon totalement aléatoire, 1 seul maximum par tile
- [x] Niveau 3 : placés de façon aléatoire mais pas sur les cases du départ des joueurs

4 - La **distribution des "cartes objectifs"** aux joueurs :

- [x] Niveau 1 : de façon fixe
- [ ] Niveau 2 : de façon aléatoire

5 - Le **déplacement des pions case par case**
 - [x] Fait

6 - Le **déplacement d'une ligne ou colonne du board** par la tile supplémentaire :

- [ ] Niveau 1 : Toutes les lignes et colonnes
- [ ] Niveau 2 : Uniquement les lignes et colonnes impaires
- [x] Niveau 3 : En gérant le passage d'un player éjecté au côté opposé de la ligne/colonne actuelle
- [ ] Niveau 4 : En interdisant le déplacement inverse du précédent

7 - La **détection d'un objective atteint** par un player et le passage à l'objective suivant
- [x] fait en fin de tour

8 - La **détection de la fin du tour d'un player**
- [ ] Non Fait | Pas faisable de la manière dont on l'a implémenté (aucun moyen de savoir quand le Joueur veut s'arrêter de bouger)

9 - La **détection de la fin de partie** lorsqu'un player a trouvé tous ses objectifs et est revenu à sa position de départ
- [x] fait en fin de tour


## Evolution depuis le rendu1

- Le code a été mis en anglais par souci de cohérence. (sauf certains commentaires)
- Les classes Tuiles ont été remaniés afin d'avoir des morceaux de code plus court et plus simple a comprendre
- Utilisation du controller
- Supression de certaines classes inutile (Background.java) car elles n'ont au final pas été utiles
## Infos supplémentaires

- Les attributs privées des .form n'ont pas été mis sur le diagramme UML afin d'en faciliciter la lecture
- Idem pour les objectifs (ils n'y sont pas tous)
- Le jeu peut être joué par des personnes daltonien.
- On a tenté de faire le bonus 2 mais aucune solution n'a été trouvée pour relancer l'application
- Les seuls patron de conception implémenté fut le MVC et l'Observer, nous n'avons pas trouvé d'autres patrons "utile" permettant de nous faire gagner du temps (la facade a été envisagé avec "Background.java" mais fut abandonné)
- 