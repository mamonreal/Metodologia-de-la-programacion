package MP3;

public class Principal {
    public static void main (String [] args) {
        // Modelo
        Modelo modelo = new Modelo();

     // Panel Botonera
        VentanaTablero ventanaTablero = new VentanaTablero(modelo);
        ventanaTablero.setVisible(true);

     // Panel Marcador
        VentanaMarcador panelMarcador = new VentanaMarcador();
        panelMarcador.setVisible(false);

     // Registro de oyentes
        modelo.addObserver(panelMarcador);
        modelo.addObserver(ventanaTablero);
        modelo.start();
    }
}
