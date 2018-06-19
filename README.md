Manual de usuario


El sistema consiste en tres componentes: Aeroperu, el programa backend, api-front, donde ocurre el frontend, y la librería esq que contiene recursos comunes.

aeroperu está configurado para inicializarse en el puerto 8888 de localhost, y tiene controladores REST para agregar, modificar y borrar aereopuertos (usando la url de localhost "http://localhost:8888/airport/"), rutas aereas ("http://localhost:8888/route/"), cabinas ("http://localhost:8888/cabin/") y ofertas de viaje ("http://localhost:8888/offer/"), que indican relaciones de rutas y cabinas con precios asignados durante períodos de tiempo determinados. La base de datos se llama 'world' y se accede bajo el usuario 'root'.

api-front está configurado para inicializarse en el puerto 8080, y tiene 4 urls.

"http://localhost:8080/airport/" devuelve la lista completa de aereopuertos, o se puede buscar uno específico filtrandolo por su código IATA en "http://localhost:8080/airport/{iata}"

"http://localhost:8080/offer/{iata}/{iata2}/{date}" devuelve ofertas para todas las rutas que van desde un aereopuerto con código 'iata' a uno con código 'iata2' disponibles en una fecha determinada (que se especifica en el formato 'dd-mm-aaaa'). Lo que ésto hace es comparar el input de fecha con las fechas de inicio y finalización de las ofertas,
y devolver las que estén entre medio de ambas. Las ofertas desponen de un filtro adicional en la url "http://localhost:8080/offer/{iata}/{iata2}/{date}/{cabin}". Allí se consulta por un id para que las ofertas retornadas correspondan a una cabina específica.
