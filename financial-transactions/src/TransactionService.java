import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionService {


    public BigDecimal calculateExpenses(List<Transaction> transactions) {

        BigDecimal totalExpenses = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getCategory() == Category.EXPENSE) {
                totalExpenses = totalExpenses.add(transaction.getValue());
            }
        }

        return totalExpenses;
    }

    public BigDecimal calculateIncome(List<Transaction> transactions) {

        BigDecimal totalIncome = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getCategory() == Category.INCOME) {
                totalIncome = totalIncome.add(transaction.getValue());
            }
        }

        return totalIncome;
    }

    public BigDecimal calculateTotalBalance(List<Transaction> transactions) {
        BigDecimal totalIncome = calculateIncome(transactions);
        BigDecimal totalExpense = calculateExpenses(transactions);

        return totalIncome.subtract(totalExpense);
    }

    public List<Transaction> filterByCategory(List<Transaction> transactions, Category category) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getCategory() == category) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public Transaction findTransactionById(Long id, List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (Objects.equals(transaction.getID(), id)) {
                return transaction;
            }
        }
        return null;
    }
}
