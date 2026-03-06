void main() {
    Scanner sc = new Scanner(System.in);

    System.out.printf("Número de linhas: ");
    int M = sc.nextInt();
    System.out.printf("Número de colunas: ");
    int N = sc.nextInt();
    int[][] matrix = new int[M][N];

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            matrix[i][j] = sc.nextInt();
        }
    }

    System.out.println("Valor a ser buscado: ");
    int X = sc.nextInt();

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if (matrix[i][j] == X) {
                System.out.println("Posição: " + (i + 1) + "," + (j + 1));
                if (j > 0) {
                    System.out.println("Esquerda: " + matrix[i][j - 1]);
                }
                if (i > 0) {
                    System.out.println("Acima: " + matrix[i - 1][j]);
                }
                if (j < N - 1) {
                    System.out.println("Direita: " + matrix[i][j + 1]);
                }
                if (i < M - 1) {
                    System.out.println("Abaixo: " + matrix[i + 1][j]);
                }
            }
        }
    }
}
