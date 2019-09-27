

import java.util.Scanner; 


public class CoinStrip {

	//Method to choose and coin and check if a valid coin was selected
	//returns 0 if invalid. returns the number of the coin being moved if valid. 
	public static int validCoinMove(int coinNumber, int arr[],String playername) {
		Scanner s = new Scanner(System.in);
		System.out.println(playername+", what coin will you move?");
		int coinMove = s.nextInt();
		if (coinMove>coinNumber || coinMove<=0) {
			System.out.println("Illegal Move: Invalid Coin");
			return 0;
		}
		for(int	i = 0;i<arr.length;i++){
			if (arr[i]==coinMove) {
				if(arr[i-1]>0) {
					System.out.println("Illegal Move: You can't move this coin!");
					return 0;
				}
			}
		}
		return coinMove;
	}
	//Method to move coin and check if it's a valid movement
	//returns -1 is invalid. returns the number of array placement of the moving coin otherwise. 
	public static int validMove(int chosenCoin,int arr[],String playername) {
		int coinSpot=-1;
		Scanner s = new Scanner(System.in);
		System.out.println(playername+", how many spaces will you move?");
		int playerMove = s.nextInt();

		if(playerMove<0) {
			System.out.println("Illegal Move: Invalid movement");
			return -1;
		}

		for(int	i = 0;i<arr.length;i++){
			if (arr[i]==chosenCoin) {
				coinSpot=i;
				System.out.println(arr[i]);
				if(i-playerMove<0) {
					System.out.println("Illegal Move: Off the Strip");
					return -1; 
				}
				if(arr[i-playerMove]>0) {
					System.out.println("Illegal Move: Coin already present");
					return -1; 
				}
				for(int x=i-playerMove;x<i;x++) {
					if(arr[x]>0) {
						System.out.println("Illegal Move: You can't pass a coin");
						return -1; 
					}
				}
				//moving the coin
				arr[coinSpot-playerMove]=arr[coinSpot];
				arr[coinSpot]=0;
				printStrip(arr);
				return coinSpot; 
			}
		}
		return -1;

	}


	//Method to print out the current game strip
	public static void printStrip(int arr[]) {

		for(int	i = 0;i<arr.length-1;i++){
			System.out.print("+---+");
		}
		System.out.println("+---+");


		for(int	i = 0;i<arr.length-1;i++){
			System.out.print("| "+arr[i]+" |");
		}
		System.out.println("| "+arr[arr.length-1]+" |");


		for(int	i = 0;i<arr.length-1;i++){
			System.out.print("+---+");
		}
		System.out.println("+---+");


	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int strip[];
		int stripLength=0;
		//Creating the game strip based on user input 
		boolean validStripLength=false;
		while(!validStripLength) {
			validStripLength=true;
			System.out.println("What is the Strip Length?:"); //how long is the strip?
			stripLength = s.nextInt();
			if(stripLength<=1) { //check if the length is valid
				System.out.println("Not a valid strip length");
				validStripLength=false;
			}

		}
		//create strip
		strip = new int [stripLength];


		for(int i=0;i<strip.length;i++) {//fill the strip with 0s these are blank spaces
			strip[i]=0;
		}

		//inserting coins based on user input
		System.out.println("How many coins?:");
		int coinNumber = s.nextInt();
		//check a valid number of coins were inserted
		while(coinNumber<=0 || coinNumber>=strip.length) {
			System.out.println("Invalid");
			System.out.println("How many coins?:");
			coinNumber = s.nextInt();
		}
		//Insert the coins randomly 
		int c=1;
		while(c<=coinNumber-1) {
			int random = (int)(Math.random() * strip.length-1);
			if(strip[random]==0 && random>0) {
				strip[random]=c;
				c++;
			}

		}
		//make sure the last box of the strip always has a coin 
		strip[strip.length-1]=coinNumber;

		printStrip(strip);


		//the game controls

		boolean gameOver=false;
		//checking the sum of all coin numbers
		int coinSum=0;
		for(int f=1;f<=coinNumber;f++) {
			coinSum+=f;
		}



		//Loop that initiates the game
		String player1="Player1";
		String player2="Player2";
		while(!gameOver) {
			int sum = 0;

			for(int t=0;t<coinNumber;t++) {
				sum+=strip[t];
			}
			//check if the coins are all in the leftmost boxes
			if(coinSum==sum) {
				gameOver=true;
				System.out.println("Game Over: Player 2 Wins!");
				break;
			}

			//Choosing a coin
			int valid1=0;

			while(valid1==0){
				valid1 = validCoinMove(coinNumber,strip,player1);
			}


			//check move is valid and move the coin
			int valid2 = -1;
			while (valid2==-1) {
				valid2=validMove(valid1,strip,player1);
			}


			//check if the game is over
			sum = 0;
			for(int t=0;t<coinNumber;t++) {
				sum+=strip[t];
			}

			if(coinSum==sum) {
				gameOver=true;
				System.out.println("Game Over: Player 1 Wins!");
				break;
			}
			//player2's turn
			//choosing coin
			int valid3 = 0;
			while(valid3 ==0){
				valid3 =validCoinMove(coinNumber,strip,player2);
			}

			//checking if move is valid and move the coin
			int valid4 = -1;
			while (valid4==-1) {
				valid4=validMove(valid3,strip,player2);

			}


		}
	}
}

















