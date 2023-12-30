//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class calculator extends Frame implements ActionListener, WindowListener
//{
//    private Container container;
//    private GridBagLayout layout;
//    private GridBagConstraints constraints;
//    private JTextField displayField;         //计算结果显示区
//    private String lastCommand;           //保存+,-,*,/,=命令0
//    private double result;               //保存计算结果
//    private boolean start;           //判断是否为数字的开始
//    private JMenuBar menubar;
//    private JMenuItem m_exit;
//    private JMenuItem m2_ejz;
//    private JMenuItem m2_bjz;
//    private Dialog dialog;
//    private Label label_dialog;
//    private JButton button_sqrt;
//    private JButton button_plusminus;
//    private JButton button_CE;
//    private JButton button_cancel;
//    private JButton button_1;
//    private JButton button_2;
//    private JButton button_3;
//    private JButton button_4;
//    private JButton button_5;
//    private JButton button_6;
//    private JButton button_7;
//    private JButton button_8;
//    private JButton button_9;
//    private JButton button_0;
//    private JButton button_plus;
//    private JButton button_minus;
//    private JButton button_multiply;
//    private JButton button_divide;
//    private JButton button_point;
//    private JButton button_equal;
//    private JButton button_log;
//    private JButton button_tan;
//    private JButton button_cos;
//    private JButton button_sin;
//    private JButton button_exp;
//
//    public calculator()       //构造方法设置布局、为按钮注册事件监听器
//    {
//        super( "My Calculator" );
//        this.setLocation( 350,150 );
//        this.setSize( 450,400 );
//        this.setResizable( true );
//        this.setLayout( new GridLayout( 7,1 ) );
//        this.addmyMenu();                   //调用成员方法添加菜单
//        displayField = new JTextField( 30 );
//        this.add( displayField );
//        displayField.setEditable( true );
//
//        start = true;
//        result = 0;
//        lastCommand = "=";
//
//        JPanel panel0 = new JPanel();
//        panel0.setLayout( new GridLayout( 1,4,4,4 ) );
//
//
//        JPanel panel1 = new JPanel();
//        panel1.setLayout( new GridLayout( 1,5,4,4 ) );
//        this.add( panel1 );
//        button_sqrt = new JButton( "sqrt" );//根号
//        button_plusminus = new JButton( "+/-" );
//        button_exp = new JButton( "exp" );//底数e的n次幂
//        button_CE = new JButton( "退位");
//        button_cancel = new JButton( "c" );//清除
//
//        JPanel panel2 = new  JPanel();
//        panel2.setLayout( new GridLayout( 1,5,4,4 ) );
//        this.add( panel2 );
//        button_7 = new JButton( "7" );
//        button_8 = new JButton( "8" );
//        button_9 = new JButton( "9" );
//        button_log = new JButton( "log" );//对数
//        button_divide = new JButton( "/" );//除
//
//        JPanel panel3 = new JPanel();
//        panel3.setLayout( new GridLayout(1,5,4,4) );
//        this.add( panel3 );
//        button_4 = new JButton( "4" );
//        button_5 = new JButton( "5" );
//        button_6 = new JButton( "6" );
//        button_tan = new JButton( "tan" );//正切
//        button_multiply = new JButton( "*" );//乘法
//
//        JPanel panel4=new  JPanel();
//        panel4.setLayout( new GridLayout( 1,5,4,4 ) );
//        this.add(panel4);
//        button_1 = new JButton( "1" );
//        button_2 = new JButton( "2" );
//        button_3 = new JButton( "3" );
//        button_cos = new JButton( "cos");//余弦
//        button_minus = new JButton( "-" );
//
//        JPanel panel5 = new  JPanel();
//        panel5.setLayout( new GridLayout( 1,5,4,4 ) );
//        this.add( panel5 );
//        button_0 = new JButton( "0" );
//        button_point=new JButton( "." );
//        button_equal = new JButton( "=" );
//        button_sin = new JButton( "sin" );//正弦
//        button_plus = new JButton( "+" );
//
//        panel1.add( button_sqrt );
//        panel1.add( button_plusminus );
//        panel1.add( button_exp );
//        panel1.add( button_CE );
//        panel1.add( button_cancel );
//        panel2.add( button_7 );
//        panel2.add( button_8 );
//        panel2.add( button_9 );
//        panel2.add( button_log );
//        panel2.add( button_divide );
//        panel3.add( button_4 );
//        panel3.add( button_5 );
//        panel3.add( button_6 );
//        panel3.add( button_tan );
//        panel3.add( button_multiply );
//        panel4.add( button_1 );
//        panel4.add( button_2 );
//        panel4.add( button_3 );
//        panel4.add( button_cos );
//        panel4.add( button_minus );
//        panel5.add( button_0 );
//        panel5.add( button_point );
//        panel5.add( button_equal );
//        panel5.add( button_sin );
//        panel5.add( button_plus) ;
//
//        button_sqrt.addActionListener( this );
//        button_plusminus.addActionListener( this );
//        button_exp.addActionListener( this );
//        button_CE.addActionListener( this );
//        button_cancel.addActionListener( this );
//        button_7.addActionListener( this );
//        button_8.addActionListener( this );
//        button_9.addActionListener( this );
//        button_log.addActionListener( this );
//        button_divide.addActionListener( this );
//        button_4.addActionListener( this );
//        button_5.addActionListener( this );
//        button_6.addActionListener( this );
//        button_tan.addActionListener( this );
//        button_multiply.addActionListener( this );
//        button_1.addActionListener( this );
//        button_2.addActionListener( this );
//        button_3.addActionListener( this );
//        button_cos.addActionListener( this );
//        button_minus.addActionListener( this );
//        button_0.addActionListener( this );
//        button_point.addActionListener( this );
//        button_equal.addActionListener( this );
//        button_sin.addActionListener( this );
//        button_plus.addActionListener( this );
//
//        this.addWindowListener( new WinClose() );      //注册窗口监听器
//        this.setVisible( true );
//    }
//
//    private void addmyMenu()        //菜单的添加
//    {
//        JMenuBar menubar = new JMenuBar();
//        this.add( menubar );
//        JMenu m1 = new JMenu( "选项" );
//        JMenu m2 = new JMenu( "进制转换" );
//
//        JMenuItem m1_exit = new JMenuItem( "退出" );
//        m1_exit.addActionListener( this );
//        JMenuItem m2_ejz = new JMenuItem( "二进制" );
//        m2_ejz.addActionListener( this );
//        JMenuItem m2_bjz = new JMenuItem("八进制");
//        m2_bjz.addActionListener( this );
//        JMenuItem m2_sljz = new JMenuItem("十六进制");
//        m2_sljz.addActionListener( this );
//
//        JMenu m3 = new JMenu( "帮助" );
//        JMenuItem m3_Help = new JMenuItem( "用法" );
//        m3_Help.addActionListener( this );
//
//        dialog = new Dialog( this, "提示" , true );     //模式窗口
//        dialog.setSize( 240,80 );
//        label_dialog = new Label("", Label.CENTER );   //标签的字符串为空，居中对齐
//        dialog.add( label_dialog );
//        dialog.addWindowListener( this );          //为对话框注册窗口事件监听器
//
//        m1.add( m1_exit );
//        menubar.add( m1 );
//        m2.add( m2_ejz );
//        m2.add( m2_bjz );
//        m2.add( m2_sljz );
//        menubar.add( m2 );
//        m3.add( m3_Help );
//        menubar.add( m3 );
//    }
//
//    public void actionPerformed(ActionEvent e)       //按钮的单击事件处理方法
//    {
//        if(
//                e.getSource().equals( button_1 )||e.getSource().equals( button_2 )||
//                        e.getSource().equals( button_3 )||e.getSource().equals( button_4 )||
//                        e.getSource().equals( button_5 )|| e.getSource().equals( button_6 )||
//                        e.getSource().equals( button_7 )|| e.getSource().equals( button_8 )||
//                        e.getSource().equals( button_9 ) ||e.getSource().equals( button_0 )||
//                        e.getSource().equals( button_point )||e.getSource().equals( button_plusminus )||
//                        e.getSource().equals( button_cancel )||e.getSource().equals( button_CE )
//        )
//        {      //非运算符的处理方法
//            String input = e.getActionCommand();
//
//            if ( start )
//            {
//                displayField.setText("");
//                start = false;
//                if( input.equals( "+/-" ) )
//                    displayField.setText( displayField.getText()+ "-" );
//            }
//            if( !input.equals( "+/-" ) )
//            {
//                String str = displayField.getText();
//                if( input.equals( "退格" ) )          //退格键的实现方法
//                {
//                    if( str.length() > 0 )
//                        displayField.setText( str.substring( 0,str.length() - 1 ) );
//                }
//                else if( input.equals( "C" ) )         //清零键的实现方法
//                {
//                    displayField.setText( "0" );
//                    start = true;
//                }
//                else
//                    displayField.setText( displayField.getText() + input );
//            }
//        }
//        else if ( e.getActionCommand() == "二进制" )   //二进制的转换
//        {
//            int n = Integer.parseInt( displayField.getText() );
//            displayField.setText( Integer.toBinaryString( n ) );
//        }
//        else if ( e.getActionCommand() == "八进制" )    //八进制的转换
//        {
//            int n = Integer.parseInt( displayField.getText() );
//            displayField.setText( Integer.toOctalString( n ) );
//        }
//        else if ( e.getActionCommand() == "十六进制" )    //十六进制的转换
//        {
//            int n = Integer.parseInt( displayField.getText() );
//            displayField.setText( Integer.toHexString( n ) );
//        }
//
//        else if ( e.getActionCommand() == "退出" )      //选项中退出的处理方法
//        {
//            System.exit( 0 );
//        }
//        else if ( e.getActionCommand() == "用法" )      //按下'帮助'菜单栏中用法的处理方法
//        {
//            label_dialog.setText( "sqrt,exp等键是先输运算符再输数字\n" );
//            dialog.setLocation( 400,250 );
//            dialog.setVisible( true );
//        }
//        else        //各运算符的识别
//        {
//            String command = e.getActionCommand();
//            if( start )
//            {
//                lastCommand = command;
//            }
//            else
//            {
//                calculate( Double.parseDouble( displayField.getText() ) );
//                lastCommand = command;
//                start = true;
//            }
//        }
//    }
//
//    public void calculate( double x )          //各运算符的具体运算方法
//    {
//        double d = 0;
//        if ( lastCommand.equals( "+" ) )
//            result += x;
//        else if (lastCommand.equals( "-" ) )
//            result -= x;
//        else if ( lastCommand.equals( "*" ) )
//            result *= x;
//        else if ( lastCommand.equals( "/" ) )
//            result /= x;
//        else if ( lastCommand.equals( "=" ) )
//            result = x;
//        else if ( lastCommand.equals( "sqrt" ) )
//        {
//            d = Math.sqrt( x );
//            result = d;
//        }
//        else if ( lastCommand.equals( "exp" ) )
//        {
//            d = Math.exp( x );
//            result = d;
//        }
//        else if ( lastCommand.equals( "log" ) )
//        {
//            d = Math.log( x );
//            result = d;
//        }
//        else if ( lastCommand.equals( "tan" ) )
//        {
//            d = Math.tan(x);
//            result = d;
//        }
//        else if ( lastCommand.equals( "cos" ) )
//        {
//            d = Math.cos( x );
//            result = d;
//        }
//        else if ( lastCommand.equals( "sin" ) )
//        {
//            d = Math.sin( x );
//            result = d;
//        }
//        displayField.setText( ""+ result );
//    }
//
//    public void windowClosing( WindowEvent e )
//    {
//        if( e.getSource() == dialog )
//            dialog.setVisible( false );           //隐藏对话框
//        else
//            System.exit( 0 );
//    }
//
//    public void windowOpened( WindowEvent e )         {  }
//    public void windowActivated( WindowEvent e )      {  }
//    public void windowDeactivated( WindowEvent e )    {  }
//    public void windowClosed( WindowEvent e )         {  }
//    public void windowIconified( WindowEvent e )      {  }
//    public void windowDeiconified( WindowEvent e )    {  }
//
//    public static void main( String args[] )
//    {
//        calculator calculator = new calculator();
//    }
//}
//
//class WinClose implements WindowListener
//{
//    public void windowClosing( WindowEvent e )    //单击窗口关闭按钮时触发并执行实现窗口监听器接口中的方法
//    {
//        System.exit( 0 );          //结束程序运行
//    }
//    public void windowOpened( WindowEvent e ){ }
//    public void windowActivated( WindowEvent e ){}
//    public void windowDeactivated( WindowEvent e){ }
//    public void windowClosed( WindowEvent e ){ }
//    public void windowIconified( WindowEvent e ){ }
//    public void windowDeiconified( WindowEvent e ){ }
//}




import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.*;


public class MyCalculator extends JFrame{
    //用于判断是否重新开始
    private boolean start = true;
    private double result = 0;
    //存放加减乘除等于等
    private String command = "=";
    private JTextField jTextField;
    private JPanel jPanel = new JPanel();
    private JButton[] jButtons;

    //用构造方法进行必要的设置
    public MyCalculator() {
        super();

        this.setTitle("JokerAlgerの标准计算器");
        this.setSize(900, 450);
        this.setLocationRelativeTo(null);

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

        //添加文本域
        jTextField = new JTextField(30);
        jTextField.setText("");
        jTextField.setEditable(true);
        this.add(jTextField,"North");

        //添加按钮
        jPanel.setLayout(new GridLayout(5,7,3,3));
        String names[] = {
                "+/-","PI","1/X","C","/","*","Back","X^2","X^3",
                "X^y","7","8","9","-","X!","√X","3^√X","4","5",
                "6","+","sin","cos","tan","1","2","3","%",
                "Bin","Dec","cot","e","0",".","="
        };
        jButtons = new JButton[names.length];
        MyActionListener actionListener= new MyActionListener();

        //利用循环创建按钮对象并添加事件监听器
        for(int i = 0; i < names.length; i++) {

            jButtons[i] = new JButton(names[i]);
            jButtons[i].addActionListener(actionListener);

            //设置按钮背景颜色
            jButtons[i].setBackground(Color.lightGray);
            if(names[i].equals("="))
                jButtons[i].setBackground(Color.RED);
            else if((int)names[i].charAt(0)>=48 && (int)names[i].charAt(0)<=57
                    && names[i].length() == 1)
                jButtons[i].setBackground(Color.WHITE);
            else if(names[i].equals("Back"))
                jButtons[i].setBackground(Color.GRAY);

            jPanel.add(jButtons[i]);
        }

        this.add(jPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    //用内部类实现事件监听器
    class MyActionListener implements ActionListener{
        //按钮被单击
        public void actionPerformed(ActionEvent e) {

            String input = e.getActionCommand();
            //开始
            if(start) {
                if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
                        && input.length() == 1 ) {
                    jTextField.setText(""+input);
                }
                if(input.equals("+/-")) {
                    jTextField.setText("-");
                }
                if(input.equals("PI")) {
                    jTextField.setText(""+Math.PI);
                }
                start = false;
                if(input.equals("C"))
                    jTextField.setText("");
            }
            //0~9数字等非运算符
            else if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
                    && input.length() == 1 || input.equals(".")){
                jTextField.setText(jTextField.getText()+input);
            }
            //实现所有 数组+运算符 的运算

            //实现清零键
            else if(input.equals("C"))
                jTextField.setText("");
                //实现退格键
            else if(input.equals("Back")) {
                if(jTextField.getText().length() > 0){
                    jTextField.setText(jTextField.getText().substring(0,jTextField.getText().length()-1));
                }
            }
            //实现正弦三角函数
            else if(input.equals("sin")) {
                result = Math.sin(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现余弦三角函数
            else if(input.equals("cos")) {
                result = Math.cos(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现余切三角函数
            else if(input.equals("cot")) {
                result = 1.0/Math.tan(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现正切三角函数
            else if(input.equals("tan")) {
                result = Math.tan(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            //实现十进制到二进制的转化
            else if(input.equals("Bin")) {
                String result2 = Integer.toBinaryString(Integer.parseInt(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(result2));
                start = true;
            }
            //实现二进制到十进制的转化
            else if(input.equals("Dec")) {
                try {
                    String result2 = Integer.valueOf(jTextField.getText(),2).toString();
                    jTextField.setText(""+getPrettyNumber(result2));
                }catch(NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "对不起，数字错误，请重新输入！", "Error!", JOptionPane.ERROR_MESSAGE);
                    throw new NumberFormatException("数字格式错误");
                }finally {
                    start = true;
                }

            }
            //实现1/x
            else if(input.equals("1/X")) {
                result = 1 / Double.parseDouble(jTextField.getText());
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现平方计算
            else if(input.equals("X^2")) {
                result = Math.pow(Double.parseDouble(jTextField.getText()), 2);
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现立方计算
            else if(input.equals("X^3")) {
                result = Math.pow(Double.parseDouble(jTextField.getText()), 3);
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现阶乘
            else if(input.equals("X!")) {
                if(Double.parseDouble(jTextField.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "对不起，阶乘计算不能为负数", "Error!", JOptionPane.ERROR_MESSAGE);
                    jTextField.setText("对不起，阶乘计算不能为负数");
                    start = true;
                    throw new IllegalArgumentException("阶乘计算出现负数");
                }else {
                    int sum;
                    sum = factorial(Integer.parseInt(jTextField.getText()));
                    jTextField.setText(Integer.toString(sum));
                    start = true;
                }

            }
            //实现百分号计算
            else if(input.equals("%")) {
                result = Double.parseDouble(jTextField.getText())/ 100.0;
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现开平方根
            else if(input.equals("√X")) {
                result = Math.sqrt(Double.parseDouble(jTextField.getText()));
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现开立方根
            else if(input.equals("3^√X")) {
                result = Math.pow(Double.parseDouble(jTextField.getText()),1.0/3);
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现自然常数e
            else if (input.equals("e")) {
                String resultE = String.format("%f", Math.E);
                jTextField.setText(resultE);
                start = true;
            }


            //实现加减乘除等 数字+运算符+数字 形式的运算
            else {
                if(!start) {

                    if(command.equals("+"))
                        result += Double.parseDouble(jTextField.getText());
                    else if(command.equals("-"))
                        result -= Double.parseDouble(jTextField.getText());
                    else if(command.equals("*"))
                        result *= Double.parseDouble(jTextField.getText());
                    else if(command.equals("/")) {
                        if(Double.parseDouble(jTextField.getText()) != 0) {
                            result /= Double.parseDouble(jTextField.getText());
                        }else {
                            jTextField.setText(""+"对不起，除数不能为零");
                            JOptionPane.showMessageDialog(null, "对不起，除数不能为零", "Error!", JOptionPane.ERROR_MESSAGE);
                            command = "=";
                            start = true;
                            throw new ArithmeticException("除数为零");
                        }

                    }
                    else if(command.equals("="))
                        result = Double.parseDouble(jTextField.getText());

                    else if(command.equals("X^y"))
                        result = Math.pow(result, Double.parseDouble(jTextField.getText()));
                    jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                    command = input;
                    start = true;
                }
            }
        }
    }
    //去掉小数点后没用的0
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }
    // 用于切换计算器模式
    private void switchCalculatorMode(String mode) {
        dispose(); // 关闭当前窗口

        if (mode.equals("Standard")) {
            new MyCalculator(); // 打开标准计算器
        } else if (mode.equals("Scientific"))
            new ScientificCalculator(); // 打开科学计算器

    }
    //用循环计算阶乘
    public static int factorial(int num) {
        int sum = 1;
        for(int i = 1;i <= num; i++){
            sum *= i;
        }
        return sum;
    }

    public static void main(String[] args) {
        MyCalculator myCalculator = new MyCalculator();

    }

}



























