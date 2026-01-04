**SbEcom**

**Project**: A lightweight Spring Boot example e-commerce backend (products & categories) with pagination, sorting, image upload support and JWT-based security scaffolding.

**Features**
- **API Endpoints**: CRUD for categories and products, search, pagination and sorting.
- **Image upload**: Update product image via multipart upload.
- **In-memory DB**: H2 is configured for quick local testing.
- **JWT support**: Security scaffolding is present (JWT utils, filters). Admin endpoints are organized under `/admin`.

**Tech Stack**
- **Framework**: Spring Boot
- **Java**: 21 (see `pom.xml` property `java.version`)
- **DB**: H2 (in-memory)

**Prerequisites**
- Java 21 or newer installed
- Git (optional)

**Configuration**
- Application properties are in `src/main/resources/application.properties`.
- Notable properties:
  - `spring.h2.console.enable=true` (H2 console enabled)
  - `spring.datasource.url=jdbc:h2:mem:test`
  - `project.image= images/` (image storage path used by the app)
  - `spring.app.jwtSecret` and `spring.app.jwtExpirationMs` (JWT configuration)

You can override properties with environment variables or JVM system properties (for example `-Dspring.app.jwtSecret=yourSecret`).

**Build & Run (local)**
From the project root run:

```bash
# Build
./mvnw clean package -DskipTests

# Run (dev)
./mvnw spring-boot:run

# Or run the produced jar
java -jar target/sb-ecom-0.0.1-SNAPSHOT.jar
```

**H2 Console**
- URL: `http://localhost:8080/h2-console`
- JDBC URL (default): `jdbc:h2:mem:test`

**Base Path**: All APIs are prefixed with `/api`

**Available endpoints (summary)**
- `GET  /api/echo?message=...` — simple echo for quick checks

- Category endpoints
  - `GET  /api/public/categories` — list categories (pagination + sorting)
    - Query params: `pageNumber`, `pageSize`, `sortBy`, `sortOrder`
  - `POST /api/public/categories` — create category (JSON body)
  - `PUT  /api/public/categories/{categoryId}` — update category (JSON body)
  - `DELETE /api/admin/categories/{categoryId}` — delete category (admin)

- Product endpoints
  - `POST   /api/admin/categories/{categoryId}/products` — add product to a category (admin)
  - `GET    /api/public/products` — list products (pagination + sorting)
    - Query params: `pageNumber`, `pageSize`, `sortBy`, `sortOrder`
  - `GET    /api/public/categories/{categoryId}/products` — products by category
  - `GET    /api/public/products/keyword/{keyword}` — search products by keyword
  - `PUT    /api/admin/products/{productId}` — update product (admin)
  - `DELETE /api/admin/products/{productId}` — delete product (admin)
  - `PUT    /api/admin/products/{productId}/image` — update product image (multipart form field name: `image`)

Notes:
- Endpoints under `/admin` are intended to be secured — the project includes JWT utilities and filters. You will need to wire an authentication flow (login endpoint) and provide a valid Authorization header `Bearer <token>` to access admin endpoints.

**Example curl requests**

# List products (first page)
```bash
curl -s "http://localhost:8080/api/public/products?pageNumber=0&pageSize=10"
```

# Create a category
```bash
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"title":"Electronics","description":"Gadgets"}' \
  http://localhost:8080/api/public/categories
```

# Upload product image (admin)
```bash
curl -X PUT -H "Authorization: Bearer <TOKEN>" \
  -F "image=@/path/to/image.jpg" \
  http://localhost:8080/api/admin/products/123/image
```

**Testing**
- Unit and integration tests (if present) can be run with:
```bash
./mvnw test
```

**Troubleshooting**
- If you see port conflicts, change `server.port` in `application.properties` or pass `-Dserver.port=8081`.
- If JWT-secured endpoints return 401/403, ensure the token is valid and the secret configured matches the signing secret.

**Next steps / Suggestions**
- Add a login/authentication endpoint to issue JWT tokens for admin flows.
- Add persistence (PostgreSQL / MySQL) configuration for non-volatile data.
- Add API documentation (Swagger / OpenAPI)

**License**
- Add a LICENSE file if you plan to make this public.

Enjoy working on the project — open an issue or ask if you'd like me to add Swagger, seed data, or CI steps.
