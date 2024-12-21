# Gebruik de officiële Keycloak image als basis
FROM quay.io/keycloak/keycloak:latest

# Zet de juiste Keycloak instellingen (kan aangepast worden)
ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin

# Verander de poort naar 8085
ENV KEYCLOAK_HTTP_PORT=8085

# Expose de aangepaste poort
EXPOSE 8085 8085

# Start Keycloak in ontwikkelmodus
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
