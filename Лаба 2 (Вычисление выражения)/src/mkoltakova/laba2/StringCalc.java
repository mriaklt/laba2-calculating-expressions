package mkoltakova.laba2;

/*
    Строка разбивается на цифры и знаки действийб они записываются в массив chars
    чтобы было проще это сделать, всё вводится через пробелы
    ind – индекс текущего символа

    Функция solve обрабаьывает сложение и вычитание,
    фугкуция multiply – умножение и деление,
    а factor – порядок действий со скобками

    после результата работы сктода класса выводится вычисленное значени тестового вырадения(для проверки)


*/

public class StringCalc {
    public static void main(String[] args) {
        String exp = "5 * ( 12 - 23 / 4 * ( 3 + 6 ) + 3 / 4 ) + 10";
        StringCalc calc = new StringCalc(exp);
        System.out.println(exp + " = " + calc.solve());
        System.out.println("Calculated result: " + (5*(12-23.0/4*(3+6)+3.0/4)+10));
    }

    private String[] chars;
    private int ind;

    public StringCalc(String exp) {  // Конструктор класса
        this.chars = exp.split(" ");
        this.ind = 0;
    }


    private double solve() {
        double first = multiply();

        while (ind < chars.length) {
            String operator = chars[ind];
            if (!operator.equals("+") && !operator.equals("-")) { break; }
            else { ind++; }

            double second = multiply();
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }



    public double factor() {
        String next = chars[ind];
        double result;
        if (next.equals("(")) {
            ind++;
            result = solve();
            String lastBracket;
            if (ind < chars.length) {
                lastBracket = chars[ind];
            } else { lastBracket = "null"; }
            if (lastBracket.equals(")")) {
                ind++;
                return result;
            }
            System.out.println("Bad expression!");

        }
        ind++;
        return Double.parseDouble(next);
    }


    public double multiply() {
        double first = factor();

        while (ind < chars.length) {
            String operator = chars[ind];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                ind++;
            }

            double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }




}