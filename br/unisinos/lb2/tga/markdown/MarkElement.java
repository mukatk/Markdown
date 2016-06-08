package br.unisinos.lb2.tga.markdown;

/**
 *
 * @author Samuel Carvalho Locatelli
 * @turma 43
 */
public abstract class MarkElement {
    protected String content;
    protected boolean breakLine;

    public MarkElement(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBreakLine() {
        return breakLine;
    }

    public void setBreakLine(boolean breakLine) {
        this.breakLine = breakLine;
    }

    @Override
    public String toString() {
        return "content=" + content + ", ";
    }
    
    public abstract String toHtml();
}
