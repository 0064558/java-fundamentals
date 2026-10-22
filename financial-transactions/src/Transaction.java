import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
    private final Long ID;
    private String description;
    private BigDecimal value;
    private Category category;

    public Transaction(Long ID, String description, BigDecimal value, Category category) {

        if (ID == null || ID.compareTo(0L) <= 0) {
            throw new IllegalArgumentException(
                    "O ID deve estar preenchido e ser maior que zero."
            );
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição deve ser preenchida."
            );
        }

        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor deve ser maior que zero."
            );
        }

        if (category == null) {
            throw new IllegalArgumentException(
                    "A categoria deve ser informada."
            );
        }

        this.ID = ID;
        this.description = description;
        this.value = value;
        this.category = category;
    }

    public Long getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição deve ser preenchida."
            );
        }

        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {

        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor deve ser maior que zero."
            );
        }

        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {

        if (category == null) {
            throw new IllegalArgumentException(
                    "A categoria deve ser informada."
            );
        }

        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", category=" + category +
                '}';
    }
}
