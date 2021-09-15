# Pandemic Combat Aid System


## Rodar o projeto
Para rodar o projeto basta clonar o repositório, importar o arquivo POM e startar a aplicação, as tabelas serão criadas automaticamente.

A documentação da API foi feita com o Swagger, e encontra-se disponível em http://localhost:8080/swagger-ui/.


## Considerações
A principio pensei em ter uma Classe Resource e as classes Employee e Material herdando dela, porém estava tendo alguns problemas com essa abordagem então decidi fazer apenas a classe Resource colocando um campo Enumerado para informar o tipo do Resource.

Foi feio a implementação da maioria dos requisitos com exceção do histórico da negocição(o intercâmbio de recursos foi implementado), e média de recursos por hospital.

Tive algumas dificuldades com esses dois requisitos e devido ao tempo não consegui terminar a implementação.

A média de recursos pensei em fazer uma query como "select distinct (r.name), count(*) as quantity from resources as r group by r.name" na tabela de recursos para pegar cada tipo de recurso e contar a quantidade de cada um e retornar como um Map<String,Integer> e depois fazer a media baseado na quantidade total de hospitais divido pela quantiodade total de cada recurso, porém tive algumas dificuldades nessa abordagem.

Em relação ao histórico de negociação, pensei em criar outra tabela com o id do hospital que está enviando, o id do hospital que está recebendo, os recursos, o tipo da operação(Se a operação foi de entrada de recursos ou saída de recursos do hospital) e a data da transação e salvar no banco.

No final o diagrama de classes ficou assim.

![image](https://user-images.githubusercontent.com/67595193/133419724-ce358f51-335c-4102-97c6-bcfde04559ce.png)
