import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class RoundButton extends JButton {
    private int arcWidth;  // Ancho del arco
    private int arcHeight; // Altura del arco

    public RoundButton(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;

        // Configuraciones para eliminar el estilo estándar del botón
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // Dibujar texto centrado
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - textWidth) , (getHeight() + textHeight) );

        g2.dispose();
        super.paintComponent(g);
    }
}


public class ICentro extends JFrame implements ActionListener {

    private JLabel ingresarDato;
    private JLabel titulo;
    private JLabel descripcion;
    private JTextField descripcionText;
    private JTableHeader header1;
    private JLabel cantidad;
    private JTextField cantidadText;
    private JLabel costoUnitario;
    private JTextField costoUnitarioText;
    private JLabel fechaAdquisicion;
    private JLabel fecha;
    private JTextField fechaAdquisicionText;
    private JLabel nroFactura;
    private JTextField nroFacturaText;
    private JLabel ciEquipo;
    private JTextField ciEquipoText;

    RoundButton registrarData;
    RoundButton generarReporte;
    RoundButton salir;

    public ICentro(){
        setLayout(null);//indico que voy a usar coodernadas

        titulo = new JLabel("Registro y control de Equipos en Centro de Investigación");
        titulo.setBounds(5,5,500,30);
        titulo.setForeground(Color.WHITE); // Color blanco para mejor visibilidad
        titulo.setHorizontalAlignment(SwingConstants.LEFT);
        add(titulo);

        header1 = new JTableHeader();
        header1.setBounds(0,0,700,30);
        header1.setBackground(new Color(108, 108, 108));
        add(header1);

        ingresarDato = new JLabel("Ingrese data del equipo:");
        ingresarDato.setBounds(10,32,300,30);
        add(ingresarDato);//agrega la etiqueta a la interfaz

        descripcion = new JLabel("Descripción:");
        descripcion.setBounds(15,62,100,30);
        add(descripcion);//agrega la etiqueta a la interfaz

        descripcionText = new JTextField();
        descripcionText.setBounds(100,69,550,20);
        add(descripcionText);

        cantidad = new JLabel("Cantidad:");
        cantidad.setBounds(23,99,100,30);
        add(cantidad);

        cantidadText = new JTextField();
        cantidadText.setBounds(100,105,40,20);
        add(cantidadText);

        costoUnitario = new JLabel("Costo Unitario (Bs.):");
        costoUnitario.setBounds(400,99,200,30);
        add(costoUnitario);

        costoUnitarioText = new JTextField();
        costoUnitarioText.setBounds(520,105,130,20);
        add(costoUnitarioText);

        fechaAdquisicion = new JLabel("Fecha de adquisición: ");
        fechaAdquisicion.setBounds(10,140,250,30);
        add(fechaAdquisicion);
        fecha = new JLabel("dd/mm/aaaa ");
        fecha.setBounds(10,150,100,30);
        add(fecha);

        fechaAdquisicionText = new JTextField();
        fechaAdquisicionText.setBounds(150,150,130,20);
        add(fechaAdquisicionText);

        nroFactura = new JLabel("Nro. de Factura:");
        nroFactura.setBounds(410,140,100,30);
        add(nroFactura);

        nroFacturaText = new JTextField();
        nroFacturaText.setBounds(520,147,130,20);
        add(nroFacturaText);

        ciEquipo = new JLabel("C.I del Responsable del equipo:");
        ciEquipo.setBounds(10,190,250,30);
        add(ciEquipo);

        ciEquipoText = new JTextField();
        ciEquipoText.setBounds(200,197,130,20);
        add(ciEquipoText);

        registrarData = new RoundButton("Registrar Data", 20, 20);
        registrarData.setBounds(247,300,130,30);
        registrarData.setBackground(new Color(200, 196, 196));
        add(registrarData);
        registrarData.addActionListener(this);//activa la funcionalidad del boton

        generarReporte = new RoundButton("Generar Reporte", 20, 20);
        generarReporte.setBounds(405,300,140,30);
        generarReporte.setBackground(new Color(200, 196, 196));
        add(generarReporte);
        generarReporte.addActionListener(this);//activa la funcionalidad del boton

        salir = new RoundButton("Salir", 20, 20);
        salir.setBounds(567,300,80,30);
        salir.setBackground(new Color(200, 196, 196));
        add(salir);
        salir.addActionListener(this);//activa la funcionalidad del boton

    }

    public void actionPerformed(ActionEvent e){//captura al evento (se guarda en la memoria)
        if(e.getSource() == salir){//si se presiona el boton1 se ejecuta este evento (pueden haber varios botones)
            System.exit(0);//cierra la interfaz
        }
        if(e.getSource() == generarReporte) {
            // Abrir nueva ventana
            String ciResponsable = ciEquipoText.getText(); // Obtener el texto de ciEquipoText
            String cantidadEquipos= cantidadText.getText();
            String costo= costoUnitarioText.getText();
            IReporte nuevaVentana = new IReporte(); // Pasar el texto al constructor
            //IReporte nuevaVentana = new IReporte();
            nuevaVentana.setVisible(true);
            nuevaVentana.setBounds(0, 0, 700, 400); // Dimensiones de la nueva ventana
            nuevaVentana.setLocationRelativeTo(null);

            // Cerrar ventana actual
            dispose();
        }
        if (e.getSource() == registrarData) {
            try {
                File file = new File("inventario.txt");

                // Crear el archivo si no existe
                if (!file.exists()) {
                    file.createNewFile();
                }

                // Escribir los datos en el archivo
                FileWriter writer = new FileWriter(file, true);
                writer.write(descripcionText.getText() + "#");
                writer.write(cantidadText.getText() + "#");
                writer.write(costoUnitarioText.getText() + "#");
                writer.write(fechaAdquisicionText.getText() + "#");
                writer.write(nroFacturaText.getText() + "#");
                writer.write(ciEquipoText.getText());
                writer.write("\n");
                writer.close();

                JOptionPane.showMessageDialog(this, "Datos registrados correctamente.");

                // Limpiar los campos de texto
                descripcionText.setText("");
                cantidadText.setText("");
                costoUnitarioText.setText("");
                fechaAdquisicionText.setText("");
                nroFacturaText.setText("");
                ciEquipoText.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar los datos: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ICentro formulario = new ICentro();
        formulario.setTitle("ICentro");
        formulario.setBounds(0,0,700,400);//dimension de la interfaz
        formulario.setVisible(true);//la interfaz es visible
        formulario.setLocationRelativeTo(null);//coloca la interfaz en el medio de la pantalla
        formulario.setResizable(false);//usuario no modifica tamaño de la interfaz
    }

}

class IReporte extends JFrame implements ActionListener  {

    private JLabel titulo1;
    private JTableHeader header2;
    private JLabel tipoReporte;
    private JRadioButton individual, general;
    private ButtonGroup bg;
    private JLabel ciEquipoR;
    private JTextField ciEquipoRText;
    private JLabel totalEquipos;
    private JLabel totalEquipos1;
    private JLabel totalEquipos2;

    RoundButton continuar;
    RoundButton totalizar;

    public IReporte(){
        setLayout(null);//indico que voy a usar coodernadas

        titulo1 = new JLabel("Registro y control de Equipos en Centro de Investigación");
        titulo1.setBounds(5,5,500,30);
        titulo1.setForeground(Color.WHITE); // Color blanco para mejor visibilidad
        titulo1.setHorizontalAlignment(SwingConstants.LEFT);
        add(titulo1);

        header2 = new JTableHeader();
        header2.setBounds(0,0,700,30);
        header2.setBackground(new Color(108, 108, 108));
        add(header2);

        tipoReporte = new JLabel("Tipo reporte:");
        tipoReporte.setBounds(10,32,300,30);
        add(tipoReporte);//agrega la etiqueta a la interfaz

        bg = new ButtonGroup();

        individual = new JRadioButton("Individual");
        individual.setBounds(100, 32, 100, 30);
        individual.setSelected(true);
        individual.addActionListener(this); // Escuchar eventos de acción
        add(individual);
        bg.add(individual);

        general = new JRadioButton("General");
        general.setBounds(200, 32, 100, 30);
        general.addActionListener(this); // Escuchar eventos de acción
        add(general);
        bg.add(general);

        ciEquipoR = new JLabel("C.I del Responsable del equipo:");
        ciEquipoR.setBounds(10,70,250,30);
        add(ciEquipoR);

        ciEquipoRText = new JTextField();
        ciEquipoRText.setBounds(200,77,130,20);
        ciEquipoRText.setEditable(true); // habilitar edición
        add(ciEquipoRText);

        totalizar = new RoundButton("Totalizar", 20, 20);
        totalizar.setBounds(350,73,90,30);
        totalizar.setBackground(new Color(200, 196, 196));
        add(totalizar);
        totalizar.addActionListener(this);

        totalEquipos = new JLabel("Totalizacion:");
        totalEquipos.setBounds(10,190,250,30);
        add(totalEquipos);

        totalEquipos1 = new JLabel("____equipos");
        totalEquipos1.setBounds(10,205,250,30);
        add(totalEquipos1);

        totalEquipos2 = new JLabel("______Bs.");
        totalEquipos2.setBounds(10,220,250,30);
        add(totalEquipos2);

        continuar = new RoundButton("Continuar",20,20);
        continuar.setBounds(557,300,100,30);
        continuar.setBackground(new Color(200, 196, 196));
        add(continuar);
        continuar.addActionListener(this);//activa la funcionalidad del boton


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == totalizar) {
            totalizarEquipos();
        }
        if (general.isSelected()) {
            // Abrir nueva ventana y cerrar la actual
            IReporte2 reporteGeneral = new IReporte2(totalEquipos1.getText(),totalEquipos2.getText(), "inventario.txt");
            reporteGeneral.setVisible(true);
            reporteGeneral.setBounds(0, 0, 700, 400);
            reporteGeneral.setLocationRelativeTo(null);
            dispose(); // Cierra la ventana actual
        }
        if (individual.isSelected()) {
            //JOptionPane.showMessageDialog(this, "Seleccionaste reporte Individual");
        }
        if (e.getSource() == continuar) {
            ICentro centro = new ICentro();
            centro.setVisible(true);
            centro.setBounds(0, 0, 700, 400);
            centro.setLocationRelativeTo(null);
            dispose(); // Cierra la ventana actual
        }
    }

    private void totalizarEquipos() {
        String ci = ciEquipoRText.getText().trim();

        int totalCantidad = 0;
        double totalCosto = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader("inventario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts.length == 6 && parts[5].equals(ci)) {
                    // Parsear y sumar los valores
                    int cantidad = Integer.parseInt(parts[1]);
                    double costo = Double.parseDouble(parts[2]);
                    totalCantidad += cantidad;
                    totalCosto += cantidad * costo; // Totalizar costo
                }
            }

            // Actualizar las etiquetas
            totalEquipos1.setText(totalCantidad + " equipos");
            totalEquipos2.setText(totalCosto + " Bs.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato del archivo.");
        }
    }

    public static void main(String[] args) {
        IReporte reporte = new IReporte();
        reporte.setTitle("IReporte");
        reporte.setBounds(0,0,700,400);//dimension de la interfaz
        reporte.setVisible(true);//la interfaz es visible
        reporte.setLocationRelativeTo(null);//coloca la interfaz en el medio de la pantalla
        reporte.setResizable(false);//usuario no modifica tamaño de la interfaz
    }

}

class IReporte2 extends JFrame implements ActionListener  {

    private JLabel titulo1;
    private JTableHeader header2;
    private JLabel tipoReporte;
    private JRadioButton individual, general;
    private ButtonGroup bg;
    private JLabel ciEquipoR;
    private JScrollPane scrollpane1;
    private JTextArea ciEquipoRAText;
    private JLabel totalEquipos;
    private JLabel totalEquipos1;
    private JLabel totalEquipos2;

    private int cantidadTotalEquipos = 0; // Declarada como variable de instancia
    private int cantidadTotalCostoUnitario = 0; // Declarada como variable de instancia

    RoundButton continuar;

    public IReporte2(String cantidadEquipos,String costo, String archivo){
        setLayout(null);//indico que voy a usar coodernadas

        titulo1 = new JLabel("Registro y control de Equipos en Centro de Investigación");
        titulo1.setBounds(5,5,500,30);
        titulo1.setForeground(Color.WHITE); // Color blanco para mejor visibilidad
        titulo1.setHorizontalAlignment(SwingConstants.LEFT);
        add(titulo1);

        header2 = new JTableHeader();
        header2.setBounds(0,0,700,30);
        header2.setBackground(new Color(108, 108, 108));
        add(header2);

        tipoReporte = new JLabel("Tipo reporte:");
        tipoReporte.setBounds(10,32,300,30);
        add(tipoReporte);//agrega la etiqueta a la interfaz

        bg = new ButtonGroup();

        individual = new JRadioButton("Individual");
        individual.setBounds(100, 32, 100, 30);
        individual.addActionListener(this); // Escuchar eventos de acción
        add(individual);
        bg.add(individual);

        general = new JRadioButton("General");
        general.setBounds(200, 32, 100, 30);
        general.addActionListener(this); // Escuchar eventos de acción
        general.setSelected(true);
        add(general);
        bg.add(general);

        ciEquipoR = new JLabel("   C.I del Responsable                  Cantidad equipos                   Monto total (Bs.)");
        ciEquipoR.setBounds(10,75,500,30);
        add(ciEquipoR);

        // JTextArea con padding
        ciEquipoRAText = new JTextArea();
        ciEquipoRAText.setLineWrap(false); // Ajustar texto automáticamente
        ciEquipoRAText.setWrapStyleWord(false); // Ajustar por palabra
        ciEquipoRAText.setEditable(false);
        ciEquipoRAText.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding de 10px en cada lado

        // JScrollPane para el JTextArea
        scrollpane1 = new JScrollPane(ciEquipoRAText);
        scrollpane1.setBounds(10,100,500,100);
        add(scrollpane1);

        totalEquipos = new JLabel("Totalizacion:");
        totalEquipos.setBounds(10,210,250,30);
        add(totalEquipos);



        totalEquipos1 = new JLabel("0");
        totalEquipos1.setBounds(10,230,250,30);
        add(totalEquipos1);

        totalEquipos2 = new JLabel("0");
        totalEquipos2.setBounds(10,250,250,30);
        add(totalEquipos2);

        continuar = new RoundButton("Continuar",20,20);
        continuar.setBounds(557,300,100,30);
        continuar.setBackground(new Color(200, 196, 196));
        add(continuar);
        continuar.addActionListener(this);//activa la funcionalidad del boton

        cargarDatosDesdeArchivo(archivo);

    }

    private void cargarDatosDesdeArchivo(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            StringBuilder contenido = new StringBuilder();

            cantidadTotalEquipos = 0; // Inicializa las variables
            cantidadTotalCostoUnitario = 0;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("#");
                if (partes.length == 6) {
                    String ci = partes[5];
                    String ct = partes[1];
                    String mu = partes[2];
                    contenido.append(String.format("%-50s %-50s %-20s%n", ci, ct, mu));
                    cantidadTotalEquipos += Integer.parseInt(ct); // Sumar cantidad de equipos
                    cantidadTotalCostoUnitario += Integer.parseInt(mu); // Sumar costo unitario
                }
            }

            // Mostrar contenido en el JTextArea
            ciEquipoRAText.setText(contenido.toString());
            ciEquipoRAText.setCaretPosition(0); // Mostrar desde el inicio

            // Actualizar los valores en las etiquetas
            totalEquipos1.setText(String.valueOf(cantidadTotalEquipos)+" equipos");
            totalEquipos2.setText(String.valueOf(cantidadTotalCostoUnitario)+" Bs.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continuar) {
            ICentro centro = new ICentro();
            centro.setVisible(true);
            centro.setBounds(0, 0, 700, 400);
            centro.setLocationRelativeTo(null);
            dispose(); // Cierra la ventana actual
        }

        // Manejo de acciones para los botones de radio (opcional)
        if (e.getSource() == individual) {
            // Abrir nueva ventana
            IReporte nuevaVentana = new IReporte();
            nuevaVentana.setVisible(true);
            nuevaVentana.setBounds(0, 0, 700, 400); // Dimensiones de la nueva ventana
            nuevaVentana.setLocationRelativeTo(null);

            // Cerrar ventana actual
            dispose();
        } else if (e.getSource() == general) {
            //System.out.println("Opción seleccionada: General");
        }
    }

    public static void main(String[] args) {
        IReporte2 reporte = new IReporte2("","","");
        reporte.setTitle("IReporte");
        reporte.setBounds(0,0,700,400);//dimension de la interfaz
        reporte.setVisible(true);//la interfaz es visible
        reporte.setLocationRelativeTo(null);//coloca la interfaz en el medio de la pantalla
        reporte.setResizable(false);//usuario no modifica tamaño de la interfaz
    }

}

