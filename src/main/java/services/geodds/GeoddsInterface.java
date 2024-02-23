package services.geodds;

import retrofit2.Call;
import retrofit2.http.*;
import services.geodds.entities.DistanciaAPI;

public interface GeoddsInterface {

  @GET("distancia")
  Call<DistanciaAPI> distancia(
      @Header("Authorization") String token,
      @Query("localidadOrigenId") String localidadOrigen,
      @Query("calleOrigen") String calleOrigen,
      @Query("alturaOrigen") String alturaOrigen,
      @Query("localidadDestinoId") String localidadDestino,
      @Query("calleDestino") String calleDestino,
      @Query("alturaDestino") String alturaDestino
  );

}
