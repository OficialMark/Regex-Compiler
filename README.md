# Lista 2 -- Teoria da Computação / Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação & Fundamentos Matemáticos**

**Autor:** Bruno da Fonseca Chevitarese & Marcos Vinícius Souza dos Santos

## Problema 4
Este projeto visa converter uma expressão regular pura em um Autômato Finito Não Determinístico com Movimentos Vazios
(NFA-ε). A entrada segue a definição de expressões regulares com os símbolos especiais \null (∅), \empty (ε), operadores 
| (união), * (estrela de Kleene).

## **Solução**
Os autômatos são modelados por classes que representam suas estruturas básicas. Cada estado é um Elemento, definido por 
um nome e uma lista de Conexões. A classe Conexão possui um símbolo (caractere necessário para a transição) e um Elemento de destino.
A classe Sentença representa o autômato, contendo um Elemento Inicial e um Elemento Final.

A classe Setença implementa em seus métodos as operações de união, concatenação e estrela de kleene, seguindo
o Algoritmo de Thompson.

A leitura dos caracteres é feita pela classe Parser, que processa a entrada, empilha valores e operações conforme a 
precedência e executa as transformações. No final, imprime o autômato gerado, informando o estado inicial, os estados de aceitação e a função de transição.


## **Execução**

### Pré-requisitos:
- Java JDK instalado.
- Terminal ou IDE configurado para executar programas Java.

### Tutorial
Navegue até a pasta ``/problemas/probl4/src`` e execute:

```
 java Main.java
```
Ou, através de um IDE, execute o arquivo ``Main.java``