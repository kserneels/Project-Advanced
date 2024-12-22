# Use the official Keycloak image
FROM quay.io/keycloak/keycloak:26.0.7

# Set environment variables for the Keycloak admin
ENV KC_BOOTSTRAP_ADMIN_USERNAME=admin
ENV KC_BOOTSTRAP_ADMIN_PASSWORD=admin
ENV KEYCLOAK_HTTP_PORT=8085

# Copy the realm-export.json to the container
COPY ./realm-export.json /opt/keycloak/data/import/realm-export.json

# Expose the port
EXPOSE 8085

# Correct the command: No need to repeat kc.sh
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port=8085", "--import-realm"]
