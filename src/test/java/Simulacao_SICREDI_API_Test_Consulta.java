import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 *  autor: Edenilson Teixeira Paschoa
 */
public class Simulacao_SICREDI_API_Test_Consulta {
    @Test
    public void ConsultaSimulacaoOK_200() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 200);
    }



    @Test
    public void ConsultaSimulacaoPorCPFOK_200() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes/66414919004";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 200);

    }


    @Test
    public void ConsultaSimulacaoPorCPFNOK_404() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes/66414919014";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 404);
    }
}

