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
public class Simulacao_SICREDI_API_Test_Alteracao {
    @Test
    public void AlterarSimulacaoOK_200() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes/36843012809";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012809,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 200);

    }

    @Test
    public void AlterarSimulacaoCPFVazioOK_200() throws URISyntaxException, IOException, InterruptedException {
        // A API está retornando a menssagem de erro coreta no body porém o status está como 400
        var url = "http://localhost:8080/api/v1/simulacoes/36843012809";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 200);

    }

    @Test
    public void AlterarSimulacaoCPFNNumericoNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // Sem mensagem de erro
        var url = "http://localhost:8080/api/v1/simulacoes/36843012809";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"cpf\": 368.430.128-09,\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400);

    }


    @Test
    public void AlterarSimulacaoEmailNullNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // Sem mensagem de erro
        var url = "http://localhost:8080/api/v1/simulacoes/36843012809";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012819,\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400 && response.body().contains("E-mail não deve ser vazio"));
        //Sem Validação
    }


    @Test
    public void AlterarSimulacaoNomeNullNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // Sem mensagem de erro
        var url = "http://localhost:8080/api/v1/simulacoes/36843012819";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"cpf\": 36843012819,\n" +
                        "  \"valor\": 1200,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400 && response.body().contains("Nome não pode ser vazio"));
        // Sem validação
    }

    @Test
    public void AlterarSimulacaoNaoEncontradaNOK_404() throws URISyntaxException, IOException, InterruptedException {
        var url = "http://localhost:8080/api/v1/simulacoes/36843012809";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012809,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 404  && response.body().contains("CPF 36843012809 não encontrado"));

    }


    @Test
    public void AlterarSimulacaoLimiteMenorNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // A API está retornando a menssagem de erro coreta no body porém o status está como 400
        var url = "http://localhost:8080/api/v1/simulacoes/36843012999";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012999,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 999,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400  && response.body().contains("Valor não pode ser menor que 1000"));
        // Validação não efetuada
    }

    @Test
    public void AlterarSimulacaoLimiteMaiorNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // A API está retornando a menssagem de erro coreta no body porém o status está como 400
        var url = "http://localhost:8080/api/v1/simulacoes/36843012899";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012899,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 41000,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400  && response.body().contains("Valor deve ser menor ou igual a R$ 40.000"));
        // Validação não efetuada
    }


    @Test
    public void AlterarSimulacaoLimiteMenorParcelasNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // A API está retornando a menssagem de erro coreta no body porém o status está como 400
        var url = "http://localhost:8080/api/v1/simulacoes/36843012999";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012999,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1000,\n" +
                        "  \"parcelas\": 1,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400  && response.body().contains("Parcelas deve ser igual ou maior que 2"));
    }


    @Test
    public void AlterarSimulacaoLimiteMaiorParcelasNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // A API está retornando a menssagem de erro coreta no body porém o status está como 400
        var url = "http://localhost:8080/api/v1/simulacoes/36843012999";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012999,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1000,\n" +
                        "  \"parcelas\": 49,\n" +
                        "  \"seguro\": true\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400  && response.body().contains("Parcelas deve ser igual ou menor que 48"));
        // Validação não efetuada
    }


    @Test
    public void AlterarSimulacaoSeguroNullNOK_400() throws URISyntaxException, IOException, InterruptedException {
        // A API está retornando a menssagem de erro coreta no body porém o status está como 400
        var url = "http://localhost:8080/api/v1/simulacoes/36843012999";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"nome\": \"Edenilson\",\n" +
                        "  \"cpf\": 36843012999,\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 1000,\n" +
                        "  \"parcelas\": 49,\n" +
                        "}"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assert (response.statusCode() == 400  && response.body().contains("Seguro não pode ser nulo"));
        // Validação não efetuada
    }
}
