package utils;

import com.google.gson.Gson;
import service.Exchange;
import service.ExchangeRate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class JsonReader {








    public List<ExchangeRate> parseJson(String lastDate) throws IOException {





        int year = Integer.parseInt(lastDate.charAt(6) +""+ lastDate.charAt(7)+""+ lastDate.charAt(8) +""+ lastDate.charAt(9));
        int month = Integer.parseInt(lastDate.charAt(3) +""+lastDate.charAt(4));
        int day  = Integer.parseInt(lastDate.charAt(0)+""+lastDate.charAt(1));


        LocalDate localDate = LocalDate.of(year,month,day);

        System.out.println("now  is " + LocalDate.now());
        List<String > list = DateSetting.getDates(localDate,LocalDate.now().plusDays(1));
List<ExchangeRate> lists =new ArrayList<>();






    for (int i =0; i < list.size(); i++) {
        ExchangeRate exchangeRate = new ExchangeRate();
        System.out.println(list.get(i));
        URL url = new URL("https://api.privatbank.ua/p24api/exchange_rates?json&date=" + list.get(i));

       
        InputStreamReader reader = new InputStreamReader(url.openStream());


        Exchange exchange = new Gson().fromJson(reader, Exchange.class);
      //  System.out.println(exchange);


        int indexUSD = 1;
        for (int j = 0; j < exchange.getExchangeRate().size(); j++) {
            if (exchange.getExchangeRate().get(j).getCurrency() != null && exchange.getExchangeRate().get(j).getCurrency().equals("USD")) {
                indexUSD = j;
                break;
            }
        }


        exchangeRate.setDate(list.get(i));
        exchangeRate.setBaseCurrency(exchange.getExchangeRate().get(indexUSD).getBaseCurrency());
        exchangeRate.setCurrency(exchange.getExchangeRate().get(indexUSD).getCurrency());
        exchangeRate.setSaleRateNB(exchange.getExchangeRate().get(indexUSD).getSaleRateNB());
        exchangeRate.setPurchaseRateNB(exchange.getExchangeRate().get(indexUSD).getPurchaseRateNB());
        exchangeRate.setSaleRate(exchange.getExchangeRate().get(indexUSD).getSaleRate());
        exchangeRate.setPurchaseRate(exchange.getExchangeRate().get(indexUSD).getPurchaseRate());


//        System.out.println(exchangeRate);

        lists.add(exchangeRate);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


        return lists;
    }

}
