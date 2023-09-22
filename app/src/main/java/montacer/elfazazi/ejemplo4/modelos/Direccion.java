package montacer.elfazazi.ejemplo4.modelos;

public class Direccion {
    private  String calle;
    private  int numero;
    private String ciudad;

    public Direccion() {
    }

    public Direccion(String calle, int numero) {
        this.calle = calle;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return
                "calle=" + calle +
                ", numero=" + numero +
                ", ciudad='" + ciudad;
    }
}
