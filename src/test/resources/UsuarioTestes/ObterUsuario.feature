# language: pt

@ObterUsuarioTeste
Funcionalidade: Testar se ha usuario cadastrado com os dados a seguir.
  O sistema deve obter o usuario atraves de seu nome e validar seu email.

  Esquema do Cenario: Obter usuario por nome e validar email
    Dado usuario com nome "<nome>"
    Entao verifica seu email "<email>"

    Exemplos:
      | nome          | email                    |
      | Administrador | douglasf.filho@gmail.com |