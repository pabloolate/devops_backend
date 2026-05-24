# EP2 Backend + DB

Repositorio para la capa privada de servicios de la EP2.

## Contenido

- `back-Ventas_SpringBoot/`: microservicio de ventas.
- `back-Despachos_SpringBoot/`: microservicio de despachos.
- `sql/`: script inicial para la base de datos.
- `docker-compose.db.yml`: levanta MySQL en la EC2 DB.
- `docker-compose.backend.yml`: levanta ambos backends en la EC2 Backend usando build local.
- `docker-compose.backend.prod.yml`: compose preparado para usar imágenes desde Docker Hub.

## IPs usadas

- Backend privado: `10.0.2.128`
- DB privada: `10.0.3.204`

## Levantar DB en EC2 DB

```bash
cd /opt/ep2/backend
docker compose -f docker-compose.db.yml up -d
docker compose -f docker-compose.db.yml logs -f mysql
```

Validar:

```bash
docker exec -it ep2_mysql mysql -u ep2_user -pep2_pass ep2_innovatech
SHOW TABLES;
SELECT * FROM venta;
SELECT * FROM despacho;
exit;
```

## Levantar backends en EC2 Backend

```bash
cd /opt/ep2/backend
docker compose -f docker-compose.backend.yml up -d --build
docker ps
```

Validar desde la EC2 Backend:

```bash
curl http://localhost:8080/api/v1/ventas
curl http://localhost:8081/api/v1/despachos
```

Validar desde EC2 Frontend:

```bash
curl http://10.0.2.128:8080/api/v1/ventas
curl http://10.0.2.128:8081/api/v1/despachos
```

## Variables reales de backend

Los backends usan:

```env
DB_ENDPOINT=10.0.3.204
DB_PORT=3306
DB_NAME=ep2_innovatech
DB_USERNAME=ep2_user
DB_PASSWORD=ep2_pass
```

## Nota de seguridad

No subir archivos `.pem`, `.env` reales ni carpetas `target/`.
