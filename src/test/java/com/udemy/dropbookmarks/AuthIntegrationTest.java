package com.udemy.dropbookmarks;


import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class AuthIntegrationTest {

    private static final String CONFIG_PATH
            = "config.yml";

    @ClassRule
    public static final DropwizardAppRule<DropBookmarksConfiguration> RULE
            = new DropwizardAppRule<>(DropBookmarksApplication.class,
            CONFIG_PATH);

    private static final HttpAuthenticationFeature FEATURE
            = HttpAuthenticationFeature.basic("username","password");

    private static final String TARGET
            = "https://localhost:8443";

    private static final String PATH
            = "/hello/secured";

    private static final String  TRUST_STORE_FILE_NAME
            = "dropbookmarks.keystore";

    private static final String TRUST_STORE_FILE_PASSWORD
            = "password";

    private Client client;

    @Before
    public void setUp() {
        SslConfigurator configurator = SslConfigurator.newInstance();
            configurator.trustStoreFile(TRUST_STORE_FILE_NAME)
                        .trustStorePassword(TRUST_STORE_FILE_PASSWORD);
        SSLContext context = configurator.createSSLContext();
        client = ClientBuilder.newBuilder()
                    .sslContext(context)
                    .build();
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void testUnauthorisedPath() {
        Response response = client
                .target(TARGET)
                .path(PATH)
                .request()
                .get();

        assertEquals(Response.Status
                .UNAUTHORIZED.getStatusCode(),
                response.getStatus());
    }

    @Test
    public void testAuthorisedPath() {
        String expected = "Hello Secured World";
        client.register(FEATURE);
        String actual = client
                .target(TARGET)
                .path(PATH)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        assertEquals(expected,actual);

    }

}
