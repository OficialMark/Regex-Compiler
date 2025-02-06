package Model.Components;

import java.util.ArrayList;

public class Sentence {
    public Element Initial;
    public Element Final;
    static int i = 0;
    static ArrayList<Link> visited = new ArrayList<>();

    public Sentence(String regex, int pos) {
        Initial = new Element();
        Final = new Element();

        Link link = new Link(Final, regex.charAt(pos));
        Initial.Links.add(link);
    }

    public Sentence(String symbol){
        Initial = new Element();
        Final = new Element();

        Link link = new Link(Final, symbol);
        Initial.Links.add(link);
    }

    public void union(Sentence target){
        Element newInit = new Element();

        newInit.Links.add(new Link(this.Initial, "\\empty"));
        newInit.Links.add(new Link(target.Initial, "\\empty"));

        Element newFinal = new Element();

        this.Final.Links.add(new Link(newFinal, "\\empty"));
        target.Final.Links.add(new Link(newFinal, "\\empty"));

        this.Initial = newInit;
        this.Final = newFinal;
    }

    public void concat(Sentence target){
        this.Final.Links.add(new Link(target.Initial, "\\empty"));

        this.Final = target.Final;
    }

    public void star(){
        //liga o antigo final ao antigo inicial, movimento vazio
        this.Final.Links.add(new Link(this.Initial, "\\empty"));

        //cria um estado final, liga estado final do elemento nesse novo estado, liga novo estado ao inicial do elemento, ambos movimentos vazios
        Element newFinal = new Element();

        this.Final.Links.add(new Link(newFinal, "\\empty"));
        newFinal.Links.add(new Link(this.Initial, "\\empty"));

        //cria um estado inicial, liga ao estado inicial do elemento, e liga novo inicial ao novo estado final, ambos movimentos vazios
        Element newInit = new Element();

        newInit.Links.add(new Link(this.Initial, "\\empty"));
        newInit.Links.add(new Link(newFinal, "\\empty"));

        this.Initial = newInit;
        this.Final = newFinal;
    }

    private void sortNames(Element element){

        if (element.State == null) {
            element.State = ("q" + (i+""));
            i++;
        }

        if (element == Final) return;

        for (int j = 0; j < element.Links.size(); j++){

            Link link = element.Links.get(j);
            if (visited.contains(link)) continue;

            visited.add(link);

            Element target = link.Target;
            sortNames(target);
        }

    }

    private void printElement(Element element){
        if (element == Final) return;

        for (int j = 0; j < element.Links.size(); j++){

            Link link = element.Links.get(j);

            if (visited.contains(link)) continue;
            visited.add(link);

            System.out.println(element.State + " -> " + link.Target.State + ": " + link.Symbol);

            Element target = link.Target;
            printElement(target);
        }

    }

    public void ToString(){
        sortNames(Initial);

        System.out.println("Initial state: " + Initial.State);
        System.out.println("Accepting states: " + Final.State);

        visited = new ArrayList<>();
        printElement(Initial);
    }
}
