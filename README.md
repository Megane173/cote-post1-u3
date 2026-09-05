# Post-contenido — Unidad 3: Patrones Estructurales en ConfUDES

## Descripción
Repositorio del post-contenido de la Unidad 3 de Patrones de Diseño
de Software. Un único proyecto Spring Boot (confudes-patrones-
estructurales) que resuelve cuatro necesidades reales del backend
de ConfUDES, una plataforma de gestión de congresos académicos:
registro de asistencia con un proveedor externo, emisión de
certificados, mejoras opcionales sobre el certificado emitido y
control de acceso a la descarga masiva.

## Cómo ejecutar
```
$ mvn clean package
$ mvn spring-boot:run
$ mvn test
```

## Decisiones de diseño

### Necesidad 1 — Registro de asistencia
El patron aplicado fue Adapter, se considero utilizar Facade porque podria permitir "esconder la complejidad de varios colaboradores en una operacion simple". Facade puede incorporar una Adapter en su subsistema, pero el problema es que Facade por si mismo solo proporciona una interfaz simplificada a un grupo complejo de clases, lo cual no es realmente lo que se quiere resolver, se quiere traducir la interfaz de un tercero a la que ya tenemos, asi que seria sobreingenieria implementar una Facade cuando simplemente necesitamos de un Adapter.

### Necesidad 2 — Emisión de certificados
En este caso se aplico Facade, este caso se trata del acoplamiento que hay entre varios colaboradores y el controlador de certificados, por decirlo de otra forma, el problema es de cuántos colaboradores conoce el cliente y, que ademas conoce la secuencia de ejecución. Todos los componentes que se utilizan en este flujo son propios del mismo sistema, todos tienen interfaces congruentes, por lo que no existe la necesidad de traducir interfaces entre componentes, diferente al caso de la necesidad 1. Facade encaja en este caso, pues consiste en simplificar al cliente las relaciones complejas entre varias clases, proporcionandole puntos de acceso puntuales al cliente para lo que necesite. Sin embargo, en la necesidad 1 no fue la escogida por no centrarse en la traduccion entre modulos o componentes con interfaces diferentes.


### Necesidad 3 — Mejoras opcionales del certificado
[Qué patrón se aplicó, por qué se descartaron la herencia y los
parámetros booleanos, y por qué el patrón usado en la Necesidad 4 no
serviría aquí.]

### Necesidad 4 — Control de acceso a la descarga masiva
[Qué patrón se aplicó, por qué se descartó el patrón usado en la
Necesidad 3 a pesar de la similitud estructural, y cómo la solución
evita ejecutar la operación costosa cuando el usuario no tiene
permiso.]

### Reflexión — Composite y Flyweight (opcional)
[Una o dos frases sobre dónde encajaría cada uno en ConfUDES, o por
qué no aplican en este proyecto.]

## Herramientas utilizadas
- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- VS Code o IntelliJ IDEA, Git, GitHub

## Conclusiones
[Párrafo de 3-5 oraciones con los aprendizajes más relevantes de
ambas partes, incluyendo qué hizo difícil o fácil decidir entre
patrones cercanos.]
