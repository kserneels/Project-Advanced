# Gebruik de specifieke Keycloak versie
FROM quay.io/keycloak/keycloak:26.0.7

# Stel de omgevingsvariabelen voor de Keycloak admin in
ENV KC_BOOTSTRAP_ADMIN_USERNAME=admin
ENV KC_BOOTSTRAP_ADMIN_PASSWORD=admin
ENV KEYCLOAK_HTTP_PORT=8085

# Expose de aangepaste poort
EXPOSE 8085

# Start Keycloak in ontwikkelmodus en gebruik de juiste poort
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port=8085"]
