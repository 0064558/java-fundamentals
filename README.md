# CSV Summary Generator

Projeto em Java para praticar leitura e escrita de arquivos.

O programa:
- le um arquivo `.csv` com `nome, preco unitario, quantidade`;
- calcula o valor total por item (`preco * quantidade`);
- gera um arquivo `summary.csv` na pasta `out` dentro da pasta de origem.

## Exemplo de entrada
```csv
TV,1000.00,1
Mouse,40.00,2
Tablet,350.50,3
```

## Exemplo de saida (`out/summary.csv`)
```csv
TV, 1000.00
Mouse, 80.00
Tablet, 1051.50
```

## Conceitos praticados
- `File`, `FileReader`, `BufferedReader`
- `FileWriter`, `BufferedWriter`
- tratamento de excecoes com `try-with-resources`
- manipulacao de strings (`split`, `trim`)
