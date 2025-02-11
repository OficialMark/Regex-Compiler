# Lista 2 -- Linguagens Formais e Autômatos -- 2024/2

**Introdução à Teoria da Computação; Fundamentos Matemáticos;  Computação e Representação**

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

```bash
 java Main.java
```
Ou, através de um IDE, execute o arquivo ``Main.java``

### Comportamento
Após executar, o console exibirá ``Digite a expressão regular: ``, e, após o usuário inserir a cadeia, o programa imprimirá 
o autômato seguindo o formato especificado, como no exemplo abaixo:

```bash
Digite a expressão regular: (a|\empty)(ab|bb)*
Initial state: q0
Accepting states: q15
q0 -> q1: \empty
q1 -> q2: a
q2 -> q3: \empty
q3 -> q4: \empty
q4 -> q5: \empty
q5 -> q6: \empty
q6 -> q7: a
q7 -> q8: \empty
q8 -> q9: b
q9 -> q10: \empty
q10 -> q5: \empty
q5 -> q11: \empty
q11 -> q12: b
q12 -> q13: \empty
q13 -> q14: b
q14 -> q10: \empty
q10 -> q15: \empty
q4 -> q15: \empty
q0 -> q16: \empty
q16 -> q17: \empty
q17 -> q3: \empty
```
