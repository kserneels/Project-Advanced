FROM quay.io/keycloak/keycloak:26.0.7

# Set environment variables for Keycloak admin
ENV KC_BOOTSTRAP_ADMIN_USERNAME=admin
ENV KC_BOOTSTRAP_ADMIN_PASSWORD=admin
ENV KEYCLOAK_HTTP_PORT=8085

# Expose the custom port
EXPOSE 8085

# Copy realm-export.json into the container
COPY ./keycloak/realm-export.json /opt/keycloak/data/import/realm-export.json

# Start Keycloak in development mode with realm import
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port=8085", "--import-realm"]
