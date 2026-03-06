package entities;

public abstract class TaxPlayer {
    private String name;
    private Double anualIncoming;

    public TaxPlayer(String name, Double anualIncoming) {
        this.name = name;
        this.anualIncoming = anualIncoming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAnualIncoming() {
        return anualIncoming;
    }

    public void setAnualIncoming(Double anualIncoming) {
        this.anualIncoming = anualIncoming;
    }

    public abstract Double tax();
}
