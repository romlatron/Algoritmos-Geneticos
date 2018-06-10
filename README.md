# Algoritmos Géneticos

Se incluye en el repositorio el `informe.pdf`, la `presentacion.pdf`, el código fuente del proyecto y el compilado `AlgoritmosGeneticos.jar`.

## Run

Para ejecutar el proyecto clone el repositorio y ejecute las siguientes lineas.

```sh
cd AlgoritmosGeneticos
java -jar "./AlgoritmosGeneticos/dist/AlgoritmosGeneticos.jar"
```

## Configuration

Se desarrolló un archivo de configuración modificable que permite la parametrización de los algoritmos genéticos, la selección de métodos y la selección de personajes para realizar distintas pruebas. El archivo de configuración `configuration.properties` contiene toda la documentación necesaria y contiene un ejemplo funcional.

Los archivos de los items deben incluirse en el directorio  `AlgoritmosGeneticos/testdata` con los mismos nombres que los archivos de ejemplo.

## Considerations

* The parameter for the Determinist Tournament selection method should be around 2 and 3.
* `k` should always be smaller than `n`
* Boltzmann function is a linear function with unitary negative slope with `b = to`.
* The replacement methods `all`, `k`, and `mix` correspond to method 1, 2 and 3 respectively.


