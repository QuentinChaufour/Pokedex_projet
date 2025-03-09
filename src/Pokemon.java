

public class Pokemon {
    private String cardSprite;
    private String namePoke;
    private String numPoke;
    private String rarity; // common, uncommon, rare, etc
    private String setFrom; //pokemon card set name where the pokemon is from

    public Pokemon(String cardSprite, String namePoke, String numPoke, String rarity,String setPokemon) {
        this.cardSprite = cardSprite;
        this.namePoke = namePoke;
        this.numPoke = numPoke;
        this.rarity = rarity;
        this.setFrom = setPokemon;
    }

    public Pokemon(String cardSprite, String namePoke,String numPoke,String setPokemon) {
        this(cardSprite, namePoke,numPoke,"Common",setPokemon);
    }

    public String getNamePoke() {
        return this.namePoke;
    }

    public String getNumPoke() {
        return this.numPoke;
    }

    public String getRarity() {
        return this.rarity;
    } 

    public String getSetFrom() {
        return this.setFrom;
    }

    public String getCardSprite() {
        return this.cardSprite;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) obj;
        return pokemon.numPoke.equals(this.numPoke);
    }

    @Override
    public int hashCode() {
        return numPoke.hashCode();
    }

    @Override
    public String toString() {
        return "Pokemon " +
                "namePoke='" + namePoke + '\'' +
                ", rarity='" + rarity + '\'' +
                ", setFrom='" + setFrom;
    }

}
