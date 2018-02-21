/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;

public class DeveloperTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8888);

    @Test
    public void testWriteCode() {
        Developer sut = new Developer();
        assertEquals("Code, code, code", sut.writeCode());
    }

    @Test
    public void testSend() {
        WireMock wiremock = new WireMock("localhost", 8888);
        wiremock.register(post(urlEqualTo("/BigCompany"))
                .withRequestBody(containing("me"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("0")));

        Company ordina = new Company(new JavaDeveloper("me"));
        ordina.send(new Contractor("BigCompany"));

        wiremock.verifyThat(postRequestedFor(urlEqualTo("/BigCompany")));
    }

}
