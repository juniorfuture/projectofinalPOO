package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logica.AlticeSistema;

public class Graficas extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;

	public Graficas() {
		setTitle("Indicadores Generales");
		setBounds(100, 100, 1100, 620);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.setBackground(new Color(245, 247, 250));
		contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel panelTitulo = new JPanel(new BorderLayout());
		panelTitulo.setOpaque(false);

		JLabel lblTitulo = new JLabel("Panel de Indicadores");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(33, 37, 41));
		panelTitulo.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSubtitulo = new JLabel("Resumen visual de facturas y planes contratados");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSubtitulo.setForeground(new Color(108, 117, 125));
		panelTitulo.add(lblSubtitulo, BorderLayout.SOUTH);

		contentPanel.add(panelTitulo, BorderLayout.NORTH);

		JPanel panelResumen = crearPanelResumen();
		contentPanel.add(panelResumen, BorderLayout.SOUTH);

		JPanel panelGraficos = new JPanel(new GridLayout(1, 2, 15, 15));
		panelGraficos.setOpaque(false);

		panelGraficos.add(crearPanelGraficoPastel());
		panelGraficos.add(crearPanelGraficoBarras());

		contentPanel.add(panelGraficos, BorderLayout.CENTER);
	}

	private JPanel crearPanelGraficoPastel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(
						BorderFactory.createLineBorder(new Color(220, 220, 220)),
						"Estado Global de Facturas",
						TitledBorder.CENTER,
						TitledBorder.TOP,
						new Font("Segoe UI", Font.BOLD, 14),
						new Color(33, 37, 41)),
				new EmptyBorder(10, 10, 10, 10)));

		DefaultPieDataset dataset = new DefaultPieDataset();

		int[] stats = AlticeSistema.getInstance().obtenerEstadisticasFacturas();
		int pagadas = stats[0];
		int pendientes = stats[1];
		int vencidas = stats[2];

		if (pagadas == 0 && pendientes == 0 && vencidas == 0) {
			JLabel lblSinDatos = new JLabel("No hay datos de facturas para mostrar");
			lblSinDatos.setHorizontalAlignment(SwingConstants.CENTER);
			lblSinDatos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblSinDatos.setForeground(Color.GRAY);
			panel.add(lblSinDatos, BorderLayout.CENTER);
			return panel;
		}

		dataset.setValue("Pagadas", pagadas);
		dataset.setValue("Pendientes", pendientes);
		dataset.setValue("Vencidas", vencidas);

		JFreeChart chart = ChartFactory.createRingChart(
				null,
				dataset,
				true,
				true,
				false);

		chart.setBackgroundPaint(Color.WHITE);

		RingPlot plot = (RingPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setShadowPaint(null);
		plot.setSectionDepth(0.35);
		plot.setSimpleLabels(false);

		plot.setSectionPaint("Pagadas", new Color(40, 167, 69));
		plot.setSectionPaint("Pendientes", new Color(255, 193, 7));
		plot.setSectionPaint("Vencidas", new Color(220, 53, 69));

		plot.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		if (chart.getLegend() != null) {
			chart.getLegend().setItemFont(new Font("Segoe UI", Font.PLAIN, 12));
		}

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setOpaque(false);

		panel.add(chartPanel, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearPanelGraficoBarras() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createCompoundBorder(
				new TitledBorder(
						BorderFactory.createLineBorder(new Color(220, 220, 220)),
						"Planes Más Vendidos",
						TitledBorder.CENTER,
						TitledBorder.TOP,
						new Font("Segoe UI", Font.BOLD, 14),
						new Color(33, 37, 41)),
				new EmptyBorder(10, 10, 10, 10)));

		Map<String, Integer> ventas = AlticeSistema.getInstance().obtenerVentasPorPlan();

		if (ventas.isEmpty()) {
			JLabel lblSinDatos = new JLabel("No hay contratos activos para mostrar");
			lblSinDatos.setHorizontalAlignment(SwingConstants.CENTER);
			lblSinDatos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			lblSinDatos.setForeground(Color.GRAY);
			panel.add(lblSinDatos, BorderLayout.CENTER);
			return panel;
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Integer> entry : ventas.entrySet()) {
			dataset.addValue(entry.getValue(), "Contratos", entry.getKey());
		}

		JFreeChart chart = ChartFactory.createBarChart(
				null,
				"Plan",
				"Cantidad de contratos",
				dataset);

		chart.setBackgroundPaint(Color.WHITE);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(248, 249, 250));
		plot.setOutlineVisible(false);
		plot.setRangeGridlinePaint(new Color(220, 220, 220));
		plot.getDomainAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		plot.getDomainAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 12));
		plot.getRangeAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		plot.getRangeAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 12));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(0, 123, 255));
		renderer.setBaseItemLabelFont(new Font("Segoe UI", Font.BOLD, 11));
		renderer.setBaseItemLabelsVisible(true);
		renderer.setShadowVisible(false);
		renderer.setMaximumBarWidth(0.12);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setOpaque(false);

		panel.add(chartPanel, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearPanelResumen() {
		JPanel panelResumen = new JPanel(new GridLayout(1, 3, 15, 0));
		panelResumen.setOpaque(false);

		int[] stats = AlticeSistema.getInstance().obtenerEstadisticasFacturas();
		int pagadas = stats[0];
		int pendientes = stats[1];
		int vencidas = stats[2];

		panelResumen.add(crearTarjeta("Facturas Pagadas", String.valueOf(pagadas), new Color(40, 167, 69)));
		panelResumen.add(crearTarjeta("Facturas Pendientes", String.valueOf(pendientes), new Color(255, 193, 7)));
		panelResumen.add(crearTarjeta("Facturas Vencidas", String.valueOf(vencidas), new Color(220, 53, 69)));

		return panelResumen;
	}

	private JPanel crearTarjeta(String titulo, String valor, Color colorLinea) {
		JPanel card = new JPanel(new BorderLayout());
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(4, 0, 0, 0, colorLinea),
				new EmptyBorder(12, 15, 12, 15)));

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTitulo.setForeground(new Color(108, 117, 125));

		JLabel lblValor = new JLabel(valor);
		lblValor.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblValor.setForeground(new Color(33, 37, 41));

		card.add(lblTitulo, BorderLayout.NORTH);
		card.add(lblValor, BorderLayout.CENTER);

		return card;
	}
}