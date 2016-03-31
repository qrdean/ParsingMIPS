import java.io.*;
import java.util.Scanner;
/*
	This program opens a file, reads a 32-bit value,
	parses through the MIPS instruction and prints out the numeric
	value of each instruction. 
*/


public class ParsingMIPS
{
	public static void main(String[] args)
						throws IOException
	{
		//initialize "number" to input from file
		//initialize "eof" for End-of-File
		
		String binNumber, userFile;
		int bitxorOP = 0x03FFFFFF; //0000 0011 1111 1111 1111 1111 1111 1111
		int bitxorRS = 0xFC1FFFFF; //1111 1100 0001 1111 1111 1111 1111 1111
		int bitxorRT = 0xFFE0FFFF; //1111 1111 1110 0000 1111 1111 1111 1111
		int bitxorRD = 0xFFFF07FF; //1111 1111 1111 1111 0000 0111 1111 1111
		int bitxorSHAMT = 0xFFFFF83F; //1111 1111 1111 1111 1111 1000 0011 1111
		int bitxorFUNCT = 0xFFFFFFC0; //1111 1111 1111 1111 1111 1111 1100 0000
		int bitxorIMM = 0xFFFF0000; //1111 1111 1111 1111 0000 0000 0000 0000
		int bitxorADDR = 0xFFC00000; //1111 1100 0000 0000 0000 0000 0000 0000
		int bitandsix = 0x0000003F; //0000 0000 0000 0000 0000 0000 0011 1111 
		int bitandfive = 0x0000001F; //0000 0000 0000 0000 0000 0000 0001 1111
		int bitandsixteen = 0x0000FFFF; //0000 0000 0000 0000 1111 1111 1111 1111
		int bitandtwentysix = 0x003FFFFFF; //0000 0011 1111 1111 1111 1111 1111 1111
		int number, op, rs, rt, rd, shamt, funct, imm, addr, temp;
		//Scanner userFile = new Scanner(System.in);
		boolean eof = false;

		//reading file from keyboard
		Scanner sc = new Scanner(System.in);
		System.out.print("Input File name: ");
		userFile = sc.nextLine();
		System.out.print("\n");
		
		//Create binary file input objects
		FileInputStream fstream = new FileInputStream(userFile);
		DataInputStream inputFile = new DataInputStream(fstream);

		System.out.println("Reading numbers from specified file");

		//Read contents of the file
		while(!eof)
		{
			try
			{	
				number = inputFile.readInt();
				
				temp = number ^ bitxorOP;
				op = temp>>>26;
				
				
				temp = number ^ bitxorRS;
				temp = temp>>>21;
				rs = temp & bitandfive;
				
				
				temp = number ^ bitxorRT;
				temp = temp>>>16;
				rt = temp & bitandfive;
				
				temp = number ^ bitxorRD;
				temp = temp>>>11;
				rd = temp & bitandfive;
				
				temp = number ^ bitxorSHAMT;
				temp = temp>>>6;
				shamt = temp & bitandfive;
				
				temp = number ^ bitxorFUNCT;
				funct = temp & bitandsix;
				
				//addr = number ^ bitxorADDR;
				addr = number & bitandtwentysix;
				
				//imm = number ^ bitxorIMM;
				imm = number & bitandsixteen;
				
				System.out.printf("\nHexNumber %08x", number);
				System.out.printf("\nOP= \t %02x", op);
				System.out.printf("\nRS= \t %02x", rs);
				System.out.printf("\nRT= \t %02x", rt);
				System.out.printf("\nRD= \t %02x", rd);
				System.out.printf("\nSHAMT= \t %02x", shamt);
				System.out.printf("\nFUNCT= \t %02x", funct);
				System.out.printf("\nAddress=  %07x", addr);
				System.out.printf("\nImmediate= %04x\n", imm);
				
			}
			catch(EOFException e)
			{
				eof = true;
			}
		}
		System.out.println("\nDone.");

		//Close file
		inputFile.close();
	}
}
