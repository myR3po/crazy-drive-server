package tp.webservice.rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "vehicule")
@XmlType(propOrder = {"x","y","theta","vitesse"})
public class Vehicule {

  private static final double ROTATION = 5.0 ;
  private static final double ACCELERATION = 2 ;
  private static final double DESCELERATION = 1 ;
	
	private int x;
	private int y;
	private double vitesse;
	private double theta;
	
	public Vehicule(){
		
	}
	
	public Vehicule(int x0, int y0, double r0, double v0){
		x=x0;
		y=y0; 
		theta=r0;
		vitesse = r0 ;
	}

	public void tournerGauche(){
		theta -= ROTATION ;
	}
	
	public void tournerDroite(){
		theta += ROTATION ;
	}
	
	public void avancer(){
		double arg = theta*(Math.PI/180) ;
		x += Math.cos(arg) * vitesse ;
		y += Math.sin(arg) * vitesse ;
		if(vitesse < 50)
			vitesse += ACCELERATION ;
	}
	
	public void reculer(){
		double arg = theta*(Math.PI/180) ;
		x += Math.cos(arg) * vitesse ;
		y += Math.sin(arg) * vitesse ;
		if(vitesse > -10)
			vitesse -= ACCELERATION ;
		
	}
	
	public void roueLibre(){
		
		if(vitesse > 0){
			vitesse -= DESCELERATION ;
		}
		else if(vitesse < 0){
			vitesse += DESCELERATION ;
		}
		
		double arg = theta*(Math.PI/180) ;
		x += Math.cos(arg) * vitesse ;
		y += Math.sin(arg) * vitesse ;
		
	}

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public double getVitesse() {
    return vitesse;
  }

  public void setVitesse(double vitesse) {
    this.vitesse = vitesse;
  }

  public double getTheta() {
    return theta;
  }

  public void setTheta(double theta) {
    this.theta = theta;
  }

  @Override
	public String toString() {
		return new StringBuffer("x:").append(this.getX())
				.append(",y:").append(this.getY())
				.append(",angle:").append(this.getTheta()).append(",vitesse:")
				.append(this.getVitesse()).toString();
	}

}
