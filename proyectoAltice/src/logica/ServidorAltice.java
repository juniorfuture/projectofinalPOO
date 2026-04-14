package logica;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAltice {

    public static void main(String[] args) {
        System.out.println("Servidor de Datos Altice Iniciado (Puerto 5000). Esperando registros...");
        
        try (ServerSocket serverSocket = new ServerSocket(5000)) { 
            
            while (true) {
                Socket enchufeCliente = serverSocket.accept(); 
                new Thread(() -> guardarDatoRecibido(enchufeCliente)).start();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guardarDatoRecibido(Socket cliente) {
        try (ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream())) {
            
            Object objetoRecibido = flujoEntrada.readObject();

            if (objetoRecibido instanceof Contrato) {
                Contrato nuevoContrato = (Contrato) objetoRecibido;
                AlticeSistema.getInstance().registrarContrato(nuevoContrato);
                System.out.println(">> ÉXITO: Contrato " + nuevoContrato.getIdContrato() + " recibido y guardado en la central.");
            } 
            else if (objetoRecibido instanceof Cliente) {
                Cliente nuevoCliente = (Cliente) objetoRecibido;
                AlticeSistema.getInstance().registrarPersona(nuevoCliente);
                System.out.println(">> ÉXITO: Cliente " + nuevoCliente.getNombre() + " recibido y guardado en la central.");
            }

        } catch (Exception e) {
            System.out.println("Error al recibir el dato: " + e.getMessage());
        }
    }
}