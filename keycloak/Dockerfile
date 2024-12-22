# Use the official Keycloak image
FROM quay.io/keycloak/keycloak:26.0.7

# Set environment variables for the Keycloak admin
ENV KC_BOOTSTRAP_ADMIN_USERNAME=admin
ENV KC_BOOTSTRAP_ADMIN_PASSWORD=admin

# Expose the port
EXPOSE 8085

# Set the entrypoint to start the server in development mode
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
