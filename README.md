# market

Sistema demo de market para exemplificação do Spring.


## Requisitos

Para montar o ambiente do projeto é necessário:

* Java 8
* Docker
* Git
* Gradke


## Containers Dockers 

O primeiro passo a ser realizado é a configuração dos containers dockers do projeto.
Para isso é necessário executar o comando abaixo na pasta **/banco** na raíz do projeto.

    $ docker-compose up

O comando acima irá configurar todos os containers utilizados pela aplicação.


## Configuração do Backend

Este projeto foi desenvolvido utilizando a arquitetura Spring e conta com vários módulos para seu completo funcionamento.

Para rodar o projeto utilize do comando do gradle:

gradle bootRun.

