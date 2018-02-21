package model;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

class Company {

    private Client client = ClientBuilder.newClient();
    private JavaDeveloper javaDeveloper;

    public Company(JavaDeveloper javaDeveloper) {
        this.javaDeveloper = javaDeveloper;
    }

    public String send(final Contractor contractor) {
        final WebTarget webTarget = this.client.target("http://localhost:8888/{contractor}").resolveTemplate("contractor", contractor.getName());

        final Response response = webTarget.request("application/json").post(Entity.json(this.javaDeveloper), Response.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP Response: " + response.getStatus());
        }
        return response.readEntity(String.class);
    }

}
