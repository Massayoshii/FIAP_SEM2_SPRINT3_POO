package model;

public class TrechoRodovia implements MonitoravelViaIoT {
    private Long id;
    private double quilometroInicial;
    private double quilometroFinal;
    private double nivelVegetacaoCm;
    private String tipoClima;

    public TrechoRodovia(double quilometroInicial, double quilometroFinal, double nivelVegetacaoCm, String tipoClima) {
        this.quilometroInicial = quilometroInicial;
        this.quilometroFinal = quilometroFinal;
        this.nivelVegetacaoCm = nivelVegetacaoCm;
        this.tipoClima = tipoClima;
    }

    public TrechoRodovia(Long id , double quilometroInicial, double quilometroFinal, double nivelVegetacaoCm, String tipoClima) {
        this.id = id;
        this.setQuilometroInicial(quilometroInicial);
        this.setQuilometroFinal(quilometroFinal);
        this.setNivelVegetacaoCm(nivelVegetacaoCm);
        this.setTipoClima(tipoClima);
        System.out.println("\nTrecho rodovia: Km inicial:"+ this.quilometroInicial + "km | Quilometro final:"+ this.quilometroFinal + "km | Nivel de vegetacao:"+ this.nivelVegetacaoCm + "cm");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuilometroInicial() {
        return quilometroInicial;
    }

    public double getQuilometroFinal() {
        return quilometroFinal;
    }

    public double getNivelVegetacaoCm() {
        return nivelVegetacaoCm;
    }

    public String getTipoClima(){
        return tipoClima;
    }


    public void registrarCrescimento(double taxa){
        if(taxa>0){
            nivelVegetacaoCm += taxa;
            System.out.println("\nNivel de vegetacao aumentou para "+ this.nivelVegetacaoCm);
        }else {
            throw new IllegalArgumentException("\nA taxa nao pode ser 0 ou menor");
        }
    }

    public void registrarCrescimentoAutomatico(){
        if(tipoClima.equalsIgnoreCase("umido")){
            nivelVegetacaoCm += 10;
        }else {
            nivelVegetacaoCm += 4;
        }
        System.out.println("\nCrescimento automatico registrado. Nivel atual:  " + nivelVegetacaoCm);
    }

    @Override
    public void transmitirDadosSensor(double crescimento) {
        if (crescimento > 0){
            nivelVegetacaoCm += crescimento;

            System.out.println("\nSensor IoT atualizou a vegetacao para " + nivelVegetacaoCm + " cm");
        }else {
            throw new IllegalArgumentException("\nValor do sensor invalido");
        }
    }


    public void setQuilometroInicial(double quilometroInicial) {
        if(quilometroInicial >= 0){
            this.quilometroInicial = quilometroInicial;
        }else {
            throw new IllegalArgumentException("Quilometro inicial nao pode ser negativo");
        }
    }

    public void setQuilometroFinal(double quilometroFinal) {
        if(quilometroFinal > quilometroInicial){
            this.quilometroFinal = quilometroFinal;
        }else {
            throw new IllegalArgumentException("Quilometro final nao pode ser maior que o quilometro inicial");
        }
    }

    public void setNivelVegetacaoCm(double nivelVegetacaoCm) {
        if(nivelVegetacaoCm >= 0){
            this.nivelVegetacaoCm = nivelVegetacaoCm;
        }else {
            throw new IllegalArgumentException("Nivel de vegetacao nao pode ser negativo");
        }
    }

    public void setTipoClima(String tipoClima){
        if(tipoClima.equalsIgnoreCase("umido") || tipoClima.equalsIgnoreCase("seco")){
            this.tipoClima = tipoClima;
        }else {
            throw new IllegalArgumentException("O tipo de clima deve ser 'umido' ou 'seco'");
        }
    }

    @Override
    public String toString() {
        return "Trecho KM " + quilometroInicial + " ao " + quilometroFinal +
                " | Vegetação: " + nivelVegetacaoCm + " cm | Clima: " + tipoClima;
    }

}