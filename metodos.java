package br.com.metodoscurrency;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class metodos {
    String objson;
    money money;
    ArrayList<money> listOfMoney = new ArrayList<>();
    public void getConversor(String moeda1, String moeda2, double qnt) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/f83c0196d1363dec2ef4c087/pair/" + moeda1 + "/" + moeda2 + "/" + qnt))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        objson = response.body();

        Gson objGson = new Gson();
        recordObj recordObj = objGson.fromJson(objson, recordObj.class);
        money = new money(recordObj);
        money.setAmmount(qnt);
        listOfMoney.add(money);
    }


    public void ListdeCodigo(){
        System.out.println("""
                Código Moeda  |  Nome Moeda     |       País
                ************************************************************************************
                [AED]             UAE Dirha             United Arab Emirates
                [AFN]             Afghan Afgh           Afghanistan
                [ALL]             Albanian Lek          Albania
                [AMD]             Armenian Dr           Armenia
                [ANG]             Netherlands           Netherlands Antilles
                [AOA]             Angolan Kwanza        Angola
                [ARS]             Argentine Pe          Argentina
                [AUD]             Australian Do         Australia
                [AWG]             Aruban Florin         Aruba
                [AZN]             Azerbaijani Ma        Azerbaijan
                [BAM]             Bosnia and H          Bosnia and Herzegovina
                [BBD]             Barbados Dol          Barbados
                [BDT]             Bangladeshi Ta        Bangladesh
                [BGN]             Bulgarian Lev         Bulgaria
                [BHD]             Bahraini Dina         Bahrain
                [BIF]             Burundian Fra         Burundi
                [BMD]             Bermudian Do          Bermuda
                [BND]             Brunei Dollar         Brunei
                [BOB]             Bolivian Bol          Bolivia
                [BRL]             Brazilian Rea         Brazil
                ************************************************************************************
                """);
    }

    public void menu() {
        System.out.println("""
                 (1) - [USD] Dólar ➥ Peso argentino   [ARS]
                 (2) - [ARS] Peso argentino ➥ Dólar   [USD]\s
                 (3) - [USD] Dólar ➥ Real Brasileiro  [BRL]
                 (4) - [BRL] Real Brasileiro ➥ Dólar  [USD]\s
                 (5) - [USD] Dólar ➥ Peso Colombiano  [COP]
                 (6) - [COP] Peso Colombiano ➥ Dólar  [USD]
                 (7) - Histórico de Conversão de Moedas.
                 (8) - Escolher moeda a ser convertida.
                 (9) - Mostra lista de moedas aceitas.
                 (10) - Sair!!
                \s""");
    }

    public void menuOne() {
        System.out.println("""
                *==================================*
                     !!!! Conversor Moedas !!!!
                *==================================*
                """);
    }

    public void menuTwo() {
        if (money != null) {
            System.out.println("*******************************************************\n");
            System.out.printf("""
                    Valor: %2.2f [%s] corresponde ao valor final de =>>> %2.2f [%s]
                    %n""", money.getAmmount(), money.getBase_code(), money.getConversion_result(), money.getTarget_code());
            System.out.println("*******************************************************\n");
        } else {
            System.out.println("Erro: objeto money não foi inicializado.");
        }
    }

    public void mensagem(){
        System.out.println("Digite o valor para conversão");
    }

    @Override
    public String toString() {
        return "[" + money.getBase_code() + "]" +
                " corresponde ao valor final de =>>> " + money.getConversion_result() +
                " [" + money.getTarget_code() + "]";
    }

    public void ListOfMoney(){
        if (listOfMoney != null && !listOfMoney.isEmpty()){
            System.out.println("Histórico de Conversão de moeda\n");
            listOfMoney.forEach(System.out::println);
        } else {
            System.out.println("\nTente converter uma moeda");
        }
    }
}
