package entities;

public class ImportedProduct extends Product{
    private Double customFee;

    public ImportedProduct(String name, Double price, Double customFee) {
        super(name, price);
        this.customFee = customFee;
    }

    public Double getCustomFee() {
        return customFee;
    }

    public void setCustomFee(Double customFee) {
        this.customFee = customFee;
    }

    public Double totalPrice() {
        return super.getPrice() + customFee;
    }

    @Override
    public String priceTag() {
        return "Nome: " + getName()
                + " | Valor: $"
                + String.format("%.2f", totalPrice())
                + " | (Customs fee: $"
                + String.format("%.2f", customFee)
                + ")";
    }
}
