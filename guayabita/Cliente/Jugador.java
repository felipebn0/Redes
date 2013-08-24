
package Cliente;

public class Jugador {
    private byte dado;
    private byte dinero1; 
    private byte dinero2; 
    private byte Fondo;
    private byte apuesta1;
    private byte apuesta2;
    private byte Turno;
	public Jugador(int i, int j, int k, int l,
			int m, int n, int o) {
		super();
		this.dado = (byte) i;
		this.dinero1 =(byte) j;
		this.dinero2 =(byte) k;
		Fondo =(byte) l;
		this.apuesta1 =(byte) m;
		this.apuesta2 =(byte) n;
		Turno =(byte) o;
	}
	public byte getDado() {
		return dado;
	}
	public void setDado(byte dado) {
		this.dado = dado;
	}
	public byte getDinero1() {
		return dinero1;
	}
	public void setDinero1(byte dinero1) {
		this.dinero1 = dinero1;
	}
	public byte getDinero2() {
		return dinero2;
	}
	public void setDinero2(byte dinero2) {
		this.dinero2 = dinero2;
	}
	public byte getFondo() {
		return Fondo;
	}
	public void setFondo(byte fondo) {
		Fondo = fondo;
	}
	public byte getApuesta1() {
		return apuesta1;
	}
	public void setApuesta1(byte apuesta1) {
		this.apuesta1 = apuesta1;
	}
	public byte getApuesta2() {
		return apuesta2;
	}
	public void setApuesta2(byte apuesta2) {
		this.apuesta2 = apuesta2;
	}
	public byte getTurno() {
		return Turno;
	}
	public void setTurno(byte turno) {
		Turno = turno;
	}
    
    
}