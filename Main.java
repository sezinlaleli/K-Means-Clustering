//sezin laleli 191101040

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
;

public class Main extends JFrame implements ActionListener {
    JPanel bottom;
    JButton clus;
    JComboBox kCount;
    Main file;
    JButton fileB;
    static String fileName;
    static int iterCount;
    static int kNumber;
    static ArrayList<Integer> csvX = new ArrayList<>();
    static ArrayList<Integer> csvY = new ArrayList<>();
    static ArrayList<Integer> csvColor = new ArrayList<>();
    static ArrayList<Integer> centerX = new ArrayList<>();
    static ArrayList<Integer> centerY = new ArrayList<>();
    static ArrayList<Integer> centerXmin = new ArrayList<>();
    static ArrayList<Integer> centerYmin = new ArrayList<>();
    static ArrayList<Integer> centerXmax = new ArrayList<>();
    static ArrayList<Integer> centerYmax = new ArrayList<>();

    public static void main(String[] args) {
        Main k = new Main("K-Means Clustering");
        k.setVisible(true);
    }


    public Main() {
    }

    public Main(String name) {
        super(name);
        //setLocation(300, 100);
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        bottom = new JPanel();

        //it only works when hitting enter
        JLabel label1 = new JLabel("İterasyon: ");
        bottom.add(label1);

        JTextField iter = new JTextField(8);
        bottom.add(iter);

        iter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iterCount = Integer.parseInt(iter.getText());
            }
        });

        JLabel label0 = new JLabel("K sayısı:(Center) ");
        bottom.add(label0);

        String[] kC = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        kCount = new JComboBox(kC);
        bottom.add(kCount);

        kCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kNumber = Integer.parseInt((String) kCount.getSelectedItem());
            }
        });

        Path path = Paths.get("/Main.java");
        //System.out.println(path.toFile().getParent());
        fileB = new JButton("Dosyayı seç");

        file = new Main();

        fileB.addActionListener(file);

        clus = new JButton("K-Means Clustering");
        bottom.add(fileB);
        clus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        bottom.add(clus);

        add(bottom, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent evt) {
        String com = evt.getActionCommand();
        Path path = Paths.get("/Main.java");
        String p = path.toFile().getParent();
        if (com.equals("K-Means Clustering")) {
            JFileChooser j = new JFileChooser(p);
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                fileName = j.getSelectedFile().getAbsolutePath();
            } else {
                fileName = "";
            }
        } else {
            JFileChooser j = new JFileChooser(p);

            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                fileName = j.getSelectedFile().getAbsolutePath();
            }
            // if the user cancelled the operation
            else
                fileName = "";

        }

        if (!(fileName.substring(fileName.length() - 3)).equals("csv")) {
            JOptionPane.showMessageDialog(bottom, "File must be a csv file.");
        } else {
            Scanner sc = null;
            try {
                sc = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                System.exit(0);
            }
            while (sc.hasNextLine()) {
                readcsv(sc.nextLine());
            }
        }


    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Random r = new Random();
        for (int i = 0; i < kNumber; i++) {
            centerX.add(r.nextInt(1280));
            centerY.add(r.nextInt(720));
        }
        for (int j = 0; j < csvX.size(); j++) {
            int closest = 2156800;
            int whichCenter = 0;
            for (int i = 0; i < kNumber; i++) {
                if ((csvX.get(j) - centerX.get(i)) * (csvX.get(j) - centerX.get(i)) + (csvY.get(j) - centerY.get(i)) * (csvY.get(j) - centerY.get(i)) < closest) {
                    closest = (csvX.get(j) - centerX.get(i)) * (csvX.get(j) - centerX.get(i)) + (csvY.get(j) - centerY.get(i)) * (csvY.get(j) - centerY.get(i));
                    whichCenter = i;
                }
            }
            csvColor.add(whichCenter);
            switch (whichCenter) {
                case 0:
                    g2d.setColor(Color.black);
                    break;
                case 1:
                    g2d.setColor(Color.red);
                    break;
                case 2:
                    g2d.setColor(Color.green);
                    break;
                case 3:
                    g2d.setColor(Color.magenta);
                    break;
                case 4:
                    g2d.setColor(Color.orange);
                    break;
                case 5:
                    g2d.setColor(Color.pink);
                    break;
                case 6:
                    g2d.setColor(Color.gray);
                    break;
                case 7:
                    g2d.setColor(Color.yellow);
                    break;
                case 8:
                    g2d.setColor(Color.blue);
                    break;
                case 9:
                    g2d.setColor(Color.lightGray);
                    break;
                default:
                    break;
            }

        }

        for (int i = 0; i < kNumber; i++) {
            //g2d.setColor(Color.cyan);
            //g2d.fillOval(centerX.get(i), centerY.get(i), 10, 10);
        }

        for (int i = 0; i < kNumber; i++) {
            centerXmin.add(1280);
            centerXmax.add(0);
            centerYmin.add(720);
            centerYmax.add(0);
        }

        //System.out.println("iter: " + iterCount);

        for (int k = 1; k < iterCount; k++) {

            for (int i = 0; i < csvX.size(); i++) {
                int clr = csvColor.get(i);

                if (csvX.get(i) < centerXmin.get(clr)) {
                    centerXmin.set(clr, csvX.get(i));
                }
                if (csvX.get(i) > centerXmax.get(clr)) {
                    centerXmax.set(clr, csvX.get(i));
                }
                if (csvY.get(i) < centerYmin.get(clr)) {
                    centerYmin.set(clr, csvY.get(i));
                }
                if (csvY.get(i) > centerYmax.get(clr)) {
                    centerYmax.set(clr, csvY.get(i));
                }
            }

            for (int i = 0; i < kNumber; i++) {
                centerX.set(i, ((centerXmax.get(i) - centerXmin.get(i)) / 2) + centerXmin.get(i));
                centerY.set(i, ((centerYmax.get(i) - centerYmin.get(i)) / 2) + centerYmin.get(i));
                g2d.setColor(Color.cyan);
                g2d.fillOval(centerX.get(i), centerY.get(i), 10, 10);

            }
            for (int j = 0; j < csvX.size(); j++) {
                int closest = 2156800;
                int whichCenter = 0;
                for (int i = 0; i < kNumber; i++) {
                    if ((csvX.get(j) - centerX.get(i)) * (csvX.get(j) - centerX.get(i)) + (csvY.get(j) - centerY.get(i)) * (csvY.get(j) - centerY.get(i)) < closest) {
                        closest = (csvX.get(j) - centerX.get(i)) * (csvX.get(j) - centerX.get(i)) + (csvY.get(j) - centerY.get(i)) * (csvY.get(j) - centerY.get(i));
                        whichCenter = i;
                    }
                }
                csvColor.add(whichCenter);
                switch (whichCenter) {
                    case 0:
                        g2d.setColor(Color.black);
                        break;
                    case 1:
                        g2d.setColor(Color.red);
                        break;
                    case 2:
                        g2d.setColor(Color.green);
                        break;
                    case 3:
                        g2d.setColor(Color.magenta);
                        break;
                    case 4:
                        g2d.setColor(Color.orange);
                        break;
                    case 5:
                        g2d.setColor(Color.pink);
                        break;
                    case 6:
                        g2d.setColor(Color.gray);
                        break;
                    case 7:
                        g2d.setColor(Color.yellow);
                        break;
                    case 8:
                        g2d.setColor(Color.blue);
                        break;
                    case 9:
                        g2d.setColor(Color.lightGray);
                        break;
                    default:
                        break;
                }
                g2d.fillOval(csvX.get(j), csvY.get(j), 5, 5);
            }
        }


    }

    public void readcsv(String s) {
        int comma = s.indexOf(",");
        csvX.add(Integer.parseInt(s.substring(0, comma)));
        csvY.add(Integer.parseInt(s.substring(comma + 1)));
    }
}