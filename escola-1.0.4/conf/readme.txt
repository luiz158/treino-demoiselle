Para funcionamento da aplicação nos servidores tomcat a JBoss
faça os sequintes procedimentos:

******** JBoss ******** 
	Criar arquivos na pasta /jboss-4.2.3.GA/server/default/conf
		defaultRoles.properties			
		defaultUsers.properties
		
******** Tomcat ********
	No arquivo "tomcat-users.xml" inserir o seguinte usuário.	  
	<user username="user" password="user" roles="roler_test"/> 
		