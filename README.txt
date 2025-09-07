# CI/CD: Spring Boot → Docker Hub → Kubernetes (Tailored for your app)

## Replace placeholders first
- Replace `<YOUR_DOCKERHUB_USERNAME>` in:
  - Jenkinsfile (IMAGE)
  - k8s/app-deployment.yaml (image)
- In Jenkins, create credentials with ID: `docker-hub-credentials-id` (username/password for Docker Hub).

---
## One-by-one commands (first-time local test)

### Build & run locally (optional sanity check)
mvn clean package -DskipTests
docker build -t <YOUR_DOCKERHUB_USERNAME>/springbootapplication:local .
docker run -p 8080:8080 --rm <YOUR_DOCKERHUB_USERNAME>/springbootapplication:local

### Push to Docker Hub
docker login
docker tag <YOUR_DOCKERHUB_USERNAME>/springbootapplication:local <YOUR_DOCKERHUB_USERNAME>/springbootapplication:latest
docker push <YOUR_DOCKERHUB_USERNAME>/springbootapplication:latest

---
## Kubernetes (using kubectl)

# Create namespace
kubectl create namespace employee-app

# Create MySQL secret + DB + MySQL deployment & service
kubectl -n employee-app apply -f k8s/mysql-secret.yaml
kubectl -n employee-app apply -f k8s/mysql-deployment.yaml

# Deploy application
kubectl -n employee-app apply -f k8s/app-deployment.yaml
kubectl -n employee-app apply -f k8s/app-service.yaml

# Watch rollout
kubectl -n employee-app get pods
kubectl -n employee-app rollout status deployment/employee-app

# Access
# If kind/minikube: use the node IP; else cluster node IP
# URL: http://<NodeIP>:30080  (Swagger: /swagger-ui.html)

---
## Jenkins setup (essential)

1) Install Jenkins (container or VM). Ensure Docker CLI and kubectl are available to Jenkins.
2) Jenkins → Manage Jenkins → Credentials:
   - Add **Username with password** for Docker Hub → ID: `docker-hub-credentials-id`
   - (Optional) Configure KUBECONFIG at `/var/lib/jenkins/.kube/config`
3) Create a Pipeline job pointing to your repo (with this Jenkinsfile and k8s/ directory).
4) Run the job. It will:
   - Build JAR
   - Build & push Docker image (`latest` and build number)
   - Apply K8s manifests on first run
   - Update deployment image on subsequent runs and wait for rollout

---
## Notes
- Default port is 8080 (no `server.port` override found).
- App profile set to `prod` at runtime; DB points to the in-cluster MySQL service.
- For real environments, replace MySQL `emptyDir` with a PersistentVolumeClaim, and **do not** use root/root.
- Actuator health endpoints are enabled for probes; ensure they are exposed in your app.

