package entities;

public class Company extends TaxPlayer {

    private Integer numberOfEmployees;

    public Company(String name, Double anualIncoming, Integer numberOfEmployees) {
        super(name, anualIncoming);
        this.numberOfEmployees = numberOfEmployees;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public Double tax() {
        if (numberOfEmployees > 10) {
            return getAnualIncoming() * 0.14;
        } else {
            return getAnualIncoming() * 0.16;
        }
    }
}
