#Trabalho da disciplina de Sistemas Operacionais do curso de Ciência da Computação da UNESP de Rio Claro

## Funcionamento
O sistema inicialmente gera todos os pacotes, veículos e pontos de distribuição, com base no argumentos de entrada, da seguinte forma: A letra que o argumento se refere + a qtd.dele, por exemplo:
S10 C50 P60 A40. (Lembrando P >> C >> A).

No inicio, tanto os pacotes quanto os veículos são destinados aleatoriamente para pontos de distribuição.
Após isso, os caminhões começam a percorrer os pontos de distribuição (que estão interconectados em formato de lista circular), pegando os pacotes presentes no ponto e levando até o destino. Caso o ponto de redistribuição já tenha um veículo e um segundo chegar, este precisará esperar até que o veículo inicial libere ele.

## Estrutura
### dataStructure
CircularList: Classe para a implementação de uma lista circular para ligar os pontos de redistribuição.
Node: Classe que representar os elementos da lista circular.

### entities
Delivery: Modelo de um pacote a ser entregue.
Redistributor: Classe responsável pelo ponto de distribuição.
Vehicle: Representação do veículo responsável por carregar os pacotes e distribuir eles nos pontos de entrega.

### monitors
MessageMonitor: Classe que implementa os métodos responsáveis por printar no prompt cada novo avanço na entrega dos pacotes. Ao mesmo tempo, é ele quem cria um arquivo .csv para cada pacote e nele grava o rastreio desse determinado pacote.

### services
DeliveryService: Classe responsável pela lógica das ações relacionadas a um pacote.
RedistributorService: Implementa os métodos associados ao ponto de distribuição.
VehicleService: Possui as funções que um veículo pode realizar.

### util
ApplicationContext: Classe responsável por implementar métodos que nos permitem manipular a lista circular de pontos de redistribuição.
ParameterValidator: Valida os dados de entrada do programa
Random Generator: Cria valores aleatórios que serão utilizados para criar as diferentes instâncias das nossas entidades.




