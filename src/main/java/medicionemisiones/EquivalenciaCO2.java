package medicionemisiones;

public enum EquivalenciaCO2 {
  GRAMO_CO2_EQ{
    @Override
    public double valorEnGramos(double valor) {
      return valor;
    }

    @Override
    public double valorEnKilogramos(double valor) {
      return valor / 1000;
    }

    @Override
    public double valorEnToneladas(double valor) {
      return valor / 1000000;
    }
  },
  KILOGRAMO_CO2_EQ{
    @Override
    public double valorEnGramos(double valor) {
      return valor * 1000;
    }

    @Override
    public double valorEnKilogramos(double valor) {
      return valor;
    }

    @Override
    public double valorEnToneladas(double valor) {
      return valor / 1000;
    }
  },
  TN_CO2_EQ{
    @Override
    public double valorEnGramos(double valor) {
      return valor * 1000000;
    }

    @Override
    public double valorEnKilogramos(double valor) {
      return valor * 1000;
    }

    @Override
    public double valorEnToneladas(double valor) {
      return valor;
    }
  };

  public abstract double valorEnGramos(double valor);
  public abstract double valorEnKilogramos(double valor);
  public abstract double valorEnToneladas(double valor);
}


