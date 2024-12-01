# Rendu 1

## Package model : 

---

### <span style="color:orange"> Classes : </span>

#### Joueur 

- Il possède sa liste d'objectifs a remplir ainsi que le nombre déja éffecuté + restant
- Connais la tuile sur lequel il se trouve (pour faciliter le déplacement)


#### Plateau

- utiliser pour modifier le plateau du jeu 
- a voir si il ne doit pas être déplace dans le package "controller"

#### Tuiles
<span style="color:green"> Classe abstract</span>

- parent de **TuileLibre** et **TuileObjectif**
- a comme parametres son pattern et un boolean pour savoir si la tuile est fixe ou non

#### TuileLibre

- enfant de **Tuile** 
- renvoie null pour getObjectif() car son role n'est pas d'accueiler des objectifs
-> Decider de cette facon afin de pouvoir le modifier facilement plus tard

#### TuileObjectif

- possède un parametre en plus : un Objectif associé

### <span style="color:orange"> Enumeration </span>

#### Direction

- Utilisée pour savoir dans quel sens poussé une tuile + dans quel sens l'orienté

#### Objectif


- Objectifs en rapport avec l'IUT
- liste non completes encore pour le moment

#### Pattern

- pattern utilisé pour la création de tuile
- utilisé pour faciliter l'utilisation
