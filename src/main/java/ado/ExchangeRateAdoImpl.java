package ado;

import service.ExchangeRate;
import utils.JsonReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExchangeRateAdoImpl implements  ExchangeRateAdo {
private  EntityManagerFactory emf;
private EntityManager entityManager;
   private JsonReader js = new JsonReader();
    public ExchangeRateAdoImpl() {
        emf = Persistence.createEntityManagerFactory("exchange");
        entityManager = emf.createEntityManager();

    }

    @Override
    public boolean addDataToBD(List<ExchangeRate> list) {


        EntityTransaction tr = entityManager.getTransaction();


        try {


            for (int i = 0; i < list.size(); i++) {
                tr.begin();
                entityManager.persist(list.get(i));

                tr.commit();
            }
        }catch (Exception ex ){
            tr.rollback();
            System.out.println(ex.getCause());
            return false;
        }finally {
            if(entityManager!=null)
                entityManager.close();
            if(emf!=null)
                emf.close();
        }
        return true;
    }

    @Override
    public ExchangeRate getRateByDate(String data) {

        ExchangeRate ex = new ExchangeRate();


        ex = (ExchangeRate) entityManager.createQuery("Select e From ExchangeRate e where e.date='"+data+"'").getSingleResult();


        return ex;

    }



    @Override
    public List<ExchangeRate> getAllDate() {

      List<ExchangeRate> res = entityManager.createQuery("Select e From ExchangeRate e order by e.idRate asc ").getResultList();

        return res;
    }

    @Override
    public boolean updateData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exchange");
       EntityManager entity = emf.createEntityManager();
        List<ExchangeRate> oldList = getAllDate();
        String lastDate = oldList.get(oldList.size()-1).getDate();
        System.out.println(lastDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.YYYY");

                Date date = new Date();
        String dateNow = sdf.format(date);

        if (lastDate.equals(dateNow)){
            return true;
        }else {


            List<ExchangeRate> list = null;
            try {
                list = js.parseJson(lastDate);

            } catch (IOException e) {
                e.printStackTrace();
            }

            EntityTransaction tr = entity.getTransaction();


            try {


                for (int i = 0; i < list.size(); i++) {
                    tr.begin();
                    entity.merge(list.get(i));

                    tr.commit();
                }
            } catch (Exception ex) {
                tr.rollback();
                System.out.println(ex.getCause());
                return false;
            }

            return true;
        }


    }

    @Override
    public double avgExchangeRate(String startDate, String endDate) {

        List<ExchangeRate> allData = getAllDate();

        int idStartDate=0;
        int idEndDate=0;

            for (int i = 0 ;i< allData.size();i++){
               if (allData.get(i).getDate().equals(startDate.trim())){
                   idStartDate=i;
            }
               if (allData.get(i).getDate().equals(endDate.trim())){
                   idEndDate=i;
               }
        }

            int count=0;
            double sum = 0;
            double avg;
        for (int i=idStartDate ; i<idEndDate; i++){

            sum = sum+ allData.get(i).getPurchaseRate();

        ++count;
        }

        avg=sum/count;

        return avg;
    }


}
