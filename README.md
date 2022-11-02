# Desafio Sockets <br><br>

## ☑️Objetivo
Fazer uma aplicação que envie uma mensagem através da rede ethernet usando um  proprietário padrão, e outra que receba uma mensagem enviada pelo aplicativo anterior com o `protocolo proprietário`. Esse aplicativo deve verificar se a mensagem recebida está correta  ou não. Se a mensagem estiver correta, apresentar a mesma na tela e informar que a  mensagem está correta. Se a mensagem não estiver correta, apenas informar que a  mensagem está errada. <br><br>

# Cliente <br><br>
## 🅿️Protocolo Proprietário
Esse protocolo faz a validação de um byte array, através de um `STX` (start-text/header) e `ETX` (end-text/footer), por exemplo: 
<br><br>![image](https://user-images.githubusercontent.com/101574001/199534789-f9c96e03-0f37-45de-9b16-5affa833c4af.png) <br><br>
A validação é realizada pelo `BCC` (binary cycle check), onde pego cada byte do array de bytes e realizo o <b>XOR</b> do header, mensagem e footer, assim agrupando a sum junto a mensagem.<br><br>![image](https://user-images.githubusercontent.com/101574001/199537237-a5ac67d1-02f5-4411-9be7-aa24684f6392.png)

## 🔄Datagram Socket
Nesse desafio foi utilizado <b>UDP</b>, uma conexão mais simples, onde é necessário um ip, porta, mensagem e o tamanho da mensagem, o qual é enviado através de um pacote para o servidor.<br>
