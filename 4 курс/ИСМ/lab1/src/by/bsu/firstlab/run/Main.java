package by.bsu.firstlab.run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main extends JFrame {

    private static final int NUM_OF_RULES = 10;
    private static final String FILE_NAME = "input.txt";
    private static final String ERROR = "error";
    private static final String INFO = "info";

    private List<List<Pair>> knowledgeBase = new ArrayList<>();
    private List<Pair> answers = new ArrayList<>();
    private JTextArea jTextArea;
    private JPanel jPanel;
    private JPanel panelForButton;
    private JButton startButton;
    private JButton resetDataButton;

    private Main() throws IOException {
        super("First Lab");
        setSize(400, 400);
        jPanel = new JPanel();
        panelForButton = new JPanel();
        startButton = new JButton("Start Process Of Search");
        resetDataButton = new JButton("Reset All Data");
        panelForButton.add(startButton);
        panelForButton.add(resetDataButton);
        add(jPanel);
        add(panelForButton, BorderLayout.SOUTH);
        jTextArea = new JTextArea(20, 20);
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font("TimesRoman", Font.ITALIC, 16));
        jTextArea.setDisabledTextColor(new Color(12, 12, 12));
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane);
        jTextArea.setEnabled(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
                String[] mas = {"цена", "производитель", "год", "тип"};
                String criteria = (String) JOptionPane.showInputDialog(
                        jPanel,
                        "Enter what you are searching for:",
                        "Choose criteria",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, mas, mas[0]);
                if (criteria == null) {
                    return;
                }
                String result = findOrAsk(criteria);
                if (result.equals("no")) {
                    printLog("Rules not found!", ERROR);
                    JOptionPane.showMessageDialog(jPanel, "Rules not found!");
                } else {
                    JOptionPane.showMessageDialog(jPanel, criteria + " = " + result);
                }
            }
        });

        resetDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    knowledgeBase.clear();
                    answers.clear();
                    setDataToList();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setDataToList();

        for (List list : knowledgeBase) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
            System.out.println();
        }

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

    private String findOrAsk(String criteria) {
        boolean b = false, b2;
        for (int i = 0; i < NUM_OF_RULES; i++) {
            if (knowledgeBase.get(i).get(knowledgeBase.get(i).size() - 1).getKey().equals(criteria)) {
                b = true;
                b2 = false;
                for (int j = 0; j < knowledgeBase.get(i).size() - 1; j++) {
                    String new_criteria = knowledgeBase.get(i).get(j).getKey();
                    String result;
                    if (answers.stream().filter(answer -> answer.getKey().equals(new_criteria)).count() == 0) {//если ещё не подобрали данный критерий
                        result = findOrAsk(new_criteria);
                    } else {
                        result = getAnswer(new_criteria);
                    }
                    if (!knowledgeBase.get(i).get(j).getValue().equals(result)) {
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    String value = knowledgeBase.get(i).get(knowledgeBase.get(i).size() - 1).getValue();
                    answers.add(new Pair(criteria, value));
                    printLog(criteria + " = " + value, INFO);
                    return value;//проверили все "если" для правила и всё совпало
                }
            }
        }
        if (!b) {
            String value = showInputMessage("Введите \"" + criteria + "\":");
            if (value == null || value.equals("")) {
                System.exit(0);
            }
            answers.add(new Pair(criteria, value));
            printLog(criteria + " = " + value, INFO);
            return value;//нет такого "то" в правилах, то спросим у пользователя
        }
        return "no";//не нашли правил, удовлетворяющих заданным критериям
    }

    private String getAnswer(String criteria) {
        for (Pair answer : answers) {
            if (answer.getKey().equals(criteria)) {
                return answer.getValue();
            }
        }
        return "";
    }

    private void printLog(String message, String flag) {
        switch(flag){
            case INFO:
                jTextArea.setText(jTextArea.getText() + " " + INFO + ": " + message + "\n");
                break;
            case ERROR:
                jTextArea.setText(jTextArea.getText() + " " + ERROR + ": " +message + "\n");
                break;
        }
    }

    private String showInputMessage(String message) {
        return JOptionPane.showInputDialog(jPanel, message);
    }

    private void setDataToList() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FILE_NAME)));
        for (int i = 0; i < NUM_OF_RULES; i++) {
            String temp = "a";
            List<Pair> pairs = new ArrayList<>();
            while (bufferedReader.ready() && !temp.equals("")) {
                temp = bufferedReader.readLine();
                if (!temp.equals("")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(temp, "-");
                    pairs.add(new Pair(stringTokenizer.nextToken(), stringTokenizer.nextToken()));
                }
            }
            knowledgeBase.add(pairs);
        }
    }

}

