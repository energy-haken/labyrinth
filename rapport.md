# Rendu 1

## Package model : 

---

### <span style="color:orange"> Classes : </span>

#### Joueur 

- Il possède sa liste d'objectifs a remplir ainsi que le nombre déja éffecuté + restant
- Connais la tuile sur lequel il se trouve (pour faciliter le déplacement et détecter s'il se trouve sur l'objectif)


#### Plateau

- Contient les méthodes permettant de gérer les déplacement de tuiles

#### Tuiles
<span style="color:green"> Classe abstract</span>

- parent de **TuileLibre** et **TuileObjectif**
- a comme attribut son pattern, sa direction et un boolean pour savoir si la tuile est fixe ou non. 
- La combinaison de Pattern + Direction nous permettra de définir ou sont les sortie de la tuile et où sont les murs. 

#### TuileLibre

- enfant de **Tuile** 
- renvoie null pour getObjectif() car son role n'est pas d'accueiler des objectifs

#### TuileObjectif

- possède un attribut en plus : un Objectif associé

### <span style="color:orange"> Enumeration </span>

#### Direction

###### Utilisé pour connaître de multiples informations :
- l'orientation d'une tuile
- les sortie et murs d'une tuiles (en combinaison avec le pattern)
- le sens de rotation que le joueur veut faire subir a une tuile
- les paramètres de poussé d'une tuile (le sens et la direction)

#### Objectif

- Objectifs en rapport avec l'IUT
- liste non complète encore pour le moment

#### Pattern

- pattern utilisé pour la création de tuile
- utilisé pour faciliter et formaliser l'utilisation (notamment pour pouvoir définir ou sont les murs et les sorties d'une tuile)

