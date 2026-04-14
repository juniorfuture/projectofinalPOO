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
import javax.swing.border.LineBorder;

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

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public Graficas() {
		setTitle("Panel de Indicadores");
		setBounds(100, 100, 1180, 700);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(COLOR_FONDO);

		contentPanel = new JPanel(new BorderLayout(18, 18));
		contentPanel.setBackground(COLOR_FONDO);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.add(crearHeader(), BorderLayout.NORTH);
		contentPanel.add(crearPanelCentral(), BorderLayout.CENTER);
		contentPanel.add(crearPanelResumen(), BorderLayout.SOUTH);
	}

	private JPanel crearHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(COLOR_PANEL);
		header.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(20, 22, 20, 22)));

		JLabel lblTitulo = new JLabel("Indicadores Generales");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitulo.setForeground(COLOR_TEXTO);

		JLabel lblSubtitulo = new JLabel("Resumen visual del comportamiento de facturas y contratos del sistema.");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSubtitulo.setForeground(COLOR_SECUNDARIO);

		JPanel panelTextos = new JPanel(new BorderLayout(0, 4));
		panelTextos.setOpaque(false);
		panelTextos.add(lblTitulo, BorderLayout.NORTH);
		panelTextos.add(lblSubtitulo, BorderLayout.CENTER);

		header.add(panelTextos, BorderLayout.WEST);

		return header;
	}

	private JPanel crearPanelCentral() {
		JPanel panelGraficos = new JPanel(new GridLayout(1, 2, 18, 18));
		panelGraficos.setOpaque(false);

		panelGraficos.add(crearPanelGraficoPastel());
		panelGraficos.add(crearPanelGraficoBarras());

		return panelGraficos;
	}

	private JPanel crearPanelGraficoPastel() {
		JPanel panel = new JPanel(new BorderLayout(12, 12));
		panel.setBackground(COLOR_PANEL);
		panel.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(16, 16, 16, 16)));

		JLabel titulo = new JLabel("Estado Global de Facturas");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titulo.setForeground(COLOR_TEXTO);
		panel.add(titulo, BorderLayout.NORTH);

		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

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

		JFreeChart chart = ChartFactory.createRingChart(null, dataset, true, true, false);
		chart.setBackgroundPaint(COLOR_PANEL);

		RingPlot plot = (RingPlot) chart.getPlot();
		plot.setBackgroundPaint(COLOR_PANEL);
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
		JPanel panel = new JPanel(new BorderLayout(12, 12));
		panel.setBackground(COLOR_PANEL);
		panel.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(16, 16, 16, 16)));

		JLabel titulo = new JLabel("Planes Más Vendidos");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titulo.setForeground(COLOR_TEXTO);
		panel.add(titulo, BorderLayout.NORTH);

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

		chart.setBackgroundPaint(COLOR_PANEL);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(248, 249, 250));
		plot.setOutlineVisible(false);
		plot.setRangeGridlinePaint(new Color(220, 220, 220));
		plot.getDomainAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		plot.getDomainAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 12));
		plot.getRangeAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		plot.getRangeAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 12));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(31, 111, 235));
		renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 11));
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setShadowVisible(false);
		renderer.setMaximumBarWidth(0.12);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setOpaque(false);

		panel.add(chartPanel, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearPanelResumen() {
		JPanel panelResumen = new JPanel(new GridLayout(1, 3, 18, 0));
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
		card.setBackground(COLOR_PANEL);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(4, 0, 0, 0, colorLinea),
				BorderFactory.createCompoundBorder(
						new LineBorder(COLOR_BORDE, 1, true),
						new EmptyBorder(14, 16, 14, 16))));

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTitulo.setForeground(COLOR_SECUNDARIO);

		JLabel lblValor = new JLabel(valor);
		lblValor.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblValor.setForeground(COLOR_TEXTO);

		card.add(lblTitulo, BorderLayout.NORTH);
		card.add(lblValor, BorderLayout.CENTER);

		return card;
	}
}