package model;

public class TuileLibre extends Tuile{
    public TuileLibre(boolean fixe , Pattern pattern , int coordoneeX , int coordoneeY) {
        super(fixe , pattern, coordoneeX , coordoneeY); ;
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
