
1) Prerequisitos

- MySQL version 5.6 o superior
- JDK 1.8 o superior
- Maven 3.2 o superior
- (Opcional) Intellij IDEA

Nota: Para resolver la conexion a la base de datos y la exposición del Service Restful utilice el framework 'Spring boot' el cual facilita el desarrollo ya que no necesita de ningún appService (debido a que esta embebido)

2) Creado de la base de datos

Ir a una terminal de linux/mac y ejecutar los siguientes comandos.

$ mysql -uroot -p
# Colocar el password de root

mysql> create database forecast_db; -- Crea la nueva base de datos
mysql> create user 'forecast'@'%' identified by 'Forecast321!'; -- Crea un usuario
mysql> grant all privileges on forecast_db.* to 'forecast'@'localhost' IDENTIFIED BY 'Forecast321!' with grant option; -- Se setean los privilegios al nuevo usuario en la nueva base de datos

3) Configuracion de la base de datos y compilación de los proyectos

La solucion esta dividida en tres proyectos distintos

3.1) Core: Donde se concentran las clases que resuelven los principales problemas
$ mvn clean package install

3.2) Database: Capa que persiste la información en la base de datos
- Configurar el acceso a la base de datos en el archivo $HOME/database/src/main/resources/application.properties
# Connect to mysql
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:mysql://localhost:3306/forecast_db
spring.datasource.username=forecast
spring.datasource.password=Forecast321!

La configuración de la base en esta versión es sencilla pero se le puede agregar propiedades para optimizar la conexión 

$ mvn clean package

3.3) ForecastApiRest: Capa que expone una Api Rest para la consulta del clima
$ mvn clean package

4) Ejecucion del proceso Batch

- Una vez compilado los proyectos 'core' y 'database' ejecutar el siguiente comando:

cd $HOME/database/java -jar target/batch-database-0.0.1-SNAPSHOT.jar

Ejecutar las siguientes queries logueado con el usuario forecast

$ mysql -uforecast -pForecast321!
mysql> use forecast_db;
mysql> use forecast_db;

mysql> select weather, count(*) from forecast group by weather;
+----------+----------+
| weather  | count(*) |
+----------+----------+
| INVALIDO |     2762 |
| LLUVIA   |      847 |
| SEQUIA   |       40 |
+----------+----------+
3 rows in set (0.04 sec)

mysql> select day_number, day_timestamp, max(perimeter) from forecast where weather='LLUVIA';
+------------+---------------------+----------------+
| day_number | day_timestamp       | max(perimeter) |
+------------+---------------------+----------------+
|         23 | 2019-01-30 00:00:00 |         6262.3 |
+------------+---------------------+----------------+
1 row in set (0.01 sec)

mysql> select * from forecast where perimeter = (select max(perimeter) from forecast where weather='LLUVIA');
+------+------------+---------------------+-----------+---------+
| id   | day_number | day_timestamp       | perimeter | weather |
+------+------------+---------------------+-----------+---------+
|   75 |         72 | 2019-03-20 00:00:00 |    6262.3 | LLUVIA  |
|  111 |        108 | 2019-04-25 00:00:00 |    6262.3 | LLUVIA  |
|  255 |        252 | 2019-09-16 00:00:00 |    6262.3 | LLUVIA  |
|  291 |        288 | 2019-10-22 00:00:00 |    6262.3 | LLUVIA  |
|  435 |        432 | 2020-03-14 00:00:00 |    6262.3 | LLUVIA  |
|  471 |        468 | 2020-04-19 00:00:00 |    6262.3 | LLUVIA  |
|  615 |        612 | 2020-09-10 00:00:00 |    6262.3 | LLUVIA  |
|  651 |        648 | 2020-10-16 00:00:00 |    6262.3 | LLUVIA  |
|  795 |        792 | 2021-03-09 00:00:00 |    6262.3 | LLUVIA  |
|  831 |        828 | 2021-04-14 00:00:00 |    6262.3 | LLUVIA  |
|  975 |        972 | 2021-09-05 00:00:00 |    6262.3 | LLUVIA  |
| 1011 |       1008 | 2021-10-11 00:00:00 |    6262.3 | LLUVIA  |
| 1155 |       1152 | 2022-03-04 00:00:00 |    6262.3 | LLUVIA  |
| 1191 |       1188 | 2022-04-09 00:00:00 |    6262.3 | LLUVIA  |
| 1335 |       1332 | 2022-08-31 00:00:00 |    6262.3 | LLUVIA  |
| 1371 |       1368 | 2022-10-06 00:00:00 |    6262.3 | LLUVIA  |
| 1515 |       1512 | 2023-02-27 00:00:00 |    6262.3 | LLUVIA  |
| 1551 |       1548 | 2023-04-04 00:00:00 |    6262.3 | LLUVIA  |
| 1695 |       1692 | 2023-08-26 00:00:00 |    6262.3 | LLUVIA  |
| 1731 |       1728 | 2023-10-01 00:00:00 |    6262.3 | LLUVIA  |
| 1875 |       1872 | 2024-02-22 00:00:00 |    6262.3 | LLUVIA  |
| 1911 |       1908 | 2024-03-29 00:00:00 |    6262.3 | LLUVIA  |
| 2055 |       2052 | 2024-08-20 00:00:00 |    6262.3 | LLUVIA  |
| 2091 |       2088 | 2024-09-25 00:00:00 |    6262.3 | LLUVIA  |
| 2235 |       2232 | 2025-02-16 00:00:00 |    6262.3 | LLUVIA  |
| 2271 |       2268 | 2025-03-24 00:00:00 |    6262.3 | LLUVIA  |
| 2415 |       2412 | 2025-08-15 00:00:00 |    6262.3 | LLUVIA  |
| 2451 |       2448 | 2025-09-20 00:00:00 |    6262.3 | LLUVIA  |
| 2595 |       2592 | 2026-02-11 00:00:00 |    6262.3 | LLUVIA  |
| 2631 |       2628 | 2026-03-19 00:00:00 |    6262.3 | LLUVIA  |
| 2775 |       2772 | 2026-08-10 00:00:00 |    6262.3 | LLUVIA  |
| 2811 |       2808 | 2026-09-15 00:00:00 |    6262.3 | LLUVIA  |
| 2955 |       2952 | 2027-02-06 00:00:00 |    6262.3 | LLUVIA  |
| 2991 |       2988 | 2027-03-14 00:00:00 |    6262.3 | LLUVIA  |
| 3135 |       3132 | 2027-08-05 00:00:00 |    6262.3 | LLUVIA  |
| 3171 |       3168 | 2027-09-10 00:00:00 |    6262.3 | LLUVIA  |
| 3315 |       3312 | 2028-02-01 00:00:00 |    6262.3 | LLUVIA  |
| 3351 |       3348 | 2028-03-08 00:00:00 |    6262.3 | LLUVIA  |
| 3495 |       3492 | 2028-07-30 00:00:00 |    6262.3 | LLUVIA  |
| 3531 |       3528 | 2028-09-04 00:00:00 |    6262.3 | LLUVIA  |
+------+------------+---------------------+-----------+---------+
40 rows in set (0.01 sec)

mysql> select * from planet;
+----+--------------+-------------------+------------------+----------+
| id | civilization | distance_from_sun | sence            | velocity |
+----+--------------+-------------------+------------------+----------+
|  1 | Ferengi      |               500 | CLOCKWISE        |        1 |
|  2 | Betazoide    |              2000 | CLOCKWISE        |        2 |
|  3 | Vulcano      |              1000 | COUNTERCLOCKWISE |        5 |
+----+--------------+-------------------+------------------+----------+
3 rows in set (0.00 sec)

TODO: La idea es cargar los planetas desde la base de datos por si hay que modificar algun parametro de inicio. No llegue a implementar eso

5) Ejecutar el Servicio Restful

- Una vez compilados los proyectos 'core' y 'forecastApiRest' ejecutar el siguiente comando:

 $ java -jar target/forecastApiRest-0.0.1-SNAPSHOT.jar
   .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.1.RELEASE)

2019-01-08 11:50:29.848  INFO 46664 --- [           main] c.m.f.ForecastApiRestApplication         : Starting ForecastApiRestApplication v0.0.1-SNAPSHOT on Diegos-MacBook-Pro.local with PID 46664 (/Users/diego.benitez/forecast/forecastApiRest/target/forecastApiRest-0.0.1-SNAPSHOT.jar started by diego.benitez in /Users/diego.benitez/forecast/forecastApiRest)
2019-01-08 11:50:29.852  INFO 46664 --- [           main] c.m.f.ForecastApiRestApplication         : No active profile set, falling back to default profiles: default
2019-01-08 11:50:30.889  INFO 46664 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2019-01-08 11:50:30.917  INFO 46664 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-01-08 11:50:30.918  INFO 46664 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/9.0.13
2019-01-08 11:50:30.935  INFO 46664 --- [           main] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: [/Users/diego.benitez/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.]
2019-01-08 11:50:31.020  INFO 46664 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-01-08 11:50:31.020  INFO 46664 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1114 ms
2019-01-08 11:50:31.247  INFO 46664 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-01-08 11:50:31.509  INFO 46664 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-01-08 11:50:31.513  INFO 46664 --- [           main] c.m.f.ForecastApiRestApplication         : Started ForecastApiRestApplication in 17.151 seconds (JVM running for 17.675)

- Una vez que arranque consultar el estado de cualquier dia ejecutando la siguiente url;

Ej: 
	GET → http://localhost:8080/clima?dia=566 → Respuesta: {dia: 566, clima: "LLUVIA"}
	GET → http://localhost:8080/clima?dia=72 → Respuesta: {dia: 72, clima: "LLUVIA"}
	GET → http://localhost:8080/clima?dia=1368 → Respuesta: {dia: 1368, clima: "LLUVIA"}
	GET → http://localhost:8080/clima?dia=1170 → Respuesta: {dia: 1170, clima: "SEQUIA"}
	GET → http://localhost:8080/clima?dia=1 → Respuesta: {dia: 1, clima: "INVALIDO"}
	