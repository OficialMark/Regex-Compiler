package Model.Components;

import java.util.ArrayList;

public class    Element {
    String State;
    ArrayList<Link> Links;

    public Element(String state) {
        State = state;
        Links = new ArrayList<>();
    }
}
