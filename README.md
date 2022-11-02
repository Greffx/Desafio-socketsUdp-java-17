# Desafio Sockets <br><br>

## ☑️Objetivo
Fazer uma aplicação que envie uma mensagem através da rede ethernet usando um  proprietário padrão, e outra que receba uma mensagem enviada pelo aplicativo anterior com o `protocolo proprietário`. Esse aplicativo deve verificar se a mensagem recebida está correta  ou não. Se a mensagem estiver correta, apresentar a mesma na tela e informar que a  mensagem está correta. Se a mensagem não estiver correta, apenas informar que a  mensagem está errada. <br><br>

# ➡️Cliente <br><br>
## 🅿️Protocolo Proprietário
Esse protocolo faz a validação de um byte array, através de um `STX` (start-text/header) e `ETX` (end-text/footer), por exemplo: 
<br><br>![image](https://user-images.githubusercontent.com/101574001/199534789-f9c96e03-0f37-45de-9b16-5affa833c4af.png) <br><br>
A validação é realizada pelo `BCC` (binary cycle check), onde pego cada byte do array de bytes e realizo o <b>XOR</b> do header, mensagem e footer, assim agrupando a sum junto a mensagem.<br><br>![image](https://user-images.githubusercontent.com/101574001/199537237-a5ac67d1-02f5-4411-9be7-aa24684f6392.png)

## 🔄Datagram Socket
Nesse desafio foi utilizado <b>UDP</b>, uma conexão mais simples, onde é necessário um ip, porta, mensagem e o tamanho da mensagem, o qual é enviado através de um pacote para o servidor.<br><br>
![image](https://user-images.githubusercontent.com/101574001/199549240-30f68bb9-2572-47cf-98da-2ebe6e6d154c.png) <br><br>

# ⬅️Servidor <br><br>
Servidor por sua vez, recebe o pacote que o cliente mandou, ele sabe devido a porta que definimos.<br><br>
![image](https://user-images.githubusercontent.com/101574001/199551513-e347ef1d-f78d-4119-87af-112166ee8317.png)<br>

O servidor faz a validação através de um cálculo de <b>XOR</b> novamente, essa função deve ser feita do header até o footer, será a nossa <b>checksum</b> <br><br>
![image](https://user-images.githubusercontent.com/101574001/199559412-cadb3091-ff84-4707-8e70-b8078e24638e.png) <br><br>

E finalmente o servidor faz um for e um if para conferir se a <b>checksum</b> bate com a sum que foi enviada para o servidor junto a mensagem, ou seja, '2mensagem3BCC'. Após e finalmente, um replace para trocar o header, footer e bytes 0 por nada, deixando apenas a mensagem para o cliente. <br><br>
![image](https://user-images.githubusercontent.com/101574001/199559864-f524616a-0097-4368-a9ae-e0425aa8d853.png)  <br><br>


