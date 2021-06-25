## 1 application.properties

server.servlet.context-path=/api
server.port=8083

spring.datasource.url=jdbc:mysql://localhost/ws_comments?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=create
logging.level.org.hibernate=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.org.hibernate.cache=DEBUG
logging.level.org.hibernate.stat=DEBUG
spring.jpa.show-sql=false

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
