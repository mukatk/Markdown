package br.unisinos.lb2.tga.markdown;

import br.unisinos.lb2.tga.markdown.MarkDownExceptions.ErroDeMarcacaoException;

/**
 *
 * @author Samuel Carvalho Locatelli
 * @turma 43
 */
public class TextElement extends MarkElement {

    private char style;

    public TextElement(boolean breakLine, String content) throws ErroDeMarcacaoException {
        super(content);
        this.style = setStyle();
        this.breakLine = breakLine;
        setContent(content.replace("*", "").replace("_", ""));
    }

    public char getStyle() {
        return style;
    }

    public char setStyle() throws ErroDeMarcacaoException {
        if (content.startsWith("**") && !content.endsWith("**")) {
            throw new ErroDeMarcacaoException("Erro de marcação **");
        } else if (content.startsWith("*") && !content.endsWith("*")) {
            throw new ErroDeMarcacaoException("Erro de marcação *");
        } else if (content.startsWith("_") && !content.endsWith("_")) {
            throw new ErroDeMarcacaoException("Erro de marcação _");
        }
        
        if (content.startsWith("**") && content.endsWith("**")) {
            return 'B';
        } else if (content.startsWith("*") && content.endsWith("*")) {
            return 'I';
        } else if (content.startsWith("_") && content.endsWith("_")) {
            return 'U';
        } else {
            return ' ';
        }
    }

    public void setStyle(char style) {
        this.style = style;
    }


    @Override
    public String toString() {
        return "TextElement{" + super.toString() + "style=" + style + ", breakLine=" + breakLine + '}';
    }

    @Override
    public String toHtml() {
        String html = "";
        if (style == 'B') {
            html += "<b>" + content + "</b>";
        } else if (style == 'I') {
            html += "<i>" + content + "</i>";
        } else if (style == 'U') {
            html += "<u>" + content + "</u>";
        } else {
            html += content;
        }
        return html;
    }
    
    
}
