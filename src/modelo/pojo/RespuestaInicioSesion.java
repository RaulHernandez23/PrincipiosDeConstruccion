package modelo.pojo;

public class RespuestaInicioSesion {

    private boolean correcto;
    private String mensaje;
    private Estudiante estudiante;
    private ResponsableProyecto responsableProyecto;

    public RespuestaInicioSesion() {
    }

    public RespuestaInicioSesion(boolean correcto, String mensaje,
            Estudiante estudiante, ResponsableProyecto responsableProyecto) {

        this.correcto = correcto;
        this.mensaje = mensaje;
        this.estudiante = estudiante;
        this.responsableProyecto = responsableProyecto;

    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {

        if (mensaje != null) {
            this.mensaje = mensaje;
        }

    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {

        if (estudiante != null) {
            this.estudiante = estudiante;
        }

    }

    public ResponsableProyecto getResponsableProyecto() {
        return responsableProyecto;
    }

    public void setResponsableProyecto(
            ResponsableProyecto responsableProyecto) {

        if (responsableProyecto != null) {
            this.responsableProyecto = responsableProyecto;
        }

    }

}