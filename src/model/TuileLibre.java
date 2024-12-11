package model;

public class TuileLibre extends Tuile{
    public TuileLibre(boolean fixe , Pattern pattern , int coordoneeX , int coordoneeY, int name) {
        super(fixe , pattern, coordoneeX , coordoneeY, name); ;
    }

    /**
     * Aucun objectif présent sur les cases libres
     * @return null
     */
    @Override
    public Objectif getObjectif(){
        return null ;
    }
}
