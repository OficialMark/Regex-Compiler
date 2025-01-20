package Model.SyntaxTree;

import Model.Components.Element;
import Model.Components.Type;

public interface Node {
    NodeType type = null;
    Node LLeaf = null;
    Node RLeaf = null;
}
