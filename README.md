### Java Image Processing with Multithreading  

---
#### Representação de memória ARGB
O formato usado em nosso exemplo de processamento de imagem é uma versão da família RGB chamada ARGB, onde A significa alfa (transparência)

A representação desta cor na memória é a seguinte:  
int = 0b11111111, 0b11111111, 0b11111111, 0b11111111 = ARGB

Como podemos ver, cada componente é representado por 1 byte (8 bits), então o valor de cada componente está na faixa de 0 (0x em hexadecimal) e 255 (0xFF em hexadecimal)

Como temos 4 bytes, podemos armazenar toda a cor de um pixel em uma variável do tipo int.

---
#### Explicação do código de extração de componente
No exemplo de processamento de imagem, temos os seguintes métodos que extraem componentes individuais de um pixel:

```
public static int getRed (int rgb) {
return (rgb & 0x00FF0000) >> 16;
}

public static int getGreen (int rgb) {
return (rgb & 0x0000FF00) >> 8;
}

public static int getBlue (int rgb) {
return rgb & 0x000000FF;
}
```

Vamos explicar cada método, em particular a matemática que ocorre para obter cada componente de cor.

Para obter um determinado componente (vermelho, verde ou azul), precisamos primeiro nos livrar de todos os outros componentes de cor no pixel, enquanto mantemos o componente desejado.

Para conseguir isso, aplicamos uma máscara de bits (bitmask).

Uma máscara de bits define quais bits queremos manter e quais queremos limpar.

Aplicamos um AND bit a bit com 0x00 (0000 0000 em binário) para nos livrar de um componente, uma vez que X AND 0 = 0, para qualquer X.

Aplicamos um AND bit a bit com 0xFF (1111 1111 em binário) para manter o valor de um componente, uma vez que X AND 1 = X, para qualquer X.

No entanto, depois de aplicar uma máscara de bits, não terminamos. Ainda precisamos mudar o byte que representa nosso componente para o byte mais baixo.

Por exemplo, no método getRed (..), depois de aplicarmos a máscara de bits em 0x76543210, terminamos com 0x00540000, mas o que precisamos é 0x00000054

Portanto, precisamos deslocar todos os bits no resultado da máscara de bits para a direita, usando o operador >>.

Para a extração da cor azul, não precisamos realizar nenhuma mudança, pois já é o byte mais à direita.

Para a extração da cor verde, precisamos mover todos os bits de 1 byte (8 bits) para a direita.

Para a extração da cor vermelha, precisamos mover todos os bits 2 bytes (16 bits) para a direita.

Combinando componentes de cor em um pixel
Ao construir a cor de um pixel a partir de componentes individuais de vermelho, verde e azul, tínhamos o seguinte método:
```
public static int createRGBFromColors (int red, int green, int blue) {
int rgb = 0;

    rgb | = azul;
    rgb | = verde << 8;
    rgb | = vermelho << 16;
 
    rgb | = 0xFF000000;
 
    return rgb;
}
```
No código acima, realizamos o oposto da extração do componente de cor. Pegamos cada componente e o deslocamos para o lugar certo na representação de pixel ARGB.

O azul é colocado no byte mais baixo, então nós simplesmente bit a bit OU a representação da cor do pixel com o componente azul

O verde precisa ser colocado no segundo byte para que seja primeiro deslocado 1 byte (8 bits) para a esquerda e, em seguida, recebe um OR bit a bit com a cor do pixel

Da mesma forma, o vermelho precisa ser colocado no terceiro byte para que seu componente seja deslocado 2 bytes (16 bits) para a esquerda e, em seguida, é feito um OR bit a bit com a cor do pixel


A etapa final é definir o nível de transparência para o mais alto, tornando a cor completamente opaca (0 níveis significam totalmente transparente, 255 significa totalmente opaca).

Isso é obtido definindo o byte mais à esquerda, que representa o componente alfa para 0xFF, que é 1111 1111 em binário.