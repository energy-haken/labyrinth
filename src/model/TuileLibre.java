package model;

public class TuileLibre extends Tuile{
    public TuileLibre(boolean fixe , Pattern pattern){
        super(fixe , pattern) ;
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
