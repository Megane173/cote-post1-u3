package com.universidad.confudes.certificados.DecoratorsCertificados;

import com.universidad.confudes.certificados.ServicioCertificados;
import com.universidad.confudes.certificados.SolicitudCertificado;
import com.universidad.confudes.certificados.UtilidadesPDF;

public class MarcaDeAguaDecorator extends ServicioCertificadosDecorator {


    private final String marcaDeAgua;

    public MarcaDeAguaDecorator(ServicioCertificados wrappee, String marcaDeAgua) {
        super(wrappee);
        this.marcaDeAgua= marcaDeAgua;
    }

    @Override
    public byte[] emitir(SolicitudCertificado solicitud) {

        byte[] documento = wrappee.emitir(solicitud);

        if (documento == null) {
            return null;
        }

        return UtilidadesPDF.aplicarMarcaDeAgua(
                documento,
                this.marcaDeAgua);
    }
}