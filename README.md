
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
El patrón aplicado fue Adapter, se consideró utilizar Facade porque podría permitir "esconder la complejidad de varios colaboradores en una operación simple". Facade puede incorporar una Adapter en su subsistema, pero el problema es que Facade por si mismo solo proporciona una interfaz simplificada a un grupo complejo de clases, lo cual no es realmente lo que se quiere resolver, se quiere traducir la interfaz de un tercero a la que ya tenemos, asi que seria sobre ingenieria implementar una Facade cuando simplemente necesitamos de un Adapter.

### Necesidad 2 — Emisión de certificados
En este caso se aplico Facade, este caso se trata del acoplamiento que hay entre varios colaboradores y el controlador de certificados, por decirlo de otra forma, el problema es de cuántos colaboradores conoce el cliente y, que además conoce la secuencia de ejecución. Todos los componentes que se utilizan en este flujo son propios del mismo sistema, todos tienen interfaces congruentes, por lo que no existe la necesidad de traducir interfaces entre componentes, diferente al caso de la necesidad 1. Facade encaja en este caso, pues consiste en simplificar al cliente las relaciones complejas entre varias clases, proporcionandole puntos de acceso puntuales al cliente para lo que necesite. Sin embargo, en la necesidad 1 no fue la escogida por no centrarse en la traducción entre módulos o componentes con interfaces diferentes.


### Necesidad 3 — Mejoras opcionales del certificado
Se aplicó el patrón Decorator, se descartó la herencia porque tiene las siguientes limitaciones: La herencia es estática, por lo que no se puede alterar la funcionalidad de un objeto existente durante el tiempo de ejecución. Sólo se puede sustituir el objeto completo por otro creado a partir de una subclase diferente. También está el inconveniente de que en la mayoría de lenguajes, una subclase sólo puede heredar de una sola clase Padre.

En cuanto a la solución con parámetros booleanos, donde dentro de un método se valida y ejecuta según corresponda, resulta inferior a Decorator en este caso, pues la solución con parámetros booleanos rompe con la principio de OCP, cada vez que se quiera extender el código para añadir nuevas mejoras sobre los PDF, se tendrá que modificar el código existente. Mientras Decorator permite extenderlo, sin tocar lo que ya funciona, resultando más beneficioso para la escabilidad del software.


### Necesidad 4 — Control de acceso a la descarga masiva
El patrón que se aplicó fue Proxy, el patrón Decorator previamente usado en la necesidad 3, no encajaba con la necesidad 4 porque en este caso a diferencia de la necesidad 3, se necesita permitir como denegar el acceso al objeto real. Esto por los propósitos diferentes que tienen cada uno, un proxy controla el acceso al objeto original, mientras que un Decorator permite añadir funcionalidades a objetos colocando estos objetos dentro de objetos encapsuladores especiales que contienen estas funcionalidades. Ambos encapsulan el objeto real pero con propósitos diferentes.

Si se usara el patrón Proxy en la necesidad 3, abordaría el problema imponiendo límites o permisos entre envoltorios, lo cual es contraproducente con la idea de libertad de combinaciones posibles entre funcionalidades adicionales. Por otro lado, si se usar Decorator para la necesidad 4, se tendría el problema de que por la naturaleza y propósito de Decorator, si se implementa un verdadero Decorator, no se podría restringir el acceso al objeto real, ejecutando la operación costosa siempre y sin importar que, haciendo inutil aquella solucion al problema.

### Reflexión — Composite y Flyweight (opcional)
Composite se podría utilizar para guardar y desplegar las certificaciones según evento siendo un contenedor los eventos y una hoja los certificados. Flyweight por otro lado, ahora mismo resulta injustificado para la situación actual del programa, ya que no se presentan grandes cantidades de objetos que compartan un mismo estado común, que hagan un estado de almacenamiento común considerablemente beneficioso.

## Herramientas utilizadas
- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- VS Code o IntelliJ IDEA, Git, GitHub

## Conclusiones
Entre los aprendizajes más relevantes están: la práctica de aplicación de patrones de diseño en un proyecto Spring-boot, la aplicación de criterio de elección para decidir entre un patrón y otro, la diferenciación entre propósitos y estructura de patrones de diseño estructural aplicado a un proyecto de Spring-boot, siendo el propósito y estructura facilitadores entre la elección de un patrón u otro. Las partes más complicadas fueron la elección entre patrones similares como Adapter y Facade, o Decorator y Proxy, además la aplicación de los patrones podía tener a veces cierta dificultad, ya que necesitaban algún detalle a tener en cuenta, que sin implementar pueden ser algo difíciles de ver.
