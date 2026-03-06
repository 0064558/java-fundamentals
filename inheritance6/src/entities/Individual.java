package entities;

public class Individual extends TaxPlayer {
    private Double healthExpenditures;

    public Individual(String name, Double anualIncoming, Double healthExpenditures) {
        super(name, anualIncoming);
        this.healthExpenditures = healthExpenditures;
    }

    @Override
    public Double tax() {

        // 1. Definindo a alíquota baseada na renda
        if (getAnualIncoming() < 20000.0) {
            return getAnualIncoming() * 0.15 - healthExpenditures * 0.5;
        } else {
            return getAnualIncoming() * 0.25 - healthExpenditures * 0.5;
        }


    }
}
