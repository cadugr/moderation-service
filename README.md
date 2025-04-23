
# Moderation Service

Este microsserviço faz parte do sistema **AlgaComments**, desenvolvido como desafio do curso **Especialista Microsserviços** da **Algaworks**.  
Sua responsabilidade é validar comentários recebidos por meio da verificação de palavras proibidas. Os comentários aprovados são então persistidos pelo `comment-service`.

## 📌 Funcionalidade

- Validação de comentários com base em uma lista fixa de palavras proibidas: `["ódio", "xingamento"]`
- Exposição de um endpoint REST para moderação
- Comunicação síncrona (via HTTP/REST) com o `comment-service`

## 🚀 Execução

O projeto está configurado para ser executado na porta **8080**.

### Via IntelliJ

1. Abra o projeto no IntelliJ
2. Aguarde o carregamento do Gradle
3. Localize a classe `ModerationServiceApplication.java` e execute como uma aplicação Spring Boot

### Via Terminal

```bash
./gradlew bootRun
```

Ou, para compilar e executar o `.jar` via terminal:

```bash
./gradlew build
java -jar build/libs/moderation-service-0.0.1-SNAPSHOT.jar
```

## 📡 Endpoint

### Verificar moderação de comentário

```bash
curl --location 'http://localhost:8080/api/moderate' \
--header 'Content-Type: application/json' \
--data '{
  "text": "Este é mais um novo comentário sobre com muito ódio.",
  "commentId": "ac020698-0827-495d-afa0-0abc0cfb00c6"
}'
```

### Exemplos de resposta:

```json
{
  "approved": false,
  "reason": "the text contains the following prohibited words: ódio | xingamento"
}
```

Ou, se aprovado:

```json
{
  "approved": true,
  "reason": "no prohibited words found."
}
```

Em ambos os casos a resposta será 200 (OK).

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Gradle
