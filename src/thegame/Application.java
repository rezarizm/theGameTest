package thegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		List<Integer> arr = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		int size = validateInput(sc);
		System.out.print("processing...\nlist : ");
		for (int i =0; i<size; i++) {
			int num = getNumber ();
			System.out.print(num+" ");
			arr.add(num);
		}
		System.out.println("");
		int[] temp = new int [2] ;
		int user=0;
		int ai=0;
		int uScore=0;
		int aiScore=0;
		play(arr, temp, user, ai, uScore, aiScore, sc);
		sc.close();
		return;
	
	}
	
	public static int validateInput(Scanner sc) {
		boolean isValid = false;
		int num=0;
		while (!isValid) {
			System.out.println("Pick a number between 10 - 20");
			while (!sc.hasNextInt()) {
		        System.out.println("That's not a number!\nPick a number between 10 - 20");
		        sc.next();
		    }
			num = sc.nextInt();
			if (num<10 || num>20) {
				System.out.println("Out of Range, please input the right value!!");
			}else {
				isValid= true;
			}
		}
		return num;
	}
	public static int validateChoice (Scanner sc, int[] temp) {
		boolean isValid = false;
		int num=0;
		while(!isValid) {
			System.out.println("pick a number from the leftmost or the rightmost");
			while (!sc.hasNextInt()) {
		        System.out.println("That's not a number!\npick a number from the leftmost or the rightmost");
		        sc.next();
		    }
			num = sc.nextInt();
			if(num==temp[0] || num==temp[1]) {
				isValid= true;
			}else {
				 System.out.println("Your choice is unavailable");
			}
		}
		return num;
	}
	
	public static int getNumber () {
		Random rn = new Random();
		int answer = rn.nextInt(10) + 10;
		return answer;
	}
	public void initArray(){
		
	}
	public static int aiChoose (int[] temp) {
		Random rn = new Random();
		int answer = temp[rn.nextInt(2) + 0];
		System.out.println("AI Choose " + answer);
		return answer;
	}
	public static List<Integer> process (List<Integer> arr, int user, int ai, int[] temp){
		if (arr.size()>2) {
			if (ai==user) {
				if (temp[0]==temp[1]) {
					arr.remove(0);
					arr.remove(arr.size()-1);
				}else if (ai == temp [0]) {
					arr.remove(0);
				}else {
					arr.remove(arr.size()-1);
				}	
			}else {
				arr.remove(0);
				arr.remove(arr.size()-1);
			}	
		}else {
			arr.remove(0);
		}
		if (arr.size()>1) {
			System.out.print("list : 	");
			for (int i =0;i<arr.size();i++) {
				System.out.print(arr.get(i)+" ");
			}
			System.out.println("");
		}
		
		return arr;
	}
	public static int[] getTemp (List<Integer> arr, int[] temp) {
		temp [0] = arr.get(0);
		temp [1] = arr.get(arr.size()-1);
		return temp;
	}
	public static int getScore (int score, int choice) {
		return score+choice;
	}
	public static void getWinner (int uScore, int aiScore) {
		if (uScore == aiScore) {
			System.out.println("Draw");
		}else if (uScore > aiScore) {
			System.out.println("Congratulation!!! You win!");
		}else {
			System.out.println("Game Over, You lose");
		}
	}
	public static void play (List<Integer> arr,int[] temp, int user, int ai, int uScore, int aiScore, Scanner sc) {
		while (arr.size()>1) {
			getTemp(arr, temp);
			user = validateChoice(sc, temp);
			ai = aiChoose(temp);
			uScore = getScore(uScore, user);
			aiScore = getScore(aiScore, ai);
			System.out.println("sum user : "+ uScore );
			System.out.println("sum ai : " + aiScore);
			process(arr, user, ai, temp);
			getTemp(arr, temp);	
		}
		getWinner(uScore, aiScore);
		
	}
	
	

}
