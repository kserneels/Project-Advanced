# Use the specific Keycloak version
FROM quay.io/keycloak/keycloak:26.0.7

# Set environment variables for the Keycloak admin user
ENV KC_BOOTSTRAP_ADMIN_USERNAME=admin
ENV KC_BOOTSTRAP_ADMIN_PASSWORD=admin
ENV KC_HTTP_PORT=8085

# Copy the realm-export.json into the container
COPY keycloak/realm-export.json /opt/keycloak/data/import/realm-export.json

# Expose the custom port
EXPOSE 8085

# Start Keycloak in development mode with the specified port
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port=8085"]
