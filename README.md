# Processamento de Contratos

Projeto Java para simular o processamento de contratos com parcelamento mensal e cobranca via servico de pagamento online.

## Funcionalidades
- Leitura de contrato (numero, data e valor total)
- Geracao de parcelas por quantidade de meses informada
- Calculo de vencimento de cada parcela (`+1`, `+2`, `+3` meses...)
- Aplicacao de:
  - Juros simples de `1% ao mes`
  - Taxa de pagamento de `2%` (Paypal)
- Exibicao das parcelas com data e valor final

## Estrutura
- `entities`
  - `Contract`: dados do contrato e lista de parcelas
  - `Installment`: data de vencimento e valor da parcela
- `services`
  - `OnlinePaymentService`: interface de servico de pagamento
  - `PaypalService`: implementacao com regra de juros/taxa
  - `ContractService`: processa contrato e gera parcelas

## Conceitos praticados
- Programacao orientada a objetos
- Interfaces e injecao de dependencia
- Separacao de responsabilidades
- Manipulacao de datas com `LocalDate`
