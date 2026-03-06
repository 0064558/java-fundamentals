# Cálculo de Impostos

Projeto Java de cálculo de impostos para pessoa física e jurídica, com leitura de **N** contribuintes e cálculo individual conforme as regras:

## Pessoa física

- Pessoas cuja renda foi abaixo de `20000.00` pagam `15%` de imposto.
- Pessoas com renda de `20000.00` em diante pagam `25%` de imposto.
- Se a pessoa teve gastos com saúde, `50%` destes gastos são abatidos no imposto.

Exemplo:

`(50000 * 25%) - (2000 * 50%) = 11500.00`

## Pessoa jurídica

- Pessoas jurídicas pagam `16%` de imposto.
- Se a empresa possuir mais de 10 funcionários, paga `14%` de imposto.

Aplicação dos conceitos de herança e polimorfismo com as classes `TaxPlayer`, `Individual` e `Company`.


## Terminal
![img.png](img.png)