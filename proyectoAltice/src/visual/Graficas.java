package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.AlticeSistema;

public class Graficas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Graficas() {
		setTitle("Indicadores Generales");
		setBounds(100, 100, 950, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 2, 20, 0));

		PanelPastel panelPastel = new PanelPastel();
		panelPastel.setBorder(new TitledBorder(null, "Estado Global de Facturas", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.BOLD, 14), Color.BLACK));
		contentPanel.add(panelPastel);

		PanelBarras panelBarras = new PanelBarras();
		panelBarras.setBorder(new TitledBorder(null, "Planes Más Vendidos", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.BOLD, 14), Color.BLACK));
		contentPanel.add(panelBarras);
	}

	class PanelPastel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int[] stats = AlticeSistema.getInstance().obtenerEstadisticasFacturas();
			int pagadas = stats[0];
			int pendientes = stats[1];
			int vencidas = stats[2];
			double total = pagadas + pendientes + vencidas;

			if (total == 0) {
				g2d.drawString("No hay datos de facturas para mostrar.", 50, getHeight() / 2);
				return;
			}

			int x = 50;
			int y = 50;
			int width = 250;
			int height = 250;

			int anglePagadas = (int) Math.round((pagadas / total) * 360);
			int anglePendientes = (int) Math.round((pendientes / total) * 360);
			int angleVencidas = 360 - anglePagadas - anglePendientes;

			int startAngle = 0;

			g2d.setColor(new Color(46, 204, 113));
			g2d.fillArc(x, y, width, height, startAngle, anglePagadas);
			startAngle += anglePagadas;

			g2d.setColor(new Color(241, 196, 15));
			g2d.fillArc(x, y, width, height, startAngle, anglePendientes);
			startAngle += anglePendientes;

			g2d.setColor(new Color(231, 76, 60));
			g2d.fillArc(x, y, width, height, startAngle, angleVencidas);

			int leyendX = 330;
			int leyendY = 100;

			g2d.setColor(new Color(46, 204, 113));
			g2d.fillRect(leyendX, leyendY, 15, 15);
			g2d.setColor(Color.BLACK);
			g2d.drawString("Pagadas: " + pagadas, leyendX + 25, leyendY + 12);

			g2d.setColor(new Color(241, 196, 15));
			g2d.fillRect(leyendX, leyendY + 30, 15, 15);
			g2d.setColor(Color.BLACK);
			g2d.drawString("Pendientes: " + pendientes, leyendX + 25, leyendY + 42);

			g2d.setColor(new Color(231, 76, 60));
			g2d.fillRect(leyendX, leyendY + 60, 15, 15);
			g2d.setColor(Color.BLACK);
			g2d.drawString("Vencidas: " + vencidas, leyendX + 25, leyendY + 72);
		}
	}

	class PanelBarras extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Map<String, Integer> ventas = AlticeSistema.getInstance().obtenerVentasPorPlan();

			if (ventas.isEmpty()) {
				g2d.drawString("No hay contratos activos para mostrar.", 50, getHeight() / 2);
				return;
			}

			int maxVentas = 0;
			for (int valor : ventas.values()) {
				if (valor > maxVentas) {
					maxVentas = valor;
				}
			}

			int width = getWidth();
			int height = getHeight() - 50;
			int barWidth = 50;
			int spacing = 40;
			int x = 50;

			g2d.setColor(Color.BLACK);
			g2d.drawLine(40, height, width - 20, height);
			g2d.drawLine(40, 20, 40, height);

			FontMetrics metrics = g2d.getFontMetrics();

			for (Map.Entry<String, Integer> entry : ventas.entrySet()) {
				String nombrePlan = entry.getKey();
				int cantidad = entry.getValue();

				double scale = (double) cantidad / maxVentas;
				int barHeight = (int) (scale * (height - 50));
				int y = height - barHeight;

				g2d.setColor(new Color(52, 152, 219));
				g2d.fillRect(x, y, barWidth, barHeight);

				g2d.setColor(Color.BLACK);
				g2d.drawRect(x, y, barWidth, barHeight);

				String valorTxt = String.valueOf(cantidad);
				int txtWidth = metrics.stringWidth(valorTxt);
				g2d.drawString(valorTxt, x + (barWidth - txtWidth) / 2, y - 5);

				String planTxt = nombrePlan;
				if (planTxt.length() > 8) {
					planTxt = planTxt.substring(0, 8) + "..";
				}
				g2d.drawString(planTxt, x, height + 20);

				x += barWidth + spacing;
			}
		}
	}
}