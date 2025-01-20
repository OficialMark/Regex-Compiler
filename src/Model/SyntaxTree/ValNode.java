package Model.SyntaxTree;

import Model.Components.Sentence;

public class ValNode implements Node{
    NodeType type;
    Sentence value;

    public ValNode(NodeType type, Sentence value) {
        this.type = type;
        this.value = value;
    }
}
