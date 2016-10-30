package Var7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class MainFrame extends JFrame {

    // Размеры окна приложения в виде констант
    private static final int WIDTH = 700;
    private static final int HEIGHT = 325;

    // Текстовые поля для считывания значений переменных
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    //Текстовые поля для переменных памяти
    private JTextField textFieldM1;
    private JTextField textFieldM2;
    private JTextField textFieldM3;
    Double mem1 = 0.;
    Double mem2 = 0.;
    Double mem3 = 0.;

    // Текстовое поле для отображения результата
    private JTextField textFieldResult;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioButtons2 = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    //Контейнер для отображения радио-кнопок Memory
    private Box hboxMemoryType = Box.createHorizontalBox();
    private int memoryId = 1;

    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return (Math.pow(Math.log(Math.pow(1+z,2))+ Math.cos(Math.PI * Math.pow(y,3)),1/4))/(
                Math.pow(Math.cos(Math.pow(Math.E,x))+Math.sqrt(1/x)+ Math.pow(Math.E,Math.pow(x,2)),Math.sin(x)));

    }

    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return (Math.pow(1+Math.pow(x,2),1/y))/(Math.pow(Math.E,Math.sin(z)+x));

    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                Var7.MainFrame.this.formulaId = formulaId;

            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addRadioButton2 (String buttonName, final int memoryId) {
        JRadioButton button2 = new JRadioButton(buttonName);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;

            }
        });
        radioButtons2.add(button2);
        hboxMemoryType.add(button2);
    }

    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);

        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
      //  hboxFormulaType.setBorder(
        //        BorderFactory.createLineBorder(Color.GREEN));

        //Memory
        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioButton2("M1", 1);
        addRadioButton2("M2", 2);
        addRadioButton2("M3", 3);

        radioButtons2.setSelected(
                radioButtons2.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
      //  hboxMemoryType.setBorder(
         //       BorderFactory.createLineBorder(Color.GRAY));

        // Создать область с полями ввода для M1 и M2,M3
        JLabel labelForM1 = new JLabel("M1:");
        textFieldM1 = new JTextField("0", 15);
        textFieldM1.setMaximumSize(textFieldM1.getPreferredSize());

        JLabel labelForM2 = new JLabel("M2:");
        textFieldM2 = new JTextField("0", 15);
        textFieldM2.setMaximumSize(textFieldM2.getPreferredSize());

        JLabel labelForM3 = new JLabel("M3:");
        textFieldM3 = new JTextField("0", 15);
        textFieldM3.setMaximumSize(textFieldM3.getPreferredSize());

        Box hboxVariablesM = Box.createHorizontalBox();
      //  hboxVariablesM.setBorder( BorderFactory.createLineBorder(Color.BLUE));

        hboxVariablesM.add(Box.createHorizontalGlue());

        hboxVariablesM.add(labelForM1);
        hboxVariablesM.add(Box.createHorizontalStrut(10));
        hboxVariablesM.add(textFieldM1);

        hboxVariablesM.add(Box.createHorizontalStrut(10));

        hboxVariablesM.add(labelForM2);
        hboxVariablesM.add(Box.createHorizontalStrut(10));
        hboxVariablesM.add(textFieldM2);

        hboxVariablesM.add(Box.createHorizontalStrut(10));

        hboxVariablesM.add(labelForM3);
        hboxVariablesM.add(Box.createHorizontalStrut(10));
        hboxVariablesM.add(textFieldM3);

        hboxVariablesM.add(Box.createHorizontalGlue());

        // Создать область для кнопок
        JButton buttonMemory = new JButton("M+");
        buttonMemory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                try {
                    Double result = Double.parseDouble(textFieldResult.getText());

                    if (memoryId==1) {
                        mem1 += result;
                        textFieldM1.setText(mem1.toString());
                    }
                    if (memoryId==2) {
                        mem2 += result;
                        textFieldM2.setText(mem2.toString());
                    }
                    if (memoryId==3) {
                        mem3 += result;
                        textFieldM3.setText(mem3.toString());
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Var7.MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonOff = new JButton("MC");
        buttonOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId==1){
                    textFieldM1.setText("0");
                    mem1=0.;
                }
                if (memoryId==2) {
                    textFieldM2.setText("0");
                    mem2=0.;
                }
                if (memoryId==3) {
                    textFieldM3.setText("0");
                    mem3=0.;
                }
            }
        });
        Box hboxButtons2 = Box.createHorizontalBox();
        hboxButtons2.add(Box.createHorizontalGlue());
        hboxButtons2.add(buttonMemory);
        hboxButtons2.add(Box.createHorizontalStrut(30));
        hboxButtons2.add(buttonOff);
        hboxButtons2.add(Box.createHorizontalGlue());
      //  hboxButtons2.setBorder( BorderFactory.createLineBorder(Color.GREEN));

        // Создать область с полями ввода для X и Y,Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
       // hboxVariables.setBorder( BorderFactory.createLineBorder(Color.GRAY));

        hboxVariables.add(Box.createHorizontalGlue());

        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);

        hboxVariables.add(Box.createHorizontalStrut(10));

        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);

        hboxVariables.add(Box.createHorizontalStrut(10));

        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);

        hboxVariables.add(Box.createHorizontalGlue());

        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        //labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();

        hboxResult.add(Box.createHorizontalGlue());

        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);

        hboxResult.add(Box.createHorizontalGlue());

      //  hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Var7.MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldM1.setText("0");
                textFieldM2.setText("0");
                textFieldM3.setText("0");
                textFieldResult.setText("0");
                mem1 = 0.;
                mem2= 0.;
                mem3 = 0.;
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
      //  hboxButtons.setBorder( BorderFactory.createLineBorder(Color.GREEN));

        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(Box.createVerticalStrut(10));
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxVariablesM);
        contentBox.add(hboxButtons2);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}
