package logica;

import java.io.*;
import java.net.*;
import java.util.*;

public class Flujo extends Thread {
	Socket nsfd;
	DataInputStream FlujoLectura;
	DataOutputStream FlujoEscritura;

	public Flujo(Socket sfd) {
		nsfd = sfd;
		try {
			FlujoLectura = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			FlujoEscritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
		} catch (IOException ioe) {
			System.out.println("IOException(Flujo): " + ioe);
		}
	}

	public void run() {
		broadcast(nsfd.getInetAddress() + "> se ha conectado");
		Servidor.usuarios.add(this);
		while (true) {
			try {
				String linea = FlujoLectura.readUTF();
				if (!linea.equals("")) {
					linea = nsfd.getInetAddress() + "> " + linea;
					broadcast(linea);
				}
			} catch (IOException ioe) {
				Servidor.usuarios.removeElement(this);
				broadcast(nsfd.getInetAddress() + "> se ha desconectado");
				break;
			}
		}
	}

	public void broadcast(String mensaje) {
		synchronized (Servidor.usuarios) {
			Enumeration<Flujo> e = Servidor.usuarios.elements();
			while (e.hasMoreElements()) {
				Flujo f = e.nextElement();
				try {
					synchronized (f.FlujoEscritura) {
						f.FlujoEscritura.writeUTF(mensaje);
						f.FlujoEscritura.flush();
					}
				} catch (IOException ioe) {
					System.out.println("Error: " + ioe);
				}
			}
		}
	}
}