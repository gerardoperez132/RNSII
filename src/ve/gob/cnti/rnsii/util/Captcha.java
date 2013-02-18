package ve.gob.cnti.rnsii.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * Esta clase se encarga de crear un captcha y guardar el valor del
 * string en la sesion para su uso el LoginControlador
 *
 * @author Joaquín Pereira
 * 
 */
@SuppressWarnings("serial")
public class Captcha extends ActionSupport implements SessionAware {
	//Número de caracteres a mostrar en la imagen del captcha
	private int NUM = 6;
	//Ancho y altura de la imagen
	private int width = 165;
	private int heigth = 35;
	//Distancia entre caracteres
	private int distance_charc = 28;
	//Caracteres elegible
	String elegibleChars = "ABCDEFGHJKLMPQRSTUVWXYabcdefhjkmnpqrstuvwxy23456789";
	
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private InputStream imageStream;
    @SuppressWarnings("rawtypes")
	private Map session;

    public String getCheckCodeImage(String str, int show, ByteArrayOutputStream output) {
        Random random = new Random();        
        BufferedImage image = new BufferedImage(width, heigth, BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Arial", Font.BOLD, 24);
        int distance = distance_charc;
        Graphics d = image.getGraphics();
        d.setColor(Color.LIGHT_GRAY); 
        d.fillRect(0, 0, image.getWidth(), image.getHeight());
        d.setColor(Color.BLACK);
        d.setFont(font);
        String checkCode = "";
        char tmp;
        int x = -distance;
        for (int i = 0; i < show; i++) {
            tmp = str.charAt(random.nextInt(str.length() - 1));
            checkCode = checkCode + tmp;
            x = x + distance;
            d.setColor(new Color(random.nextInt(100) + 50, random.nextInt(100) + 50, random.nextInt(100) + 50));
            d.drawString(tmp + "", x, random.nextInt(image.getHeight() - (font.getSize())) + (font.getSize()));
        }              
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(random.nextInt(image.getWidth()), random.nextInt(image.getHeight()), random.nextInt(image.getWidth()),
                    random.nextInt(image.getHeight()));
        }
        d.dispose();
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            log.warn("Error creando Captcha: ", e);
        }
        return checkCode;
    }

    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	
    	//Creando el Stream de salida que recibira los datos de la imagen    	
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        //Generando la imagen y el string del captcha
        String checkCode = getCheckCodeImage(elegibleChars, NUM, output);
        
        //Guardando en la sesion el string del captcha
        session.put("captcha", checkCode);
        
        //Guardando la imagen
        imageStream = new ByteArrayInputStream(output.toByteArray());
        
        //cerrando el flujo
        output.close();
        
        return SUCCESS;
    }

    public InputStream getImageStream() {
        return imageStream;
    }

    @SuppressWarnings("rawtypes")
	public void setSession(Map session) {
        this.session = session;
    }
}