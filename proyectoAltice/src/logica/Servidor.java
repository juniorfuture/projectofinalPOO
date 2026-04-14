package logica;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
	public static Vector<Flujo> usuarios = new Vector<>();

	public static void main(String args[]) {
		ServerSocket sfd = null;
			try {
				sfd = new ServerSocket(7000);
				System.out.println("Servidor de Chat iniciado en el puerto 7000. Esperando conexiones...");
			} catch (IOException ioe) {
				System.out.println("El servidor de chat ya está encendido o el puerto está ocupado.");
				return; 
			}

		while (true) {
			try {
				Socket nsfd = sfd.accept();
				System.out.println("Conexión aceptada de: " + nsfd.getInetAddress());
				Flujo flujo = new Flujo(nsfd);
				flujo.start();
				
			} catch (IOException ioe) {
				System.out.println("Error: " + ioe);
			}
		}
	}
}