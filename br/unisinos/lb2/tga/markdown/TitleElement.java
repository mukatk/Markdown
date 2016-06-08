package br.unisinos.lb2.tga.markdown;

/**
 *
 * @author Samuel Carvalho Locatelli
 * @turma 43
 */
public class TitleElement extends MarkElement {
    private int level;

    public TitleElement(int level, String content) {
        super(content.replace("#", ""));
        this.level = level;
        this.breakLine = true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "TitleElement{" + super.toString() + "level=" + level + '}';
    }

    @Override
    public String toHtml() {
        if (this.level == 1) {
            return "<h1>" + content + "</h1>";
        } else {
            return "<h2>" + content + "</h2>";
        }
    }
    
    
    
    
}
