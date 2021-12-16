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
public class Simulacao_SICREDI_API_Test_Delete {
    // Este Metodo não está deletando o registro
    // Está retornando 200 para qualquer informação numerica passada
    @Test
    public void DeleteSimulacaoPorIdOK_204() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes/66414919004";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .DELETE()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 204);
    }

    @Test
    public void DeleteSimulacaoPorIdNOK_404() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes/9999999999";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .DELETE()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 404);
    }
}
