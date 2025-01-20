package Model.SyntaxTree;

import Model.Components.Type;

public class OpNode implements Node{
    NodeType type;
    Type value;

    public OpNode(NodeType type, Type value) {
        this.type = type;
        this.value = value;
    }
}
