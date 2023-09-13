import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll, close;
    JTextArea textArea;



    TextEditor(){
        frame=new JFrame();
        menubar=new JMenuBar();

        textArea=new JTextArea();


        file=new JMenu("File");
        edit=new JMenu("Edit");

        newFile =new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        menubar.add(file);
        menubar.add(edit);


        frame.setJMenuBar(menubar);

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout());

        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane= new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);


        frame.add(panel);


        frame.setBounds(400,150,700,500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);


    }

    public static void main(String[] agrs){
        TextEditor texteditor = new TextEditor(); 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==cut){
            textArea.cut();
        }
        if(e.getSource()==copy){
            textArea.copy();
        }
        if(e.getSource()==paste){
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            textArea.selectAll();
        }
        if(e.getSource()==close){
            System.exit(0);
        }

        if(e.getSource()==newFile){
            //

        }
        if(e.getSource()==openFile){
            //
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption= fileChooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String path=file.getPath();

                try{
                    FileReader fileReader= new FileReader(path);

                    BufferedReader bufferedReader=new BufferedReader(fileReader);

                    String intermediate="";
                    String output="";

                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }

                    textArea.setText(output);

                }

                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(e.getSource()==saveFile){
            //
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption= fileChooser.showSaveDialog(null);
            
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    FileWriter fileWriter=new FileWriter(file);

                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);

                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(e.getSource()==newFile){
            TextEditor textEditor=new TextEditor();
        }
    }
    
}
