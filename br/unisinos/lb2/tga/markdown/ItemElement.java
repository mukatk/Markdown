package br.unisinos.lb2.tga.markdown;

/**
 *
 * @author Samuel Carvalho Locatelli
 * @turma 43
 */
public class ItemElement extends MarkElement {
    private char style; //U = Bullet O = Enumerated

    public ItemElement(boolean breakLine, char style, String content) {
        super(content.replace("1. ", "").replace("* ", ""));
        this.breakLine = breakLine;
        this.style = style;
    }

    public char getStyle() {
        return style;
    }

    public void setStyle(char style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "ItemElement{" + super.toString() + "breakLine=" + breakLine + ", style=" + style + '}';
    }

    @Override
    public String toHtml() {
        return content;
    }
    
   
}
