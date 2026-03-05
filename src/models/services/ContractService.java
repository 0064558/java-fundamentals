package models.services;

import models.entities.Contract;
import models.entities.Installment;

import java.time.LocalDate;


public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {
        double basicQuota = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = onlinePaymentService.interest(basicQuota, i);
            double fee = onlinePaymentService.paymentFee(basicQuota + interest);
            double finalQuota = basicQuota + interest + fee;

            Installment installment = new Installment(dueDate, finalQuota);
            contract.addInstallment(installment);
        }
    }
}
