
import java.util.*;
/*À Faire:	Séparer en class logique.
 * Créer un bon GUI qui te laisse jouer avec les pieces.
 * */
public class PieceOrganise2 {

	static Piece UltimatePuzzlePieces[] = Piece.getPieces();
	/*Fonction pour avoir les détailles des cotés de chaque piece*/
	
	/*Fonction pour copier un tableau sans copier le pointeur (Il y a probablement un déjà faite mais je reinvente la roue...*/
	public static boolean[] tabcopie(boolean[] listof9by9) {
		boolean []killlist = new boolean[listof9by9.length];
		for(int i=0; i<listof9by9.length;i++)
			killlist[i] = listof9by9[i];
		return killlist;
	}
	
	/*Fonction pour créer une liste de 9 pieces sur 16 avec un tableau boolean. */
	
	public static boolean[] getnew3by3list() {
		boolean tab[] = new boolean[16];
		for(int i=0; i<9; i++) {
			tab[i] = true;
		}
		/*if(tab[0] == true)	System.out.println("tab[0] est vrai ici");*/	
		return tab; // Tab = 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0
	}
	
	/*Fonction qui utilise la liste ci-desus pour avoir 9 pieces à jouer avec*/
	
	/* ex:
	 * Tab = 1 1 1 1 0 1 1 1 0 1  0  0  0  1  0  0
	 *Piece= 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
	 * 
	 * List de 9 pieces: 1 2 3 4 6 7 8 10 14
	 * */
	
	/*La methode est a tester*/
	public static Piece[] get16Pieces(boolean listof9by9[], int combinaisonfaite) {
		Piece Piecesfor3by3plusrestant[] = new Piece[16];
		boolean listde9[] = tabcopie(listof9by9);
		int i=0;
			for(int j=0; j<16; j++) {
				if(listde9[j] == true) {
					listde9[j]= false;
					Piecesfor3by3plusrestant[i++] = UltimatePuzzlePieces[j];
				}
			}
		
		boolean listdesrestes[] = tabcopie(listof9by9);
			for(int j=0; j<16; j++) {
				if(listdesrestes[j] == false) {
					listdesrestes[j] = true;
					Piecesfor3by3plusrestant[i++] = UltimatePuzzlePieces[j];
				}
			}
		if(i!=16) {
			System.out.println("Il y a eu une erreur quelquepart dans la methode get16pieces"); 
		}
			/*Un test*/
			/*
			if(listof9by9[0] == true) {
				for(i=0; i<9; i++)
					System.out.println(Piecesfor3by3[i].getLeft() );
			}
			*/
			/*Pour savoir la pourcentage de completion:*/
			if(combinaisonfaite %650 == 0) {	/*65/6435 est approx. 1%*/
				System.out.println("\nPourcentage de completion: " + (100*combinaisonfaite) / 6435 + "%");
			}
			/*System.out.println("             " +combinaisonfaite+ "\n\n");*/
			
		return Piecesfor3by3plusrestant;
	}

	/*Fonction Pour incrementer list des pieces que le programme joue avec de facon
	 * systematique et efficace et pour ne pas sauter des combinaisons
	 * 
	 * nombre de combinaison: (15) = 6354?
	 *  					  (8 )
	 * ex:
	 * 1 1 1 1 0 1 1 1 0 1  0  0  0  1  0  0
	 * incrementé:
	 * 1 1 1 1 0 1 1 1 0 1  0  0  0  0  1  0
	 * incrementé:
	 * 1 1 1 1 0 1 1 1 0 1  0  0  0  0  0  1
	 * incrementé:
	 * 1 1 1 1 0 1 1 1 0 0  1  1  0  0  0  0
	 * Note le 1 à la gauche réprésente la piece clé qui est toujours là.
	 * 
	 * Le Programme essai de mettre les pieces autour de la piece clé en sorte que tout les combinaisons
	 * possible sont essaiyer
	 * 
	 * 
	 * */
	public static boolean[] incrementlist(boolean listof9by9[]) {
		int dernier=0;
		int nemedelagaucheabouger =1;
	
		
		for(nemedelagaucheabouger =1; nemedelagaucheabouger < 9; nemedelagaucheabouger++) {
				for(int i = 0; i<16; i++) {
					if(listof9by9[i] == true)	dernier= i;
				}
				
				if(dernier < 16 - nemedelagaucheabouger) {
					listof9by9[dernier] = false;
					
					for(int k = 1; k<=nemedelagaucheabouger; k++)
						listof9by9[dernier+k] = true;
					
					return listof9by9;
				} else {
					listof9by9[16-nemedelagaucheabouger] = false;
					continue;
				}
		}
			
		System.out.println("Tout les combinaison on été fait!"); /*Si le 1 à la gauche est bouger, le programme est terminé*/
		for(int over=0; over<16; over++) {
			listof9by9[over] = false;
		}
		return listof9by9;

	}
	/*Fonction pour simplement imprimmer la liste des piece que le programme
	 * peut jouer avec. Devrait etre utilise lors des test
	 * 
	 * ex:
	 * Ça imprime:		1 1 1 1 0 1 1 1 0 1  0  0  0  1  0  0
	 * */
	public static void printtab(boolean listof9by92[]) {
		for(int i=0; i<16; i++) {
			if(listof9by92[i] == true)	System.out.print("1  ");
			else						System.out.print("0  ");
		}
		System.out.println();
	
	}
	
	public static void main(String[] args) {
		
		/*LaisseUtilisateurPigerCle();		Peut-etre au futur... il n'y a pas de besoin maintenant*/
		
		Searchsolution4by4();		/*Pour l'utiliser dans une fonction magique qui fait le travaille
										le nom du méthode va changer*/
		System.out.println("\n Le programme est complèté");
		
		System.out.println("Il y a " +Nombredesolution3par3oulapiececleestaucoin /*1721*/ + " facons d'avoir une solution 3*3 où la piece clé est sur un des quatre coins");
		System.out.println("Le nombre de solutions que le programme a put trouver est "+ solution4par4);
	}
	
	/* Voici la fonction clé:
	 * 
	 *  public static Searchsolution4by4(piece[]);	
	 * 
	 * -Doit trouver TOUT les combinaison possible de 8 pieces (+ la piece clé a haut gauche) sur 16 ensuite doit appeler d'autre méthode récursive
	 * pour entourer la solution 3*3 afin de trouvé une solution.
	 * 
	 * -La  piece clé doit etre au coin hautgauche.
	 *
	 * Pour glorifié le programme: La piece clé devrait etre choisie par l'utilisateur.
	 * -La premiere piece subit 3 rotation pour chaque fois que ça essait de trouver une solution 3*3
	 * 	
	 * 
	 * NOTE:
	 * -si le programme est trop lent, je chance l'algorithme en quelquechose de plus humain. 
	 * ou je la convertie en program de c++ (ça serait assez facile)
	 * Mais, Ce n'est pas le cas! :)
	 * 
	 * 
	 * 
	 * -La boite que j'ai acheté dit qu'il y a 48 solutions.
	 * 
	 * Il y a réellement 12 solutions mais chaque solution peut compté pour 4 car il
	 * y a 4 rotations du meme solution possible.
	 * 
	 * */
	
	static int combo =0; /*compte combien de combinaison que le programme à faite
	le variable nous durant les test.*/
	static int KEY = 0;
		public static void Searchsolution4by4() {
			
			boolean tab[] = getnew3by3list();		/*pour les 9 pieces*/
			
			
			
			Piece listde9piecePluslereste[] = get16Pieces( tab, ++combo); /*Pour recevoir 9 pieces à jouer avec*/
			
			int used[] = new int[16];	/*Nombre de piece sur les 9 déjà utilisé + le restant*/
			used[0] = 0;/*la piece clé est toujours utilisé*/
			/*used[0-8] = une valeur 0-8*/
			/*used[9-16] = une valeur 9-16*/
			//System.out.println("Searchsolution3by3"); /*Pour tester*/
			
			if( tab[0] == false)	System.out.println("What!"); /*Pour tester pour un bougue*/
			
			/*NOTE importante:	Pour tester, j'ai mit en commentaire le code nécessaire pour chercher avec chaque
			 * combinaison 3*3 ensuite trouver une solution 4*4. Au-lieu, Ça cherche avec seulement un combinaison de 9 pieces
			 * 
			 */
			/* Il y a 7 tableau contenant les coordonnees x et y de chaque piece a inserer en ordre du premier au dernier
			 * (La piece clé est déja inséré)
			 * 
			 * */
										/*Note: chaque tableau est utilisé par une différente méthode de récursion*/
											/*Tableau 0:*/
			 int [][][]tabdecoordonne = { { {1, 0, 1, 0, 1, 2, 2, 2},	// Les coordonnées x des 8 premiers piece a insèrer (1/1)
				 							{0, 1, 1, 2, 2, 2, 1, 0}},	// Les coordonnées y des 8 premiers piece a insèrer 
											/*Tableau 1:*/		
				 						{	{0, 1, 2},// Les coordonnées x Possible des 3 prochaine pieces a insèrer (1/2)
				 							{3, 3, 3}// Les coordonnées y Possible des 3 prochaine pieces a insèrer 
			    						},
										/*Tableau 2:*/				    						
				 						{  {3, 3, 3, 3},// Les coordonnées x et y Possible des derniers pieces a insèrer (1/4)
				 						   {3, 2, 1, 0}
				 						}, 
										/*Tableau 3:*/	
				 						{	{-1, -1, -1, -1},// Les coordonnées x et y Possible des derniers pieces a insèrer  (2/4)
				 							{3, 2, 1, 0}
				 						},
										/*Tableau 4:*/
				 						{
				 							{2, 1, 0},	// Les coordonnées x et y Possible des 3 prochaine pieces a insèrer  (2/2)
				 							{-1, -1, -1}
				 						},
										/*Tableau 5:*/
				 						{
				 							{-1, -1, -1, -1},// Les coordonnées x et y Possible des derniers pieces a insèrer   (3/4)
				 							{-1, 0, 1, 2}
				 						},
										/*Tableau 6:*/
				 						{
				 							{3, 3, 3, 3},// Les coordonnées x et y Possible des derniers pieces a insèrer  (4/4)
				 							{-1, 0, 1, 2}
				 						}
				 						
			 							};
			 
/*   méthode d'entourage 1:		 méthode d'entourage 2:	                                              
 *   *  1  8  15             15 *  1  8                                     
 *   2  3  7  14             14 2  3  7                                        
 *   4  5  6  13             13 4  5  6                                       
 *   9 10 11  12             12 9 10 11                                    
 *   méthode d'entourage 3:		 méthode d'entourage 4:	                                              
 *12 11 10  9                   11 10  9  12          
 *13  *  1  8                    *  1  8  13                                  
 *14  2  3  7                    2  3  7  14                                    
 *15  4  5  6                    4  5  6  15                                    
 *                                                                                                      
 * Pour méthode d'entourage1 on utilise le tableau de coordonné:
 * 0 ensuite 1 ensuite 2
 
 * Pour méthode d'entourage2 on utilise le tableau de coordonné:
 * 0 ensuite 1 ensuite 3                                                                          
 
 * Pour méthode d'entourage3 on utilise le tableau de coordonné:
 * 0 ensuite 2 ensuite 5
 
 * Pour méthode d'entourage4 on utilise le tableau de coordonné:
 * 0 ensuite 2 ensuite 6 *                                                                          
 
 * */


			 int Tabdesposs[][][] = new int [15][2][10];
			 /*Le premier 15 est pour le nombre de piece a inserer autour du piece clé. 
			  * Le 2 est pour le numero du piece et la rotation de la piece 
			  * (Cest pas necessaire car les méthodes redéfinie cette dimension...)
			  * 2 = [0]:La pieces utilisé pour une possibilité
			  *		[1]:La rotation utilisé pour une possibilité
			  
			  *10 = list max des possibilités
			  *Si il y a plus que 10 possibilités d'insération, il y a des problemes.
			  * */
			
			 listde9piecePluslereste[KEY].setX(0); /*Insération de la Piece clé.*/
			 listde9piecePluslereste[KEY].setY(0);
			 listde9piecePluslereste[KEY].setFp(0);
			 while( tab[KEY] == true ) {  //Tant et aussi longtemps qu'on joue avec la piece clé...
				/*Boucles rotation du piece clé*/
				for( int Rotationkey =0; Rotationkey<4; Rotationkey++) {
					//System.out.println("Premiere essay");	//Pour tester
					
					EssaitToutcombinaisonavec9pieceaumilieur(1, Tabdesposs, listde9piecePluslereste, used, tabdecoordonne);
			
					listde9piecePluslereste[KEY].rotCounter();	
					/*rotation de 90 degre au sens opposee a l<aiguille apres chaque essai*/
				}
				tab = incrementlist(tab);
				
				listde9piecePluslereste = get16Pieces( tab, combo);
				combo++;	/*combo et pour compter le nombres de combinaison faite*/
			}
			/*Pour tester une list:
			 * 
			 * for(int i=0; i<16; i++)
			if( tab[i] == false)	System.out.println("tab["+i+"] est faux");
		
		
			Note-On devrait creer une toile Des lien commencant par le haut gauche*/
			/* Ou autrement dit: des fonctions qui mene a des sousfonctions ou la récursion
			 * 
			jusquau temps que le programme à trouvé la solution
			
			/* Pour tester si on a neuf pieces: (Devrait avoir un fonction test)
			/*for(int i=0; i<9; i++) {
				System.out.println(listof9[i].getLeft() );
			}
			
	
			/*Piece key = listof9[0];	/*listof9[0] is the key piece that will never move ensuring us that the prog. will go throught everything
		
			/*
			
			
			
			
			/*Pour le list des Pieces que l'ordinateur utilise pour resoudre le 3*3
			Cest bon*/

		}
		static int PasDePiece = -123;
		static Scanner sc = new Scanner(System.in);
		static int PIECE = 0;
		static int ROT = 1;
		static int X = 0;
		static int Y = 1;
		
		static int Nombredesolution3par3oulapiececleestaucoin = 0;
		
		public static void EssaitToutcombinaisonavec9pieceaumilieur(int NPiecesUtilise, int Tabdesposs[][][], Piece listde16pieces[], int[] listPiecesUtilise, int [][][]tabdecoordonne) {
			//System.out.println("\nDebut de la methode de la " + NPiecesUtilise + "eme piece a inserer");
				int x;/*Coordonné x et y p/r au piece clé*/
				int y;
			/*//Pour verifier:
			if(listde16pieces[0].getFp() == 0) {
				System.out.println("La piece cl/ peut etre tourn/ de cette facon meme si il n<y a de solution comme ceci");
				sc.next();
			}*/
				
			/*tableau des possibilités: */
			if(NPiecesUtilise<9) {
				x = tabdecoordonne[0][X][NPiecesUtilise-1];	/*coordonné prit du tableau de coordonné*/
				y = tabdecoordonne[0][Y][NPiecesUtilise-1];
				
				Tabdesposs[NPiecesUtilise - 1]= fitPiece(listde16pieces, listPiecesUtilise,  NPiecesUtilise, x, y);
				
				if(Tabdesposs[NPiecesUtilise - 1][0][0] != PasDePiece) {
				for(int i=0; i<Tabdesposs[NPiecesUtilise - 1][0].length; i++) { /*Tabdesposs[NPiecesUtilise - 1][0].length = le nombre de possibilité insération de pieces*/
					
					
						
						listPiecesUtilise[NPiecesUtilise] = Tabdesposs[NPiecesUtilise - 1][PIECE][i];/*Ca ne compte pas la clé*/
						listde16pieces[listPiecesUtilise[ NPiecesUtilise]].setFp(Tabdesposs[NPiecesUtilise - 1][ROT][i]);
						
						listde16pieces[listPiecesUtilise[ NPiecesUtilise]].setX(x);
						listde16pieces[listPiecesUtilise[ NPiecesUtilise]].setY(y);
						
						/*Cette fonction doit etre APRÈS les définitions.*/
						//Printinfo(x, y, i, NPiecesUtilise, listde16pieces, used, Tabdesposs);
						
						/*Un test pour vérifié que chaque piece inséré est différent*/
						for(int k=0; k<=NPiecesUtilise; k++) {
							for(int l=0; l<=NPiecesUtilise; l++) {
								if(l != k) {
									if(Piece.comparePiece(listde16pieces[listPiecesUtilise[ k]], listde16pieces[listPiecesUtilise[ l]] )) {
										System.out.println("La Piece "+ l + " est egale à la piece "+ k);
										
										//sc.next(); /*Une pause pour vérifié la validité de la solution*/
										
									}
								}
							}
						}
						
						//if(Pieces ne se connecte pas XXX)	(Pas besoin)
						EssaitToutcombinaisonavec9pieceaumilieur(NPiecesUtilise +1, Tabdesposs, listde16pieces, listPiecesUtilise, tabdecoordonne);
					
				
				
				}
				} else {
					//System.out.println("Il n'y a pas de possibilite d'insération de la " +NPiecesUtilise+"eme piece a "+x+" "+y );	
				}
			} else {
				//System.out.println("Solution:");
				//Printsolution(listde16pieces, 3);
				Nombredesolution3par3oulapiececleestaucoin++;
				//if(Nombredesolution3par3oulapiececleestaucoin== 1000)
					//sc.next(); /*Une pause apres l'affichage de la solution*/
				/*
				 * Lorsqu'on trouve solution 3*3---->
				 * -Doit verifier si on peut entourer les piece 3*3
				 * Doit utiliser les quatre coins.
				 * 	-si par chance il trouve la solution, On l'imprime!(au moin tab 4*4)
				 * 
				 * -On devrait avoir une methode differente pour insérer les derniers pieces.
				 * */
				Essaitdecompleterautour(NPiecesUtilise, Tabdesposs, listde16pieces, listPiecesUtilise, 1, tabdecoordonne);
				Essaitdecompleterautour(NPiecesUtilise, Tabdesposs, listde16pieces, listPiecesUtilise, 4, tabdecoordonne);
				
			}
			//System.out.println("Fin de la methode de la " + NPiecesUtilise + "eme piece a inserer");
			//sc.next(); //Une Pause pour les test
			//System.out.println("\n" +NPiecesUtilise+ " ");	Pour tester
	}
	static int solution4par4=0;
	public static void Essaitdecompleterautour(int NPiecesUtilise, int Tabdesposs[][][], Piece listde16pieces[], int[] used, int indice, int [][][]tabdecoordonne) {
			//System.out.println("\nDebut de la methode de l'entourage " + NPiecesUtilise + "eme piece a inserer");
			int x;/*Coordonné x et y p/r au piece clé*/
			int y;
			/*tableau des possibilités: */
			if(NPiecesUtilise>=9 && NPiecesUtilise<12) {
				x = tabdecoordonne[indice][X][NPiecesUtilise-9];	/*coordonné prit du tableau de coordonné*/
				y = tabdecoordonne[indice][Y][NPiecesUtilise-9];
				
				Tabdesposs[NPiecesUtilise - 1]= fitPiece(listde16pieces, used,  NPiecesUtilise, x, y);
				
				if(Tabdesposs[NPiecesUtilise - 1][0][0] != PasDePiece) {
				for(int i=0; i<Tabdesposs[NPiecesUtilise - 1][0].length; i++) { /*Tabdesposs[NPiecesUtilise - 1][0].length = le nombre de possibilité insération de pieces*/
					
						used[NPiecesUtilise] = Tabdesposs[NPiecesUtilise - 1][PIECE][i];/*Ca ne compte pas la clé*/
						listde16pieces[used[ NPiecesUtilise]].setFp(Tabdesposs[NPiecesUtilise - 1][ROT][i]);
						
						listde16pieces[used[ NPiecesUtilise]].setX(x);
						listde16pieces[used[ NPiecesUtilise]].setY(y);
						
						/*Cette fonction doit etre APRÈS les définitions.*/
						//Printinfo(x, y, i, NPiecesUtilise, listde16pieces, used, Tabdesposs);
						
						/*Un test pour vérifié que chaque piece inséré est différent*/
						for(int k=0; k<=NPiecesUtilise; k++) {
							for(int l=0; l<=NPiecesUtilise; l++) {
								if(l != k) {
									if(Piece.comparePiece(listde16pieces[used[ k]], listde16pieces[used[ l]] )) {
										System.out.println("La Piece "+ l + " est egale à la piece "+ k);
										
										//sc.next(); /*Une pause pour vérifié la validité de la solution*/
										
									}
								}
							}
						}
						Essaitdecompleterautour(NPiecesUtilise + 1, Tabdesposs, listde16pieces, used, indice, tabdecoordonne);
						
				}	
				} else {
					//System.out.println("Il n'y a pas de possibilite d'insération de la " +NPiecesUtilise+"eme piece a "+x+" "+y );
				}
			} else {
				//Prochaine recursion
				if(indice == 1) {
					EssaitDeTrouverLaSolution(NPiecesUtilise, Tabdesposs, listde16pieces, used, 2, tabdecoordonne);
					EssaitDeTrouverLaSolution(NPiecesUtilise, Tabdesposs, listde16pieces, used, 3, tabdecoordonne);
				} else if( indice == 4) {
					EssaitDeTrouverLaSolution(NPiecesUtilise, Tabdesposs, listde16pieces, used, 5, tabdecoordonne);
					EssaitDeTrouverLaSolution(NPiecesUtilise, Tabdesposs, listde16pieces, used, 6, tabdecoordonne);
				} else {
					System.out.println("Error: Probleme d'indice");
					sc.next();
				}
			}
	}
	public static void EssaitDeTrouverLaSolution(int NPiecesUtilise, int Tabdesposs[][][], Piece listde16pieces[], int[] used, int indice, int [][][]tabdecoordonne) {
		//System.out.println("\nDebut de la methode de la completion" + NPiecesUtilise + "eme piece a inserer");
		int x;/*Coordonné x et y p/r au piece clé*/
		int y;
		/*tableau des possibilités: */
		if(NPiecesUtilise>=12 && NPiecesUtilise<16) {
			x = tabdecoordonne[indice][X][NPiecesUtilise-12];	/*coordonné prit du tableau de coordonné*/
			y = tabdecoordonne[indice][Y][NPiecesUtilise-12];
			
			Tabdesposs[NPiecesUtilise - 1]= fitPiece(listde16pieces, used,  NPiecesUtilise, x, y);
			
			if(Tabdesposs[NPiecesUtilise - 1][0][0] != PasDePiece) {
			for(int i=0; i<Tabdesposs[NPiecesUtilise - 1][0].length; i++) { /*Tabdesposs[NPiecesUtilise - 1][0].length = le nombre de possibilité insération de pieces*/
				
					used[NPiecesUtilise] = Tabdesposs[NPiecesUtilise - 1][PIECE][i];/*Ca ne compte pas la clé*/
					listde16pieces[used[ NPiecesUtilise]].setFp(Tabdesposs[NPiecesUtilise - 1][ROT][i]);
					
					listde16pieces[used[ NPiecesUtilise]].setX(x);
					listde16pieces[used[ NPiecesUtilise]].setY(y);
					
					/*Cette fonction doit etre APRÈS les définitions.*/
					//Printinfo(x, y, i, NPiecesUtilise, listde16pieces, used, Tabdesposs);
					/*Un test pour vérifié que chaque piece inséré est différent*/
					for(int k=0; k<=NPiecesUtilise; k++) {
						for(int l=0; l<=NPiecesUtilise; l++) {
							if(l != k) {
								if(Piece.comparePiece(listde16pieces[used[ k]], listde16pieces[used[ l]] )) {
									System.out.println("La Piece "+ l + " est egale à la piece "+ k);
									
									//sc.next(); /*Une pause pour vérifié la validité de la solution*/
									
								}
							}
						}
					}
					EssaitDeTrouverLaSolution(NPiecesUtilise + 1, Tabdesposs, listde16pieces, used, indice, tabdecoordonne);
					
			}	
			} else  {
				//System.out.println("Il n'y a pas de possibilite d'insération de la " +NPiecesUtilise+"eme piece a "+x+" "+y );
			}
		} else {
			//Solution Trouvé
			System.out.println("Solution 4*4 trouver!\n");
			System.out.println("Combinaison: "+ combo + "/6435");
			solution4par4++;
			System.out.println("C'est la "+	solution4par4 +"eme solution");
			Printsolution(listde16pieces, 0);
			//sc.next();//Une pause necessaire
		}
			
	}
		public static int getNumero(Piece a) {
			Piece UltimatePuzzlePiece[] = Piece.getPieces();	/*Ces pieces devrait etre static*/
			for(int i=0; i<16; i++) {
				if(Piece.comparePiece(UltimatePuzzlePiece[i], a) == true)
					return i+1;
			}
			System.out.println("Erreur: Je ne pouvais pas trouver la numero de cette piece...");
			return -1;
		}
		
		public static void Printinfo(int x, int y, int i, int NPiecesUtilise, Piece listde16pieces[], int used[], int [][][]Tabdesposs ) {
			
			System.out.print("\n" + (i+1) + 
					"eme possibilite a "
					+ x+" "+y + 
					"\n NPiecesUtilise="+NPiecesUtilise+ " Piece = "
					+ getNumero(listde16pieces[used[ NPiecesUtilise]]) );
			if(NPiecesUtilise<9) 
					System.out.println(" rot="+ Tabdesposs[NPiecesUtilise - 1][ROT][i]);
			else if(NPiecesUtilise>=9 && NPiecesUtilise<12)
					System.out.println(" rot="+ Tabdesposs[NPiecesUtilise - 1][ROT][i]);
			else if(NPiecesUtilise>=12 && NPiecesUtilise<16)
				System.out.println(" rot="+ Tabdesposs[NPiecesUtilise - 1][ROT][i]);
		
			
		}
		public static void Printsolution(Piece listde16pieces[], int dim) {
			if(dim*dim == 4) {
				for(int y = 0; y<2; y++) {
					for(int x=0; x<2; x++) {
						for(int i=0; i<9; i++) {/*Pour les 9 premiers pieces*/
							if(listde16pieces[i].getX() == x && listde16pieces[i].getY() == y)
							System.out.print(1+getNumero(listde16pieces[i]) + " " + listde16pieces[i].getFp() + " ( "+listde16pieces[i].getX()+", "+ listde16pieces[i].getY() +")     ");
						}
					}
					System.out.println();
				}
			} else if(dim*dim == 9) {
				for(int y = 0; y<3; y++) {
					for(int x=0; x<3; x++) {
						for(int i=0; i<9; i++) {/*Pour les 9 premiers pieces*/
							if(listde16pieces[i].getX() == x && listde16pieces[i].getY() == y)
							System.out.print(getNumero(listde16pieces[i]) + " " + listde16pieces[i].getFp() + " ( "+listde16pieces[i].getX()+", "+ listde16pieces[i].getY() +")     ");
						}
					}
					System.out.println();
				}
			} else {	/*La solution 4*4 ne commence pas nécessairement à (0,0)*/
				for(int y = -1; y<4; y++) {
					for(int x=-1; x<4; x++) {
						for(int i=0; i<16; i++) {/*Pour les 9 premiers pieces*/
							if(listde16pieces[i].getX() == x && listde16pieces[i].getY() == y)
							System.out.print(getNumero(listde16pieces[i]) + " " + listde16pieces[i].getFp() + " ( "+listde16pieces[i].getX()+", "+ listde16pieces[i].getY() +")     ");
						}
					}
					System.out.println();
				}
				
			}
			
		}
		/*A mettre en Piece.java*/
		public static boolean Comparecote(Piece p1 , Piece p2, int cotep1) {	/*Retourne true s<il n'y a pas de prob et return false s<il y a.*/
		  		if(cotep1 == 0) {	/*Piece 2 sur Piece 1*/
		  			if(Piece.ChercheTrou(p1, p1.getTop()) == Piece.ChercheTrou(p2, p2.getBottom()))
		  				return true;
		  			else return false;
		  		
		  		} else if(cotep1 == 1) {	/*Piece 2 à la droite de Piece 1*/
		 			if(Piece.ChercheTrou(p1, p1.getRight()) == Piece.ChercheTrou(p2, p2.getLeft()))
		  				return true;
		 			else return false;
				
		  		} else if(cotep1 == 2) {	/*Piece 2 sous  Piece 1*/
		 			if(Piece.ChercheTrou(p1, p1.getBottom()) == Piece.ChercheTrou(p2, p2.getTop()))
		  				return true;
		 			else return false;
				
		  		}	else if(cotep1 == 3) {	/*Piece 2 à la gauche de Piece 1*/
		 			if(Piece.ChercheTrou(p1, p1.getLeft()) == Piece.ChercheTrou(p2, p2.getRight()))
		  				return true;
		 			else return false;
		 			
				} else {
					System.out.println("Une grosse probleme avec la fonction comparecote");
					return false;
				}
		 	}
		 

	
		
		/*fonction pour tester*/
		/*
		public static int getTop(int fp) {	return fp;			}
		public static int getRight(int fp) {	return (1+ fp)%4;	}
		public static int getBottom(int fp) {return (2+ fp)%4;	}
		public static int getLeft(int fp) {	return (3+ fp)%4;	}
	*/
	
		public static int[][] fitPiece(Piece Piecelistof16[], int Used[], int Nused, int x, int y) {
			
			boolean fpposs[] = new boolean[4];
			
			int autour[] = new int[4];/*Enregistre le nombre du pieces qui sont autour du piece qu'on veut utiliser
										s'il y a pas a la droite du piece par ex, autour[1] = */
			/*		autour[*]      _0_
			 					3 |__ | 1
			 					    2
			*/						
			
			int nombredepiecesautour=0; /*compteur de pieces autour du piece a inserer*/
			
			/*int INSIDE =0; 	//si neccessaire
			int OUTSIDE = 1;*/
			int i =0;
			
			for(i=0; i<4; i++) {
				fpposs[i] =true;
				autour[i] = PasDePiece;
				//if(Nused>=9 && fpposs[i])	System.out.println("fpposs[i] =true :Bonjour " + Nused + " coor: " + x +" "+ y);
				
			}
			/*Nused doit etre = 1*/
		for(i=0; i< Nused; i++) {/*Ajoute condition:	if i<9:*/	/*On cherche tout les pieces qui sont déja utilisé pour savoir quel rotation la piece à insérer est logique à vérifier doit être*/
			
			/* ex:
			 * __________		__________
			 * |		|  _	|		  | on sait qu'il doit avoir un trou à la gauche pour pouvoir être 
			 * |		|_| |_  |Piece à  |	inséré à cette endroit.
			 * |	     _   _|	|insérer  |	Ce fait élimine des possibilité de comment la piece va être insérer
			 * |________| |_|	|_________| et va sauver du temps durant la prochaine itération.(Lorsque ça cherche
			 * 								 tout les facons que les pieces inutilisé peut-être inséré.)
			 * 						NOTE:	
			 * 					 Je ne suis
			 * 								pas sûr que ca sauve du temps mais cette algorithme est indentique à 
			 * 								celui de l'humain
			 * 					
			 * 								
			 * 
			 * */
			
			/*
			 * Note
			 *  x---->                  
			 *  y  -1              
			 *  |-1 0   1   2   3   4    
			 *  |   1              
			 *  |   2              
			 *  V   3              
			 *      4            
			 * LE 0 signigie la piece clé
			 * 
			 * */
			
				if(Piecelistof16[Used[i]].getY() == y && Piecelistof16[Used[i]].getX() == x) {
					System.out.print("La piece est deja utilise par le "+ i+"eme piece"+ "change l'emplacement du "+ Nused+"eme piece");
				}
			
			
				if(Piecelistof16[Used[i]].getY() == y) {	/*Meme rangée que la pièce à insérer*/
						if(Piecelistof16[Used[i]].getX() == x-1) {
							nombredepiecesautour++;
							autour[3]=i;
								
							/*Gauche du piece à insérer:*/
								if(Piecelistof16[Used[i]].getRight() < 2) {	/*testé et passé*/
										fpposs[1] = false;//outside
										fpposs[2] = false;
										/*if(getLeft(1) <2 && getLeft(2) <2)
											System.out.println("1Pas Une erreur Gauche du piece coor (outside)"+ x + " "+y);	
										else
											System.out.println("2****************Une erreur Gauche du piece coor (outside)"+ x + " "+y);
										*/
								} else if(Piecelistof16[Used[i]].getRight() >= 2) {
										fpposs[0] = false;//inside
										fpposs[3] = false;
										/*if(getLeft(0) >= 2 && getLeft(3) >=2)
											System.out.println("3Pas Une erreur Gauche du piece coor (inside)"+ x + " "+y);
										else
											System.out.println("4****************Une erreur Gauche du piece coor (inside)"+ x + " "+y);
										*/
								}
						
						} else if(Piecelistof16[Used[i]].getX() == x+1) {/*Source d'erreur probable*/
							nombredepiecesautour++;	
							autour[1] = i;
							/*Droite du piece à insérer:*/
								if(Piecelistof16[Used[i]].getLeft() < 2) { /*Droite du piece a inserer*/
										fpposs[0] = false;
										fpposs[3] = false;
										/*if(getRight(0) <2 && getRight(3) <2)
											System.out.println("5Pas Une erreur Droite du piece coor (outside)"+ x + " "+y);
										else
											System.out.println("6****************Une erreur Droite du piece coor (outside)"+ x + " "+y);
										*/
								} else if(Piecelistof16[Used[i]].getLeft() >= 2) {
										fpposs[1] = false;
										fpposs[2] = false;
										/*if(getRight(1) >= 2 && getRight(2) >= 2)
											System.out.println("7Pas Une erreur Droite du piece coor (inside)"+ x + " "+y);
										else
											System.out.println("8****************Une erreur Droite du piece coor (inside)"+ x + " "+y);
										*//*Je devrais lancé des exeptions au lieu...*/
								}
						}
				
				} else if(Piecelistof16[Used[i]].getX() == x) {	/*Meme colonne.*/
						if(Piecelistof16[Used[i]].getY() == y-1) {/*Testé et passé*/
							nombredepiecesautour++;	
							autour[0] = i;
							/*Haut du piece à insérer:*/
								if(Piecelistof16[Used[i]].getBottom() < 2) {
										fpposs[0] = false;//outside
										fpposs[1] = false;
										/*if(getTop(0) <2 && getTop(1) <2)
											System.out.println("9Pas Une erreur Haut du piece coor (OUTSIDE)"+ x + " "+y);
										else
											System.out.println("10****************Une erreur Haut du piece coor (OUTSIDE)"+ x + " "+y);
									*/
								} else if(Piecelistof16[Used[i]].getBottom() >= 2) {
										fpposs[2] = false;//inside
										fpposs[3] = false;
										/*if(getTop(2) >=2 && getTop(3) >=2)
											System.out.println("11Pas Une erreur Haut du piece coor (INSIDE)"+ x + " "+y);
										else
											System.out.println("12****************Une erreur Haut du piece coor (INSIDE)"+ x + " "+y);
								*/
								}
								
						} else if(Piecelistof16[Used[i]].getY() == y+1) {/*Source d'erreur probable*/
							nombredepiecesautour++;
							autour[2] = i;
							/*Bas du piece à insérer:*/
								if(Piecelistof16[Used[i]].getTop() < 2) {
										fpposs[2] = false;
										fpposs[3] = false;
										/*if(getBottom(2) <2 && getBottom(3) <2)
											System.out.println("13Pas Une erreur Bas du piece coor (outside)"+ x + " "+y);
										else
											System.out.println("14****************Une erreur Bas du piece coor (outside)"+ x + " "+y);
											*/
								} else if(Piecelistof16[Used[i]].getTop() >= 2) {
										fpposs[0] = false;
										fpposs[1] = false;
										/*if(getBottom(0) >= 2 && getBottom(1) >= 2)
											System.out.println("15Pas Une erreur Bas du piece coor (inside)"+ x + " "+y);
										else
											System.out.println("16****************Une erreur Bas du piece coor (inside)"+ x + " "+y);
										*/
								}
						}
				}
			/*if(Nused>=9 && fpposs[0]) System.out.println("fpposs[0] =true :Bonjour dans boucle for " + Nused + " coor: " + x +" "+ y);
			else if(Nused>=9)System.out.println("fpposs[0] =false :Bonjour dans boucle for " + Nused + " coor: " + x +" "+ y);
			if(Nused>=9) System.out.println("ici!");*/
		}
		/*Utilise l'info recolté pour tester*/
	//if(Nused>=9)	System.out.println("\nNombre de Piece autour du " + Nused +"eme pièce à " + x + " "+y +" insérer est: "+ nombredepiecesautour);
		
	if(nombredepiecesautour ==0) {
			
			              for(int j=0; j<Nused; j++) {
			            	  System.out.println(Piecelistof16[Used[j]].getX() + " "+Piecelistof16[Used[j]].getY());
			            	 
			              }
			
		}
		/*à tester*/
		
		/*On Regarde si c'est possible d'ajouter une piece*/
		boolean Piecepossible = false;
		
		for(i=0; i<4; i++) {
			if(fpposs[i])	Piecepossible = true;	/*Cest possible qu'il n'y a pas que facon à insérer une piece*/
			//if(Nused>=9 && fpposs[i]) System.out.println("PASDECHANCE:Bonjour " + Nused);
		}
		/*
		 * ex:
		 * __________		__________         _______
		 * |		|  _	|		  |    _  |      |
		 * |		|_| |_  |Piece à  |	  / \_|      |
		 * |	     _   _|	|insérer  |	 |   _       |
		 * |________| |_|	|impossible   \_/ |______|
		 * 
		 * 
		 * 
		 * 
		 * */
		int tabdeposs[][] = new int[2][10];
		
		int compteurdepossibilite=0;
		boolean unusedPiece; /*variable pour essayer d'ajouter une piece inutilisé*/
	
if(Piecepossible) {
			//if(Nused>=9)System.out.println("10: bonjour Piece possible");
		/*Boucle pour les premiers 9 pieces*/
			if(Nused<9) {
				
				for(int j=0; j<9; j++) {	/*Boucle pour trouvé des pieces candidat*/
					/*Si la pièce est déjà utilisé, on n'essaie pas de l'insérer*/
					unusedPiece = true;
					
					for(int k=0; k< Nused; k++) {
						if(Used[k]==j)	unusedPiece = false; 
					}
					
						if(unusedPiece) {
								for(int fp=0; fp<4; fp++) {	/*On essait chaque rotation de la piece candidat pour etre inséré*/
										if(fpposs[fp]) {	/*Si la rotation n'est pas déclaré illogique 
														(Infomation grace à l'itération au début du méthode)*/
												
											if( TestPieceEntre(fp, Piecelistof16[j], Piecelistof16, Used,  autour)) {	/*Si ça entre sans problème:*/
													
												//if(Nused>8)System.out.print(compteurdepossibilite + " ");
														
													/*Imprime un combinaison 2*2 pour tester s'il n'y a pas d'erreur.
													 * NOTE:	Je vérifie avec les vrais pièces				*/
													
												/*if(x==1 && y==1)//Pour tester
													System.out.println("\n1     "+ (Used[1]+1) + "\n" + (Used[2]+1) + "     "+ (j+1)+ "\n");
												*/		
												tabdeposs[0][compteurdepossibilite] = j;
												tabdeposs[1][compteurdepossibilite] = fp;
												compteurdepossibilite++;
											
												/*Insérer les coordonnées du piece*/
											}
											
										}
								}
								
						}
				}
				
			} else if(Nused >= 9 && Nused<16) {
				//if(Nused>=9)System.out.println("11: bonjour Piece possible");
		for(int j=0; j<16; j++) {/*Boucle pour trouvé des pieces candidat*/
				unusedPiece = true;
				//System.out.println("1:Bonjour" + Nused);
				/*On n'a pas besoin de verifier les premiers 9 pieces parce quon sait deja quil sont utilise*/
				/*Mais en cas de bougue...*/
				/*k = 0...*/
					for(int k=0; k< Nused; k++) {	/*On n'a pas besoin de verifier les premiers 9 pieces parce quon sait deja quil sont utilise*/
						if(Used[k]==j) {
							unusedPiece = false; 
							if(k<9) {
								//System.out.println("Un erreur, used[0-8] contient des valeurs plus gros que 8");
							}
								
						}
					}
				if(unusedPiece) {	/*Peut-etre ca devrait etre partie du for boucle*/
					for(int fp=0; fp<4; fp++) {
						//System.out.println("2:Bonjour" + Nused);
							if(fpposs[fp]) {
								//System.out.println("3:Bonjour" + Nused + "fp= " + fp);
									if( TestPieceEntre(fp, Piecelistof16[j], Piecelistof16, Used,  autour)) {	/*Si ça entre sans problème:*/
										tabdeposs[0][compteurdepossibilite] = j;
										tabdeposs[1][compteurdepossibilite] = fp;
										compteurdepossibilite++;
										//System.out.println("Il y a une inseration possible");
									}	
							}
					}
				}	
		}
	}
}/*Completer le bloc de if piece possible*/
	

if( compteurdepossibilite > 0) {
	int tabdechoix[][] = new int[2][compteurdepossibilite];
	for(int j=0; j<2; j++) {
		for(i=0; i<compteurdepossibilite; i++) {
			tabdechoix[j][i] = tabdeposs[j][i];		/*Est-ce que c'est la bonne convention?*/
		}
	}
	return tabdechoix;
	
} else {
	int pasdeposs[][] = new int[1][1];
	pasdeposs[0][0] = PasDePiece;		/*valeur sentinel s'il n<y a pas de possibilité*/
	return pasdeposs;
}

	
}/*Fin de methode*/
		

	
	/*Méthode qui vérifie que la Piece peut entré dans une coordonné spécifique et un angle spécifique*/
	public static boolean TestPieceEntre(int fp, Piece essai, Piece Piecelistof16[], int used[], int autour[]) {
		int PasDePiece = -123;
		/*Pour verifier si on peut inserer la piece*//*En haut, a droite, en bas et à la gauche*/
		essai.setFp(fp);
		
		/*-1 veut dire qu'il n'y pas de piece en haut du piece qu'on veut insérer*/
		/*Haut*/
		if( ( autour[0]==PasDePiece || Piece.checkVertconnect(Piecelistof16[used[autour[0]]], essai) )==  false)
			return false;
			/*le PasDePiece veut dire qu'il n'y pas de piece en haut du piece qu'on veut insérer*/
		
		/*Droite*/
		if( ( autour[1]==PasDePiece || Piece.checkHorconnect(essai, Piecelistof16[used[autour[1]]]) ) ==  false)
			return false;
		/*Bas*/
		if( ( autour[2]==PasDePiece || Piece.checkVertconnect( essai, Piecelistof16[used[autour[2]]]) )== false)
			return false;
		/*Gauche*/
		if( ( autour[3]==PasDePiece ||Piece.checkHorconnect( Piecelistof16[used[autour[3]]], essai) )  == false)
			return false;
		
		return true;
		
	}
		
}

