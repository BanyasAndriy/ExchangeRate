package ado;

import service.ExchangeRate;

import java.util.List;

public interface ExchangeRateAdo {

    public boolean addDataToBD(List<ExchangeRate> list );
     public ExchangeRate getRateByDate(String data);
     public List<ExchangeRate> getAllDate();
     public boolean updateData();
     public double avgExchangeRate(String startDate , String endDate);

}
