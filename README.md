# Library API

## Descrição
API para cadastro e gerenciamento de autores e livros, incluindo funcionalidades de consulta, adição, atualização e remoção.

## Contrato da API
**Endpoints:**
- `/autores` - Gerenciamento de autores
- `/livros` - Gerenciamento de livros

## Cadastro de Autor

### Regras de Negócio
- Não permitir cadastrar um autor com mesmo Nome, Data de Nascimento e Nacionalidade.
- Não permitir excluir um autor que possuir algum livro.

### Campos
- Nome (*Obrigatório*)
- Data de Nascimento (*Obrigatório*)
- Nacionalidade (*Obrigatório*)
- ID (UUID, gerado automaticamente)
- Data Cadastro
- Data Última Atualização
- Usuário Última Atualização

## Cadastro de Livros

### Regras de Negócio
- Não permitir cadastrar um Livro com mesmo ISBN que outro.
- Se a data de publicação for a partir de 2020, deverá ter o preço informado obrigatoriamente.
- Data de publicação não pode ser uma data futura.

### Campos
- ISBN (*Obrigatório*)
- Título (*Obrigatório*)
- Data de Publicação (*Obrigatório*)
- Gênero
- Preço
- Autor (*Obrigatório*)
- ID (UUID, gerado automaticamente)
- Data Cadastro
- Data Última Atualização
- Usuário Última Atualização

---
## Tecnologias e Conceitos Utilizados

### @EnableJpaAuditing
- Permite auditar entidades automaticamente.
- Utiliza `@EntityListeners(AuditingEntityListener.class)`.
- Anotações `@CreatedDate` e `@LastModifiedDate` para registrar data de criação e última modificação.

### Idempotência
- Conceito que garante que a mesma requisição executada múltiplas vezes tenha o mesmo efeito.
- Aplicado em operações de escrita para evitar efeitos colaterais indesejados.

### Exceções Personalizadas
- Uso de `@RestControllerAdvice` para centralizar o tratamento de exceções.
- Definição de classes de erro personalizadas para padronizar respostas.

### Lombok
- Uso de `@AllArgsConstructor` para gerar automaticamente um construtor com todos os argumentos obrigatórios.
- Redução de boilerplate no código.
- Uso de `@RequiredArgsConstructor` para gerar automaticamente construtores que incluam variáveis declaradas como obrigatórias.

### Validação com `@Valid`
- Uso da dependência `spring-boot-starter-validation`.
- Permite validação automática de entidades e DTOs.

### Pesquisa Dinâmica com Example
- Utiliza `ExampleMatcher` para criar consultas dinâmicas e flexíveis.
- Permite buscar registros baseando-se nos valores informados em um objeto de exemplo.

### MapStruct
- Framework para mapeamento automático entre objetos.
- Reduz necessidade de conversões manuais entre DTOs e entidades.
- **Exemplo de Uso:**
```java
@Mapper(componentModel = "spring")
public interface AutorMapper {
    AutorDTO toDto(Autor autor);
    Autor toEntity(AutorDTO autorDTO);
}
```

### Supplier
- Interface funcional do Java que fornece valores sob demanda.
- **Exemplo de Uso:**
```java
Supplier<LocalDateTime> dataAtual = LocalDateTime::now;
System.out.println("Data Atual: " + dataAtual.get());
```

### Métodos de Referência
- Simplifica chamadas a métodos estáticos ou de instância.
- **Exemplo de Uso:**
```java
List<String> nomes = List.of("Ana", "Bruno", "Carlos");
nomes.forEach(System.out::println);
```

---

Se desejar adicionar mais detalhes ou modificar algo, me avise! 🚀

