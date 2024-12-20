# Projeto CRUD REST API

## Tecnologias Usadas

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original-wordmark.svg" height="40" width="40"/>`MySQL`
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="40" width="40"/>  `Spring Boot 3`
<img src="https://www.vectorlogo.zone/logos/hibernate/hibernate-icon.svg" width="40" height="40">  `Hibernate`
<img src="https://www.svgrepo.com/show/374111/swagger.svg" width="50" height="50">  `Swagger`
<img src="https://raw.githubusercontent.com/projectlombok/lombok/f3a4b1b4151a9dd1646f1b170c17f5f29903f45a/src/installer/lombok/installer/lombok.svg" width="40" height="40">  `Lombok`

<img src="https://spring.io/img/logos/spring-initializr.svg" width="40" height="40">  `Spring Data Jpa`
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/>`Java`
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original-wordmark.svg" width="50" height="50"/>`JUnit 5`
<img src="https://upload.wikimedia.org/wikipedia/commons/2/2c/Mockito_Logo.png" width="70" height="70"/>`Mockito`
          
## Instruções de Instalação

1. Clone o repositório: `git clone https://github.com/Fernand0-jf/API-GMAIL`
2. Configure o banco de dados MySQL e atualize as configurações no arquivo `application.properties`.
3. Instale as dependências: `mvn install` ou `./gradlew build`
4. Execute a aplicação: `mvn spring-boot:run` ou `./gradlew bootRun`

## Configuração da API

- Certifique-se de configurar corretamente o arquivo `application.properties` com as credenciais do banco de dados PostgreSQL.
- Você pode ajustar outras configurações no arquivo para atender às suas necessidades específicas.

## Documentação da API com Swagger

Acesse a documentação da API utilizando Swagger:

-verifique se está com host e a porta certa ex: http://host:port/swagger-ui.html
- [Swagger UI](http://localhost:8080/swagger-ui.html): Visualize e teste as endpoints da API.

## Uso da API

- Utilize o Postman ou qualquer outra ferramenta para realizar requisições HTTP para as seguintes rotas:

### Rotas da API

- `POST /main/send/{email}`: envia um unico email
- `POST /main/send/id/{groupId}`: envia um corpo de email para todos os usuarios cadastrados no banco de dados com o mesmo groupId
- `POST /userMain/save`: Salva o email do usuario no banco de dados
- `DELETE /userMain/delete/{id}`: Exclui o email do usuario do banco de dados

### Exemplos

#### Envia Um Unico Email

**Request:**
```http
POST /main/send/teste@ok.com
Content-Type: application/json

{
    "subject":"teste envio de email 1",
    "message":"eu conseguir enviar um email"
}
```
**Resposta:**
```http

email send

```

#### Salva Um Usuário No DB

**Request:**
```http
POST /userMain/save
Content-Type: application/json

{
    "username":"teste",
    "groupId":1,
    "email":"teste@ok.com"
}
```
**Resposta:**
```http

user save

```
#### Envia Email Para Todos Os Usuários Com o Mesmo GroupId

**Request:**
```http
POST /main/send/id/1
```
**Resposta:**
```http
{
    "subject":"2 teste envio de email",
    "message":"eu conseguir enviar um email"
}
```
## Deletar Usuário

**Request:**
```http
DELETE /userMain/delete/1
```
**Resposta**
```http

user delete

```
## Testes Unitários

Neste projeto, foi realizado um conjunto abrangente de testes unitários utilizando JUnit e Mockito. Esses testes foram elaborados para validar cada aspecto da aplicação.

### JUnit e Mockito

- **JUnit:** O JUnit é um framework de teste unitário amplamente utilizado para a linguagem Java. Ele oferece uma estrutura simples e poderosa para escrever e executar testes automatizados.
- **Mockito:** O Mockito é uma biblioteca popular para criação de mocks e stubs em testes unitários. Ele nos permite simular o comportamento de objetos e controlar o resultado das chamadas de métodos, facilitando o teste de componentes isolados.

### Cobertura de Teste

Após a implementação dos testes unitários, realizei uma análise de cobertura para garantir a abrangência dos testes. Fico satisfeito em informar que alcançamos uma cobertura de teste de 100%. Isso significa que todas as partes críticas do código foram testadas e validadas, proporcionando uma confiança adicional na qualidade do software.

### Benefícios dos Testes Unitários

Os testes unitários oferecem uma série de benefícios significativos para o desenvolvimento de software:

- **Detecção Precoce de Problemas:** Os testes unitários nos ajudam a identificar problemas de código precocemente, antes que eles se tornem mais difíceis e custosos de corrigir.
- **Facilidade de Manutenção:** Uma suíte robusta de testes unitários simplifica a manutenção do código, permitindo que façamos alterações com confiança, sabendo que os testes nos alertarão sobre qualquer regressão.
- **Documentação Viva:** Os testes unitários também servem como uma forma de documentação viva do código. Eles descrevem como as várias partes do sistema devem se comportar e podem ajudar os novos membros da equipe a entender o funcionamento do software.

### Conclusão

Os testes unitários são uma parte essencial do processo de desenvolvimento de software. Eles garantem a robustez, confiabilidade e qualidade do código, permitindo que entreguemos produtos de alta qualidade aos nossos usuários finais. Com uma cobertura de teste completa e o uso de ferramentas poderosas como JUnit e Mockito.
