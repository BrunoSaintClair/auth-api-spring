# Resumo básico da utilização do SpringSecurity nessa aplicação


## Fluxos da aplicação

----------

### Entidade User

Necessita implementar a interface UserDetails e todos os seus métodos.

```getAuthorities()```: retorna a lista de permissões/autoridades do usuário.
```getUsername()```: retorna o identificador do usuário utilizado para autenticação.
```isAccountNonExpired()```: indica se a conta do usuário está expirada.
```isAccountNonLocked()```: indica se a conta está bloqueada.
```isCredentialsNonExpired()```: indica se as credenciais(senha) expiraram. 
```isEnabled()```: indica se o usuário está ativo.

### AuthService

Necessita implementar UserDetailsService e o método loadUserByUsername.

```loadUserByUsername()```: O Spring usa essa função para buscar um usuário no banco de dados pelo seu nome de usuário.

### TokenService

Necessita conter o atributo que irá conter a chave secreta utilizada para criptografar os tokens.

```generateToken(User user)```: retorna o token JWT criado.
```validateToken(String token)```: utiliza o mesmo algoritmo e a mesma chave secreta utilizados na criação para validar se o token existe e não expirou.

### SecurityConfiguration

Precisa ter a annotation @Configuratione(indica que é uma classe de configuração do Spring,
permite que o Spring gerencie os beans definidos) e @EnableWebConfiguration(substitui a configuração padrão de segurança do Spring Security, permitindo personalização.)

Tem como atributo o SecurityFilter.

```securityFilterChain(HttpSecurity httpSecurity)```: é chamado uma única vez, na inicialização do Spring Security, para configurar as regras de segurança.
Ele não é chamado em cada requisição. Necessita receber a annotation @Bean para permitir que o Spring gerencie esse objeto e o disponibilize onde for necessário.
Define regras de segurança para os endpoints da Api. Retorna um SecurityFilterChain, que contém a configuração de segurança da aplicação. 

```authenticationManager(AuthenticationConfiguration authenticationConfiguration)```: esse método cria um AuthenticationManager, que é o responsável por autenticar os usuários no Spring Security.

```passwordEncoder()```: define um PasswordEncoder, que é responsável por criptografar senhas antes de armazená-las no banco de dados.

### SecurityFilter

Necessita extender OncePerRequestFilter e receber a annotation de @Component, para ela ser gerenciada corretamente pelo Spring.

```doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException```:
Implementa o filtro que intercepta todas as requisições da API antes de chegarem nos controllers.

```recoverToken(HttpServletRequest request)```: função que resgata o token passado no header da requisição.