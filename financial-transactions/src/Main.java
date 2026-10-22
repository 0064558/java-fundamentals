
void main() {

    List<Transaction> transactions = new ArrayList<>();
    TransactionService transactionService = new TransactionService();

    transactions.add(new Transaction(1L, "Salário", new BigDecimal("3000.00"), Category.INCOME));

    transactions.add(new Transaction(2L, "Comida", new BigDecimal("150.00"), Category.EXPENSE));
    transactions.add(new Transaction(3L, "Comida", new BigDecimal("80.00"), Category.EXPENSE));

    BigDecimal expenses = transactionService.calculateExpenses(transactions);
    BigDecimal incomes = transactionService.calculateIncome(transactions);
    BigDecimal totalBalance = transactionService.calculateTotalBalance(transactions);

    System.out.println("Transações: " + transactions);
    System.out.println("Despesas: " + expenses);
    System.out.println("Receitas: " + incomes);
    System.out.println("Saldo total: " + totalBalance);


    List<Transaction> incomesTransactions =
            transactionService.filterByCategory(transactions, Category.INCOME);

    List<Transaction> expensesTransactions =
            transactionService.filterByCategory(transactions, Category.EXPENSE);

    System.out.println("Receitas: " + incomesTransactions);
    System.out.println("Despesas: " + expensesTransactions);

    System.out.println(transactionService.findTransactionById(1L, transactions));
}
