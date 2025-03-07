

public class Pokemon {
    private String cardSprite;
    private String namePoke;
    private String rarity; // common, uncommon, rare, etc
    private String cardType; // alternative art, full art, etc
    private String setFrom; //pokemon card set name where the pokemon is from

    public Pokemon(String cardSprite, String namePoke, String rarity,String cardType,String setPokemon) {
        this.cardSprite = cardSprite;
        this.namePoke = namePoke;
        this.rarity = rarity;
        this.cardType = cardType;
        this.setFrom = setPokemon;
    }

    public Pokemon(String cardSprite, String namePoke,String setPokemon) {
        this(cardSprite, namePoke,"Common","regular",setPokemon);
    }

    public String getNamePoke() {
        return this.namePoke;
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
    public String toString() {
        return "Pokemon " +
                "namePoke='" + namePoke + '\'' +
                ", rarity='" + rarity + '\'' +
                ", cardType='" + cardType + '\'' +
                ", setFrom='" + setFrom;
    }

}
