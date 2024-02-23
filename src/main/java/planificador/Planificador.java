package planificador;

import planificador.tareas.TareaEnvioNotificaciones;

public class Planificador {

  public static void main(String[] args){
    try {
      System.out.println("Iniciando tarea");
      new TareaEnvioNotificaciones().execute();
    } catch (Exception err){
      System.out.println("Ocurrió un error enviando las notificaciones");
    }
  }
}
