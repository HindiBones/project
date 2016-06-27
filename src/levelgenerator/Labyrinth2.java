package gui;
	

	import java.util.Random;

//	import datenst;
//	import pp2015.team12.shared.map.textures.*;
public class Labyrinth2 {


	/**
	 * At first this class creates a basis for a level - border and stones. Then a
	 * labyrinth is created using FloodFill algorithm. Some architectural structures
	 * will be generated here, too.
	 * 
	 * @author Ulko Michael 5830648 Team 12
	 * 
	 */
//	public class Level_Basic {

		private Tile[][] map;

		/**
		 * Give a border to the double array of Tile and then fill it with "stone"
		 * everywhere
		 * 
		 * @param actID
		 * @author Michael Ulko
		 */
		private void fillMapArray(int actID) {
			this.map = new Tile[32][62];
			/* Fill the whole level with boundary elements */
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					/* Create a Tile object depends on current act */
					if (actID == 1) {
						map[i][j] = new SW_Border_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Border_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Border_1();
					}
				}
			}
			/*
			 * The frame of the level has boundary type. Now fill the inside part
			 * with stones
			 */
			for (int i = 1; i < map.length - 1; i++) {
				for (int j = 1; j < map[i].length - 1; j++) {
					/* Create a Tile object depends on current act */
					if (actID == 1) {
						/* Use random generator */
						int kindWall = kindWall(7);
						if (kindWall == 0) {
							map[i][j] = new SW_Nt_8();
						} else if (kindWall == 1) {
							map[i][j] = new SW_Object_7_Colomne();
						} else if (kindWall == 2) {
							map[i][j] = new SW_Object_0_Laterne();
						} else if (kindWall == 3) {
							map[i][j] = new SW_Nt_9();
						} else if (kindWall == 4) {
							map[i][j] = new SW_Nt_10();
						} else if (kindWall == 5) {
							map[i][j] = new SW_Nt_11();
						} else if (kindWall == 6) {
							map[i][j] = new SW_Nt_12();
						}
					}
					/* Use random generator */
					else if (actID == 2) {
						int kindWall = kindWall(18);
						if (kindWall == 0) {
							map[i][j] = new RW_Object_3_Busch();
						} else if (kindWall == 1) {
							map[i][j] = new RW_Object_4_Stone_1();
						} else if (kindWall == 2) {
							map[i][j] = new RW_Object_5_Stone_2();
						} else if (kindWall == 3) {
							map[i][j] = new RW_Object_6_Stone_3();
						} else if (kindWall == 4) {
							map[i][j] = new RW_Object_7_Tree();
						} else if (kindWall == 5) {
							map[i][j] = new RW_Nt_18();
						} else if (kindWall == 6) {
							map[i][j] = new RW_Nt_19();
						} else if (kindWall == 7) {
							map[i][j] = new RW_Nt_20();
						} else if (kindWall == 8) {
							map[i][j] = new RW_Nt_21();
						} else if (kindWall == 9) {
							map[i][j] = new RW_Nt_22();
						} else if (kindWall == 10) {
							map[i][j] = new RW_Nt_23();
						} else if (kindWall == 11) {
							map[i][j] = new RW_Nt_24();
						} else if (kindWall == 12) {
							map[i][j] = new RW_Nt_25();
						} else if (kindWall == 13) {
							map[i][j] = new RW_Nt_26();
						} else if (kindWall == 14) {
							map[i][j] = new RW_Nt_27();
						} else if (kindWall == 15) {
							map[i][j] = new RW_Nt_28();
						} else if (kindWall == 16) {
							map[i][j] = new RW_Nt_29();
						} else if (kindWall == 17) {
							map[i][j] = new RW_Nt_30();
						}
					}
					/* Use random generator */
					else if (actID == 3) {
						int kindWall = kindWall(7);
						if (kindWall == 0) {
							map[i][j] = new UW_Nt_1();
						} else if (kindWall == 1) {
							map[i][j] = new UW_Nt_2();
						} else if (kindWall == 2) {
							map[i][j] = new UW_Nt_3();
						} else if (kindWall == 3) {
							map[i][j] = new UW_Nt_4();
						} else if (kindWall == 4) {
							map[i][j] = new UW_Nt_5();
						} else if (kindWall == 5) {
							map[i][j] = new UW_Nt_6();
						} else if (kindWall == 6) {
							map[i][j] = new UW_Nt_7();
						}
					}
				}
			}
		}

		/**
		 * Random generator which decides, which "stone" texture should be taken.
		 * 
		 * @param nrTextures
		 *            Amount of available textures
		 * @return Number of taken texture
		 * @author Michael Ulko
		 */
		private int kindWall(int nrTextures) {
			Random zahl = new Random();
			int temp = Math.abs(zahl.nextInt(nrTextures));
			return temp;
		}

		/**
		 * Print the generated level in the console
		 * 
		 * @author Michael Ulko
		 */
		public void printArray() {
			/* The symbols are printed depends on the current act */
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					/* Stand for the first level */
					if (map[i][j].getImageID() == 15000) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15001) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15002) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15003) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15004) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15005) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15006) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15007) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15008) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15009) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15100) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15101) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15102) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15103) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15104) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15105) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15106) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15107) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15108) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15109) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15200) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15201) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15202) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15203) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15204) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15500) {
						System.out.print("ecke1");
					} else if (map[i][j].getImageID() == 15501) {
						System.out.print("wall1");
					} else if (map[i][j].getImageID() == 15502) {
						System.out.print("ecke2");
					} else if (map[i][j].getImageID() == 15503) {
						System.out.print("wall2");
					} else if (map[i][j].getImageID() == 15504) {
						System.out.print("ecke3");
					} else if (map[i][j].getImageID() == 15505) {
						System.out.print("wall3");
					} else if (map[i][j].getImageID() == 15506) {
						System.out.print("ecke4");
					} else if (map[i][j].getImageID() == 15507) {
						System.out.print("tt1tt");
					} else if (map[i][j].getImageID() == 15508) {
						System.out.print("tt2tt");
					} else if (map[i][j].getImageID() == 15509) {
						System.out.print(".weg.");
					}
					/* Stand for the first act */
					else if (map[i][j].getImageID() == 1110) {
						System.out.print(" GBG ");
					} else if (map[i][j].getImageID() == 1010) {
						System.out.print(" 111 ");
					} else if (map[i][j].getImageID() == 1020) {
						System.out.print("11111");
					} else if (map[i][j].getImageID() == 1030) {
						System.out.print("  .  ");
					} else if (map[i][j].getImageID() == 1041) {
						System.out.print("'E1D'");
					} else if (map[i][j].getImageID() == 1042) {
						System.out.print("'E1L'");
					} else if (map[i][j].getImageID() == 1043) {
						System.out.print("'E1R'");
					} else if (map[i][j].getImageID() == 1044) {
						System.out.print("'O1D'");
					} else if (map[i][j].getImageID() == 1045) {
						System.out.print("'O1L'");
					} else if (map[i][j].getImageID() == 1046) {
						System.out.print("'O1R'");
					} else if (map[i][j].getImageID() == 1050) {
						System.out.print("1lat1");
					} else if (map[i][j].getImageID() == 1150) {
						System.out.print("1tre1");
					} else if (map[i][j].getImageID() == 1250) {
						System.out.print("1kap1");
					} else if (map[i][j].getImageID() == 1350) {
						System.out.print("1sau1");
					} else if (map[i][j].getImageID() == 1450) {
						System.out.print("1s1t1");
					} else if (map[i][j].getImageID() == 1550) {
						System.out.print("1s2t1");
					} else if (map[i][j].getImageID() == 1650) {
						System.out.print("1s3t1");
					} else if (map[i][j].getImageID() == 1750) {
						System.out.print("1col1");
					} else if (map[i][j].getImageID() == 1160) {
						System.out.print("1r1g1");
					} else if (map[i][j].getImageID() == 1260) {
						System.out.print("1r2g1");
					} else if (map[i][j].getImageID() == 1360) {
						System.out.print("1r3g1");
					} else if (map[i][j].getImageID() == 1460) {
						System.out.print("1r4g1");
					} else if (map[i][j].getImageID() == 1560) {
						System.out.print("1r5g1");
					} else if (map[i][j].getImageID() == 1660) {
						System.out.print("1r6g1");
					} else if (map[i][j].getImageID() == 1760) {
						System.out.print("1r7g1");
					} else if (map[i][j].getImageID() == 1860) {
						System.out.print("1r8g1");
					} else if (map[i][j].getImageID() == 1960) {
						System.out.print("1r9g1");
					} else if (map[i][j].getImageID() == 1070) {
						System.out.print("1w0w1");
					} else if (map[i][j].getImageID() == 1170) {
						System.out.print("1w1w1");
					} else if (map[i][j].getImageID() == 1270) {
						System.out.print("1w2w1");
					} else if (map[i][j].getImageID() == 1370) {
						System.out.print("1w3w1");
					} else if (map[i][j].getImageID() == 1470) {
						System.out.print("1w4w1");
					} else if (map[i][j].getImageID() == 1570) {
						System.out.print("1w5w1");
					} else if (map[i][j].getImageID() == 1670) {
						System.out.print("1w6w1");
					} else if (map[i][j].getImageID() == 1770) {
						System.out.print("1w7w1");
					} else if (map[i][j].getImageID() == 1870) {
						System.out.print("1w8w1");
					} else if (map[i][j].getImageID() == 1970) {
						System.out.print("1w9w1");
					} else if (map[i][j].getImageID() == 1090) {
						System.out.print("1TRD1");
					} else if (map[i][j].getImageID() == 9080) {
						System.out.print("1n8t1");
					} else if (map[i][j].getImageID() == 9090) {
						System.out.print("1n9t1");
					} else if (map[i][j].getImageID() == 9100) {
						System.out.print("1n101");
					} else if (map[i][j].getImageID() == 9110) {
						System.out.print("1n111");
					} else if (map[i][j].getImageID() == 9120) {
						System.out.print("1n121");
					} else if (map[i][j].getImageID() == 9130) {
						System.out.print("1n131");
					} else if (map[i][j].getImageID() == 9140) {
						System.out.print("1n141");
					} else if (map[i][j].getImageID() == 9150) {
						System.out.print("1n151");
					} else if (map[i][j].getImageID() == 9160) {
						System.out.print("1n161");
					} else if (map[i][j].getImageID() == 9170) {
						System.out.print("1n171");
					} else if (map[i][j].getImageID() == 10902) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10903) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10904) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10905) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10906) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10907) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10908) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10909) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11000) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11001) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11002) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11003) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11004) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11005) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11006) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11007) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11008) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11009) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11100) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11101) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11102) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11103) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11104) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11105) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11106) {
						System.out.print("road1");
					} else if (map[i][j].getImageID() == 11107) {
						System.out.print("road2");
					} else if (map[i][j].getImageID() == 11108) {
						System.out.print("road3");
					}
					/* Stand for the second act */
					if (map[i][j].getImageID() == 2010) {
						System.out.print(" 222 ");
					} else if (map[i][j].getImageID() == 2020) {
						System.out.print("22222");
					} else if (map[i][j].getImageID() == 2030) {
						System.out.print(" . . ");
					} else if (map[i][j].getImageID() == 2041) {
						System.out.print("'E2D'");
					} else if (map[i][j].getImageID() == 2042) {
						System.out.print("'E2L'");
					} else if (map[i][j].getImageID() == 2043) {
						System.out.print("'E2R'");
					} else if (map[i][j].getImageID() == 2044) {
						System.out.print("'O2D'");
					} else if (map[i][j].getImageID() == 2045) {
						System.out.print("'O2L'");
					} else if (map[i][j].getImageID() == 2046) {
						System.out.print("'O2R'");
					} else if (map[i][j].getImageID() == 2150) {
						System.out.print("2ben2");
					} else if (map[i][j].getImageID() == 2250) {
						System.out.print("2box2");
					} else if (map[i][j].getImageID() == 2350) {
						System.out.print("2bus2");
					} else if (map[i][j].getImageID() == 2450) {
						System.out.print("2s1t2");
					} else if (map[i][j].getImageID() == 2550) {
						System.out.print("2s2t2");
					} else if (map[i][j].getImageID() == 2650) {
						System.out.print("2s3t2");
					} else if (map[i][j].getImageID() == 2750) {
						System.out.print("2tre2");
					} else if (map[i][j].getImageID() == 2160) {
						System.out.print("2r1g2");
					} else if (map[i][j].getImageID() == 2260) {
						System.out.print("2r2g2");
					} else if (map[i][j].getImageID() == 2360) {
						System.out.print("2r3g2");
					} else if (map[i][j].getImageID() == 2460) {
						System.out.print("2r4g2");
					} else if (map[i][j].getImageID() == 2560) {
						System.out.print("2r5g2");
					} else if (map[i][j].getImageID() == 2660) {
						System.out.print("2r6g2");
					} else if (map[i][j].getImageID() == 2760) {
						System.out.print("2r7g2");
					} else if (map[i][j].getImageID() == 2860) {
						System.out.print("2r8g2");
					} else if (map[i][j].getImageID() == 2960) {
						System.out.print("2r9g2");
					} else if (map[i][j].getImageID() == 2070) {
						System.out.print("2w0w2");
					} else if (map[i][j].getImageID() == 2170) {
						System.out.print("2w1w2");
					} else if (map[i][j].getImageID() == 2270) {
						System.out.print("2w2w2");
					} else if (map[i][j].getImageID() == 2370) {
						System.out.print("2w3w2");
					} else if (map[i][j].getImageID() == 2470) {
						System.out.print("2w4w2");
					} else if (map[i][j].getImageID() == 2570) {
						System.out.print("2w5w2");
					} else if (map[i][j].getImageID() == 2670) {
						System.out.print("2w6w2");
					} else if (map[i][j].getImageID() == 2770) {
						System.out.print("2w7w2");
					} else if (map[i][j].getImageID() == 2870) {
						System.out.print("2w8w2");
					} else if (map[i][j].getImageID() == 2970) {
						System.out.print("2w9w2");
					} else if (map[i][j].getImageID() == 9180) {
						System.out.print("2n182");
					} else if (map[i][j].getImageID() == 9190) {
						System.out.print("2n192");
					} else if (map[i][j].getImageID() == 9200) {
						System.out.print("2n202");
					} else if (map[i][j].getImageID() == 9210) {
						System.out.print("2n212");
					} else if (map[i][j].getImageID() == 9220) {
						System.out.print("2n222");
					} else if (map[i][j].getImageID() == 9230) {
						System.out.print("2n232");
					} else if (map[i][j].getImageID() == 9240) {
						System.out.print("2n242");
					} else if (map[i][j].getImageID() == 9250) {
						System.out.print("2n252");
					} else if (map[i][j].getImageID() == 9260) {
						System.out.print("2n262");
					} else if (map[i][j].getImageID() == 9270) {
						System.out.print("2n272");
					} else if (map[i][j].getImageID() == 9280) {
						System.out.print("2n282");
					} else if (map[i][j].getImageID() == 9290) {
						System.out.print("2n292");
					} else if (map[i][j].getImageID() == 9300) {
						System.out.print("2n302");
					} else if (map[i][j].getImageID() == 9310) {
						System.out.print("2n312");
					} else if (map[i][j].getImageID() == 9320) {
						System.out.print("2n322");
					} else if (map[i][j].getImageID() == 9330) {
						System.out.print("2n332");
					} else if (map[i][j].getImageID() == 9340) {
						System.out.print("2n342");
					} else if (map[i][j].getImageID() == 9350) {
						System.out.print("2n352");
					} else if (map[i][j].getImageID() == 9360) {
						System.out.print("2n362");
					} else if (map[i][j].getImageID() == 9370) {
						System.out.print("2n372");
					} else if (map[i][j].getImageID() == 9380) {
						System.out.print("2n382");
					} else if (map[i][j].getImageID() == 9390) {
						System.out.print("2n392");
					} else if (map[i][j].getImageID() == 9400) {
						System.out.print("2n402");
					} else if (map[i][j].getImageID() == 10001) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10002) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10003) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10004) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10005) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10006) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10007) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10008) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10009) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10100) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10101) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10102) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10103) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10104) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10105) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10106) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10107) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10108) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10109) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10200) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10201) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10202) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10203) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10204) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10205) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10206) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10207) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10208) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10209) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10300) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10301) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10302) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10303) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10304) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10305) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10306) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10307) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10308) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10309) {
						System.out.print("WALLF");
					} else if (map[i][j].getImageID() == 10400) {
						System.out.print("WALLF");
					} else if (map[i][j].getImageID() == 10401) {
						System.out.print("WALLF");
					} else if (map[i][j].getImageID() == 10402) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10403) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10404) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10405) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10406) {
						System.out.print("brid1");
					} else if (map[i][j].getImageID() == 10407) {
						System.out.print("brid2");
					} else if (map[i][j].getImageID() == 10408) {
						System.out.print(".bod.");
					}
					/* Stand for the third act */
					if (map[i][j].getImageID() == 3010) {
						System.out.print(" 333 ");
					} else if (map[i][j].getImageID() == 3020) {
						System.out.print("33333");
					} else if (map[i][j].getImageID() == 3030) {
						System.out.print(" ... ");
					} else if (map[i][j].getImageID() == 3041) {
						System.out.print("'E3D'");
					} else if (map[i][j].getImageID() == 3042) {
						System.out.print("'E3L'");
					} else if (map[i][j].getImageID() == 3043) {
						System.out.print("'E3R'");
					} else if (map[i][j].getImageID() == 3044) {
						System.out.print("'O3D'");
					} else if (map[i][j].getImageID() == 3045) {
						System.out.print("'O3L'");
					} else if (map[i][j].getImageID() == 3046) {
						System.out.print("'O3R'");
					} else if (map[i][j].getImageID() == 3150) {
						System.out.print("3dSk3");
					} else if (map[i][j].getImageID() == 3250) {
						System.out.print("3hol3");
					} else if (map[i][j].getImageID() == 3350) {
						System.out.print("3sau3");
					} else if (map[i][j].getImageID() == 3450) {
						System.out.print("3slz3");
					} else if (map[i][j].getImageID() == 3550) {
						System.out.print("3s1t3");
					} else if (map[i][j].getImageID() == 3650) {
						System.out.print("3s2t3");
					} else if (map[i][j].getImageID() == 9010) {
						System.out.print("3n1t3");
					} else if (map[i][j].getImageID() == 9020) {
						System.out.print("3n2t3");
					} else if (map[i][j].getImageID() == 9030) {
						System.out.print("3n3t3");
					} else if (map[i][j].getImageID() == 9040) {
						System.out.print("3n4t3");
					} else if (map[i][j].getImageID() == 9050) {
						System.out.print("3n5t3");
					} else if (map[i][j].getImageID() == 9060) {
						System.out.print("3n6t3");
					} else if (map[i][j].getImageID() == 9070) {
						System.out.print("3n7t3");
					} else if (map[i][j].getImageID() == 10408) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10409) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10500) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10501) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10502) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10503) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10504) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10505) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10506) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10507) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10508) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10509) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10600) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10601) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10602) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10603) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10604) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10605) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10606) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10607) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10608) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10609) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10700) {
						System.out.print(".lav.");
					} else if (map[i][j].getImageID() == 10701) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10702) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10703) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10704) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10705) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10706) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10707) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10708) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10709) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10800) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10801) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10802) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10803) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10804) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10805) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10806) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10807) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10808) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 10809) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 10900) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 10901) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 11106) {
						System.out.print("road1");
					} else if (map[i][j].getImageID() == 11107) {
						System.out.print("road2");
					} else if (map[i][j].getImageID() == 11108) {
						System.out.print("road3");
					} else if (map[i][j].getImageID() == 12000) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12001) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12002) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12003) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12004) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12005) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12006) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12007) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12008) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12009) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12100) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12101) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12102) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12103) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12104) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12105) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12106) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12107) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12108) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12109) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12200) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12201) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12202) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12203) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12204) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12205) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12206) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12207) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12208) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12209) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12300) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12301) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12302) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12303) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12304) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12305) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12306) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12307) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12308) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12309) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12400) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12401) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12402) {
						System.out.print("place");
					}
				}
				System.out.println();
			}
			System.out.println();
		}

		/**
		 * Print the generated level in the console. This method serves the same aim
		 * like above method. The sole difference consists in the parameter which is
		 * passed.
		 * 
		 * @param map
		 *            Double array of Type Tile
		 * @author Michael Ulko
		 */
		public void printArray(Tile[][] map) {
			/* The symbols are printed depends on the current act */
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					/* First level */
					if (map[i][j].getImageID() == 15000) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15001) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15002) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15003) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15004) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15005) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15006) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15007) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15008) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15009) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15100) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15101) {
						System.out.print("knast");
					} else if (map[i][j].getImageID() == 15102) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15103) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15104) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15105) {
						System.out.print("kaput");
					} else if (map[i][j].getImageID() == 15500) {
						System.out.print("ecke1");
					} else if (map[i][j].getImageID() == 15501) {
						System.out.print("wall1");
					} else if (map[i][j].getImageID() == 15502) {
						System.out.print("ecke2");
					} else if (map[i][j].getImageID() == 15503) {
						System.out.print("wall2");
					} else if (map[i][j].getImageID() == 15504) {
						System.out.print("ecke3");
					} else if (map[i][j].getImageID() == 15505) {
						System.out.print("wall3");
					} else if (map[i][j].getImageID() == 15506) {
						System.out.print("ecke4");
					} else if (map[i][j].getImageID() == 15507) {
						System.out.print("tt1tt");
					} else if (map[i][j].getImageID() == 15508) {
						System.out.print("tt2tt");
					} else if (map[i][j].getImageID() == 15509) {
						System.out.print(".weg.");
					} else if (map[i][j].getImageID() == 15106) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15107) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15108) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15109) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15200) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15201) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15202) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15203) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 15204) {
						System.out.print("haus1");
					}
					/* Stand for the first act */
					else if (map[i][j].getImageID() == 1110) {
						System.out.print(" GBG ");
					} else if (map[i][j].getImageID() == 1010) {
						System.out.print(" 111 ");
					} else if (map[i][j].getImageID() == 1020) {
						System.out.print("11111");
					} else if (map[i][j].getImageID() == 1030) {
						System.out.print("  .  ");
					} else if (map[i][j].getImageID() == 1041) {
						System.out.print("'E1D'");
					} else if (map[i][j].getImageID() == 1042) {
						System.out.print("'E1L'");
					} else if (map[i][j].getImageID() == 1043) {
						System.out.print("'E1R'");
					} else if (map[i][j].getImageID() == 1044) {
						System.out.print("'O1D'");
					} else if (map[i][j].getImageID() == 1045) {
						System.out.print("'O1L'");
					} else if (map[i][j].getImageID() == 1046) {
						System.out.print("'O1R'");
					} else if (map[i][j].getImageID() == 1050) {
						System.out.print("1lat1");
					} else if (map[i][j].getImageID() == 1150) {
						System.out.print("1tre1");
					} else if (map[i][j].getImageID() == 1250) {
						System.out.print("1kap1");
					} else if (map[i][j].getImageID() == 1350) {
						System.out.print("1sau1");
					} else if (map[i][j].getImageID() == 1450) {
						System.out.print("1s1t1");
					} else if (map[i][j].getImageID() == 1550) {
						System.out.print("1s2t1");
					} else if (map[i][j].getImageID() == 1650) {
						System.out.print("1s3t1");
					} else if (map[i][j].getImageID() == 1750) {
						System.out.print("1col1");
					} else if (map[i][j].getImageID() == 1160) {
						System.out.print("1r1g1");
					} else if (map[i][j].getImageID() == 1260) {
						System.out.print("1r2g1");
					} else if (map[i][j].getImageID() == 1360) {
						System.out.print("1r3g1");
					} else if (map[i][j].getImageID() == 1460) {
						System.out.print("1r4g1");
					} else if (map[i][j].getImageID() == 1560) {
						System.out.print("1r5g1");
					} else if (map[i][j].getImageID() == 1660) {
						System.out.print("1r6g1");
					} else if (map[i][j].getImageID() == 1760) {
						System.out.print("1r7g1");
					} else if (map[i][j].getImageID() == 1860) {
						System.out.print("1r8g1");
					} else if (map[i][j].getImageID() == 1960) {
						System.out.print("1r9g1");
					} else if (map[i][j].getImageID() == 1070) {
						System.out.print("1w0w1");
					} else if (map[i][j].getImageID() == 1170) {
						System.out.print("1w1w1");
					} else if (map[i][j].getImageID() == 1270) {
						System.out.print("1w2w1");
					} else if (map[i][j].getImageID() == 1370) {
						System.out.print("1w3w1");
					} else if (map[i][j].getImageID() == 1470) {
						System.out.print("1w4w1");
					} else if (map[i][j].getImageID() == 1570) {
						System.out.print("1w5w1");
					} else if (map[i][j].getImageID() == 1670) {
						System.out.print("1w6w1");
					} else if (map[i][j].getImageID() == 1770) {
						System.out.print("1w7w1");
					} else if (map[i][j].getImageID() == 1870) {
						System.out.print("1w8w1");
					} else if (map[i][j].getImageID() == 1970) {
						System.out.print("1w9w1");
					} else if (map[i][j].getImageID() == 1090) {
						System.out.print("1TRD1");
					} else if (map[i][j].getImageID() == 9080) {
						System.out.print("1n8t1");
					} else if (map[i][j].getImageID() == 9090) {
						System.out.print("1n9t1");
					} else if (map[i][j].getImageID() == 9100) {
						System.out.print("1n101");
					} else if (map[i][j].getImageID() == 9110) {
						System.out.print("1n111");
					} else if (map[i][j].getImageID() == 9120) {
						System.out.print("1n121");
					} else if (map[i][j].getImageID() == 9130) {
						System.out.print("1n131");
					} else if (map[i][j].getImageID() == 9140) {
						System.out.print("1n141");
					} else if (map[i][j].getImageID() == 9150) {
						System.out.print("1n151");
					} else if (map[i][j].getImageID() == 9160) {
						System.out.print("1n161");
					} else if (map[i][j].getImageID() == 9170) {
						System.out.print("1n171");
					} else if (map[i][j].getImageID() == 10902) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10903) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10904) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10905) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10906) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10907) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10908) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 10909) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11000) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11001) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11002) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11003) {
						System.out.print("churc");
					} else if (map[i][j].getImageID() == 11004) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11005) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11006) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11007) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11008) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11009) {
						System.out.print("te1ml");
					} else if (map[i][j].getImageID() == 11100) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11101) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11102) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11103) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11104) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11105) {
						System.out.print("te2ml");
					} else if (map[i][j].getImageID() == 11106) {
						System.out.print("road1");
					} else if (map[i][j].getImageID() == 11107) {
						System.out.print("road2");
					} else if (map[i][j].getImageID() == 11108) {
						System.out.print("road3");
					}
					/* Stand for the second act */
					if (map[i][j].getImageID() == 2010) {
						System.out.print(" 222 ");
					} else if (map[i][j].getImageID() == 2020) {
						System.out.print("22222");
					} else if (map[i][j].getImageID() == 2030) {
						System.out.print(" . . ");
					} else if (map[i][j].getImageID() == 2041) {
						System.out.print("'E2D'");
					} else if (map[i][j].getImageID() == 2042) {
						System.out.print("'E2L'");
					} else if (map[i][j].getImageID() == 2043) {
						System.out.print("'E2R'");
					} else if (map[i][j].getImageID() == 2044) {
						System.out.print("'O2D'");
					} else if (map[i][j].getImageID() == 2045) {
						System.out.print("'O2L'");
					} else if (map[i][j].getImageID() == 2046) {
						System.out.print("'O2R'");
					} else if (map[i][j].getImageID() == 2150) {
						System.out.print("2ben2");
					} else if (map[i][j].getImageID() == 2250) {
						System.out.print("2box2");
					} else if (map[i][j].getImageID() == 2350) {
						System.out.print("2bus2");
					} else if (map[i][j].getImageID() == 2450) {
						System.out.print("2s1t2");
					} else if (map[i][j].getImageID() == 2550) {
						System.out.print("2s2t2");
					} else if (map[i][j].getImageID() == 2650) {
						System.out.print("2s3t2");
					} else if (map[i][j].getImageID() == 2750) {
						System.out.print("2tre2");
					} else if (map[i][j].getImageID() == 2160) {
						System.out.print("2r1g2");
					} else if (map[i][j].getImageID() == 2260) {
						System.out.print("2r2g2");
					} else if (map[i][j].getImageID() == 2360) {
						System.out.print("2r3g2");
					} else if (map[i][j].getImageID() == 2460) {
						System.out.print("2r4g2");
					} else if (map[i][j].getImageID() == 2560) {
						System.out.print("2r5g2");
					} else if (map[i][j].getImageID() == 2660) {
						System.out.print("2r6g2");
					} else if (map[i][j].getImageID() == 2760) {
						System.out.print("2r7g2");
					} else if (map[i][j].getImageID() == 2860) {
						System.out.print("2r8g2");
					} else if (map[i][j].getImageID() == 2960) {
						System.out.print("2r9g2");
					} else if (map[i][j].getImageID() == 2070) {
						System.out.print("2w0w2");
					} else if (map[i][j].getImageID() == 2170) {
						System.out.print("2w1w2");
					} else if (map[i][j].getImageID() == 2270) {
						System.out.print("2w2w2");
					} else if (map[i][j].getImageID() == 2370) {
						System.out.print("2w3w2");
					} else if (map[i][j].getImageID() == 2470) {
						System.out.print("2w4w2");
					} else if (map[i][j].getImageID() == 2570) {
						System.out.print("2w5w2");
					} else if (map[i][j].getImageID() == 2670) {
						System.out.print("2w6w2");
					} else if (map[i][j].getImageID() == 2770) {
						System.out.print("2w7w2");
					} else if (map[i][j].getImageID() == 2870) {
						System.out.print("2w8w2");
					} else if (map[i][j].getImageID() == 2970) {
						System.out.print("2w9w2");
					} else if (map[i][j].getImageID() == 9180) {
						System.out.print("2n182");
					} else if (map[i][j].getImageID() == 9190) {
						System.out.print("2n192");
					} else if (map[i][j].getImageID() == 9200) {
						System.out.print("2n202");
					} else if (map[i][j].getImageID() == 9210) {
						System.out.print("2n212");
					} else if (map[i][j].getImageID() == 9220) {
						System.out.print("2n222");
					} else if (map[i][j].getImageID() == 9230) {
						System.out.print("2n232");
					} else if (map[i][j].getImageID() == 9240) {
						System.out.print("2n242");
					} else if (map[i][j].getImageID() == 9250) {
						System.out.print("2n252");
					} else if (map[i][j].getImageID() == 9260) {
						System.out.print("2n262");
					} else if (map[i][j].getImageID() == 9270) {
						System.out.print("2n272");
					} else if (map[i][j].getImageID() == 9280) {
						System.out.print("2n282");
					} else if (map[i][j].getImageID() == 9290) {
						System.out.print("2n292");
					} else if (map[i][j].getImageID() == 9300) {
						System.out.print("2n302");
					} else if (map[i][j].getImageID() == 9310) {
						System.out.print("2n312");
					} else if (map[i][j].getImageID() == 9320) {
						System.out.print("2n322");
					} else if (map[i][j].getImageID() == 9330) {
						System.out.print("2n332");
					} else if (map[i][j].getImageID() == 9340) {
						System.out.print("2n342");
					} else if (map[i][j].getImageID() == 9350) {
						System.out.print("2n352");
					} else if (map[i][j].getImageID() == 9360) {
						System.out.print("2n362");
					} else if (map[i][j].getImageID() == 9370) {
						System.out.print("2n372");
					} else if (map[i][j].getImageID() == 9380) {
						System.out.print("2n382");
					} else if (map[i][j].getImageID() == 9390) {
						System.out.print("2n392");
					} else if (map[i][j].getImageID() == 9400) {
						System.out.print("2n402");
					} else if (map[i][j].getImageID() == 10001) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10002) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10003) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10004) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10005) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10006) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10007) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10008) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10009) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10100) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10101) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10102) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10103) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10104) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10105) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10106) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10107) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10108) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10109) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10200) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10201) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10202) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10203) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10204) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10205) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10206) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10207) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10208) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10209) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10300) {
						System.out.print("SCHLS");
					} else if (map[i][j].getImageID() == 10301) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10302) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10303) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10304) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10305) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10306) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10307) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10308) {
						System.out.print("WALL.");
					} else if (map[i][j].getImageID() == 10309) {
						System.out.print("WALLF");
					} else if (map[i][j].getImageID() == 10400) {
						System.out.print("WALLF");
					} else if (map[i][j].getImageID() == 10401) {
						System.out.print("WALLF");
					} else if (map[i][j].getImageID() == 10402) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10403) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10404) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10405) {
						System.out.print("river");
					} else if (map[i][j].getImageID() == 10406) {
						System.out.print("brid1");
					} else if (map[i][j].getImageID() == 10407) {
						System.out.print("brid2");
					}
					/* Stand for the third act */
					if (map[i][j].getImageID() == 3010) {
						System.out.print(" 333 ");
					} else if (map[i][j].getImageID() == 3020) {
						System.out.print("33333");
					} else if (map[i][j].getImageID() == 3030) {
						System.out.print(" ... ");
					} else if (map[i][j].getImageID() == 3041) {
						System.out.print("'E3D'");
					} else if (map[i][j].getImageID() == 3042) {
						System.out.print("'E3L'");
					} else if (map[i][j].getImageID() == 3043) {
						System.out.print("'E3R'");
					} else if (map[i][j].getImageID() == 3044) {
						System.out.print("'O3D'");
					} else if (map[i][j].getImageID() == 3045) {
						System.out.print("'O3L'");
					} else if (map[i][j].getImageID() == 3046) {
						System.out.print("'O3R'");
					} else if (map[i][j].getImageID() == 3150) {
						System.out.print("3dSk3");
					} else if (map[i][j].getImageID() == 3250) {
						System.out.print("3hol3");
					} else if (map[i][j].getImageID() == 3350) {
						System.out.print("3sau3");
					} else if (map[i][j].getImageID() == 3450) {
						System.out.print("3slz3");
					} else if (map[i][j].getImageID() == 3550) {
						System.out.print("3s1t3");
					} else if (map[i][j].getImageID() == 3650) {
						System.out.print("3s2t3");
					} else if (map[i][j].getImageID() == 9010) {
						System.out.print("3n1t3");
					} else if (map[i][j].getImageID() == 9020) {
						System.out.print("3n2t3");
					} else if (map[i][j].getImageID() == 9030) {
						System.out.print("3n3t3");
					} else if (map[i][j].getImageID() == 9040) {
						System.out.print("3n4t3");
					} else if (map[i][j].getImageID() == 9050) {
						System.out.print("3n5t3");
					} else if (map[i][j].getImageID() == 9060) {
						System.out.print("3n6t3");
					} else if (map[i][j].getImageID() == 9070) {
						System.out.print("3n7t3");
					} else if (map[i][j].getImageID() == 10408) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10409) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10500) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10501) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10502) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10503) {
						System.out.print("stone");
					} else if (map[i][j].getImageID() == 10504) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10505) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10506) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10507) {
						System.out.print("bodAb");
					} else if (map[i][j].getImageID() == 10508) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10509) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10600) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10601) {
						System.out.print("BodEk");
					} else if (map[i][j].getImageID() == 10602) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10603) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10604) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10605) {
						System.out.print("LavAb");
					} else if (map[i][j].getImageID() == 10606) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10607) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10608) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10609) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10700) {
						System.out.print(".lav.");
					} else if (map[i][j].getImageID() == 10701) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10702) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10703) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10704) {
						System.out.print("LavEk");
					} else if (map[i][j].getImageID() == 10705) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10706) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10707) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10708) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10709) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10800) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10801) {
						System.out.print("BoBoE");
					} else if (map[i][j].getImageID() == 10802) {
						System.out.print("BoBos");
					} else if (map[i][j].getImageID() == 10803) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10804) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10805) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10806) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10807) {
						System.out.print("leite");
					} else if (map[i][j].getImageID() == 10808) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 10809) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 10900) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 10901) {
						System.out.print("altar");
					} else if (map[i][j].getImageID() == 12000) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12001) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12002) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12003) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12004) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12005) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12006) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12007) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12008) {
						System.out.print("haus1");
					} else if (map[i][j].getImageID() == 12009) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12100) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12101) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12102) {
						System.out.print("haus2");
					} else if (map[i][j].getImageID() == 12103) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12104) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12105) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12106) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12107) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12108) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12109) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12200) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12201) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12202) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12203) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12204) {
						System.out.print("haus3");
					} else if (map[i][j].getImageID() == 12205) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12206) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12207) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12208) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12209) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12300) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12301) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12302) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12303) {
						System.out.print("haus4");
					} else if (map[i][j].getImageID() == 12304) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12305) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12306) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12307) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12308) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12309) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12400) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12401) {
						System.out.print("place");
					} else if (map[i][j].getImageID() == 12402) {
						System.out.print("place");
					}
				}
				System.out.println();
			}
			System.out.println();
		}

		/**
		 * 
		 * @return Double array of type Tile
		 * @author Michael Ulko
		 */
		public Tile[][] getLevel() {
			return this.map;
		}

		/**
		 * Computation of a random variable, which decides the direction of RFF
		 * 
		 * @return One of four possible values: 1 or 4 for up, 2 or 5 for right, 3
		 *         or 6 for down and 4 or 7 for left
		 * @author Michael Ulko
		 */
		private int randomMethod() {
			/* Create a new Random object */
			Random zahl = new Random();
			/* Decide the possible value and initialize the variable */
			int zahlRandom = Math.abs(zahl.nextInt(8));
			return zahlRandom;
		}

		/**
		 * Randomized Flood Fill for building a labyrinth
		 * 
		 * @param map
		 *            Double array of type Tile is used here
		 * @param x
		 *            Height of the double Array of type Tile
		 * @param y
		 *            Width of the double array of type Tile
		 * @param actID
		 *            Current act numer (1, 2 or 3)
		 * @author Michael Ulko
		 */
		private void floodFill(Tile[][] map, int x, int y, int actID) {
			int randomZahl = randomMethod();
			/* Create an object for current Tile array field depends on act */
			if (actID == 1) {
				map[x][y] = new SW_Floor_1();
			} else if (actID == 2) {
				map[x][y] = new RW_Floor_1();
			} else if (actID == 3) {
				map[x][y] = new UW_Floor_1();
			}

			/* Decides which way the RFF should go the next step */
			switch (randomZahl) {
			case 0: {
				/** RFF goes up if possible depends on current act */
				if ((x - 2 >= 0 && map[x - 2][y].getImageID() == 1020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 1050)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 1750)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9080)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9090)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9100)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9110)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9120)) {
					map[x - 1][y] = new SW_Floor_1();
					floodFill(map, x - 2, y, actID);
				}
				if ((x - 2 >= 0 && map[x - 2][y].getImageID() == 2020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2750)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2350)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2450)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2650)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2550)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9180)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9190)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9200)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9210)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9220)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9230)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9240)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9250)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9260)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9270)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9280)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9290)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9300)) {
					map[x - 1][y] = new RW_Floor_1();
					floodFill(map, x - 2, y, actID);
				}
				if ((x - 2 >= 0 && map[x - 2][y].getImageID() == 3020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9010)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9030)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9040)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9050)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9060)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9070)) {
					map[x - 1][y] = new UW_Floor_1();
					floodFill(map, x - 2, y, actID);
				}
			}
			case 1: {
				/* RFF goes right if possible depends on current act */
				if ((y + 2 < map[x].length && map[x][y + 2].getImageID() == 1020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 1050)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 1750)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9080)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9090)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9100)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9110)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9120)) {
					map[x][y + 1] = new SW_Floor_1();
					floodFill(map, x, y + 2, actID);
				}
				if ((y + 2 < map[x].length && map[x][y + 2].getImageID() == 2020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2750)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2350)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2450)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2650)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2550)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9180)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9190)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9200)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9210)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9220)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9230)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9240)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9250)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9260)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9270)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9280)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9290)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9300)) {
					map[x][y + 1] = new RW_Floor_1();
					floodFill(map, x, y + 2, actID);
				}
				if ((y + 2 < map[x].length && map[x][y + 2].getImageID() == 3020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9010)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9030)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9040)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9050)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9060)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9070)) {
					map[x][y + 1] = new UW_Floor_1();
					floodFill(map, x, y + 2, actID);
				}
			}
			case 2: {
				/* RFF goes down if possible depends on current act */
				if ((x + 2 < map.length && map[x + 2][y].getImageID() == 1020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 1050)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 1750)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9080)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9090)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9100)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9110)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9120)) {
					map[x + 1][y] = new SW_Floor_1();
					floodFill(map, x + 2, y, actID);
				}
				if ((x + 2 < map.length && map[x + 2][y].getImageID() == 2020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2750)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2350)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2450)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2650)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2550)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9180)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9190)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9200)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9210)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9220)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9230)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9240)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9250)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9260)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9270)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9280)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9290)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9300)) {
					map[x + 1][y] = new RW_Floor_1();
					floodFill(map, x + 2, y, actID);
				}
				if ((x + 2 < map.length && map[x + 2][y].getImageID() == 3020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9010)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9030)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9040)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9050)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9060)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9070)) {
					map[x + 1][y] = new UW_Floor_1();
					floodFill(map, x + 2, y, actID);
				}
			}
			case 3: {
				/* RFF goes left if possible depends on current act */
				if ((y - 2 >= 0 && map[x][y - 2].getImageID() == 1020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 1050)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 1750)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9080)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9090)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9100)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9110)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9120)) {
					map[x][y - 1] = new SW_Floor_1();
					floodFill(map, x, y - 2, actID);
				}
				if ((y - 2 >= 0 && map[x][y - 2].getImageID() == 2020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2750)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2350)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2450)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2650)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2550)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9180)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9190)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9200)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9210)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9220)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9230)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9240)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9250)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9260)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9270)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9280)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9290)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9300)) {
					map[x][y - 1] = new RW_Floor_1();
					floodFill(map, x, y - 2, actID);
				}
				if ((y - 2 >= 0 && map[x][y - 2].getImageID() == 3020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9010)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9030)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9040)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9050)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9060)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9070)) {
					map[x][y - 1] = new UW_Floor_1();
					floodFill(map, x, y - 2, actID);
				}
			}
			case 4: {
				/** RFF goes up if possible depends on current act */
				if ((x - 2 >= 0 && map[x - 2][y].getImageID() == 1020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 1050)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 1750)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9080)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9090)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9100)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9110)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9120)) {
					map[x - 1][y] = new SW_Floor_1();
					floodFill(map, x - 2, y, actID);
				}
				if ((x - 2 >= 0 && map[x - 2][y].getImageID() == 2020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2750)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2350)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2450)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2650)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 2550)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9180)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9190)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9200)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9210)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9220)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9230)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9240)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9250)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9260)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9270)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9280)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9290)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9300)) {
					map[x - 1][y] = new RW_Floor_1();
					floodFill(map, x - 2, y, actID);
				}
				if ((x - 2 >= 0 && map[x - 2][y].getImageID() == 3020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9010)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9020)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9030)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9040)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9050)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9060)
						|| (x - 2 >= 0 && map[x - 2][y].getImageID() == 9070)) {
					map[x - 1][y] = new UW_Floor_1();
					floodFill(map, x - 2, y, actID);
				}
			}
			case 5: {
				/* RFF goes right if possible depends on current act */
				if ((y + 2 < map[x].length && map[x][y + 2].getImageID() == 1020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 1050)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 1750)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9080)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9090)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9100)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9110)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9120)) {
					map[x][y + 1] = new SW_Floor_1();
					floodFill(map, x, y + 2, actID);
				}
				if ((y + 2 < map[x].length && map[x][y + 2].getImageID() == 2020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2750)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2350)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2450)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2650)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 2550)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9180)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9190)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9200)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9210)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9220)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9230)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9240)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9250)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9260)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9270)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9280)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9290)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9300)) {
					map[x][y + 1] = new RW_Floor_1();
					floodFill(map, x, y + 2, actID);
				}
				if ((y + 2 < map[x].length && map[x][y + 2].getImageID() == 3020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9010)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9020)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9030)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9040)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9050)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9060)
						|| (y + 2 < map[x].length && map[x][y + 2].getImageID() == 9070)) {
					map[x][y + 1] = new UW_Floor_1();
					floodFill(map, x, y + 2, actID);
				}
			}
			case 6: {
				/* RFF goes down if possible depends on current act */
				if ((x + 2 < map.length && map[x + 2][y].getImageID() == 1020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 1050)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 1750)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9080)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9090)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9100)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9110)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9120)) {
					map[x + 1][y] = new SW_Floor_1();
					floodFill(map, x + 2, y, actID);
				}
				if ((x + 2 < map.length && map[x + 2][y].getImageID() == 2020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2750)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2350)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2450)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2650)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 2550)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9180)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9190)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9200)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9210)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9220)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9230)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9240)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9250)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9260)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9270)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9280)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9290)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9300)) {
					map[x + 1][y] = new RW_Floor_1();
					floodFill(map, x + 2, y, actID);
				}
				if ((x + 2 < map.length && map[x + 2][y].getImageID() == 3020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9010)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9020)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9030)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9040)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9050)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9060)
						|| (x + 2 < map.length && map[x + 2][y].getImageID() == 9070)) {
					map[x + 1][y] = new UW_Floor_1();
					floodFill(map, x + 2, y, actID);
				}
			}
			case 7: {
				/* RFF goes left if possible depends on current act */
				if ((y - 2 >= 0 && map[x][y - 2].getImageID() == 1020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 1050)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 1750)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9080)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9090)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9100)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9110)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9120)) {
					map[x][y - 1] = new SW_Floor_1();
					floodFill(map, x, y - 2, actID);
				}
				if ((y - 2 >= 0 && map[x][y - 2].getImageID() == 2020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2750)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2350)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2450)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2650)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 2550)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9180)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9190)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9200)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9210)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9220)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9230)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9240)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9250)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9260)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9270)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9280)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9290)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9300)) {
					map[x][y - 1] = new RW_Floor_1();
					floodFill(map, x, y - 2, actID);
				}
				if ((y - 2 >= 0 && map[x][y - 2].getImageID() == 3020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9010)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9020)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9030)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9040)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9050)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9060)
						|| (y - 2 >= 0 && map[x][y - 2].getImageID() == 9070)) {
					map[x][y - 1] = new UW_Floor_1();
					floodFill(map, x, y - 2, actID);
				}
			}
			}
		}

		/**
		 * Produce the basic structure for every level: global border, stones and
		 * labyrinth.
		 * 
		 * @param actID
		 *            Current act number
		 * 
		 * @author Ulko, Michael
		 */
		public void generateLevel(int actID) {
			/* Fill a level with global border and stones */
			fillMapArray(actID);
			/* Produce labyrinth through the stones */
			floodFill(this.map, 1, 60, actID);
		}

		/**
		 * Produce start level
		 * 
		 * @author Ulko, Michael
		 */
		public void generateStartRoom() {
			/* Size of the start level */
			this.map = new Tile[18][30];
			/* Place global Border for the level */
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					// map[i][j] = new SW_Border_0();
					if (i == 0 && j == 0) {
						map[i][j] = new Knast_550();
					} else if (i == 0 && j == 29) {
						map[i][j] = new Knast_552();
					} else if (i == 17 && j == 29) {
						map[i][j] = new Knast_554();
					} else if (i == 17 && j == 0) {
						map[i][j] = new Knast_556();
					} else if (i == 0 && (j >= 1 && j <= 28)) {
						map[i][j] = new Knast_551();
					} else if (i == 17 && (j >= 1 && j <= 28)) {
						map[i][j] = new Knast_555();
					} else if ((i >= 1 && i <= 16) && (j == 0 || j == 29)) {
						map[i][j] = new Knast_553();
					}
				}
			}
			/* Place normal walls in level */
			for (int i = 1; i < map.length - 1; i++) {
				for (int j = 1; j < map[i].length - 1; j++) {
					map[i][j] = new SW_Wall_1();
					if (i == 1 && j == 1) {
						map[i][j] = new Knast_550();
					} else if (i == 1 && j == 28) {
						map[i][j] = new Knast_552();
					} else if (i == 16 && j == 28) {
						map[i][j] = new Knast_554();
					} else if (i == 16 && j == 1) {
						map[i][j] = new Knast_556();
					} else if (i == 1 && (j >= 2 && j <= 27)) {
						map[i][j] = new Knast_551();
					} else if (i == 16 && (j >= 2 && j <= 27)) {
						map[i][j] = new Knast_555();
					} else if ((i >= 2 && i <= 15) && (j == 1 || j == 28)) {
						map[i][j] = new Knast_553();
					} else if ((i >= 2 && i <= 15) && (j == 9 || j == 20)) {
						map[i][j] = new Knast_553();
					}
				}
			}
			/* Place special wall elements in level */
			map[1][9] = new Knast_557();
			map[1][20] = new Knast_557();
			map[16][9] = new Knast_558();
			map[16][20] = new Knast_558();
			/* Place floor elemets in level */
			for (int i = 2; i < map.length - 2; i++) {
				for (int j = 2; j <= 8; j++) {
					map[i][j] = new SW_Floor_1();
				}
			}
			for (int i = 2; i < map.length - 2; i++) {
				for (int j = 10; j <= 12; j++) {
					map[i][j] = new SW_Floor_1();
				}
			}
			for (int i = 2; i < map.length - 2; i++) {
				for (int j = 13; j <= 19; j++) {
					map[i][j] = new SW_Floor_1();
				}
			}
			for (int i = 2; i < map.length - 2; i++) {
				for (int j = 21; j <= 27; j++) {
					map[i][j] = new SW_Floor_1();
				}
			}
			/* Place the first building in level */
			for (int i = 2; i <= 5; i++) {
				for (int j = 14; j <= 16; j++) {
					if (i == 2 && j == 14) {
						map[i][j] = new Knast_500();
					} else if (i == 2 && j == 15) {
						map[i][j] = new Knast_501();
					} else if (i == 2 && j == 16) {
						map[i][j] = new Knast_502();
					} else if (i == 3 && j == 14) {
						map[i][j] = new Knast_503();
					} else if (i == 3 && j == 15) {
						map[i][j] = new Knast_504();
					} else if (i == 3 && j == 16) {
						map[i][j] = new Knast_505();
					} else if (i == 4 && j == 14) {
						map[i][j] = new Knast_506();
					} else if (i == 4 && j == 15) {
						map[i][j] = new Knast_507();
					} else if (i == 4 && j == 16) {
						map[i][j] = new Knast_508();
					} else if (i == 5 && j == 14) {
						map[i][j] = new Knast_509();
					} else if (i == 5 && j == 15) {
						map[i][j] = new Knast_510();
					} else if (i == 5 && j == 16) {
						map[i][j] = new Knast_511();
					}
				}
			}
			/* Place the second building in level */
			for (int i = 8; i <= 9; i++) {
				for (int j = 10; j <= 11; j++) {
					if (i == 8 && j == 10) {
						map[i][j] = new Knast_512();
					} else if (i == 8 && j == 11) {
						map[i][j] = new Knast_513();
					} else if (i == 9 && j == 10) {
						map[i][j] = new Knast_514();
					} else if (i == 9 && j == 11) {
						map[i][j] = new Knast_515();
					}
				}
			}
			/* Place the way in level */
			for (int i = 6; i <= 15; i++) {
				map[i][15] = new Knast_559();
			}
			/* Place third building in level */
			for (int i = 11; i <= 13; i++) {
				for (int j = 17; j <= 19; j++) {
					if (i == 11 && j == 17) {
						map[i][j] = new Knast_516();
					} else if (i == 11 && j == 18) {
						map[i][j] = new Knast_517();
					} else if (i == 11 && j == 19) {
						map[i][j] = new Knast_518();
					} else if (i == 12 && j == 17) {
						map[i][j] = new Knast_519();
					} else if (i == 12 && j == 18) {
						map[i][j] = new Knast_520();
					} else if (i == 12 && j == 19) {
						map[i][j] = new Knast_521();
					} else if (i == 13 && j == 17) {
						map[i][j] = new Knast_522();
					} else if (i == 13 && j == 18) {
						map[i][j] = new Knast_523();
					} else if (i == 13 && j == 19) {
						map[i][j] = new Knast_524();
					}
				}
			}
		}

		/**
		 * Produce boss level (22, 44 or 66)
		 * 
		 * @param actID
		 *            Current act number (1, 2 or 3)
		 * @author Michael Ulko
		 */
		public void generateBossRoom(int actID) {
			/* Size of every boss level */
			this.map = new Tile[18][30];
			/* Set the global border of every boss level depends on current act */
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Border_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Border_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Border_1();
					}
				}
			}
			/* Place the wall in level depends on act */
			for (int i = 1; i < map.length - 1; i++) {
				for (int j = 1; j < map[i].length - 1; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Nt_11();
					} else if (actID == 2) {
						map[i][j] = new RW_Object_5_Stone_2();
					} else if (actID == 3) {
						/* Use random generator for choosing an element */
						int temp = placeStoneBoss(map);
						if (temp == 0) {
							map[i][j] = new UW_St_Stone_48();
						} else if (temp == 1) {
							map[i][j] = new UW_St_Stone_49();
						} else if (temp == 2) {
							map[i][j] = new UW_St_Stone_50();
						} else if (temp == 3) {
							map[i][j] = new UW_St_Stone_51();
						} else if (temp == 4) {
							map[i][j] = new UW_St_Stone_52();
						} else if (temp == 5) {
							map[i][j] = new UW_St_Stone_53();
						}
					}
				}
			}
			/* Set the floor in level */
			for (int i = 2; i <= 2; i++) {
				for (int j = 8; j <= 21; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			for (int i = 3; i <= 3; i++) {
				for (int j = 4; j <= 25; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			for (int i = 4; i <= 5; i++) {
				for (int j = 3; j <= 26; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			for (int i = 6; i <= 11; i++) {
				for (int j = 2; j <= 27; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			for (int i = 12; i <= 13; i++) {
				for (int j = 3; j <= 26; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			for (int i = 14; i <= 14; i++) {
				for (int j = 4; j <= 25; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			for (int i = 15; i <= 15; i++) {
				for (int j = 8; j <= 21; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			/* Add special textures to the level depends on act */
			if (actID == 1) {
				placeTexturesBoss1(map);
			} else if (actID == 2) {
				for (int i = 5; i <= 10; i++) {
					for (int j = 13; j <= 17; j++) {
						placeCastleBoss(map);
						createCastleWallBoss(map);
					}
				}
			} else if (actID == 3) {
				placeTexturesBoss3(map);
			}
		}

		/**
		 * Decide which texture should be used like a border element in a shop level
		 * 
		 * @param elZahl
		 *            Amount of available textures
		 * @return Number of chosen texture
		 * @author Michael Ulko
		 */
		private int decideShopObject(int elZahl) {
			Random zahl = new Random();
			int pos1 = Math.abs(zahl.nextInt(elZahl));
			return pos1;
		}

		/**
		 * Set special textures in the first boss level (22)
		 * 
		 * @param map
		 *            Double array of Tile
		 * @author Michael Ulko
		 */
		private void placeTexturesBoss1(Tile[][] map) {
			/* Set church */
			for (int i = 3; i <= 6; i++) {
				for (int j = 14; j <= 16; j++) {
					if (i == 3 && j == 14) {
						map[i][j] = new SW_St_Church_92();
					} else if (i == 3 && j == 15) {
						map[i][j] = new SW_St_Church_93();
					} else if (i == 3 && j == 16) {
						map[i][j] = new SW_St_Church_94();
					} else if (i == 4 && j == 14) {
						map[i][j] = new SW_St_Church_95();
					} else if (i == 4 && j == 15) {
						map[i][j] = new SW_St_Church_96();
					} else if (i == 4 && j == 16) {
						map[i][j] = new SW_St_Church_97();
					} else if (i == 5 && j == 14) {
						map[i][j] = new SW_St_Church_98();
					} else if (i == 5 && j == 15) {
						map[i][j] = new SW_St_Church_99();
					} else if (i == 5 && j == 16) {
						map[i][j] = new SW_St_Church_100();
					} else if (i == 6 && j == 14) {
						map[i][j] = new SW_St_Church_101();
					} else if (i == 6 && j == 15) {
						map[i][j] = new SW_St_Church_102();
					} else if (i == 6 && j == 16) {
						map[i][j] = new SW_St_Church_103();
					}
				}
			}
			/* Set the first temple */
			for (int i = 3; i <= 5; i++) {
				for (int j = 9; j <= 10; j++) {
					if (i == 3 && j == 9) {
						map[i][j] = new SW_St_Temple_104();
					} else if (i == 3 && j == 10) {
						map[i][j] = new SW_St_Temple_105();
					} else if (i == 4 && j == 9) {
						map[i][j] = new SW_St_Temple_106();
					} else if (i == 4 && j == 10) {
						map[i][j] = new SW_St_Temple_107();
					} else if (i == 5 && j == 9) {
						map[i][j] = new SW_St_Temple_108();
					} else if (i == 5 && j == 10) {
						map[i][j] = new SW_St_Temple_109();
					}
				}
			}
			/* Set the second temple */
			for (int i = 3; i <= 5; i++) {
				for (int j = 20; j <= 21; j++) {
					if (i == 3 && j == 20) {
						map[i][j] = new SW_St_Temple_110();
					} else if (i == 3 && j == 21) {
						map[i][j] = new SW_St_Temple_111();
					} else if (i == 4 && j == 20) {
						map[i][j] = new SW_St_Temple_112();
					} else if (i == 4 && j == 21) {
						map[i][j] = new SW_St_Temple_113();
					} else if (i == 5 && j == 20) {
						map[i][j] = new SW_St_Temple_114();
					} else if (i == 5 && j == 21) {
						map[i][j] = new SW_St_Temple_115();
					}
				}
			}
			/* Place the way */
			for (int i = 8; i <= 8; i++) {
				for (int j = 2; j <= 27; j++) {
					map[i][j] = new SW_St_Road_117();
				}
			}
			for (int i = 9; i <= 15; i++) {
				for (int j = 15; j <= 15; j++) {
					map[i][j] = new SW_St_Road_116();
				}
			}
			map[8][15] = new SW_St_Road_118();
		}

		/**
		 * Place Castle in the second boss level (44)
		 * 
		 * @param map
		 *            Double array of Tile
		 * @author Michael Ulko
		 */
		private void placeCastleBoss(Tile[][] map) {
			for (int i = 2; i <= 7; i++) {
				for (int j = 13; j <= 17; j++) {
					if (i == 2 && j == 13) {
						map[i][j] = new RW_St_Castle_1();
					} else if (i == 2 && j == 14) {
						map[i][j] = new RW_St_Castle_2();
					} else if (i == 2 && j == 15) {
						map[i][j] = new RW_St_Castle_3();
					} else if (i == 2 && j == 16) {
						map[i][j] = new RW_St_Castle_4();
					} else if (i == 2 && j == 17) {
						map[i][j] = new RW_St_Castle_5();
					} else if (i == 3 && j == 13) {
						map[i][j] = new RW_St_Castle_6();
					} else if (i == 3 && j == 14) {
						map[i][j] = new RW_St_Castle_7();
					} else if (i == 3 && j == 15) {
						map[i][j] = new RW_St_Castle_8();
					} else if (i == 3 && j == 16) {
						map[i][j] = new RW_St_Castle_9();
					} else if (i == 3 && j == 17) {
						map[i][j] = new RW_St_Castle_10();
					} else if (i == 4 && j == 13) {
						map[i][j] = new RW_St_Castle_11();
					} else if (i == 4 && j == 14) {
						map[i][j] = new RW_St_Castle_12();
					} else if (i == 4 && j == 15) {
						map[i][j] = new RW_St_Castle_13();
					} else if (i == 4 && j == 16) {
						map[i][j] = new RW_St_Castle_14();
					} else if (i == 4 && j == 17) {
						map[i][j] = new RW_St_Castle_15();
					} else if (i == 5 && j == 13) {
						map[i][j] = new RW_St_Castle_16();
					} else if (i == 5 && j == 14) {
						map[i][j] = new RW_St_Castle_17();
					} else if (i == 5 && j == 15) {
						map[i][j] = new RW_St_Castle_18();
					} else if (i == 5 && j == 16) {
						map[i][j] = new RW_St_Castle_19();
					} else if (i == 5 && j == 17) {
						map[i][j] = new RW_St_Castle_20();
					} else if (i == 6 && j == 13) {
						map[i][j] = new RW_St_Castle_21();
					} else if (i == 6 && j == 14) {
						map[i][j] = new RW_St_Castle_22();
					} else if (i == 6 && j == 15) {
						map[i][j] = new RW_St_Castle_23();
					} else if (i == 6 && j == 16) {
						map[i][j] = new RW_St_Castle_24();
					} else if (i == 6 && j == 17) {
						map[i][j] = new RW_St_Castle_25();
					} else if (i == 7 && j == 13) {
						map[i][j] = new RW_St_Castle_26();
					} else if (i == 7 && j == 14) {
						map[i][j] = new RW_St_Castle_27();
					} else if (i == 7 && j == 15) {
						map[i][j] = new RW_St_Castle_28();
					} else if (i == 7 && j == 16) {
						map[i][j] = new RW_St_Castle_29();
					} else if (i == 7 && j == 17) {
						map[i][j] = new RW_St_Castle_30();
					}
				}
			}
		}

		/**
		 * Place walls, river and bridges around the castle in the second boss level
		 * 
		 * @param map
		 *            Double Array of Tile
		 * @author Michael Ulko
		 */
		private void createCastleWallBoss(Tile[][] map) {
			for (int i = 1; i <= 13; i++) {
				for (int j = 8; j <= 21; j++) {
					if ((i == 1 || i == 9) && (j == 8 || j == 20)) {
						map[i][j] = new RW_St_CastleWall_31();
					} else if ((i == 1 || i == 9) && (j == 9 || j == 21)) {
						map[i][j] = new RW_St_CastleWall_32();
					} else if ((i == 12 || i == 5) && (j == 8 || j == 20)) {
						map[i][j] = new RW_St_CastleWall_33();
					} else if ((i == 12 || i == 5) && (j == 9 || j == 21)) {
						map[i][j] = new RW_St_CastleWall_34();
					} else if ((i == 13 || i == 6) && (j == 8 || j == 20)) {
						map[i][j] = new RW_St_CastleWall_35();
					} else if ((i == 13 || i == 6) && (j == 9 || j == 21)) {
						map[i][j] = new RW_St_CastleWall_36();
					} else if ((i == 2 || i == 3 || i == 4 || i == 10 || i == 11)
							&& (j == 8 || j == 20)) {
						map[i][j] = new RW_St_CastleWall_37();
					} else if ((i == 2 || i == 3 || i == 4 || i == 10 || i == 11)
							&& (j == 9 || j == 21)) {
						map[i][j] = new RW_St_CastleWall_38();
					}
				}
			}
			for (int j = 9; j <= 19; j++) {
				if ((j >= 9 && j <= 13) || (j >= 16 && j <= 19)) {
					map[11][j] = new RW_St_CastleWallFront_39();
					map[12][j] = new RW_St_CastleWallFront_40();
					map[13][j] = new RW_St_CastleWallFront_41();
				}
			}
			/* Set the river */
			for (int i = 1; i <= 14; i++) {
				for (int j = 7; j <= 22; j++) {
					if (i != 14 && j == 7) {
						map[i][j] = new RW_St_River_42();
					} else if (i == 14 && j == 7) {
						map[i][j] = new RW_St_River_43();
					} else if (i == 14 && (j >= 8 && j <= 21)) {
						map[i][j] = new RW_St_River_44();
					} else if (i == 14 && j == 22) {
						map[i][j] = new RW_St_River_45();
					} else if (i != 14 && j == 22) {
						map[i][j] = new RW_St_River_42();
					}
				}
			}
			/* Set the bridge */
			map[7][7] = new RW_St_Bridge_46();
			map[8][7] = new RW_St_Bridge_46();
			map[7][22] = new RW_St_Bridge_46();
			map[8][22] = new RW_St_Bridge_46();
			map[14][14] = new RW_St_Bridge_47();
			map[14][15] = new RW_St_Bridge_47();
		}

		/**
		 * Decide which texture should be used like wall in third boss level (66)
		 * 
		 * @param map
		 *            Double array of Tile
		 * @return Number of chosen texture
		 * @author Michael Ulko
		 */
		private int placeStoneBoss(Tile[][] map) {
			Random zahl = new Random();
			int temp = Math.abs(zahl.nextInt(6));
			return temp;
		}

		/**
		 * Set special textures in third boss level (66)
		 * 
		 * @param map
		 *            Double array of Tile
		 * @author Michael Ulko
		 */
		private void placeTexturesBoss3(Tile[][] map) {
			/* Place floor textures */
			for (int i = 2; i <= 15; i++) {
				for (int j = 3; j <= 26; j++) {
					if (i == 2 && j == 3) {
						map[i][j] = new UW_St_Boden_58();
					} else if (i == 2 && (j >= 4 && j <= 25)) {
						map[i][j] = new UW_St_Boden_55();
					} else if (i == 2 && j == 26) {
						map[i][j] = new UW_St_Boden_59();
					} else if ((i >= 3 && i <= 14) && j == 26) {
						map[i][j] = new UW_St_Boden_56();
					} else if (i == 15 && j == 26) {
						map[i][j] = new UW_St_Boden_60();
					} else if (i == 15 && (j >= 4 && j <= 25)) {
						map[i][j] = new UW_St_Boden_57();
					} else if (i == 15 && j == 3) {
						map[i][j] = new UW_St_Boden_61();
					} else if ((i >= 3 && i <= 14) && j == 3) {
						map[i][j] = new UW_St_Boden_54();
					}
				}
			}
			/* Place lava textures */
			for (int i = 3; i <= 14; i++) {
				for (int j = 4; j <= 25; j++) {
					if (i == 3 && j == 4) {
						map[i][j] = new UW_St_Lava_66();
					} else if (i == 3 && (j >= 5 && j <= 24)) {
						map[i][j] = new UW_St_Lava_63();
					} else if (i == 3 && j == 25) {
						map[i][j] = new UW_St_Lava_67();
					} else if ((i >= 4 && i <= 13) && j == 25) {
						map[i][j] = new UW_St_Lava_64();
					} else if (i == 14 && j == 25) {
						map[i][j] = new UW_St_Lava_68();
					} else if (i == 14 && (j >= 5 && j <= 24)) {
						map[i][j] = new UW_St_Lava_65();
					} else if (i == 14 && j == 4) {
						map[i][j] = new UW_St_Lava_69();
					} else if ((i >= 4 && i <= 13) && j == 4) {
						map[i][j] = new UW_St_Lava_62();
					}
				}
			}
			for (int i = 4; i <= 13; i++) {
				for (int j = 5; j <= 24; j++) {
					map[i][j] = new UW_St_Lava_70();
				}
			}
			for (int i = 5; i <= 12; i++) {
				for (int j = 10; j <= 19; j++) {
					if (i == 5 && j == 10) {
						map[i][j] = new UW_St_Lava_71();
					} else if (i == 5 && (j >= 11 && j <= 18)) {
						map[i][j] = new UW_St_Lava_65();
					} else if (i == 5 && j == 19) {
						map[i][j] = new UW_St_Lava_72();
					} else if ((i >= 6 && i <= 11) && j == 19) {
						map[i][j] = new UW_St_Lava_62();
					} else if (i == 12 && j == 19) {
						map[i][j] = new UW_St_Lava_73();
					} else if (i == 12 && (j >= 11 && j <= 18)) {
						map[i][j] = new UW_St_Lava_63();
					} else if (i == 12 && j == 10) {
						map[i][j] = new UW_St_Lava_74();
					} else if ((i >= 6 && i <= 11) && j == 10) {
						map[i][j] = new UW_St_Lava_64();
					}
				}
			}
			for (int i = 7; i <= 10; i++) {
				for (int j = 12; j <= 17; j++) {
					map[i][j] = new UW_Floor_1();
				}
			}
			/* Place special walls for boss */
			for (int i = 6; i <= 11; i++) {
				for (int j = 11; j <= 18; j++) {
					if (i == 6 && j == 11) {
						map[i][j] = new UW_St_BodenBoss_75();
					} else if (i == 6 && (j >= 12 && j <= 17)) {
						map[i][j] = new UW_St_BodenBoss_76();
					} else if (i == 6 && j == 18) {
						map[i][j] = new UW_St_BodenBoss_77();
					} else if ((i >= 7 && i <= 10) && j == 18) {
						map[i][j] = new UW_St_BodenBoss_78();
					} else if (i == 11 && j == 18) {
						map[i][j] = new UW_St_BodenBoss_79();
					} else if (i == 11 && (j >= 12 && j <= 17)) {
						map[i][j] = new UW_St_BodenBoss_80();
					} else if (i == 11 && j == 11) {
						map[i][j] = new UW_St_BodenBoss_81();
					} else if ((i >= 7 && i <= 10) && j == 11) {
						map[i][j] = new UW_St_BodenBoss_82();
					}
				}
			}
			/* Place bridges to the boss */
			for (int i = 8; i <= 8; i++) {
				for (int j = 3; j <= 26; j++) {
					if (j == 3 || j == 18) {
						map[i][j] = new UW_St_Leiter_83();
					} else if (j == 4 || j == 19) {
						map[i][j] = new UW_St_Leiter_84();
					} else if ((j >= 5 && j <= 9) || (j >= 20 && j <= 24)) {
						map[i][j] = new UW_St_Leiter_85();
					} else if (j == 10 || j == 25) {
						map[i][j] = new UW_St_Leiter_86();
					} else if (j == 11 || j == 26) {
						map[i][j] = new UW_St_Leiter_87();
					}
				}
			}
			/* Set special structure near the boss */
			map[7][14] = new UW_St_Altar_88();
			map[7][15] = new UW_St_Altar_89();
			map[8][14] = new UW_St_Altar_90();
			map[8][15] = new UW_St_Altar_91();
		}

		/**
		 * Produce shop level
		 * 
		 * @param actID
		 *            Current act
		 * @author Ulko, Michael
		 */
		public void generateShopRoom(int actID) {
			/* Size of shop level */
			this.map = new Tile[18][30];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Border_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Border_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Border_1();
					}
				}
			}
			/* Set walls around shop levels in the first and second act */
			for (int i = 1; i < map.length - 1; i++) {
				for (int j = 1; j < map[i].length - 1; j++) {
					if (actID == 1) {
						int objectNr = decideShopObject(10);
						if (objectNr == 0) {
							map[i][j] = new SW_Shop_Wall_0();
						} else if (objectNr == 1) {
							map[i][j] = new SW_Shop_Wall_1();
						} else if (objectNr == 2) {
							map[i][j] = new SW_Shop_Wall_2();
						} else if (objectNr == 3) {
							map[i][j] = new SW_Shop_Wall_3();
						} else if (objectNr == 4) {
							map[i][j] = new SW_Shop_Wall_4();
						} else if (objectNr == 5) {
							map[i][j] = new SW_Shop_Wall_5();
						} else if (objectNr == 6) {
							map[i][j] = new SW_Shop_Wall_6();
						} else if (objectNr == 7) {
							map[i][j] = new SW_Shop_Wall_7();
						} else if (objectNr == 8) {
							map[i][j] = new SW_Shop_Wall_8();
						} else if (objectNr == 9) {
							map[i][j] = new SW_Shop_Wall_9();
						}
					} else if (actID == 2) {
						int objectNr = decideShopObject(10);
						if (objectNr == 0) {
							map[i][j] = new RW_Shop_Wall_0();
						} else if (objectNr == 1) {
							map[i][j] = new RW_Shop_Wall_1();
						} else if (objectNr == 2) {
							map[i][j] = new RW_Shop_Wall_2();
						} else if (objectNr == 3) {
							map[i][j] = new RW_Shop_Wall_3();
						} else if (objectNr == 4) {
							map[i][j] = new RW_Shop_Wall_4();
						} else if (objectNr == 5) {
							map[i][j] = new RW_Shop_Wall_5();
						} else if (objectNr == 6) {
							map[i][j] = new RW_Shop_Wall_6();
						} else if (objectNr == 7) {
							map[i][j] = new RW_Shop_Wall_7();
						} else if (objectNr == 8) {
							map[i][j] = new RW_Shop_Wall_8();
						} else if (objectNr == 9) {
							map[i][j] = new RW_Shop_Wall_9();
						}
					} else if (actID == 3) {
						int kindWall = kindWall(7);
						if (kindWall == 0) {
							map[i][j] = new UW_Nt_1();
						} else if (kindWall == 1) {
							map[i][j] = new UW_Nt_2();
						} else if (kindWall == 2) {
							map[i][j] = new UW_Nt_3();
						} else if (kindWall == 3) {
							map[i][j] = new UW_Nt_4();
						} else if (kindWall == 4) {
							map[i][j] = new UW_Nt_5();
						} else if (kindWall == 5) {
							map[i][j] = new UW_Nt_6();
						} else if (kindWall == 6) {
							map[i][j] = new UW_Nt_7();
						}
					}
				}
			}
			/* Set floor textures in level */
			for (int i = 2; i < map.length - 2; i++) {
				for (int j = 2; j < map[i].length - 2; j++) {
					if (actID == 1) {
						map[i][j] = new SW_Floor_1();
					} else if (actID == 2) {
						map[i][j] = new RW_Floor_1();
					} else if (actID == 3) {
						map[i][j] = new UW_Floor_1();
					}
				}
			}
			/*
			 * Place shelves in shops (1 and 2 act)
			 */
			for (int i = 2; i <= 15; i++) {
				for (int j = 5; j <= 23; j++) {
					if ((i >= 4 && i <= 13) && (j == 10 || j == 18)) {
						if (actID == 1) {
							int objectNr = decideShopObject(9);
							if (objectNr == 0) {
								map[i][j] = new SW_Shop_Object_1();
							} else if (objectNr == 1) {
								map[i][j] = new SW_Shop_Object_2();
							} else if (objectNr == 2) {
								map[i][j] = new SW_Shop_Object_3();
							} else if (objectNr == 3) {
								map[i][j] = new SW_Shop_Object_4();
							} else if (objectNr == 4) {
								map[i][j] = new SW_Shop_Object_5();
							} else if (objectNr == 5) {
								map[i][j] = new SW_Shop_Object_6();
							} else if (objectNr == 6) {
								map[i][j] = new SW_Shop_Object_7();
							} else if (objectNr == 7) {
								map[i][j] = new SW_Shop_Object_8();
							} else if (objectNr == 8) {
								map[i][j] = new SW_Shop_Object_9();
							}
						} else if (actID == 2) {
							int objectNr = decideShopObject(9);
							if (objectNr == 0) {
								map[i][j] = new RW_Shop_Object_1();
							} else if (objectNr == 1) {
								map[i][j] = new RW_Shop_Object_2();
							} else if (objectNr == 2) {
								map[i][j] = new RW_Shop_Object_3();
							} else if (objectNr == 3) {
								map[i][j] = new RW_Shop_Object_4();
							} else if (objectNr == 4) {
								map[i][j] = new RW_Shop_Object_5();
							} else if (objectNr == 5) {
								map[i][j] = new RW_Shop_Object_6();
							} else if (objectNr == 6) {
								map[i][j] = new RW_Shop_Object_7();
							} else if (objectNr == 7) {
								map[i][j] = new RW_Shop_Object_8();
							} else if (objectNr == 8) {
								map[i][j] = new RW_Shop_Object_9();
							}
						}
					} else if ((i == 2 || i == 3 || i == 14 || i == 15)
							&& (j == 8 || j == 20)) {
						if (actID == 1) {
							int objectNr = decideShopObject(9);
							if (objectNr == 0) {
								map[i][j] = new SW_Shop_Object_1();
							} else if (objectNr == 1) {
								map[i][j] = new SW_Shop_Object_2();
							} else if (objectNr == 2) {
								map[i][j] = new SW_Shop_Object_3();
							} else if (objectNr == 3) {
								map[i][j] = new SW_Shop_Object_4();
							} else if (objectNr == 4) {
								map[i][j] = new SW_Shop_Object_5();
							} else if (objectNr == 5) {
								map[i][j] = new SW_Shop_Object_6();
							} else if (objectNr == 6) {
								map[i][j] = new SW_Shop_Object_7();
							} else if (objectNr == 7) {
								map[i][j] = new SW_Shop_Object_8();
							} else if (objectNr == 8) {
								map[i][j] = new SW_Shop_Object_9();
							}
						} else if (actID == 2) {
							int objectNr = decideShopObject(9);
							if (objectNr == 0) {
								map[i][j] = new RW_Shop_Object_1();
							} else if (objectNr == 1) {
								map[i][j] = new RW_Shop_Object_2();
							} else if (objectNr == 2) {
								map[i][j] = new RW_Shop_Object_3();
							} else if (objectNr == 3) {
								map[i][j] = new RW_Shop_Object_4();
							} else if (objectNr == 4) {
								map[i][j] = new RW_Shop_Object_5();
							} else if (objectNr == 5) {
								map[i][j] = new RW_Shop_Object_6();
							} else if (objectNr == 6) {
								map[i][j] = new RW_Shop_Object_7();
							} else if (objectNr == 7) {
								map[i][j] = new RW_Shop_Object_8();
							} else if (objectNr == 8) {
								map[i][j] = new RW_Shop_Object_9();
							}
						}
					} else if ((i == 3 || i == 14)
							&& (j == 8 || j == 9 || j == 19 || j == 20)) {
						if (actID == 1) {
							int objectNr = decideShopObject(9);
							if (objectNr == 0) {
								map[i][j] = new SW_Shop_Object_1();
							} else if (objectNr == 1) {
								map[i][j] = new SW_Shop_Object_2();
							} else if (objectNr == 2) {
								map[i][j] = new SW_Shop_Object_3();
							} else if (objectNr == 3) {
								map[i][j] = new SW_Shop_Object_4();
							} else if (objectNr == 4) {
								map[i][j] = new SW_Shop_Object_5();
							} else if (objectNr == 5) {
								map[i][j] = new SW_Shop_Object_6();
							} else if (objectNr == 6) {
								map[i][j] = new SW_Shop_Object_7();
							} else if (objectNr == 7) {
								map[i][j] = new SW_Shop_Object_8();
							} else if (objectNr == 8) {
								map[i][j] = new SW_Shop_Object_9();
							}
						} else if (actID == 2) {
							int objectNr = decideShopObject(9);
							if (objectNr == 0) {
								map[i][j] = new RW_Shop_Object_1();
							} else if (objectNr == 1) {
								map[i][j] = new RW_Shop_Object_2();
							} else if (objectNr == 2) {
								map[i][j] = new RW_Shop_Object_3();
							} else if (objectNr == 3) {
								map[i][j] = new RW_Shop_Object_4();
							} else if (objectNr == 4) {
								map[i][j] = new RW_Shop_Object_5();
							} else if (objectNr == 5) {
								map[i][j] = new RW_Shop_Object_6();
							} else if (objectNr == 6) {
								map[i][j] = new RW_Shop_Object_7();
							} else if (objectNr == 7) {
								map[i][j] = new RW_Shop_Object_8();
							} else if (objectNr == 8) {
								map[i][j] = new RW_Shop_Object_9();
							}
						}
					}
				}
			}
			/* Place super special textures in all shops of 3 act */
			if (actID == 3) {
				for (int i = 4; i <= 6; i++) {
					for (int j = 10; j <= 12; j++) {
						if (i == 4 && j == 10) {
							map[i][j] = new MarketPlace_UW_200();
						} else if (i == 4 && j == 11) {
							map[i][j] = new MarketPlace_UW_201();
						} else if (i == 4 && j == 12) {
							map[i][j] = new MarketPlace_UW_202();
						} else if (i == 5 && j == 10) {
							map[i][j] = new MarketPlace_UW_203();
						} else if (i == 5 && j == 11) {
							map[i][j] = new MarketPlace_UW_204();
						} else if (i == 5 && j == 12) {
							map[i][j] = new MarketPlace_UW_205();
						} else if (i == 6 && j == 10) {
							map[i][j] = new MarketPlace_UW_206();
						} else if (i == 6 && j == 11) {
							map[i][j] = new MarketPlace_UW_207();
						} else if (i == 6 && j == 12) {
							map[i][j] = new MarketPlace_UW_208();
						}
					}
				}
				for (int i = 4; i <= 5; i++) {
					for (int j = 16; j <= 17; j++) {
						if (i == 4 && j == 16) {
							map[i][j] = new MarketPlace_UW_209();
						} else if (i == 4 && j == 17) {
							map[i][j] = new MarketPlace_UW_210();
						} else if (i == 5 && j == 16) {
							map[i][j] = new MarketPlace_UW_211();
						} else if (i == 5 && j == 17) {
							map[i][j] = new MarketPlace_UW_212();
						}
					}
				}
				for (int i = 6; i <= 9; i++) {
					for (int j = 20; j <= 22; j++) {
						if (i == 6 && j == 20) {
							map[i][j] = new MarketPlace_UW_213();
						} else if (i == 6 && j == 21) {
							map[i][j] = new MarketPlace_UW_214();
						} else if (i == 6 && j == 22) {
							map[i][j] = new MarketPlace_UW_215();
						} else if (i == 7 && j == 20) {
							map[i][j] = new MarketPlace_UW_216();
						} else if (i == 7 && j == 21) {
							map[i][j] = new MarketPlace_UW_217();
						} else if (i == 7 && j == 22) {
							map[i][j] = new MarketPlace_UW_218();
						} else if (i == 8 && j == 20) {
							map[i][j] = new MarketPlace_UW_219();
						} else if (i == 8 && j == 21) {
							map[i][j] = new MarketPlace_UW_220();
						} else if (i == 8 && j == 22) {
							map[i][j] = new MarketPlace_UW_221();
						} else if (i == 9 && j == 20) {
							map[i][j] = new MarketPlace_UW_222();
						} else if (i == 9 && j == 21) {
							map[i][j] = new MarketPlace_UW_223();
						} else if (i == 9 && j == 22) {
							map[i][j] = new MarketPlace_UW_224();
						}
					}
				}
				for (int i = 6; i <= 8; i++) {
					for (int j = 5; j <= 7; j++) {
						if (i == 6 && j == 5) {
							map[i][j] = new MarketPlace_UW_225();
						} else if (i == 6 && j == 6) {
							map[i][j] = new MarketPlace_UW_226();
						} else if (i == 6 && j == 7) {
							map[i][j] = new MarketPlace_UW_227();
						} else if (i == 7 && j == 5) {
							map[i][j] = new MarketPlace_UW_228();
						} else if (i == 7 && j == 6) {
							map[i][j] = new MarketPlace_UW_229();
						} else if (i == 7 && j == 7) {
							map[i][j] = new MarketPlace_UW_230();
						} else if (i == 8 && j == 5) {
							map[i][j] = new MarketPlace_UW_231();
						} else if (i == 8 && j == 6) {
							map[i][j] = new MarketPlace_UW_232();
						} else if (i == 8 && j == 7) {
							map[i][j] = new MarketPlace_UW_233();
						}
					}
				}
				for (int i = 11; i <= 13; i++) {
					for (int j = 10; j <= 12; j++) {
						if (i == 11 && j == 10) {
							map[i][j] = new MarketPlace_UW_234();
						} else if (i == 11 && j == 11) {
							map[i][j] = new MarketPlace_UW_235();
						} else if (i == 11 && j == 12) {
							map[i][j] = new MarketPlace_UW_236();
						} else if (i == 12 && j == 10) {
							map[i][j] = new MarketPlace_UW_237();
						} else if (i == 12 && j == 11) {
							map[i][j] = new MarketPlace_UW_238();
						} else if (i == 12 && j == 12) {
							map[i][j] = new MarketPlace_UW_239();
						} else if (i == 13 && j == 10) {
							map[i][j] = new MarketPlace_UW_240();
						} else if (i == 13 && j == 11) {
							map[i][j] = new MarketPlace_UW_241();
						} else if (i == 13 && j == 12) {
							map[i][j] = new MarketPlace_UW_242();
						}
					}
				}
				
				for (int i = 10; i <= 12; i++) {
					for (int j = 16; j <= 18; j++) {
						if (i == 10 && j == 16) {
							map[i][j] = new MarketPlace_UW_234();
						} else if (i == 10 && j == 17) {
							map[i][j] = new MarketPlace_UW_235();
						} else if (i == 10 && j == 18) {
							map[i][j] = new MarketPlace_UW_236();
						} else if (i == 11 && j == 16) {
							map[i][j] = new MarketPlace_UW_237();
						} else if (i == 11 && j == 17) {
							map[i][j] = new MarketPlace_UW_238();
						} else if (i == 11 && j == 18) {
							map[i][j] = new MarketPlace_UW_239();
						} else if (i == 12 && j == 16) {
							map[i][j] = new MarketPlace_UW_240();
						} else if (i == 12 && j == 17) {
							map[i][j] = new MarketPlace_UW_241();
						} else if (i == 12 && j == 18) {
							map[i][j] = new MarketPlace_UW_242();
						}
					}
				}
			}
		}
	

}
