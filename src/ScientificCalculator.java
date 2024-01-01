import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Stack;
import javax.swing.*;


public class ScientificCalculator extends JFrame {
    private JTextField jTextField;
    private JPanel jPanel;
    private JButton[] jButtons;
    private double result;
    private String command = "";
    private boolean start;
    public class ExpressionEvaluator {

        public double evaluate(String expression) {
            try {
                String[] tokens = expression.split("\\s+");
                Stack<Double> stack = new Stack<>();

                for (String token : tokens) {
                    if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                        stack.push(Double.parseDouble(token));
                    } else if (isOperator(token)) {
                        double operand2 = stack.pop();
                        double operand1 = stack.pop();
                        double result = performOperation(operand1, operand2, token);
                        stack.push(result);
                    } else {
                        throw new IllegalArgumentException("无效的令牌: " + token);
                    }
                }

                if (stack.size() == 1) {
                    return stack.pop();
                } else {
                    throw new IllegalArgumentException("表达式无效");
                }
            } catch (Exception e) {
                throw new RuntimeException("表达式求值错误: " + e.getMessage());
            }
        }

        private boolean isOperator(String token) {
            return token.matches("[+\\-*/^]");
        }


        private double performOperation(double operand1, double operand2, String operator) {
            switch (operator) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1 - operand2;
                case "*":
                    return operand1 * operand2;
                case "/":
                    if (operand2 != 0) {
                        return operand1 / operand2;
                    } else {
                        throw new IllegalArgumentException("除数不能为零");
                    }
                case "^":
                    return Math.pow(operand1, operand2);
                default:
                    throw new IllegalArgumentException("无效的运算符: " + operator);
            }
        }
    }

    public ScientificCalculator() {
        super();

        this.setTitle("JokerAlgerの科学计算器");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);

        jPanel = new JPanel();

        // 添加菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("菜单");
        JMenuItem standardCalculatorItem = new JMenuItem("标准计算器");
        JMenuItem scientificCalculatorItem = new JMenuItem("科学计算器");

        // 添加菜单项的监听器
        standardCalculatorItem.addActionListener(e -> switchCalculatorMode("Standard"));
        scientificCalculatorItem.addActionListener(e -> switchCalculatorMode("Scientific"));

        viewMenu.add(standardCalculatorItem);
        viewMenu.add(scientificCalculatorItem);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);

        // 添加文本域
        jTextField = new JTextField(30);
        jTextField.setText("");
        jTextField.setEditable(true);
        this.add(jTextField, "North");

        // 添加按钮
        jPanel.setLayout(new GridLayout(6, 8, 3, 3));
        String names[] = {
                "+/-", "PI", "~", "1/X", "X^2", "e", "%", "C", "Back", "X^y",
                "X!", "√X", "3^√X", "&", "7", "8", "9", "+", "sin", "cos", "(", ")",
                "^", "4", "5", "6", "-", "tan", "cot", "<<", ">>", "|", "1", "2", "3", "*",
                "Hex", "DEC", "Oct", "Bin", "AND", ".", "0", "=",
                "/", "OR", "NOT", "XOR", "A", "B", "C", "D", "E", "F"
        };
        jButtons = new JButton[names.length];
        MyActionListener actionListener = new MyActionListener();

        for (int i = 0; i < names.length; i++) {
            jButtons[i] = new JButton(names[i]);
            jButtons[i].addActionListener(actionListener);
            jButtons[i].setBackground(Color.lightGray);
            if (names[i].equals("="))
                jButtons[i].setBackground(Color.RED);
            else if ((int) names[i].charAt(0) >= 48 && (int) names[i].charAt(0) <= 57
                    && names[i].length() == 1)
                jButtons[i].setBackground(Color.WHITE);
            else if (names[i].equals("Back"))
                jButtons[i].setBackground(Color.GRAY);

            jPanel.add(jButtons[i]);
        }

        this.add(jPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // ExpressionEvaluator 内部类用于处理表达式求值
//    private class ExpressionEvaluator {
//        public double evaluate(String expression) {
//            try {
//                ScriptEngineManager manager = new ScriptEngineManager();
//                ScriptEngine engine = manager.getEngineByName("JavaScript");
//                return ((Number) engine.eval(expression)).doubleValue();
//            } catch (Exception e) {
//                throw new RuntimeException("表达式求值错误");
//            }
//        }
//    }
    private void handleExpressionError() {
        jTextField.setText("表达式错误");
        JOptionPane.showMessageDialog(null, "表达式错误", "Error!", JOptionPane.ERROR_MESSAGE);
        command = "=";
        start = true;
    }

//    private static class ExpressionEvaluator {
//        public double evaluate(String expression) {
//            try {
//                // 使用ScriptEngineManager进行表达式求值
//                ScriptEngineManager manager = new ScriptEngineManager();
//                ScriptEngine engine = manager.getEngineByName("JavaScript");
//                Object result = engine.eval(expression);
//
//                if (result instanceof Number) {
//                    return ((Number) result).doubleValue();
//                } else {
//                    throw new IllegalArgumentException("表达式求值错误");
//                }
//            } catch (Exception e) {
//                throw new RuntimeException("表达式求值错误");
//            }
//        }
//    }

    // 用内部类实现事件监听器
    class MyActionListener implements ActionListener {
        private void handleDivideByZeroError() {
            jTextField.setText("对不起，除数不能为零");
            JOptionPane.showMessageDialog(null, "对不起，除数不能为零", "Error!", JOptionPane.ERROR_MESSAGE);
            command = "=";
            start = true;
        }
        private double evaluateExpression(String expression) {
            try {
                return new ExpressionEvaluator().evaluate(expression);
            } catch (Exception ex) {
                handleExpressionError();
                return 0;  // 或者其他默认值
            }
        }


        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();

            if (start) {
                if ((int) input.charAt(0) >= 48 && (int) input.charAt(0) <= 57 && input.length() == 1) {
                    jTextField.setText("" + input);
                }
                if (input.equals("+/-")) {
                    String currentText = jTextField.getText();
                    if (currentText.startsWith("-")) {
                        jTextField.setText(currentText.substring(1)); // 移除负号
                    } else {
                        jTextField.setText("-" + currentText); // 添加负号
                    }
                    jTextField.setText("-");
                }
                if (input.equals("PI")) {
                    jTextField.setText("" + Math.PI);
                }
                start = false;
                if (input.equals("C"))
                    jTextField.setText("");
            } else if (input.equals("=")) {
                String expression = jTextField.getText();
                try {
                    double result = evaluateExpression(expression);
                    jTextField.setText(getPrettyNumber(Double.toString(result)));
                } catch (Exception ex) {
                    handleExpressionError();
                }
                start = true;


            } else if ((int) input.charAt(0) >= 48 && (int) input.charAt(0) <= 57 && input.length() == 1 || input.equals(".")) {
                jTextField.setText(jTextField.getText() + input);
            } else if (input.equals("C")) {
                jTextField.setText("");
            } else if (input.equals("Back")) {
                if (jTextField.getText().length() > 0) {
                    jTextField.setText(jTextField.getText().substring(0, jTextField.getText().length() - 1));
                }
            } else if (input.equals("sin")) {
                result = Math.sin(Double.parseDouble(jTextField.getText()));
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } else if (input.equals("cos")) {
                result = Math.cos(Double.parseDouble(jTextField.getText()));
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } else if (input.equals("cot")) {
                result = 1.0 / Math.tan(Double.parseDouble(jTextField.getText()));
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } else if (input.equals("tan")) {
                result = Math.tan(Double.parseDouble(jTextField.getText()));
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } else if (input.equals("Bin")) {
                try {
                    int decimalNumber = Integer.parseInt(jTextField.getText());
                    String result2 = Integer.toBinaryString(decimalNumber);
                    jTextField.setText("" + getPrettyNumber(result2));
                    start = true;
                } catch (NumberFormatException ex) {
                    handleExpressionError();
                }
            } else if (input.equals("Oct")) {
                try {
                    int decimalNumber = Integer.parseInt(jTextField.getText());
                    String result8 = Integer.toOctalString(decimalNumber);
                    jTextField.setText("" + getPrettyNumber(result8));
                    start = true;
                } catch (NumberFormatException ex) {
                    handleExpressionError();
                }
            } else if (input.equals("Dec")) {
                try {
                    String result10 = Integer.valueOf(jTextField.getText(), 2).toString();
                    jTextField.setText("" + getPrettyNumber(result10));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "对不起，数字错误，请重新输入！", "Error!", JOptionPane.ERROR_MESSAGE);
                    handleExpressionError();
                } finally {
                    start = true;
                }
            }else if (input.equals("Hex")) {
                try {
                    // 尝试从jTextField中的文本解析整数值
                    int decimalNumber = Integer.parseInt(jTextField.getText());

                    // 将十进制数字转换为十六进制字符串表示，并将字母转换为大写
                    String result16 = Integer.toHexString(decimalNumber).toUpperCase();

                    // 使用名为getPrettyNumber的方法格式化十六进制结果
                    jTextField.setText("" + getPrettyNumber(result16));

                    // 将布尔变量'start'设置为true
                    start = true;
                } catch (NumberFormatException ex) {
                    // 处理jTextField中的文本不是有效整数的情况
                    handleExpressionError();
                }
            }
            else if (input.equals("1/X")) {
                try {
                    double divisor = Double.parseDouble(jTextField.getText());
                    if (divisor != 0) {
                        result = 1 / divisor;
                        jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                        start = true;
                    } else {
                        handleDivideByZeroError();
                    }
                } catch (NumberFormatException ex) {
                    handleExpressionError();
                }
            } else if (input.equals("X^2")) {
                result = Math.pow(Double.parseDouble(jTextField.getText()), 2);
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } else if (input.equals("X^3")) {
                result = Math.pow(Double.parseDouble(jTextField.getText()), 3);
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } else if (input.equals("X!")) {
                try {
                    int value = Integer.parseInt(jTextField.getText());
                    if (value < 0) {
                        handleExpressionError();
                        jTextField.setText("对不起，阶乘计算不能为负数");
                        start = true;
                        throw new IllegalArgumentException("阶乘计算出现负数");
                    } else {
                        int sum = 1;
                        for (int i = 1; i <= value; i++) {
                            sum *= i;
                        }
                        jTextField.setText(Integer.toString(sum));
                        start = true;
                    }
                } catch (IllegalArgumentException ex) {
                    handleExpressionError();
                }
            } else if (input.equals("%")) {
                result = Double.parseDouble(jTextField.getText()) / 100.0;
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            }else if (input.equals("(")) {
                // 在文本域中追加 "("
                jTextField.setText(jTextField.getText() + "(");
                start = false; // 或者保持 start 的状态，根据需要设置为 true 或 false
            }
            else if (input.equals(")")) {
                // 在文本域中追加 ")"
                jTextField.setText(jTextField.getText() + ")");
                start = false; // 或者保持 start 的状态，根据需要设置为 true 或 false
            }else if (input.equals("√X")) {
                try {
                    double value = Double.parseDouble(jTextField.getText());
                    if (value >= 0) {
                        result = Math.sqrt(value);
                        jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                        start = true;
                    } else {
                        handleExpressionError();
                        jTextField.setText("对不起，负数无法开平方");
                        start = true;
                        throw new IllegalArgumentException("负数无法开平方");
                    }
                } catch (IllegalArgumentException ex) {
                    handleExpressionError();
                }
            } else if (input.equals("3^√X")) {
                try {
                    double value = Double.parseDouble(jTextField.getText());
                    if (value >= 0) {
                        result = Math.pow(value, 1.0 / 3);
                        jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                        start = true;
                    } else {
                        handleExpressionError();
                        jTextField.setText("对不起，负数无法进行立方根运算");
                        start = true;
                        throw new IllegalArgumentException("负数无法进行立方根运算");
                    }
                } catch (IllegalArgumentException ex) {
                    handleExpressionError();
                }
            } else if (input.equals("e")) {
                jTextField.setText("" + Math.E);
                start = true;
            }else if (input.equals("<<")) {
                // 处理左移事件
                try {
                    int value = Integer.parseInt(jTextField.getText());
                    int intValueResult = (int) result;
                    int intValueResultLeftShift = value << 1;
                    result = (double) intValueResultLeftShift;
                    jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                    start = true;
                } catch (NumberFormatException ex) {
                    handleExpressionError();
                }

            } else if (input.equals(">>")) {
                // 处理右移事件
                try {
                    int value = Integer.parseInt(jTextField.getText());
                    int intValueResult = (int) result;
                    int intValueResultRightShift = intValueResult >> 1;
                    result = (double) intValueResultRightShift;
                    jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                    start = true;
                } catch (NumberFormatException ex) {
                    handleExpressionError();
                }

            }  else if (input.equals("|")) {
            // 处理按位或事件
            try {
                int value = Integer.parseInt(jTextField.getText());
                int intValueResult = (int) Math.round(result);  // 四舍五入到最接近的整数
                int intValueResultOrValue = intValueResult | value;
                result = (double) intValueResultOrValue;
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } catch (NumberFormatException ex) {
                handleExpressionError();
            }
        }
            else if (input.equals("AND")) {
            // 处理按位与事件
            try {
                if (!start) {
                    int value = Integer.parseInt(jTextField.getText());
                    int intValueResult = (int) Math.round(result);  // 四舍五入到最接近的整数
                    result = (double) (intValueResult & value);
//                    jTextField.setText(getPrettyNumber(Double.toString(result)));
                    start = true;
                }
            } catch (NumberFormatException ex) {
                handleExpressionError();
            }
        } else if (input.equals("OR")) {
            // 处理按位或事件
            try {
                int value = Integer.parseInt(jTextField.getText());
                int intValueResult = (int) Math.round(result);  // 四舍五入到最接近的整数
                int intValueResultOrValue = intValueResult | value;
                result = (double) intValueResultOrValue;
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } catch (NumberFormatException ex) {
                handleExpressionError();
            }
        } else if (input.equals("NOT")) {
            // 处理按位非事件
            try {
                int value = Integer.parseInt(jTextField.getText());
                int intValueResult = (int) Math.round(result);  // 四舍五入到最接近的整数
                int intValueResultComplement = ~value;
                result = (double) intValueResultComplement;
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } catch (NumberFormatException ex) {
                handleExpressionError();
            }
        } else if (input.equals("XOR")) {
            // 处理按位异或事件
            try {

                int value = Integer.parseInt(jTextField.getText());
                int intValueResult = (int) Math.round(result);
                int intValueResultXorValue = intValueResult ^ value;
                result = (double) intValueResultXorValue;
                jTextField.setText("" + getPrettyNumber(Double.toString(result)));
                start = true;
            } catch (NumberFormatException ex) {
                handleExpressionError();
            }


        } else if (input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D") || input.equals("E") || input.equals("F")) {
                // 处理十六进制数字按钮事件
                jTextField.setText(jTextField.getText() + input);
            }else {
//                if (!start) {
//                    if (command.equals("+"))
//                        result += Double.parseDouble(jTextField.getText());
//                    else if (command.equals("-"))
//                        result -= Double.parseDouble(jTextField.getText());
//                    else if (command.equals("*"))
//                        result *= Double.parseDouble(jTextField.getText());
//                    else if (command.equals("/")) {
//                        double divisor = Double.parseDouble(jTextField.getText());
//                        if (divisor != 0) {
//                            result /= divisor;
//                        } else {
//                            handleDivideByZeroError();
//                            return;
//                        }
//                    } else if (command.equals("="))
//                        result = Double.parseDouble(jTextField.getText());
//                    else if (command.equals("X^y"))
//                        result = Math.pow(result, Double.parseDouble(jTextField.getText()));
//
//                    jTextField.setText(getPrettyNumber(Double.toString(result)));
//                    command = input;
//                    start = true;
//                }
                if (!start) {
                    if (command.equals("+"))
                        result += Double.parseDouble(jTextField.getText());
                    else if (command.equals("-"))
                        result -= Double.parseDouble(jTextField.getText());
                    else if (command.equals("*"))
                        result *= Double.parseDouble(jTextField.getText());
                    else if (command.equals("/")) {
                        double divisor = Double.parseDouble(jTextField.getText());
                        if (divisor != 0) {
                            result /= divisor;
                        } else {
                            handleDivideByZeroError();
                            return;
                        }
                    } else if (command.equals("="))
                        result = Double.parseDouble(jTextField.getText());
                    else if (command.equals("X^y"))
                        result = Math.pow(result, Double.parseDouble(jTextField.getText()));

                    jTextField.setText(getPrettyNumber(Double.toString(result)));
                    command = input;
                    start = true;
                    result = Double.parseDouble(jTextField.getText()); // 添加这一行
                }
            }
        }
    }

    // 用于去掉小数点后没用的0
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number)).stripTrailingZeros().toPlainString();
    }

    // 用于切换计算器模式
    private void switchCalculatorMode(String mode) {
        dispose(); // 关闭当前窗口

        if (mode.equals("Standard")) {
            new MyCalculator(); // 打开标准计算器
        } else if (mode.equals("Scientific")) {
            new ScientificCalculator(); // 打开科学计算器
        }
    }

    private void handleDivideByZeroError() {
        jTextField.setText("对不起，除数不能为零");
        JOptionPane.showMessageDialog(null, "对不起，除数不能为零", "Error!", JOptionPane.ERROR_MESSAGE);
        command = "=";
        start = true;
    }


    public static void main(String[] args) {
        ScientificCalculator scientificCalculator = new ScientificCalculator();
    }
    // 其他代码...

    private void calculate(String expression) {
        String[] input = expression.split("(?<=[-+*/^()])|(?=[-+*/^()])");
        // 使用 StringBuilder 构建后缀表达式
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for (String token : input) {
            if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                postfix.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // 弹出左括号
            } else if (token.matches("[+\\-*/^]")) {
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(token)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        // 计算后缀表达式
        Stack<Double> resultStack = new Stack<>();
        String[] postfixTokens = postfix.toString().split(" ");
        for (String token : postfixTokens) {
            if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                resultStack.push(Double.parseDouble(token));
            } else if (token.matches("[+\\-*/^]")) {
                double operand2 = resultStack.pop();
                double operand1 = resultStack.pop();
                double result = performOperation(operand1, operand2, token);
                resultStack.push(result);
            }
        }

        if (!resultStack.isEmpty()) {
            double finalResult = resultStack.pop();
            jTextField.setText(getPrettyNumber(Double.toString(finalResult)));
            start = true;
        } else {
            handleExpressionError();
        }
    }

    private int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    handleDivideByZeroError();
                    return 0; // 处理除以零
                }
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("无效的运算符: " + operator);
        }
    }


}

