import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AWTNotepad extends Frame implements ActionListener, Runnable {
    TextArea textArea;
    String fileName = "untitled.txt";
    Thread autoSaveThread;
    boolean running = true;
    Label status;

    MenuBar menuBar;
    Menu fileMenu;
    MenuItem newFile, openFile, saveFile, exit;

    public AWTNotepad() {
        setTitle("AWT Notepad");
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Create TextArea
        textArea = new TextArea();
        add(textArea, BorderLayout.CENTER);

        // Status bar
        status = new Label("Ready");
        add(status, BorderLayout.SOUTH);

        // Create Menu
        menuBar = new MenuBar();
        fileMenu = new Menu("File");

        newFile = new MenuItem("New");
        openFile = new MenuItem("Open");
        saveFile = new MenuItem("Save");
        exit = new MenuItem("Exit");

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        setMenuBar(menuBar);

        // Event listeners
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                running = false;
                dispose();
            }
        });

        setVisible(true);

        // Start auto-save thread
        autoSaveThread = new Thread(this);
        autoSaveThread.start();
    }

    // File operations
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("New")) {
            textArea.setText("");
            fileName = "untitled.txt";
            status.setText("New file created");
        } 
        else if (command.equals("Open")) {
            openFile();
        } 
        else if (command.equals("Save")) {
            saveFile();
        } 
        else if (command.equals("Exit")) {
            running = false;
            System.exit(0);
        }
    }

    void openFile() {
        FileDialog fd = new FileDialog(this, "Open File", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getDirectory() + fd.getFile();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                textArea.setText("");
                String line;
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                status.setText("Opened: " + fileName);
            } catch (IOException e) {
                status.setText("Error opening file");
            }
        }
    }

    void saveFile() {
        FileDialog fd = new FileDialog(this, "Save File", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getDirectory() + fd.getFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write(textArea.getText());
                status.setText("Saved: " + fileName);
            } catch (IOException e) {
                status.setText("Error saving file");
            }
        }
    }

    // Auto-save thread logic
    public void run() {
    	status.setText("Running Thread");
        while (running) {
        	status.setText("Running Thread in loop");
            try {
                Thread.sleep(1000); // auto-save every 1 second
                if (!fileName.equals("untitled.txt")) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                        bw.write(textArea.getText());
                        status.setText("Auto-saved at: " + new java.util.Date());
                    } catch (IOException e) {
                        status.setText("Auto-save failed");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

    public static void main(String[] args) {
        new AWTNotepad();
    }
}