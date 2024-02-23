package services.geodds;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.geodds.entities.DistanciaAPI;
import trayecto.Ubicacion;

import java.io.IOException;

public class ServiceGeodds {

  private static final String URL = "https://ddstpa.com.ar/api/";
  private static final String token = "Bearer DEq5e3WcZteFWzICxNXmKMuppLmDD5diiiqdTVS+GtQ=";
  private static Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(URL)
      .addConverterFactory(GsonConverterFactory
          .create())
      .build();

  public DistanciaAPI distancia(Ubicacion origen, Ubicacion destino) {
    try {
      GeoddsInterface geoddsService = retrofit.create(GeoddsInterface.class);
      Call<DistanciaAPI> requestDistancia = geoddsService.distancia(
          token,
          origen.getLocalidad(),
          origen.getCalle(),
          origen.getAltura(),
          destino.getLocalidad(),
          destino.getCalle(),
          destino.getAltura()
      );

      Response<DistanciaAPI> responseDistancia = requestDistancia.execute();

      return responseDistancia.body();
    } catch (IOException e) {
      throw new RuntimeException("Call failed");
    }
  }
}