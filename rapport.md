# Rendu 

## Package model : 

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
- [ ] Non Fait

9 - La **détection de la fin de partie** lorsqu'un player a trouvé tous ses objectifs et est revenu à sa position de départ
- [x] fait en fin de tour