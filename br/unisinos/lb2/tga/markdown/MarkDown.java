package br.unisinos.lb2.tga.markdown;

import br.unisinos.lb2.tga.markdown.MarkDownExceptions.ErroDeMarcacaoException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel Carvalho Locatelli
 * @turma 43
 */
public class MarkDown {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ErroDeMarcacaoException {
        JOptionPane.showMessageDialog(null, "Seja bem-vindo ao processador de arquivos Markdown");
        JFileChooser jc = new JFileChooser();
        FileReader fr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        List<MarkElement> elements = new ArrayList();
        if (jc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                fr = new FileReader(jc.getSelectedFile());
                br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    String[] splitted;
                    if (line.startsWith("#")) {
                        if (line.startsWith("##")) {
                            elements.add(new TitleElement(2, line));
                        } else {
                            elements.add(new TitleElement(1, line));
                        }
                    } else {
                        if (line.startsWith("1. ") || line.startsWith("* ")) {
                            if (line.startsWith("1. ")) {
                                boolean isItem = true;
                                boolean isFinal = false;
                                String aux = "";
                                for (int i = 0; i < line.length(); i++) {
                                    if (line.charAt(i) == '*' && line.charAt(i + 1) == '*') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'O', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            if (line.indexOf("**", i + 1) == -1) {
                                                System.out.println(line.substring(i, line.length()));
                                                elements.add(new TextElement(true, line.substring(line.indexOf("**"), line.length())));
                                                break;
                                            } else {
                                                if (line.indexOf("**", i + 1) + 2 == line.length()) {
                                                    elements.add(new TextElement(true, line.substring(i, line.indexOf("**", i + 1) + 2)));
                                                    isFinal = true;
                                                } else {
                                                    elements.add(new TextElement(false, line.substring(i, line.indexOf("**", i + 1) + 2)));
                                                }
                                                i = line.indexOf("**", i + 1) + 1;
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else if (line.charAt(i) == '_') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'O', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            if (line.indexOf("_", i + 1) == -1) {
                                                elements.add(new TextElement(true, line.substring(i)));
                                                break;
                                            } else {
                                                if (line.indexOf("_", i + 1) + 1 == line.length()) {
                                                    elements.add(new TextElement(true, line.substring(i, line.indexOf("_", i + 1) + 1)));
                                                    isFinal = true;
                                                } else {
                                                    elements.add(new TextElement(false, line.substring(i, line.indexOf("_", i + 1) + 1)));
                                                }
                                                i = line.indexOf("_", i + 1);
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else if (line.charAt(i) == '*') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'O', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            if (line.indexOf("*", i + 1) == -1) {
                                                elements.add(new TextElement(true, line.substring(i)));
                                                break;
                                            } else {
                                                if (line.indexOf("*", i + 1) + 1 == line.length()) {
                                                    elements.add(new TextElement(true, line.substring(i, line.indexOf("*", i + 1) + 1)));
                                                    isFinal = true;
                                                } else {
                                                    elements.add(new TextElement(false, line.substring(i, line.indexOf("*", i + 1) + 1)));
                                                }
                                                i = line.indexOf("*", i + 1);
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else if (line.charAt(i) == '[') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'O', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            String content = "";
                                            if (line.indexOf("]", i + 1) != -1) {
                                                content = line.substring(i, line.indexOf("]", i + 1) + 1);
                                                String ref = "";
                                                if (line.indexOf(")", line.indexOf("]", i + 1)) != -1) {
                                                    if (line.indexOf(")", line.indexOf("]", i + 1)) + 1 == line.length()) {
                                                        ref = line.substring(line.indexOf("]", i + 1) + 1, line.indexOf(")", i + 1) + 1);
                                                        elements.add(new LinkElement(ref, content, true));
                                                        i = line.length();
                                                        isFinal = true;
                                                    } else {
                                                        ref = line.substring(line.indexOf("]", i + 1) + 1, line.indexOf(")", i + 1) + 1);
                                                        i = line.indexOf(")", i + 1);
                                                        elements.add(new LinkElement(ref, content, false));
                                                    }
                                                } else {
                                                    ref = line.substring((line.indexOf("]", i + 1)) + 1, line.length());
                                                    i = line.length();
                                                    elements.add(new LinkElement(ref, content, false));
                                                }
                                            } else {
                                                aux += line.charAt(i);
                                                continue;
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else {
                                        aux += line.charAt(i);
                                    }
                                }
                                if (isItem) {
                                    elements.add(new ItemElement(true, 'O', aux));
                                } else if (!isFinal) {
                                    try {
                                        elements.add(new TextElement(true, aux));
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }
                            }

                            if (line.startsWith("* ")) {
                                boolean isItem = true;
                                boolean isFinal = false;
                                String aux = "";
                                for (int i = line.indexOf("* ") + 2; i < line.length(); i++) {
                                    if (line.charAt(i) == '*' && line.charAt(i + 1) == '*') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'U', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            if (line.indexOf("**", i + 1) == -1) {
                                                System.out.println(line.substring(i, line.length()));
                                                elements.add(new TextElement(true, line.substring(line.indexOf("**"), line.length())));
                                                break;
                                            } else {
                                                if (line.indexOf("**", i + 1) + 2 == line.length()) {
                                                    elements.add(new TextElement(true, line.substring(i, line.indexOf("**", i + 1) + 2)));
                                                    isFinal = true;
                                                } else {
                                                    elements.add(new TextElement(false, line.substring(i, line.indexOf("**", i + 1) + 2)));
                                                }
                                                i = line.indexOf("**", i + 1) + 1;
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else if (line.charAt(i) == '_') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'U', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            if (line.indexOf("_", i + 1) == -1) {
                                                elements.add(new TextElement(true, line.substring(i)));
                                                break;
                                            } else {
                                                if (line.indexOf("_", i + 1) + 1 == line.length()) {
                                                    elements.add(new TextElement(true, line.substring(i, line.indexOf("_", i + 1) + 1)));
                                                    isFinal = true;
                                                } else {
                                                    elements.add(new TextElement(false, line.substring(i, line.indexOf("_", i + 1) + 1)));
                                                }
                                                i = line.indexOf("_", i + 1);
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else if (line.charAt(i) == '*') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'U', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            if (line.indexOf("*", i + 1) == -1) {
                                                elements.add(new TextElement(true, line.substring(i)));
                                                break;
                                            } else {
                                                if (line.indexOf("*", i + 1) + 1 == line.length()) {
                                                    elements.add(new TextElement(true, line.substring(i, line.indexOf("*", i + 1) + 1)));
                                                    isFinal = true;
                                                } else {
                                                    elements.add(new TextElement(false, line.substring(i, line.indexOf("*", i + 1) + 1)));
                                                }
                                                i = line.indexOf("*", i + 1);
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else if (line.charAt(i) == '[') {
                                        try {
                                            if (isItem) {
                                                elements.add(new ItemElement(false, 'U', aux));
                                                aux = "";
                                                isItem = false;
                                            } else {
                                                try {
                                                    elements.add(new TextElement(false, aux));
                                                    aux = "";
                                                } catch (ErroDeMarcacaoException e) {
                                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                                }
                                            }
                                            String content = "";
                                            if (line.indexOf("]", i + 1) != -1) {
                                                content = line.substring(i, line.indexOf("]", i + 1) + 1);
                                                String ref = "";
                                                if (line.indexOf(")", line.indexOf("]", i + 1)) != -1) {
                                                    if (line.indexOf(")", line.indexOf("]", i + 1)) + 1 == line.length()) {
                                                        ref = line.substring(line.indexOf("]", i + 1) + 1, line.indexOf(")", i + 1) + 1);
                                                        elements.add(new LinkElement(ref, content, true));
                                                        i = line.length();
                                                        isFinal = true;
                                                    } else {
                                                        ref = line.substring(line.indexOf("]", i + 1) + 1, line.indexOf(")", i + 1) + 1);
                                                        i = line.indexOf(")", i + 1);
                                                        elements.add(new LinkElement(ref, content, false));
                                                    }
                                                } else {
                                                    ref = line.substring((line.indexOf("]", i + 1)) + 1, line.length());
                                                    i = line.length();
                                                    elements.add(new LinkElement(ref, content, false));
                                                }
                                            } else {
                                                aux += line.charAt(i);
                                                continue;
                                            }
                                        } catch (ErroDeMarcacaoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    } else {
                                        aux += line.charAt(i);
                                    }
                                }
                                if (isItem) {
                                    elements.add(new ItemElement(true, 'U', aux));
                                } else if (!isFinal) {
                                    try {
                                        elements.add(new TextElement(true, aux));
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }
                            }
                        } else if (!line.equals("")) {
                            boolean isFinal = false;
                            String aux = "";
                            for (int i = 0; i < line.length(); i++) {
                                if (line.charAt(i) == '*' && line.charAt(i + 1) == '*') {
                                    try {
                                        if (!isFinal) {
                                            elements.add(new TextElement(false, aux));
                                            aux = "";
                                        }
                                        if (line.indexOf("**", i + 1) == -1) {
                                            System.out.println(line.substring(i, line.length()));
                                            elements.add(new TextElement(true, line.substring(line.indexOf("**"), line.length())));
                                            break;
                                        } else {
                                            if (line.indexOf("**", i + 1) + 2 == line.length()) {
                                                elements.add(new TextElement(true, line.substring(i, line.indexOf("**", i + 1) + 2)));
                                                isFinal = true;
                                            } else {
                                                elements.add(new TextElement(false, line.substring(i, line.indexOf("**", i + 1) + 2)));
                                            }
                                            i = line.indexOf("**", i + 1) + 1;
                                        }
                                    } catch (ErroDeMarcacaoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                } else if (line.charAt(i) == '_') {
                                    try {
                                        if (!isFinal) {
                                            elements.add(new TextElement(false, aux));
                                            aux = "";
                                        }
                                        if (line.indexOf("_", i + 1) == -1) {
                                            elements.add(new TextElement(true, line.substring(i)));
                                            break;
                                        } else {
                                            if (line.indexOf("_", i + 1) + 1 == line.length()) {
                                                elements.add(new TextElement(true, line.substring(i, line.indexOf("_", i + 1) + 1)));
                                                isFinal = true;
                                            } else {
                                                elements.add(new TextElement(false, line.substring(i, line.indexOf("_", i + 1) + 1)));
                                            }
                                            i = line.indexOf("_", i + 1);
                                        }
                                    } catch (ErroDeMarcacaoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                } else if (line.charAt(i) == '*') {
                                    try {
                                        if (!isFinal) {
                                            elements.add(new TextElement(false, aux));
                                            aux = "";
                                        }
                                        if (line.indexOf("*", i + 1) == -1) {
                                            elements.add(new TextElement(true, line.substring(i)));
                                            break;
                                        } else {
                                            if (line.indexOf("*", i + 1) + 1 == line.length()) {
                                                elements.add(new TextElement(true, line.substring(i, line.indexOf("*", i + 1) + 1)));
                                                isFinal = true;
                                            } else {
                                                elements.add(new TextElement(false, line.substring(i, line.indexOf("*", i + 1) + 1)));
                                            }
                                            i = line.indexOf("*", i + 1);
                                        }
                                    } catch (ErroDeMarcacaoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                } else if (line.charAt(i) == '[') {
                                    try {
                                        String content = "";
                                        if (line.indexOf("]", i + 1) != -1) {
                                            if (!isFinal) {
                                                elements.add(new TextElement(false, aux));
                                                aux = "";
                                            }
                                            content = line.substring(i, line.indexOf("]", i + 1) + 1);
                                            String ref = "";
                                            if (line.indexOf(")", line.indexOf("]", i + 1)) != -1) {
                                                if (line.indexOf(")", line.indexOf("]", i + 1)) + 1 == line.length()) {
                                                    ref = line.substring(line.indexOf("]", i + 1) + 1, line.indexOf(")", i + 1) + 1);
                                                    elements.add(new LinkElement(ref, content, true));
                                                    i = line.length();
                                                    isFinal = true;
                                                } else {
                                                    ref = line.substring(line.indexOf("]", i + 1) + 1, line.indexOf(")", i + 1) + 1);
                                                    i = line.indexOf(")", i + 1);
                                                    elements.add(new LinkElement(ref, content, false));
                                                }
                                            } else {
                                                ref = line.substring((line.indexOf("]", i + 1)) + 1, line.length());
                                                i = line.length();
                                                elements.add(new LinkElement(ref, content, false));
                                            }
                                        } else {
                                            aux += line.charAt(i);
                                            continue;
                                        }
                                    } catch (ErroDeMarcacaoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                } else {
                                    aux += line.charAt(i);
                                }
                            }
                            if (!isFinal) {
                                try {
                                    elements.add(new TextElement(true, aux));
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                            }
                        }
                    }
                    line = br.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao ler arquivo");
            } finally {
                try {
                    br.close();
                    for (int i = 0; i < elements.size(); i++) {
                        System.out.println(elements.get(i));
                    }

                    //Criando html
                    if (JOptionPane.showConfirmDialog(null, "Deseja gerar um html?") == JOptionPane.YES_OPTION) {
                        String path = jc.getSelectedFile().getPath();
                        System.out.println(path);
                        FileWriter fw = new FileWriter(path.replace(".txt", "") + ".html");
                        pw = new PrintWriter(fw);
                        String html = "<!DOCTYPE>"
                                + "<html lang=pt-br>"
                                + "<head>"
                                + "<meta charset=\"UTF-8\"/>"
                                + "<title>TRAB GB Ayres e Samuel</title>"
                                + "<style> p { text-align: justify; } </style>"
                                + "</head>"
                                + "<body>";
                        for (int i = 0; i < elements.size(); i++) {
                            MarkElement m = elements.get(i);
                            if (m instanceof TitleElement) {
                                html += m.toHtml();
                            } else if (m instanceof ItemElement) {
                                if (((ItemElement) m).getStyle() == 'U') {
                                    html += "<ul>";
                                    for (; i < elements.size(); i++) {
                                        m = elements.get(i);
                                        if (!(m instanceof ItemElement)) {
                                            break;
                                        } else if (((ItemElement) m).getStyle() != 'U') {
                                            break;
                                        } else {
                                            if (m.isBreakLine()) {
                                                html += "<li>" + m.toHtml() + "</li>";
                                            } else {
                                                html += "<li>";
                                                while (!(m.isBreakLine())) {
                                                    html += m.toHtml();
                                                    i++;
                                                    m = elements.get(i);
                                                }
                                                html += m.toHtml() + "</li>";
                                            }
                                        }
                                    }
                                    i--;
                                    html += "</ul>";
                                } else {
                                    html += "<ol>";
                                    for (; i < elements.size(); i++) {
                                        m = elements.get(i);
                                        if (!(m instanceof ItemElement)) {
                                            break;
                                        } else if (((ItemElement) m).getStyle() != 'O') {
                                            break;
                                        } else {
                                            if (m.isBreakLine()) {
                                                html += "<li>" + m.toHtml() + "</li>";
                                            } else {
                                                html += "<li>";
                                                while (!(m.isBreakLine())) {
                                                    html += m.toHtml();
                                                    i++;
                                                    m = elements.get(i);
                                                }
                                                html += m.toHtml() + "</li>";
                                            }
                                        }
                                    }
                                    i--;
                                    html += "</ol>";
                                }
                            } else {
                                html += "<p>";
                                for (; i < elements.size(); i++) {
                                    m = elements.get(i);
                                    if (m instanceof TextElement || m instanceof LinkElement) {
                                        if (m.isBreakLine()) {
                                            html += m.toHtml() + "</br>";
                                        } else {
                                            for (; i < elements.size(); i++) {
                                                m = elements.get(i);
                                                html += m.toHtml();
                                                if (m.isBreakLine()) {
                                                    break;
                                                }
                                            }
                                            html += "</br>";
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                i--;
                                html += "</p>";
                            }
                        }
                        pw.println(html + "</body> </html>");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo");
                } finally {
                    pw.close();
                }
            }
        }
    }
}
