# SpriteCompiler
Projeto de um compilador de Sprites 2D.

O objetivo deste sistema é gerar Sprite 2D a partir de um conjunto de imagens. As imagens seguem uma estrutura de arquivos pré-estabelecida, que seguem as seguintes regras:
* O projeto de um sprite fica contido em uma pasta qualquer, definida como pasta principal do projeto.
* Dentro desta pasta há todos os arquivos de imagem que compõe os frames, além de uma pasta chamada "sprite".
* Os arquivos de imagem devem ter uma extensão reconhecível pelo sistema, a princípio definidas como sendo apenas as extensoes ".BMP" e ".PNG".
* É na pasta "sprite" onde é gerado o Sprite 2D, no formato ".PNG", contendo todos os frames, além de um arquivo de configuração que armazena informações do Sprite. ambos os arquivos possuem o mesmo nome da pasta principal. O arquivo de configurações é criado com uma extensão ".STT" (Sprite Settings).
