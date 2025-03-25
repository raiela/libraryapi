# Library API

## Descrição
A Library API permite o gerenciamento de Autores e Livros, possibilitando operações de cadastro, consulta, atualização e remoção, seguindo regras de negócio bem definidas.

## Contrato da API

### Cadastro de Autor
**Base URI:** `/autores`
- `POST /autores` - Cadastrar um novo autor
- `GET /autores` - Consultar autores
- `GET /autores/{id}` - Consultar um autor específico
- `PUT /autores/{id}` - Atualizar um autor
- `DELETE /autores/{id}` - Remover um autor

### Cadastro de Livros
**Base URI:** `/livros`
- `POST /livros` - Cadastrar um novo livro
- `GET /livros` - Consultar livros com filtros paginados
- `GET /livros/{id}` - Consultar um livro específico
- `PUT /livros/{id}` - Atualizar um livro
- `DELETE /livros/{id}` - Remover um livro

## Regras de Negócio
### Autores
- Um autor não pode ser cadastrado se já existir um autor com o mesmo Nome, Data de Nascimento e Nacionalidade.
- Não é permitido excluir um autor que possua livros cadastrados.

### Livros
- O ISBN de um livro deve ser único.
- Se a Data de Publicação for a partir de 2020, o preço é obrigatório.
- A Data de Publicação não pode ser uma data futura.

## Campos Lógicos
- **ID** - UUID
- **Data Cadastro** - Data de criação do registro
- **Data Ultima Atualização** - Data da última modificação
- **Usuário Ultima Atualização** - Usuário responsável pela última alteração

## Tecnologias e Conceitos Utilizados

### Auditoria com `EnableJpaAuditing`
- Ativar auditoria com `@EnableJpaAuditing`
- Utiliza `@EntityListeners(AuditingEntityListener.class)`
- Campos automáticos: `@CreatedDate` e `@LastModifiedDate`

### Idempotência
- Garantia de que uma requisição repetida produz o mesmo efeito que uma única execução.
- Evita cadastros duplicados e alterações inconsistentes.

### Exceções Personalizadas
- Implementação de exceções personalizadas para melhor tratamento de erros.
- Uso de `@RestControllerAdvice` para capturar e formatar respostas de erro.

### Lombok
- Uso de `@AllArgsConstructor` para gerar automaticamente um construtor com todos os argumentos obrigatórios.
- Redução de boilerplate no código.

### Validação com `@Valid`
- Uso da dependência `spring-boot-starter-validation`.
- Anotações como `@NotNull`, `@Size`, `@Pattern` para validar entrada de dados.

### Pesquisa Dinâmica com `Example`
- Uso da API `Example` do Spring Data JPA para permitir buscas flexíveis.
- Permite consultas dinâmicas sem necessidade de criar múltiplos métodos no repositório.

## Contribuição
- Fork o repositório
- Crie um branch para sua feature
- Envie um pull request

## Autor
Projeto desenvolvido por Raiela Quirino.

Projeto desenvolvido durante o curso "Spring Boot Expert: JPA, REST, JWT, OAuth2 com Docker e AWS" ministrado pelo professor Dougllas Sousa.



