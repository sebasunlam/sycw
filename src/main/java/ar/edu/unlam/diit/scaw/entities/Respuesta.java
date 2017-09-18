package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Respuesta implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String respuesta;
    private Integer idTipoRespuesta;
    private TipoRespuesta tipo;
    private Boolean correcta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public TipoRespuesta getTipo() {
        return tipo;
    }

    public void setTipo(TipoRespuesta tipo) {
        this.tipo = tipo;
    }

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public Integer getIdTipoRespuesta() {
        return idTipoRespuesta;
    }

    public void setIdTipoRespuesta(Integer idTipoRespuesta) {
        this.idTipoRespuesta = idTipoRespuesta;
    }
}
