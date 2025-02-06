package Model.Components;

public class Link {
    public Element Target;
    public String Symbol;

    public Link(Element target, char symbol) {
        Target = target;
        Symbol = Character.toString(symbol);
    }

    public Link(Element target, String symbol) {
        Target = target;
        Symbol = symbol;
    }
}
