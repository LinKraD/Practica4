package net.iesseveroochoa.gabrielvidal.practica4;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

@SuppressLint("ParcelCreator")
public class Poblacion implements Parcelable {
    private String provincia;
    private String localidad;
    private float valoracion;
    private String comentarios;

    public Poblacion(String provincia, String localidad, float valoracion, String comentarios) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.valoracion = valoracion;
        this.comentarios = comentarios;
    }


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poblacion poblacion = (Poblacion) o;
        return Objects.equals(provincia, poblacion.provincia) &&
                Objects.equals(localidad, poblacion.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provincia, localidad);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.provincia);
        dest.writeString(this.localidad);
        dest.writeFloat(this.valoracion);
        dest.writeString(this.comentarios);
    }

    protected Poblacion(Parcel in) {
        this.provincia = in.readString();
        this.localidad = in.readString();
        this.valoracion = in.readFloat();
        this.comentarios = in.readString();
    }

    public static final Creator<Poblacion> CREATOR = new Creator<Poblacion>() {
        @Override
        public Poblacion createFromParcel(Parcel source) {
            return new Poblacion(source);
        }

        @Override
        public Poblacion[] newArray(int size) {
            return new Poblacion[size];
        }
    };
}
