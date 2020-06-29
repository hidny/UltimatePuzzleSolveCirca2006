
public class Piece {
	private char side[] = new char[4];
	
	private int fp = 0;
	
	/*Pour les coordonnées des pièces*/
	private int x = 0;
	private int y = 0;
	
	public void setX(int x)	{this.x = x;}
	public void setY(int y)	{this.y = y;}
	public int getX()	{return x;}
	public int getY()	{return y;}
	
	/*public boolean sideconnect[] = new boolean[4];*/		/*Pour verifier si la piece est connecté*/
	
	Piece(char top, char right, char bottom, char left) {
		side[0] = top;
		side[1] = right;
		side[2] = bottom;
		side[3] = left;
	}
	public static Piece[] getPieces() {
		Piece UtimatePuzzlePieces[] = new Piece[16];
		//			FP =        0    1    2    3
		UtimatePuzzlePieces[0] = new Piece('A', 'C', 'c', 'd');
		UtimatePuzzlePieces[1] = new Piece('C', 'D', 'd', 'c');
		UtimatePuzzlePieces[2] = new Piece('C', 'D', 'b', 'c');
		UtimatePuzzlePieces[3] = new Piece('B', 'B', 'a', 'd');
		
		UtimatePuzzlePieces[4] = new Piece('C', 'B', 'b', 'a');
		UtimatePuzzlePieces[5] = new Piece('C', 'C', 'c', 'a');
		UtimatePuzzlePieces[6] = new Piece('C', 'B', 'd', 'b');
		UtimatePuzzlePieces[7] = new Piece('C', 'B', 'c', 'd');
		
		UtimatePuzzlePieces[8] = new Piece('B', 'A', 'a', 'c');
		UtimatePuzzlePieces[9] = new Piece('B', 'D', 'a', 'b');
		UtimatePuzzlePieces[10] = new Piece('C', 'D', 'a', 'a');
		UtimatePuzzlePieces[11] = new Piece('A', 'C', 'c', 'a');
		
		UtimatePuzzlePieces[12] = new Piece('D', 'A', 'd', 'b');
		UtimatePuzzlePieces[13] = new Piece('B', 'B', 'c', 'd');
		UtimatePuzzlePieces[14] = new Piece('A', 'A', 'c', 'b');
		UtimatePuzzlePieces[15] = new Piece('D', 'A', 'a', 'c');
		
		/*Note: FP=(devrait etre PR) Position de rotation 
		 * lorsque FP =0 ou 1, Le cote du piece est convexe
		 * lorsque FP = 2 ou 3, Le cote du piece est concave
		 * 
		 * Voici une Piece:
				 *      FP=0
				 *      /   \
				 *     |    |          
				 * ___ _\  /____                            
				 * |  _         |     
				 * |_| |_       |   _   
				 *  _   _|      |__| |  FP=1                    
		   Fp=3  * | |_|/\      ___  _|                                         
				 * |  /    \    | |_|
				 * |/_ _   _\   |      
				 * |____| |_____|     
				 *		FP=2
				 * 
		 * 
		 * */
		
		return UtimatePuzzlePieces;	/*Retourne tout les pieces*/
	}
	
	public void rotClock() {	fp--;	if(fp<0)	fp=3; }
	public void rotCounter() {	fp++;	if(fp>=4)	fp=0; }
	public void rot180() {		
		fp+=2;	
		if(fp==4)	fp=0; 
		if(fp==5) fp=1;
	}
	
	/*Le piece A est sur B*/
	public static boolean checkVertconnect(Piece a, Piece b) {
		if(a.side[a.getBottom()] + 'A' - 'a' == b.side[b.getTop()] ||  a.side[a.getBottom()] + 'a' - 'A'  == b.side[b.getTop()]) {
			/*a.sideconnect[2] = true;
			b.sideconnect[0] = true;		Peut-etre je vait avoir des variables qui dissent s'ils sont connecté*/
			return true;
		}	else
			/*a.sideconnect[2] = false;
			b.sideconnect[0] = false;*/
			return false;
	}
	/*Le piece A est gauche de B*/
	public static boolean checkHorconnect(Piece a, Piece b) {
		if(a.getSide()[a.getRight()] + 'A' - 'a' == b.getSide()[b.getLeft()] ||  a.getSide()[a.getRight()] + 'a' - 'A'  == b.getSide()[b.getLeft()]) {
			/*a.sideconnect[1] = true;		PAS BESOIN?
			b.sideconnect[3] = true;*/
			return true;
		}	else	
			/*a.sideconnect[1] = false;
			b.sideconnect[3] = false;*/
			return false;
	}
	/*Pour voir si deux pieces sont les memes*/
	public static boolean comparePiece(Piece a, Piece b) {
		for(int i=0; i<a.getSide().length; i++) {
			if( a.getSide()[i] != b.getSide()[i]) {
				return false;
			}
		}
		return true;
	}
	/* Pour voir si une piece peut se connecter sur un côté spécifique
	public static boolean Checkpiececonect(Piece a, char c) {
		for(int i=0; i<a.getSide().length; i++) {
			if( a.getSide()[i] != b.getSide()[i]) {
				return false;
			}
		}
		return true;
	}
	*/
	/**/
	public int getFp() {	return fp;	}
	
	public void setFp(int fp) {	
		this.fp = fp; 
		}
	public static boolean ChercheTrou(Piece p, int fp) {
		if(p.getSide()[fp] <= 'z' && p.getSide()[fp] >= 'a')	return true;
		else return false;
	}
	public char[] getSide() {	return side;	}
	
	public int getTop() {	return getFp();			}
	public int getRight() {	return (1+ getFp())%4;	}
	public int getBottom() {return (2+ getFp())%4;	}
	public int getLeft() {	return (3+getFp())%4;	}
	

	
	public static int checkTop(int fp ) {
		if( fp <2)	return 1;//cote de la Piece convexe
		
		else return 0;//cote de la Piece concave
	}
	public static int checkRight(int fp ) {
		if( (1+ fp) %4 <2)	return 1;//cote de la Piece convexe
		
		else return 0;//inside
	}
	public static int checkBottom(int fp ) {
		if( (2 + fp) %4 <2)	return 1;//cote de la Piece convexe
		
		else return 0;//cote de la Piece concave
	}
	public static int checkLeft(int fp ) {
		if( (3+ fp) %4 <2)	return 1;//cote de la Piece convexe
		
		else return 0;//cote de la Piece concave
	}
	
	public boolean checksides(char c) {
		for(int i=0; i<4; i++) {
			if(side[i] == c)	return true;
		}
		return false;
	}
	
	
	
	public boolean comparesides(char c, char d) {	/*Retoure vrai si ils sont semblable*/
		if( c == d + 'A' - 'a')		return true;
		else if( c == d - 'A' + 'a')	return true;
		else			return false;
	}
	
	public static void main(String[] args) {	/*un test, pour les fonctions de la piece.*/
		Piece a = new Piece('C', 'A', 'd', 'c');
		Piece b = new Piece('D', 'C', 'c', 'd');
		Piece c = new Piece('D', 'C', 'a', 'a');
		if(checkVertconnect( a,  b) )	System.out.println("Ca fonctionne!(1)");
		else	System.out.println("Quest ce que j'ai fait?");
		
		if(checkHorconnect(a, c))	System.out.println("Ca fonctionne!(2)");
		a.rotClock();
		if(!checkHorconnect( a,  b) )	System.out.println("Ca fonctionne!(3)");
	}
	
}
