<table>
	<tr>
		<th>dependency</th>
		<th>version</th>
		<th>url</th>
	</tr>
	<tr>
		<td>Arquillian</td><td>1.8.0.Final</td><td>https://arquillian.org</td>
	</tr>
	<tr>
		<td>Jakarta Web API</td><td>10.0.0</td><td>https://jakarta.ee/specifications/webprofile/10/</td>
	</tr>
	<tr>
		<td>Jakarta JSTL</td><td>3.0.0</td><td>https://jakarta.ee/specifications/tags/3.0/</td>
	</tr>
	<tr>
		<td>Jakarta Persistence</td><td>3.1</td><td>https://jakarta.ee/specifications/persistence/3.1/</td>
	</tr>
	<tr>
		<td>Eclipselink</td><td>4.0.2</td><td>https://eclipse.dev/eclipselink/</td>
	</tr>
	<tr>
		<td>MySql Connector</td><td>8.0.33</td><td>https://www.mysql.com/products/connector/</td>
	</tr>
	<tr>
		<td>H2 database</td><td>1.4.200<br/><i>(pending 2.2.224)</i></td><td>https://www.h2database.com/html/main.html</td>
	</tr>
	<tr>
		<td>Arquillian Payara Server Embedded</td><td>3.0.alpha8</td><td><a href="https://docs.payara.fish/community/docs/Technical%20Documentation/Ecosystem/Connector%20Suites/Arquillian%20Containers/Payara%20Server%20Embedded.html">Payara Server Embedded Arquillian Container</a></td>
	</tr>
		<tr>
		<td>Payara Server Embedded</td><td>6.2024.1</td><td><a href="https://docs.payara.fish/community/docs/Technical%20Documentation/Payara%20Server%20Documentation/Payara%20Server%20Embedded.html">Payara Server Embedded</a></td>
	</tr>
</table>
Tested on Payara Community Server 6.2024.1 (Full) (https://www.payara.fish/downloads/payara-platform-community-edition/)
<br/>
Add the following line to VM arguments: 
<pre>-Djdk.util.zip.disableZip64ExtraFieldValidation=true -Djdk.attach.allowAttachSelf=true --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/sun.net.www.protocol.jar=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.naming/javax.naming.spi=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED</pre>