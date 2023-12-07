package software.ulpgc.kata5;

import java.util.stream.IntStream;

public class FactorialCommand implements Command{
    @Override
    public Output execute(Input input) {
        try{
            int number = Integer.parseInt(input.get(":number"));
            return isOutOfBounds(number)? outOfBoundsOutput() : outputOf(number);
        } catch (NumberFormatException e) {
            return nanOutput();
        }
    }

    private Output nanOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 405;
            }

            @Override
            public String result() {
                return "Not a number";
            }
        };
    }

    private Output outputOf(int number) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(factorialOf(number));
            }
        };
    }

    private static int factorialOf(int number) {
        return IntStream.range(1,number).reduce(1,(a, i)->a*i);
    }

    private Output outOfBoundsOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 404;
            }

            @Override
            public String result() {
                return "Number Out Of Bounds";
            }
        };
    }

    private boolean isOutOfBounds(int number) {
        return number<1||number>20;
    }
}
