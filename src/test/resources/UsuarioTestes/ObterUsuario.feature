# language: pt

@ObterUsuarioTeste
Funcionalidade: Testar se ha usuario cadastrado com os dados a seguir.
O sistema deve obter o usuario atraves de seu numero de cpf.

Esquema do Cenario: Obter usuario por CPF
  Dado pesquisa do usuario com cpf <cpf>
  Entao retorna "<nome>"
    
Exemplos: 
|    cpf   |             nome               |
|8468331406|Douglas Fernandes da Silva Filho|