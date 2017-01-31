package com.example.dm2.examen_davidblanco;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssParserDom {

    private URL rssUrl;

    public RssParserDom(String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tiempo parse(){
        //Instanciamos la fabrica para DOM
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        Tiempo tiempo=null;
        try {
            //Creamos un nuevo parser DOM
            DocumentBuilder builder=factory.newDocumentBuilder();
            //Realizamos la lectura completa del XML
            Document dom=builder.parse(this.getInputStream());
            //Nos posicionamos en el nodo principal del árbol
            Element root=dom.getDocumentElement();
            //Localizamos todos los elementos <pronostico_horas>
            NodeList items=root.getElementsByTagName("pronostico_horas");
            //Recorremos la lista de pronostico
            for(int i=0;i<items.getLength();i++){
                //Obtenemos la hora
                Node item=items.item(i);
                //Obtenemos la lista de pronostico_horas
                NodeList datosTiempos=item.getChildNodes();
                //Procesamos cada dato de la hora
                for(int j=0;j<datosTiempos.getLength();j++){
                    Node dato=datosTiempos.item(j);
                    NodeList datosHoras=dato.getChildNodes();
                    for(int w=0;j<datosHoras.getLength();w++){
                        Node datos=datosHoras.item(w);
                        String et=datos.getNodeName();
                        tiempo=new Tiempo();
                            if(et.equals("hora_datos")){
                                String texto=obtenerTexto(datos);
                                tiempo.setHora(texto);
                                Log.e("tiempo",tiempo.getHora());
                            }
                            if(et.equals("texto")){
                                String texto=obtenerTexto(datos);
                                tiempo.setEstado(texto);
                                Log.e("estado",tiempo.getEstado());
                            }
                            if(et.equals("temperatura")){
                                String texto=obtenerTexto(datos);
                                tiempo.setTemperatura(Integer.parseInt(texto));
                                Log.e("temperatura",""+tiempo.getTemperatura());
                            }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return tiempo;
    }

    public String obtenerTexto (Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();
        for(int k=0;k<fragmentos.getLength();k++){
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    private InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
