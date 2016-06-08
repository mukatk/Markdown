package br.unisinos.lb2.tga.markdown;

import br.unisinos.lb2.tga.markdown.MarkDownExceptions.ErroDeMarcacaoException;

/**
 *
 * @author Samuel Carvalho Locatelli
 * @turma 43
 */
public class LinkElement extends MarkElement {
    private String ref;

    public LinkElement(String ref, String content, boolean breakLine) throws ErroDeMarcacaoException {
        super(content);
        setRef(ref);
        this.breakLine = breakLine;
        setContent(content.replace("[", "").replace("]", ""));
        this.ref = ref.replace("(", "").replace(")", "");
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) throws ErroDeMarcacaoException {
        if(ref.startsWith("(") && !ref.endsWith(")")){
            throw new ErroDeMarcacaoException("Erro de marcação, faltou fechar parenteses");
        } else if (!ref.startsWith("(") && ref.endsWith(")")) {
            throw new ErroDeMarcacaoException("Erro de marcação, faltou abrir parenteses");
        } else if (!ref.startsWith("(") && !ref.endsWith(")")) {
            throw new ErroDeMarcacaoException("Erro de marcação, faltou abrir e fechar parenteses");
        } else {
            this.ref = ref;
        }
    }
    
    @Override
    public String toString() {
        return "LinkElement{" + super.toString() + "ref=" + ref + ", breakLine=" + breakLine + '}';
    }

    @Override
    public String toHtml() {
        return "<a href=\"" + ref + "\">" + content + "</a>";
    }
    
    
}
