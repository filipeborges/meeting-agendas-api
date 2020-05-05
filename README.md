## MeetingAgendas API

Uma api simples para gerenciar sessões de votação de uma assembléia.

- Temos:
	- **MeetingAgenda** - Pauta
	- **Associate** - Associado que vota em uma Pauta

### Tecnologias e Frameworks utilizados

- API - Optou-se por utilizar o framework Spring Boot devido as facilidades de auto-configuração que esse framework oferece em cima do framework Spring. É um framework consolidado no ambiente Java e que oferece uma maior produtividade.

- Banco de dados: Optou-se por utilizar o banco de dados MongoDB, devido a ser um banco de dados mais flexível (Schema-less), o que permite um desenvolvimento mais rápido, já que o ajuste das entities é mais flexível devido a não precisar criar o banco previamente (schema).

- Docker: Foi utilizado também o docker junto com o docker-compose, para automatizar o processo de instalação/execução da aplicação, bem como do banco Mongo.

- RabbitMQ: Mensageria usada na notificação do resultado.

### Como executar a aplicação

**Requisitos:**

- Variável JAVA_HOME apontando para uma JDK 11;
- Docker e docker-compose instalados
- Linux

**Instalação:**

`./mvnw package && docker-compose up -d`
ou
`./mvnw package && sudo docker-compose up -d`

Obs: Dependendo da instalação, talvez seja necessário invocar o docker-compose com elevação de privilégios. Nesse caso em uma determinada etapa do processo será solicitado senha.

**Acesso a aplicação:**

A aplicação está acessível em: http://localhost:8080/

O Swagger está acessível em: http://localhost:8080/swagger-ui.html

### Estrutura do código

O código está escrito em inglês e possui poucos comentários - tentou-se optar por uma escrita mais semântica de forma a evitar os comentários.

Além disso, está estruturado nos seguintes pacotes:

- **client** - Classes/interfaces clientes de outros webservices (Todos os outros beans auxiliares dessas classes que residem em outros pacotes, estão dentro de um sub-pacote chamado **client**, de forma a separar esses componentes dos componentes da aplicação);
- **config** - Configuração da aplicação;
- **controller** - Controllers MVC dos endpoints;
- **converter** - Conversores Entity/Dto;
- **dto** - Classes DTOs (Estão divididos em sub-pacotes de domínio)
- **entity** - Entidades de banco de dados;
- **enums** - Enums da aplicação;
- **exception** - Exceptions de negócio;
- **repository** - Repositórios de operações no banco de dados;
- **scheduler** - Responsável por encerrar a sessão de votação e calcular o resultado;
- **service** - Serviços para as controllers;
- **util** - Utilitários;

## Modelagem do banco de dados

O formato das entidades está descrito no arquivo **DB-DATABASE-MODEL**. Como não há um esquema, as entidades estão descritas no formato simples de JSON.

## Decisões tomadas durante o desenvolvimento

- **OptimisticLocking** - Optou-se por utilizar o mecanismo de controle OptimisticLocking oferecido pelo Spring Data. Basicamente um atributo com o nome **version** controla a versão da entidade que está sendo manipulada. Caso uma entidade tenha seus atributos atualizados e ao persistir a entidade ela não esteja na sua versão mais atual (no meio tempo da operação outra thread atualizou a entidade no banco), a operação é rejeitada (de forma a evitar que os dados sejam sobre escritos).

- **Testes unitários de services** - Grande parte das operações dos serviços é chamada de métodos em cima dos repositórios. Nesse caso talvez fosse mais interessante um teste de integração usando um Mongo Embedded. Por conta disso foi feito testes unitários somente pra alguns poucos métodos desses serviços.

- **Fechamento de sessão e cálculo de resultado** - Considerando a utilização do Spring MVC, a melhor solução encontrada para encerrar a votação e cálcular o resultado, após um período variável de tempo, foi utilizar um Scheduler que roda a cada 30s, onde um serviço que procura sessões em aberto no banco e faz o fechamento das mesmas é chamado.

- **Formato de data númerico** - Procurou-se salvar a data de expiração usando a representação de milisegundos (EPOCH). A aritmética de cálculo é mais simples por ser numérico. E também evita problemas de timezone diferentes entre servidores e clientes (já que é uma unidade de tempo absoluta).

- **Consumo do resultado da votação da fila de mensagens** - Um listener simples escutando na fila do RabbitMQ irá fazer um print simples do resultado da votação no console.