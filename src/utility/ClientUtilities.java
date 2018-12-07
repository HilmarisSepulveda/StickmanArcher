package utility;

import java.util.Scanner;

import entity.Player;

public class ClientUtilities {
	
	public static void printIntroDrawing() {
		System.out.println("***************************************************************");
		System.out.println("**********              STICKMAN ARCHER              **********");
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("                                                        |\r\n" + 
				"                                                         \\.\r\n" + 
				"                                                         /|.\r\n" + 
				"                                                       /  `|.\r\n" + 
				"                                                     /     |.\r\n" + 
				"                                                   /       |.\r\n" + 
				"                                                 /         `|.\r\n" + 
				"                                               /            |.\r\n" + 
				"                                             /              |.\r\n" + 
				"                                           /                |.\r\n" + 
				"      __                                 /                  `|.\r\n" + 
				"       -\\                              /                     |.\r\n" + 
				"         \\\\                          /                       |.\r\n" + 
				"           \\\\                      /                         |.\r\n" + 
				"            \\|                   /                           |\\\r\n" + 
				"              \\#####\\          /                             ||\r\n" + 
				"          ==###########>     /                               ||\r\n" + 
				"           \\##==      \\    /                                 ||\r\n" + 
				"      ______ =       =|__/___                                ||\r\n" + 
				"  ,--' ,----`-,__ ___/'  --,-`-==============================##==========>\r\n" + 
				" \\               '        ##_______ ______   ______,--,____,=##,__\r\n" + 
				"  `,    __==    ___,-,__,--'#'  ==='      `-'              | ##,-/\r\n" + 
				"    `-,____,---'       \\####\\              |        ____,--\\_##,/\r\n" + 
				"        #_              |##   \\  _____,---==,__,---'         ##\r\n" + 
				"         #              ]===--==\\                            ||\r\n" + 
				"         #,             ]         \\                          ||\r\n" + 
				"          #_            |           \\                        ||\r\n" + 
				"           ##_       __/'             \\                      ||\r\n" + 
				"            ####='     |                \\                    |/\r\n" + 
				"             ###       |                  \\                  |.\r\n" + 
				"             ##       _'                    \\                |.\r\n" + 
				"            ###=======]                       \\              |.\r\n" + 
				"           ///        |                         \\           ,|.\r\n" + 
				"           //         |                           \\         |.\r\n" + 
				"                                                    \\      ,|.\r\n" + 
				"                                                      \\    |.\r\n" + 
				"                                                        \\  |.\r\n" + 
				"                                                          \\|.\r\n" + 
				"                                                          /.\r\n" + 
				" Image from: cyu@athena.mit.edu (Erorppn Xrzavgm)              |");
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("What is your name? ");
	}
	
	public static void showGreeting(String playerName) {
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println(" Hello " +  playerName + "!");
		System.out.println("***************************************************************");


		System.out.println();
		System.out.println(" Press enter to start the adventure.");
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println();
	}
}
