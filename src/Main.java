import model.Board;
import model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static board.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    private static Board board;
    private final static int BOARD_LIMIT = 9;

    private final String ARGS_TO_BE_USED = "\n" +
            "0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true " +
            "7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true " +
            "5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false " +
            "3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true " +
            "1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true " +
            "8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true " +
            "6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false " +
            "4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false " +
            "2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false " +
            "0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false " +
            "7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true " +
            "5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false";

    public static void main(String[] args) {
        final var positions = Stream.of(args).collect(toMap(
                k -> k.split(";")[0], v -> v.split(";")[1]
                ));

        int option = -1;

        while(true) {
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option){
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }

        }
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)){
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");
    }


    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado iniciado");
            return;
        }

        System.out.println("Informe a coluna que em que o número será inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que em que o número será inserido");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
        var value = runUntilGetValidNumber(1, 9);
        if (!board.changeValue(col, row, value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void removeNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado iniciado");
            return;
        }

        System.out.println("Informe a coluna que em que o número será inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que em que o número será inserido");
        var row = runUntilGetValidNumber(0, 8);
        if (!board.clearValue(col, row)){
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void showCurrentGame() {
    }

    private static void showGameStatus() {
    }

    private static void clearGame() {
    }

    private static void finishGame() {
    }


    private static int runUntilGetValidNumber(final int min, final int max){
        var current = scanner.nextInt();
        while (current < min || current > max){
            System.out.printf("Informe um número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;
    }
}



