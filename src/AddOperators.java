import java.util.ArrayList;
import java.util.List;

public class AddOperators {

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        findOperator(result, num, target, new StringBuilder(), 0, 0, 0);

        return result;
    }

    public static void findOperator(List<String> result, String num, int target, StringBuilder currentCombo, int index, long eval, long lastOperand) {

        if (index == num.length()) {
            if (eval == target) {
                result.add(currentCombo.toString());
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {

            String currentOperandStr = num.substring(index, i + 1);
            long currentOperand = Long.parseLong(currentOperandStr);

            if (currentOperandStr.length() > 1 && currentOperandStr.charAt(0) == '0') {
                break;
            }

            if (index == 0) {
                int temp = currentCombo.length();
                currentCombo.append(currentOperandStr);
                findOperator(result, num, target, currentCombo, i + 1, currentOperand, currentOperand);
                currentCombo.setLength(temp);
            } else {
                int lengthBefore = currentCombo.length();

                currentCombo.append('+').append(currentOperand);
                findOperator(result, num, target, currentCombo, i + 1, eval + currentOperand, currentOperand);
                currentCombo.setLength(lengthBefore);

                currentCombo.append('-').append(currentOperand);
                findOperator(result, num, target, currentCombo, i + 1, eval - currentOperand, -currentOperand);
                currentCombo.setLength(lengthBefore);

                currentCombo.append('*').append(currentOperand);
                findOperator(result, num, target, currentCombo, i + 1, eval - lastOperand + (lastOperand * currentOperand), lastOperand * currentOperand);
                currentCombo.setLength(lengthBefore);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(addOperators("232", 8));
    }
}
